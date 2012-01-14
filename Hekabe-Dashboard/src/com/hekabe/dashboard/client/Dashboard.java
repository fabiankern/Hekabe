/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hekabe.dashboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.hekabe.dashboard.client.view.MgmtView;
import com.hekabe.dashboard.client.view.NewClusterView;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class Dashboard implements EntryPoint {
	
	private SectionStack sectionStack;
	private SectionStackSection managementSection;
	private SectionStackSection newClusterSection;
	private MgmtView mgmtView;
	private NewClusterView newClusterView;
	
	public void onModuleLoad() {
		CommunicationServiceAsync rpcService = GWT.create(CommunicationService.class);
		
		RootPanel rootPanel = RootPanel.get("content");
		rootPanel.setSize("1000", "700");
		
		mgmtView = new MgmtView(this, rpcService);
		newClusterView = new NewClusterView(this, rpcService);
		
		sectionStack = new SectionStack();
		sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
		sectionStack.setSize("1000", "700");
		
		managementSection = new SectionStackSection("Management");
		newClusterSection = new SectionStackSection("New Cluster");
		
		managementSection.addItem(mgmtView);
		newClusterSection.addItem(newClusterView);
		
		sectionStack.addSection(managementSection);
		sectionStack.addSection(newClusterSection);		
		
		sectionStack.expandSection(1);
		
		rootPanel.add(sectionStack);
	}

	public MgmtView getMgmtView() {
		return mgmtView;
	}

	public NewClusterView getNewClusterView() {
		return newClusterView;
	}
}