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

import java.util.LinkedHashMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
/**
 * Combobox Version
 * Textbox Revision
 * Cassandra YAML mit sinnvollen std. Werten füllen
 */
public class Dashboard implements EntryPoint {
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	
	private final CommunicationServiceAsync communicationService = GWT
			.create(CommunicationService.class);
	private TabSet tabSet;
	private Tab createClusterTab;
	private Tab cassManagementTab;
	private Tab cassYamlTuningTab;
	private Label lblHekabeDashboard;
	private VLayout layout;
	private Label lblHardware;
	private DynamicForm dynamicForm;
	private Label lblNewLabel;
	
	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("800", "600");
		
		lblHekabeDashboard = new Label("Hekabe Dashboard");
		lblHekabeDashboard.setSize("150", "100");
		lblHekabeDashboard.setAlign(Alignment.CENTER);
		rootPanel.add(lblHekabeDashboard);
		
		tabSet = new TabSet();
		tabSet.setSize("800", "600");
		
		createClusterTab = new Tab("Create Cluster");
		
		layout = new VLayout();
		
		lblHardware = new Label("Hardware");
		layout.addMember(lblHardware);
		
		dynamicForm = new DynamicForm();
		ComboBoxItem cbProvider = new ComboBoxItem("Provider", "Provider");
		cbProvider.setShowTitle(true);
		cbProvider.setTooltip("Choose Cloud-Provider.");
		cbProvider.setValueMap("Amazon EC2","1&1 Cloud");
		ComboBoxItem cbInstanceSize = new ComboBoxItem("InstanceSize", "Instance size");
		cbInstanceSize.setShowTitle(true);
		cbInstanceSize.setTooltip("Choose size of the instance(s).");
		cbInstanceSize.setValueMap("Large (m1.large)",
								   "Extra Large (m1.xlarge)",
								   "High-Memory Extra Large (m2.xlarge)",
								   "High-Memory Double Extra Large (m2.2xlarge)",
								   "High-Memory Quadruple Extra Large (m2.4xlarge)",
								   "High-CPU Extra Large (c1.xlarge)");
		IntegerItem intNumberOfInstances = new IntegerItem();
		intNumberOfInstances.setTooltip("The number of instances you want to start.");
		intNumberOfInstances.setTitle("Number of instances");
		dynamicForm.setFields(new FormItem[] { cbProvider, intNumberOfInstances, cbInstanceSize});
		layout.addMember(dynamicForm);
		
		lblNewLabel = new Label("New Label");
		layout.addMember(lblNewLabel);
		
		createClusterTab.setPane(layout);
		tabSet.addTab(createClusterTab);
		
		cassManagementTab = new Tab("Cassandra Management");
		tabSet.addTab(cassManagementTab);
		
		cassYamlTuningTab = new Tab("Cassandra YAML Tuning");
		tabSet.addTab(cassYamlTuningTab);
		rootPanel.add(tabSet);
	}
}