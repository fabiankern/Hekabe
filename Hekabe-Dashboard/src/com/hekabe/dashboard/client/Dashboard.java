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
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.TreeGridField;

/**
 * Cassandra YAML mit sinnvollen std. Werten füllen
 */
public class Dashboard implements EntryPoint {
	private DashboardFunctions df;
	
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
	private TabSet newClusterTabSet;
	private Tab hardwareTab;
	private Tab cassandraTab;
	private Tab cassandraConfigTab;
	private Label lblHekabeDashboard;
	private VLayout LayoutCreateCluster;
	private Label lblProvider;
	private DynamicForm dynamicForm;
	private Label lblNewLabel;
	private DynamicForm dynamicForm_1;
	private TextItem txtClusterName;
	private ComboBoxItem cbPartitioner;
	private IntegerItem intReplicationFactor;
	private ComboBoxItem cbInstanceSize;
	private IntegerItem intNumberOfInstances;
	private ComboBoxItem cbProvider;
	private VLayout layoutCassandra;
	private Label lblHardware_1;
	private ListGrid runningClusterListGrid;
	private TreeGridField fieldName;
	private TreeGridField fieldProvider;
	private TreeGridField fieldNumberOfNodes;
	private TreeGridField fieldStopButton;
	private TreeGridField fieldKillButton;
	private Label lblAddNodes;
	private DynamicForm dynamicForm_2;
	private ComboBoxItem cbNodeSize;
	private ComboBoxItem cbRegion;
	private ComboBoxItem cbProviderNodes;
	private VLayout layoutNodeConfig;
	private Label lblNewLabel_1;
	private DynamicForm dynamicForm_3;
	private RadioGroupItem rgHintedHandoff;

	private IntegerItem intMaxWindowTime;

	private IntegerItem intThrottleDelay;
	private Label lblNewLabel_2;
	private DynamicForm dynamicForm_4;
	private ComboBoxItem cbSyncType;

	private Label lblNewLabel_3;

	private DynamicForm dynamicForm_5;

	private FloatItem floatReduceCacheCapacity;

	private FloatItem floatReduceCacheAt;

	private Label lblNewLabel_4;

	private DynamicForm dynamicForm_6;

	private IntegerItem intConcurrentReads;

	private IntegerItem intConcurrentWrites;

	private Label lblNewLabel_5;

	private IntegerItem intMemtableTotalSpace;

	private DynamicForm dynamicForm_7;

	private IntegerItem intMemtableWriterThreads;

	private IntegerItem intTimeWindows;

	private IntegerItem intCommitlogTotalSpace;

	private SectionStack sectionStack;

	private SectionStackSection managementSection;

	private SectionStackSection newClusterSection;

	private TabSet managementTabSet;

	private TextItem txtAccessKey;

	private TextItem txtSecretAccessKey;

	private Label lblCluster;

	private DynamicForm dynamicForm_01;

	private ComboBoxItem cbCassVersion;

	private IntegerItem intFlushFraction;

	private Button btnSwitchToTab2;

	private VLayout layoutManagement;

	private Label lblRunningCluster;

	private Tab nodeSummaryTab;

	private ListGrid nodeListGrid;

	private TreeGridField fieldIp;

	private TreeGridField fieldStartCassandraButton;

	private TreeGridField fieldStopCassandraButton;

	private TreeGridField fieldStopInstance;

	private VLayout layoutNewNode;

	private DynamicForm dynamicForm_8;

	private Label lblNewNode;

	private TextItem txtAccessKeyNodes;

	private TextItem txtSecretAccessKeyNodes;

	private TextItem txtIpAddresses1and1;

	private TextItem txtLoginName1and1;

	private TextItem txtPass1and1;

	private ComboBoxItem cbRegionNodes;

	private Tab newNodeTab;

	private Tab nodeConfigTab;

	private DynamicForm dynamicForm_3_Nodes;

	private RadioGroupItem rgHintedHandoffNodes;

	private IntegerItem intMaxWindowTimeNodes;

	private IntegerItem intThrottleDelayNodes;

	private VLayout layoutConfig;

	private DynamicForm dynamicForm_4_Nodes;

	private ComboBoxItem cbSyncTypeNodes;

	private IntegerItem intTimeWindowsNodes;

	private IntegerItem intCommitlogTotalSpaceNodes;

	private DynamicForm dynamicForm_5_Nodes;

