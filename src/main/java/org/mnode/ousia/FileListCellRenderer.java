/**
 * This file is part of Base Modules.
 *
 * Copyright (c) 2010, Ben Fortuna [fortuna@micronode.com]
 *
 * Base Modules is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Base Modules is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Base Modules.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mnode.ousia;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

/**
 * @author Ben
 *
 */
public class FileListCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;
    
    private final FileSystemView fsv;
    
    private final boolean showPath;
    
    public FileListCellRenderer() {
        this(FileSystemView.getFileSystemView(), false);
    }
    
    public FileListCellRenderer(boolean showPath) {
        this(FileSystemView.getFileSystemView(), showPath);
    }
    
    public FileListCellRenderer(FileSystemView fsv, boolean showPath) {
        this.showPath = showPath;
        this.fsv = fsv;
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        File file = (File) value;
        if (file.exists()) {
            setIcon(fsv.getSystemIcon(file));
            if (showPath) {
                setText(file.getAbsolutePath());
            }
            else {
                setText(fsv.getSystemDisplayName(file));
            }
        }
        else {
            setIcon(null);
        }
        return this;
    }
}
