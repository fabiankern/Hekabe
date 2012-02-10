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
import com.hekabe.dashboard.client.dialog.LoginDialog;
import com.hekabe.dashboard.client.view.HeaderView;
import com.hekabe.dashboard.client.view.MgmtView;
import com.hekabe.dashboard.client.view.NewClusterView;
import com.hekabe.dashboard.shared.parameter.StringParameter;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickEvent;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;

public class Dashboard implements EntryPoint {
	
	private SectionStack sectionStack;
	private SectionStackSection managementSection;
	private SectionStackSection newClusterSection;
	private MgmtView mgmtView;
	private LoginDialog loginDialog;
	private NewClusterView newClusterView;
	private HeaderView headerView;
	private Label lblUsername;
	
	/**
	 * Entry-point
	 */
	public void onModuleLoad() {
		CommunicationServiceAsync rpcService = GWT.create(CommunicationService.class);
		
		RootPanel rootPanel = RootPanel.get("content");
		rootPanel.setSize("1000", "630");
		
		mgmtView = new MgmtView(this, rpcService);
		newClusterView = new NewClusterView(this, rpcService);
		
		headerView = new HeaderView(this);
		
		sectionStack = new SectionStack();
		sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
		sectionStack.setSize("1000", "622");
		
		managementSection = new SectionStackSection(StringParameter.MANAGEMENT);
		newClusterSection = new SectionStackSection(StringParameter.NEW_CLUSTER);
		
		managementSection.addItem(mgmtView);
		newClusterSection.addItem(newClusterView);
		
		sectionStack.addSection(managementSection);
		sectionStack.addSection(newClusterSection);		
		
		sectionStack.expandSection(1);
		
		lblUsername = new Label(StringParameter.LOGGED_IN_AS);
		lblUsername.setPixelSize(1000, 16);
		lblUsername.setAlign(Alignment.RIGHT);
		
		rootPanel.add(headerView);
		rootPanel.add(sectionStack);
		
		loginDialog = new LoginDialog(this, rpcService);
		loginDialog.show();
		
		bind();		
	}
	
	/**
	 * binds handlers
	 */
	private void bind() {
		sectionStack.addSectionHeaderClickHandler(new SectionHeaderClickHandler() {
			
			public void onSectionHeaderClick(SectionHeaderClickEvent event) {
				if(event.getSection() == managementSection) {
					getMgmtView().getClusterView().getClusterData(false);
				}
				
			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public MgmtView getMgmtView() {
		return mgmtView;
	}
	
	/**
	 * 
	 * @return
	 */
	public SectionStack getSectionStack() {
		return sectionStack;
	}

	/**
	 * 
	 * @return
	 */
	public NewClusterView getNewClusterView() {
		return newClusterView;
	}
	
	/**
	 * 
	 * @return
	 */
	public LoginDialog getLoginDialog() {
		return loginDialog;
	}
	
	/**
	 * 
	 * @return
	 */
	public HeaderView getHeaderView() {
		return headerView;
	}
	
	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		StringBuilder sb = new StringBuilder(StringParameter.LOGGED_IN_AS);
		sb.append(username);
		lblUsername.setContents(sb.toString());
	}
}