	private FloatItem floatReduceCacheAtNodes;

	private FloatItem floatReduceCacheCapacityNodes;

	private DynamicForm dynamicForm_6_Nodes;

	private IntegerItem intConcurrentReadsNodes;

	private IntegerItem intConcurrentWritesNodes;

	private DynamicForm dynamicForm_7_Nodes;

	private IntegerItem intMemtableTotalSpaceNodes;

	private IntegerItem intMemtableWriterThreadsNodes;

	private IntegerItem intFlushFractionNodes;
	
	@Override
	public void onModuleLoad() {
		df = new DashboardFunctions();
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("800", "600");
		
		lblHekabeDashboard = new Label("Hekabe Dashboard");
		lblHekabeDashboard.setSize("150", "30");
		lblHekabeDashboard.setAlign(Alignment.CENTER);
		rootPanel.add(lblHekabeDashboard);
		
		sectionStack = new SectionStack();
		sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
		sectionStack.setSize("750", "650");
		
		managementSection = new SectionStackSection("Management");
		managementSection.setExpanded(true);
		newClusterSection = new SectionStackSection("New Cluster");
		newClusterSection.setExpanded(false);
		
		newClusterTabSet = new TabSet();
		newClusterTabSet.setSize("750", "595");
		managementTabSet = new TabSet();
		managementTabSet.setSize("750", "595");

		initNewClusterSection();
		initManagementSection();
		
		sectionStack.addSection(managementSection);
		sectionStack.addSection(newClusterSection);
		
		rootPanel.add(sectionStack);
	}
	
	private void initNewClusterSection() {
		initHardwareTab();
		initCassandraTab();
		initCassandraConfigTuningTab();
		
		newClusterTabSet.addTab(hardwareTab);
		newClusterTabSet.addTab(cassandraTab);
		newClusterTabSet.addTab(cassandraConfigTab);
		newClusterSection.addItem(newClusterTabSet);
	}
	
	private void initManagementSection() {
		layoutManagement = new VLayout();
		
		initClusterTable();		
		initNodeSummaryTab();
		initNewNodesTab();
		initConfigTab();
		initSchemaTab();
		
		managementTabSet.addTab(nodeSummaryTab);
		managementTabSet.addTab(newNodeTab);
		managementTabSet.addTab(nodeConfigTab);
		
		
		layoutManagement.addMember(managementTabSet);
		managementSection.addItem(layoutManagement);
	}
	
	private void initClusterTable() {
		lblRunningCluster = new Label("Cluster");
		lblRunningCluster.setHeight(30);
		layoutManagement.addMember(lblRunningCluster);
		
		runningClusterListGrid = new ListGrid();
		runningClusterListGrid.setHeight("150");
		runningClusterListGrid.setEmptyMessage("No clusters running.");
		fieldName = new TreeGridField("fieldName", "Name");
		fieldProvider = new TreeGridField("fieldProvider", "Provider");
		fieldNumberOfNodes = new TreeGridField("fieldNumberOfNodes", "Number of nodes");
		runningClusterListGrid.setFields(new ListGridField[] { fieldName, fieldProvider, fieldNumberOfNodes });
		
		layoutManagement.addMember(runningClusterListGrid);
	}

	private void initNodeSummaryTab() {
		nodeSummaryTab = new Tab("Node Summary");
		
		nodeListGrid = new ListGrid();
		nodeListGrid.setHeight(150);
		nodeListGrid.setEmptyMessage("No nodes in this cluster running.");
		fieldIp = new TreeGridField("fieldIp", "IP");
		fieldStartCassandraButton = new TreeGridField("fieldStartCassandraButton", "Start Cassandra");
		fieldStartCassandraButton.setType(ListGridFieldType.ICON);
		fieldStopCassandraButton = new TreeGridField("fieldStopCassandraButton", "Stop Cassandra");
		fieldStopCassandraButton.setType(ListGridFieldType.ICON);
		fieldStopInstance = new TreeGridField("fieldStopInstance", "Stop Instance");
		fieldStopInstance.setType(ListGridFieldType.ICON);
		nodeListGrid.setFields(new ListGridField[] { fieldIp, fieldStartCassandraButton, fieldStopCassandraButton, fieldStopInstance });
		
		nodeSummaryTab.setPane(nodeListGrid);
	}

