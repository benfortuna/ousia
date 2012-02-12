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
package org.mnode.ousia.flamingo;

import java.util.ArrayList;
import java.util.List;

import org.pushingpixels.flamingo.api.bcb.BreadcrumbBarCallBack;
import org.pushingpixels.flamingo.api.bcb.BreadcrumbBarException;
import org.pushingpixels.flamingo.api.bcb.BreadcrumbItem;
import org.pushingpixels.flamingo.api.common.StringValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BreadcrumbContextCallback extends BreadcrumbBarCallBack<BreadcrumbContext> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BreadcrumbContextCallback.class);
	
	private BreadcrumbContext rootContext;
	
	public BreadcrumbContext getRootContext() {
		return rootContext;
	}
	
	public void setRootContext(BreadcrumbContext rootContext) {
		this.rootContext = rootContext;
	}
	
	@Override
	public List<StringValuePair<BreadcrumbContext>> getPathChoices(List<BreadcrumbItem<BreadcrumbContext>> path) throws BreadcrumbBarException {
		final List<StringValuePair<BreadcrumbContext>> pathChoices = new ArrayList<StringValuePair<BreadcrumbContext>>();
		try {
			if (path == null) {
				for (BreadcrumbContext child : rootContext.getChildren()) {
//					pathChoices.add(new StringValuePair<BreadcrumbContext>(result.getName(), result));
					final StringValuePair<BreadcrumbContext> pathChoice = new StringValuePair<BreadcrumbContext>(child.getName(), child);
					pathChoice.set("icon", child.getIcon());
					pathChoices.add(pathChoice);
				}
			}
			else if (path.isEmpty()) {
				return null;
			}
			else {
				final BreadcrumbContext lastPathResult = path.get(path.size() - 1).getData();
				if (!lastPathResult.isLeaf()) {
					for (BreadcrumbContext child : lastPathResult.getChildren()) {
						final StringValuePair<BreadcrumbContext> pathChoice = new StringValuePair<BreadcrumbContext>(child.getName(), child);
						pathChoice.set("icon", child.getIcon());
						pathChoices.add(pathChoice);
					}
				}
			}
		}
		catch (BreadcrumbContextException ce) {
			if (throwsExceptions) {
				throw new BreadcrumbBarException(ce);
			}
			else {
				LOGGER.error("Exception in BreadcrumbContext", ce);
			}
		}
		
		return pathChoices;
	}
}
