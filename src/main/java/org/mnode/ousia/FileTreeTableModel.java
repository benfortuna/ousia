/**
 * Copyright (c) 2012, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mnode.ousia;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.swing.filechooser.FileSystemView;

import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.mnode.ousia.util.FileComparator;

import com.ocpsoft.pretty.time.PrettyTime;

public class FileTreeTableModel extends AbstractTreeTableModel {

	private static final String[] COLUMNS = new String[] {"Name", "Size", "Type", "Modified"};
	
	private Comparator<File> comparator = new FileComparator();
	private PrettyTime lastModifiedFormat = new PrettyTime();
	    
    public FileTreeTableModel() {
        super(FileSystemView.getFileSystemView());
    }
	    
    public int getRowCount() {
        return getFileSystemView().getRoots().length;
    }
	    
    public int getColumnCount() {
        return COLUMNS.length;
    }
	    
    public Class<?> getColumnClass(int column) {
        if (column == 1) {
            return Long.class;
        }
        return String.class;
    }
	    
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
	    
    public Object getValueAt(Object node, int column) {
        if (node instanceof File) {
        	final File file = (File) node;
	        switch(column) {
	            case 0: return getFileSystemView().getSystemDisplayName(file);
	            case 1: return !file.isDirectory() ? file.length() : null;
	            case 2: return getFileSystemView().getSystemTypeDescription(file);
	            case 3: return lastModifiedFormat.format(new Date(file.lastModified()));
	        }
        }
        return null;
    }
	    
    public Object getChild(Object parent, int index) {
        if (parent instanceof FileSystemView) {
            final File[] roots = ((FileSystemView) parent).getRoots();
            Arrays.sort(roots, comparator);
            return roots[index];
        }
        else {
            final File[] files = ((File) parent).listFiles();
            Arrays.sort(files, comparator);
            return files[index];
        }
    }
	    
    public int getChildCount(Object parent) {
        if (parent instanceof FileSystemView) {
            return ((FileSystemView) parent).getRoots().length;
        }
        else {
            return ((File) parent).listFiles().length;
        }
    }
	    
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof FileSystemView) {
            final File[] roots = ((FileSystemView) parent).getRoots();
            Arrays.sort(roots, comparator);
            for (int i = 0; i < roots.length; i++) {
                if (child == roots[i]) {
                    return i;
                }
            }
        }
        else {
            final File[] files = ((File) parent).listFiles();
            Arrays.sort(files, comparator);
            for (int i = 0; i < files.length; i++) {
                if (child == files[i]) {
                    return i;
                }
            }
        }
        return -1;
    }
	    
    public boolean isLeaf(Object node) {
        return node instanceof File && !((File) node).isDirectory();
    }

    private FileSystemView getFileSystemView() {
    	return (FileSystemView) getRoot();
    }
}