	private void initNewNodesTab() {
		newNodeTab = new Tab("New Node");
		
		layoutNewNode = new VLayout();
		
		lblNewNode = new Label("New Node");
		lblNewNode.setHeight(30);
		layoutNewNode.addMember(lblNewNode);
		
		dynamicForm_8 = new DynamicForm();
		cbProviderNodes = new ComboBoxItem("cbProviderNodes", "Provider");
		cbProviderNodes.setValueMap("Amazon EC2","1&1 Cloud");
		cbProviderNodes.setDefaultToFirstOption(true);
		txtAccessKeyNodes = new TextItem("txtAccessKey", "Access Key");
		txtSecretAccessKeyNodes = new TextItem("txtSecretAccessKey", "Secret Access Key");
		txtIpAddresses1and1 = new TextItem("txtIpAddresses", "IPs");
		txtLoginName1and1 = new TextItem("txtLoginName1and1", "Login");
		txtPass1and1 = new TextItem("txtPass1and1", "Password");
		cbRegionNodes = new ComboBoxItem("Region", "Region");
		cbRegionNodes.setValueMap("US East (Virginia)",
							 "US West (Oregon)",
							 "US West (California)",
							 "EU West (Ireland)",
							 "Asia Pacific (Singapore)",
							 "Asia Pacific (Tokyo)");
		cbRegionNodes.setDefaultToFirstOption(true);
		
		dynamicForm_8.setFields(new FormItem[] { cbProviderNodes, txtAccessKeyNodes, 
				txtSecretAccessKeyNodes, txtIpAddresses1and1, txtLoginName1and1,
				txtPass1and1, cbRegionNodes });
		
		layoutNewNode.addMember(dynamicForm_8);
		newNodeTab.setPane(layoutNewNode);
	}

	private void initConfigTab() {
		nodeConfigTab = new Tab("Config");
		
		layoutNodeConfig = new VLayout();

		layoutNodeConfig.addMember(lblNewLabel_1);
		
		dynamicForm_3_Nodes = new DynamicForm();
		rgHintedHandoffNodes = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoffNodes.setVertical(false);
		rgHintedHandoffNodes.setValueMap("Enabled","Disabled");
		intMaxWindowTimeNodes = new IntegerItem("maxWindowTimeNodes", "Maximum Window Time (ms)");
		intThrottleDelayNodes = new IntegerItem("throttleDelayNodes", "Throttle Delay (ms)");
		dynamicForm_3_Nodes.setFields(new FormItem[] { rgHintedHandoffNodes, intMaxWindowTimeNodes, intThrottleDelayNodes });
		layoutNodeConfig.addMember(dynamicForm_3_Nodes);
		
		layoutNodeConfig.addMember(lblNewLabel_2);
		
		dynamicForm_4_Nodes = new DynamicForm();
		cbSyncTypeNodes = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		cbSyncTypeNodes.setValueMap("periodic","batch");
		intTimeWindowsNodes = new IntegerItem();
		intTimeWindowsNodes.setTitle("Time window (ms)");
		intCommitlogTotalSpaceNodes = new IntegerItem();
		intCommitlogTotalSpaceNodes.setTitle("commitlog_total_space (0 = unlimited) (mb)");
		dynamicForm_4_Nodes.setFields(new FormItem[] { cbSyncTypeNodes, intTimeWindowsNodes, intCommitlogTotalSpaceNodes});
		layoutNodeConfig.addMember(dynamicForm_4_Nodes);
		
		layoutNodeConfig.addMember(lblNewLabel_3);
		
		dynamicForm_5_Nodes = new DynamicForm();
		floatReduceCacheAtNodes = new FloatItem("floatReduceCacheAt", "Reduce cache at");
		floatReduceCacheCapacityNodes = new FloatItem("floatReduceCacheCapacity", "Reduce cache capacity");
		dynamicForm_5_Nodes.setFields(new FormItem[] { floatReduceCacheAtNodes, floatReduceCacheCapacityNodes });
		layoutNodeConfig.addMember(dynamicForm_5_Nodes);
		
		layoutNodeConfig.addMember(lblNewLabel_4);
		
		dynamicForm_6_Nodes = new DynamicForm();
		intConcurrentReadsNodes = new IntegerItem("intConcurrentReads", "Concurrent Reads");
		intConcurrentWritesNodes = new IntegerItem("intConcurrentWrites", "Concurrent Writes");
		dynamicForm_6_Nodes.setFields(new FormItem[] { intConcurrentReadsNodes, intConcurrentWritesNodes });
		layoutNodeConfig.addMember(dynamicForm_6_Nodes);

		layoutNodeConfig.addMember(lblNewLabel_5);
		
		dynamicForm_7_Nodes = new DynamicForm();
		intMemtableTotalSpaceNodes = new IntegerItem("intMemtableTotalSpace", "Memtable total space (0 = unlimited) (mb)");
		intMemtableWriterThreadsNodes = new IntegerItem("intMemtableWriterThreads", "Memtable writer threads (0 = automatic)");
		intFlushFractionNodes = new IntegerItem("intFlushFraction", "Flush fraction (%)");
		dynamicForm_7_Nodes.setFields(new FormItem[] { intMemtableTotalSpaceNodes, intMemtableWriterThreadsNodes, intFlushFractionNodes });
		layoutNodeConfig.addMember(dynamicForm_7_Nodes);
		
		nodeConfigTab.setPane(layoutNodeConfig);
	}

