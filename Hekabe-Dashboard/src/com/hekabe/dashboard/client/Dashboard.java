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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;

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
	private VLayout LayoutCreateCluster;
	private Label lblHardware;
	private DynamicForm dynamicForm;
	private Label lblNewLabel;
	private DynamicForm dynamicForm_1;
	private TextItem txtClusterName;
	private ComboBoxItem cbPartitioner;
	private IntegerItem intReplicationFactor;
	private ComboBoxItem cbInstanceSize;
	private IntegerItem intNumberOfInstances;
	private ComboBoxItem cbProvider;
	private VLayout LayoutCassManagement;
	private Label lblHardware_1;
	private ListGrid runningNodesListGrid;
	private TreeGridField fieldName;
	private TreeGridField fieldSize;
	private TreeGridField fieldIp;
	private TreeGridField fieldStopButton;
	private TreeGridField fieldKillButton;
	private Label lblAddNodes;
	private DynamicForm dynamicForm_2;
	private ComboBoxItem cbNodeSize;
	private ComboBoxItem cbRegion;
	private ComboBoxItem cbProviderNodes;
	private VLayout LayoutCassYamlTuning;
	private Label lblNewLabel_1;
	private DynamicForm dynamicForm_3;
	private RadioGroupItem rgHintedHandoff;

	private IntegerItem intMaxWindowTime;

	private IntegerItem intThrottleDelay;
	private Label lblNewLabel_2;
	private DynamicForm dynamicForm_4;
	private ComboBoxItem cbSyncType;
	
	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("900", "800");
		
		lblHekabeDashboard = new Label("Hekabe Dashboard");
		lblHekabeDashboard.setSize("150", "30");
		lblHekabeDashboard.setAlign(Alignment.CENTER);
		rootPanel.add(lblHekabeDashboard);
		
		tabSet = new TabSet();
		tabSet.setSize("800", "600");
		
		createClusterTab = new Tab("Create Cluster");
		
		LayoutCreateCluster = new VLayout();
		
		lblHardware = new Label("Hardware");
		lblHardware.setHeight("30");
		LayoutCreateCluster.addMember(lblHardware);
		
		dynamicForm = new DynamicForm();
		cbProvider = new ComboBoxItem("Provider", "Provider");
		cbProvider.setShowTitle(true);
		cbProvider.setTooltip("Choose Cloud-Provider.");
		cbProvider.setValueMap("Amazon EC2","1&1 Cloud");
		cbInstanceSize = new ComboBoxItem("InstanceSize", "Instance size");
		cbInstanceSize.setShowTitle(true);
		cbInstanceSize.setTooltip("Choose size of the instance(s).");
		cbInstanceSize.setValueMap("Large (m1.large)",
								   "Extra Large (m1.xlarge)",
								   "High-Memory Extra Large (m2.xlarge)",
								   "High-Memory Double Extra Large (m2.2xlarge)",
								   "High-Memory Quadruple Extra Large (m2.4xlarge)",
								   "High-CPU Extra Large (c1.xlarge)");
		intNumberOfInstances = new IntegerItem();
		intNumberOfInstances.setTooltip("The number of instances you want to start.");
		intNumberOfInstances.setTitle("Number of instances");
		dynamicForm.setFields(new FormItem[] { cbProvider, intNumberOfInstances, cbInstanceSize});
		LayoutCreateCluster.addMember(dynamicForm);
		
		lblNewLabel = new Label("Cassandra Configuration");
		lblNewLabel.setHeight("30");
		LayoutCreateCluster.addMember(lblNewLabel);
		
		dynamicForm_1 = new DynamicForm();
		txtClusterName = new TextItem("newTextItem_1", "Cluster Name");
		intReplicationFactor = new IntegerItem();
		intReplicationFactor.setTitle("Replication Factor");
		cbPartitioner = new ComboBoxItem("newComboBoxItem_3", "Partitioner");
		cbPartitioner.setValueMap("Random Partitioner","Byte Ordered Partitioner");
		
		dynamicForm_1.setFields(new FormItem[] { txtClusterName, intReplicationFactor, cbPartitioner });
		LayoutCreateCluster.addMember(dynamicForm_1);
		
		createClusterTab.setPane(LayoutCreateCluster);
		tabSet.addTab(createClusterTab);
		
		cassManagementTab = new Tab("Cassandra Management");
		
		LayoutCassManagement = new VLayout();
		LayoutCassManagement.setWidth("750");
		
		lblHardware_1 = new Label("Hardware");
		lblHardware_1.setHeight("30");
		LayoutCassManagement.addMember(lblHardware_1);
		
		runningNodesListGrid = new ListGrid();
		runningNodesListGrid.setHeight("150");
		runningNodesListGrid.setEmptyMessage("No nodes running.");
		fieldName = new TreeGridField("fieldName", "Name");
		fieldSize = new TreeGridField("fieldSize", "Size");
		fieldIp = new TreeGridField("fieldIp", "IP");
		fieldStopButton = new TreeGridField("fieldStopButton", "Stop", 20);
		fieldStopButton.setType(ListGridFieldType.ICON);
		fieldKillButton = new TreeGridField("fieldKillButton", "Kill", 20);
		fieldKillButton.setType(ListGridFieldType.ICON);
		runningNodesListGrid.setFields(new ListGridField[] { fieldName, fieldSize, fieldIp });
		LayoutCassManagement.addMember(runningNodesListGrid);
		
		lblAddNodes = new Label("Add Nodes");
		lblAddNodes.setHeight("30");
		LayoutCassManagement.addMember(lblAddNodes);
		
		dynamicForm_2 = new DynamicForm();
		IntegerItem intNumberOfNodes = new IntegerItem();
		intNumberOfNodes.setTitle("Number of Nodes");
		cbNodeSize = new ComboBoxItem("nodeSize", "Node size");
		cbNodeSize.setValueMap("Large (m1.large)",
				   "Extra Large (m1.xlarge)",
				   "High-Memory Extra Large (m2.xlarge)",
				   "High-Memory Double Extra Large (m2.2xlarge)",
				   "High-Memory Quadruple Extra Large (m2.4xlarge)",
				   "High-CPU Extra Large (c1.xlarge)");
		cbRegion = new ComboBoxItem("Region", "Region");
		cbRegion.setValueMap("US East (Virginia)",
							 "US West (Oregon)",
							 "US West (California)",
							 "EU West (Ireland)",
							 "Asia Pacific (Singapore)",
							 "Asia Pacific (Tokyo)");
		cbProviderNodes = new ComboBoxItem("newComboBoxItem_4", "Provider");
		cbProviderNodes.setValueMap("Amazon EC2","1&1 Cloud");
		dynamicForm_2.setFields(new FormItem[] { cbProviderNodes, intNumberOfNodes, cbNodeSize, cbRegion});
		LayoutCassManagement.addMember(dynamicForm_2);
		cassManagementTab.setPane(LayoutCassManagement);
		tabSet.addTab(cassManagementTab);
		
		cassYamlTuningTab = new Tab("Cassandra YAML Tuning");
		
		LayoutCassYamlTuning = new VLayout();
		
		lblNewLabel_1 = new Label("Hinted Handoff");
		lblNewLabel_1.setHeight("30");
		LayoutCassYamlTuning.addMember(lblNewLabel_1);
		
		dynamicForm_3 = new DynamicForm();
		rgHintedHandoff = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoff.setValueMap("Enabled","Disabled");
		intMaxWindowTime = new IntegerItem("maxWindowTime", "Maximum Window Time (ms)");
		intThrottleDelay = new IntegerItem("throttleDelay", "Throttle Delay (ms)");
		dynamicForm_3.setFields(new FormItem[] { rgHintedHandoff, intMaxWindowTime, intThrottleDelay});
		LayoutCassYamlTuning.addMember(dynamicForm_3);
		
		lblNewLabel_2 = new Label("Commit Log");
		lblNewLabel_2.setHeight(30);
		LayoutCassYamlTuning.addMember(lblNewLabel_2);
		
		dynamicForm_4 = new DynamicForm();
		cbSyncType = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		dynamicForm_4.setFields(new FormItem[] { cbSyncType});
		LayoutCassYamlTuning.addMember(dynamicForm_4);
		cassYamlTuningTab.setPane(LayoutCassYamlTuning);
		tabSet.addTab(cassYamlTuningTab);
		rootPanel.add(tabSet);
	}
}