	private void initSchemaTab() {
		// TODO Auto-generated method stub
		
	}

	private void initHardwareTab() {
		hardwareTab = new Tab("1. Hardware");
		
		LayoutCreateCluster = new VLayout();
		
		lblProvider = new Label("Provider");
		lblProvider.setHeight("30");
		LayoutCreateCluster.addMember(lblProvider);
		
		dynamicForm = new DynamicForm();
		cbProvider = new ComboBoxItem("Provider", "Provider");
		cbProvider.setShowTitle(true);
		cbProvider.setTooltip("Choose Cloud-Provider.");
		cbProvider.setValueMap("Amazon EC2","1&1 Cloud");
		cbProvider.setDefaultToFirstOption(true);
		cbProvider.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				df.providerChange("");
			}
			
		});
		
		txtAccessKey = new TextItem("txtAccessKey", "Access Key");
		txtSecretAccessKey = new TextItem("txtSecretAccessKey", "Secret Access Key");
		
		dynamicForm.setFields(new FormItem[] { cbProvider, txtAccessKey, txtSecretAccessKey });
		LayoutCreateCluster.addMember(dynamicForm);
		
		lblCluster = new Label("Cluster");
		lblCluster.setHeight("30");
		LayoutCreateCluster.addMember(lblCluster);
		
		dynamicForm_01 = new DynamicForm();
		intNumberOfInstances = new IntegerItem();
		intNumberOfInstances.setTooltip("The number of instances you want to start.");
		intNumberOfInstances.setTitle("Number of instances");
		
		cbRegion = new ComboBoxItem("Region", "Region");
		cbRegion.setValueMap("US East (Virginia)",
							 "US West (Oregon)",
							 "US West (California)",
							 "EU West (Ireland)",
							 "Asia Pacific (Singapore)",
							 "Asia Pacific (Tokyo)");
		cbRegion.setDefaultToFirstOption(true);
		
		cbInstanceSize = new ComboBoxItem("InstanceSize", "Instance size");
		cbInstanceSize.setShowTitle(true);
		cbInstanceSize.setTooltip("Choose size of the instance(s).");
		cbInstanceSize.setValueMap("Large (m1.large)",
								   "Extra Large (m1.xlarge)",
								   "High-Memory Extra Large (m2.xlarge)",
								   "High-Memory Double Extra Large (m2.2xlarge)",
								   "High-Memory Quadruple Extra Large (m2.4xlarge)",
								   "High-CPU Extra Large (c1.xlarge)");
		cbInstanceSize.setDefaultToFirstOption(true);
		
		dynamicForm_01.setFields(new FormItem[] { intNumberOfInstances, cbRegion, cbInstanceSize });
		LayoutCreateCluster.addMember(dynamicForm_01);
		
		btnSwitchToTab2 = new Button("Next");
		LayoutCreateCluster.addMember(btnSwitchToTab2);
		
		hardwareTab.setPane(LayoutCreateCluster);
	}
	
	private void initCassandraTab() {
		cassandraTab = new Tab("2. Cassandra");
		
		layoutCassandra = new VLayout();
		layoutCassandra.setWidth("750");
		
		lblNewLabel = new Label("Cluster");
		lblNewLabel.setHeight("30");
		layoutCassandra.addMember(lblNewLabel);
		
		dynamicForm_1 = new DynamicForm();
		txtClusterName = new TextItem("newTextItem_1", "Cluster Name");
		cbCassVersion = new ComboBoxItem("cbCassVersion", "Cassandra Version");
		cbCassVersion.setValueMap("1.0");
		cbCassVersion.setDefaultToFirstOption(true);
		cbPartitioner = new ComboBoxItem("newComboBoxItem_3", "Partitioner");
		cbPartitioner.setValueMap("Random Partitioner","Byte Ordered Partitioner");
		cbPartitioner.setDefaultToFirstOption(true);
		
		dynamicForm_1.setFields(new FormItem[] { txtClusterName, cbCassVersion, cbPartitioner });
		layoutCassandra.addMember(dynamicForm_1);
		
		cassandraTab.setPane(layoutCassandra);
	}
	
	private void initCassandraConfigTuningTab() {
		cassandraConfigTab = new Tab("3. Cassandra Config Tuning");
		
		layoutConfig = new VLayout();
		
		lblNewLabel_1 = new Label("Hinted Handoff");
		lblNewLabel_1.setHeight("30");
		layoutConfig.addMember(lblNewLabel_1);
		
		dynamicForm_3 = new DynamicForm();
		rgHintedHandoff = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoff.setVertical(false);
		rgHintedHandoff.setValueMap("Enabled","Disabled");
		intMaxWindowTime = new IntegerItem("maxWindowTime", "Maximum Window Time (ms)");
		intThrottleDelay = new IntegerItem("throttleDelay", "Throttle Delay (ms)");
		dynamicForm_3.setFields(new FormItem[] { rgHintedHandoff, intMaxWindowTime, intThrottleDelay});
		layoutConfig.addMember(dynamicForm_3);
		
		lblNewLabel_2 = new Label("Commit Log");
		lblNewLabel_2.setHeight(30);
		layoutConfig.addMember(lblNewLabel_2);
		
		dynamicForm_4 = new DynamicForm();
		cbSyncType = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		cbSyncType.setValueMap("periodic",
							   "batch");
		intTimeWindows = new IntegerItem();
		intTimeWindows.setTitle("Time window (ms)");
		intCommitlogTotalSpace = new IntegerItem();
		intCommitlogTotalSpace.setTitle("commitlog_total_space (0 = unlimited) (mb)");
		dynamicForm_4.setFields(new FormItem[] { cbSyncType, intTimeWindows, intCommitlogTotalSpace});
		layoutConfig.addMember(dynamicForm_4);
		
		lblNewLabel_3 = new Label("Garbage Collection");
		lblNewLabel_3.setHeight(30);
		layoutConfig.addMember(lblNewLabel_3);
		
		dynamicForm_5 = new DynamicForm();
		floatReduceCacheAt = new FloatItem("floatReduceCacheAt", "Reduce cache at");
		floatReduceCacheCapacity = new FloatItem("floatReduceCacheCapacity", "Reduce cache capacity");
		dynamicForm_5.setFields(new FormItem[] { floatReduceCacheAt, floatReduceCacheCapacity });
		layoutConfig.addMember(dynamicForm_5);
		
		lblNewLabel_4 = new Label("Read/Write");
		lblNewLabel_4.setHeight(30);
		layoutConfig.addMember(lblNewLabel_4);
		
		dynamicForm_6 = new DynamicForm();
		intConcurrentReads = new IntegerItem("intConcurrentReads", "Concurrent Reads");
		intConcurrentWrites = new IntegerItem("intConcurrentWrites", "Concurrent Writes");
		dynamicForm_6.setFields(new FormItem[] { intConcurrentReads, intConcurrentWrites });
		layoutConfig.addMember(dynamicForm_6);
		
		lblNewLabel_5 = new Label("Memtable");
		lblNewLabel_5.setHeight(30);
		layoutConfig.addMember(lblNewLabel_5);
		
		dynamicForm_7 = new DynamicForm();
		intMemtableTotalSpace = new IntegerItem("intMemtableTotalSpace", "Memtable total space (0 = unlimited) (mb)");
		intMemtableWriterThreads = new IntegerItem("intMemtableWriterThreads", "Memtable writer threads (0 = automatic)");
		intFlushFraction = new IntegerItem("intFlushFraction", "Flush fraction (%)");
		dynamicForm_7.setFields(new FormItem[] { intMemtableTotalSpace, intMemtableWriterThreads, intFlushFraction });
		layoutConfig.addMember(dynamicForm_7);
		
		cassandraConfigTab.setPane(layoutConfig);
	}
}