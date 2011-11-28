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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.DoubleBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
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
	private TabLayoutPanel tabLayoutPanel;
	private Label lblHekabeDashboard;
	private VerticalPanel clusterPanel;
	private VerticalPanel cassManagementPanel;
	private VerticalPanel yamlManagementPanel;
	private Label lblHardware;
	private HorizontalPanel horizontalPanel;
	private HorizontalPanel horizontalPanel_1;
	private HorizontalPanel horizontalPanel_2;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private ListBox comboBox;
	private ListBox comboBox_1;
	private IntegerBox integerBox;
	private Label lblCassandraConfiguration;
	private HorizontalPanel horizontalPanel_3;
	private Label lblClusterName;
	private TextBox textBox;
	private HorizontalPanel horizontalPanel_4;
	private Label lblNumberOfCopies;
	private IntegerBox integerBox_1;
	private HorizontalPanel horizontalPanel_5;
	private Label lblReplikaPlacement;
	private Label lblPartitioner;
	private ListBox comboBox_2;
	private Label lblHardware_1;
	private Label lblStartstopNodes;
	private FlexTable flexTable;
	private Label lblAddNodes;
	private HorizontalPanel horizontalPanel_6;
	private HorizontalPanel horizontalPanel_7;
	private HorizontalPanel horizontalPanel_8;
	private Label lblNumberOfNodes;
	private Label lblSize;
	private Label lblRegion;
	private Button btnDoubleNumberOf;
	private IntegerBox integerBox_2;
	private ListBox comboBox_3;
	private ListBox comboBox_4;
	private Label lblHintedHandoff;
	private HorizontalPanel horizontalPanel_9;
	private RadioButton rdbtnEnabled;
	private RadioButton rdbtnDisabled;
	private HorizontalPanel horizontalPanel_10;
	private HorizontalPanel horizontalPanel_11;
	private Label lblMaxmimumWindowsTime;
	private Label lblThrottleDelay;
	private IntegerBox integerBox_3;
	private IntegerBox integerBox_4;
	private Label lblDataDisccharge;
	private Label lblCommitLog;
	private HorizontalPanel horizontalPanel_12;
	private Label lblSynchType;
	private ListBox comboBox_5;
	private HorizontalPanel horizontalPanel_13;
	private Label lblTimeWindow;
	private IntegerBox integerBox_5;
	private HorizontalPanel horizontalPanel_14;
	private Label lblCommitlogloadspace;
	private IntegerBox integerBox_6;
	private Label lblSeeds;
	private Label lblGarbageCollection;
	private HorizontalPanel horizontalPanel_15;
	private HorizontalPanel horizontalPanel_16;
	private HorizontalPanel horizontalPanel_17;
	private Label lblFlushLargestMemtables;
	private Label lblReduceCacheAt;
	private Label lblRecudeCacheCapacity;
	private DoubleBox doubleBox;
	private DoubleBox doubleBox_1;
	private DoubleBox doubleBox_2;
	private Label lblReadwrite;
	private HorizontalPanel horizontalPanel_18;
	private HorizontalPanel horizontalPanel_19;
	private Label lblConcurrentReads;
	private Label lblConcurrentWrites;
	private IntegerBox integerBox_7;
	private IntegerBox integerBox_8;
	private Label lblMemtable;
	private HorizontalPanel horizontalPanel_20;
	private HorizontalPanel horizontalPanel_21;
	private Label lblMemtableTotalSpace;
	private Label lblMemtableWriterThreads;
	private IntegerBox integerBox_9;
	private IntegerBox integerBox_10;
	
	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("800", "600");
		
		tabLayoutPanel = new TabLayoutPanel(1.5, Unit.EM);
		
		clusterPanel = new VerticalPanel();
		tabLayoutPanel.add(clusterPanel, "Create Cluster", false);
		clusterPanel.setSize("333px", "346px");
		
		lblHardware = new Label("Hardware");
		clusterPanel.add(lblHardware);
		
		horizontalPanel = new HorizontalPanel();
		clusterPanel.add(horizontalPanel);
		
		lblNewLabel = new Label("Provider");
		horizontalPanel.add(lblNewLabel);
		
		comboBox = new ListBox();
		horizontalPanel.add(comboBox);
		
		horizontalPanel_1 = new HorizontalPanel();
		clusterPanel.add(horizontalPanel_1);
		
		lblNewLabel_1 = new Label("Number of instances");
		horizontalPanel_1.add(lblNewLabel_1);
		
		integerBox = new IntegerBox();
		horizontalPanel_1.add(integerBox);
		
		horizontalPanel_2 = new HorizontalPanel();
		clusterPanel.add(horizontalPanel_2);
		
		lblNewLabel_2 = new Label("Size");
		horizontalPanel_2.add(lblNewLabel_2);
		
		comboBox_1 = new ListBox();
		horizontalPanel_2.add(comboBox_1);
		
		lblCassandraConfiguration = new Label("Cassandra Configuration");
		clusterPanel.add(lblCassandraConfiguration);
		
		horizontalPanel_3 = new HorizontalPanel();
		clusterPanel.add(horizontalPanel_3);
		
		lblClusterName = new Label("Cluster Name");
		horizontalPanel_3.add(lblClusterName);
		
		textBox = new TextBox();
		horizontalPanel_3.add(textBox);
		
		horizontalPanel_4 = new HorizontalPanel();
		clusterPanel.add(horizontalPanel_4);
		
		lblNumberOfCopies = new Label("Number of copies");
		horizontalPanel_4.add(lblNumberOfCopies);
		
		integerBox_1 = new IntegerBox();
		horizontalPanel_4.add(integerBox_1);
		
		horizontalPanel_5 = new HorizontalPanel();
		clusterPanel.add(horizontalPanel_5);
		
		lblReplikaPlacement = new Label("Replika Placement");
		horizontalPanel_5.add(lblReplikaPlacement);
		
		lblPartitioner = new Label("Partitioner");
		clusterPanel.add(lblPartitioner);
		
		comboBox_2 = new ListBox();
		clusterPanel.add(comboBox_2);
		
		cassManagementPanel = new VerticalPanel();
		tabLayoutPanel.add(cassManagementPanel, "Cassandra Management", false);
		
		lblHardware_1 = new Label("Hardware");
		cassManagementPanel.add(lblHardware_1);
		
		lblStartstopNodes = new Label("Start/stop nodes");
		cassManagementPanel.add(lblStartstopNodes);
		
		flexTable = new FlexTable();
		flexTable.setText(0, 0, "Node");
		flexTable.setText(0, 1, "Size");
		flexTable.setText(0, 2, "IP");
		flexTable.setText(0, 3, "");
		flexTable.setText(0, 4, "");
		cassManagementPanel.add(flexTable);
		
		lblAddNodes = new Label("Add Node(s)");
		cassManagementPanel.add(lblAddNodes);
		
		horizontalPanel_6 = new HorizontalPanel();
		cassManagementPanel.add(horizontalPanel_6);
		
		lblNumberOfNodes = new Label("Number of nodes");
		horizontalPanel_6.add(lblNumberOfNodes);
		
		integerBox_2 = new IntegerBox();
		horizontalPanel_6.add(integerBox_2);
		
		horizontalPanel_7 = new HorizontalPanel();
		cassManagementPanel.add(horizontalPanel_7);
		
		lblSize = new Label("Size");
		horizontalPanel_7.add(lblSize);
		
		comboBox_3 = new ListBox();
		horizontalPanel_7.add(comboBox_3);
		
		horizontalPanel_8 = new HorizontalPanel();
		cassManagementPanel.add(horizontalPanel_8);
		
		lblRegion = new Label("Region");
		horizontalPanel_8.add(lblRegion);
		
		comboBox_4 = new ListBox();
		horizontalPanel_8.add(comboBox_4);
		
		btnDoubleNumberOf = new Button("Double number of nodes");
		cassManagementPanel.add(btnDoubleNumberOf);
		
		yamlManagementPanel = new VerticalPanel();
		tabLayoutPanel.add(yamlManagementPanel, "Cassandra YAML Management", false);
		
		lblHintedHandoff = new Label("Hinted Handoff");
		yamlManagementPanel.add(lblHintedHandoff);
		
		horizontalPanel_9 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_9);
		
		rdbtnEnabled = new RadioButton("new name", "enabled");
		horizontalPanel_9.add(rdbtnEnabled);
		
		rdbtnDisabled = new RadioButton("new name", "disabled");
		horizontalPanel_9.add(rdbtnDisabled);
		
		horizontalPanel_10 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_10);
		
		lblMaxmimumWindowsTime = new Label("Maxmimum windows time (ms)");
		horizontalPanel_10.add(lblMaxmimumWindowsTime);
		
		integerBox_3 = new IntegerBox();
		horizontalPanel_10.add(integerBox_3);
		
		horizontalPanel_11 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_11);
		
		lblThrottleDelay = new Label("Throttle delay (ms)");
		horizontalPanel_11.add(lblThrottleDelay);
		
		integerBox_4 = new IntegerBox();
		horizontalPanel_11.add(integerBox_4);
		
		lblDataDisccharge = new Label("Data Disccharge");
		yamlManagementPanel.add(lblDataDisccharge);
		
		lblCommitLog = new Label("Commit Log");
		yamlManagementPanel.add(lblCommitLog);
		
		horizontalPanel_12 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_12);
		
		lblSynchType = new Label("Synch type");
		horizontalPanel_12.add(lblSynchType);
		
		comboBox_5 = new ListBox();
		horizontalPanel_12.add(comboBox_5);
		
		horizontalPanel_13 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_13);
		
		lblTimeWindow = new Label("Time window (ms)");
		horizontalPanel_13.add(lblTimeWindow);
		
		integerBox_5 = new IntegerBox();
		horizontalPanel_13.add(integerBox_5);
		
		horizontalPanel_14 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_14);
		
		lblCommitlogloadspace = new Label("commitlog_load_space (ms) (0 = unlimited)");
		horizontalPanel_14.add(lblCommitlogloadspace);
		
		integerBox_6 = new IntegerBox();
		horizontalPanel_14.add(integerBox_6);
		
		lblSeeds = new Label("Seeds");
		yamlManagementPanel.add(lblSeeds);
		
		lblGarbageCollection = new Label("Garbage Collection");
		yamlManagementPanel.add(lblGarbageCollection);
		
		horizontalPanel_15 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_15);
		
		lblFlushLargestMemtables = new Label("Flush largest memtables at");
		horizontalPanel_15.add(lblFlushLargestMemtables);
		
		doubleBox = new DoubleBox();
		horizontalPanel_15.add(doubleBox);
		
		horizontalPanel_16 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_16);
		
		lblReduceCacheAt = new Label("reduce cache at");
		horizontalPanel_16.add(lblReduceCacheAt);
		
		doubleBox_1 = new DoubleBox();
		horizontalPanel_16.add(doubleBox_1);
		
		horizontalPanel_17 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_17);
		
		lblRecudeCacheCapacity = new Label("reduce cache capacity");
		horizontalPanel_17.add(lblRecudeCacheCapacity);
		
		doubleBox_2 = new DoubleBox();
		horizontalPanel_17.add(doubleBox_2);
		
		lblReadwrite = new Label("Read/Write");
		yamlManagementPanel.add(lblReadwrite);
		
		horizontalPanel_18 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_18);
		
		lblConcurrentReads = new Label("Concurrent reads");
		horizontalPanel_18.add(lblConcurrentReads);
		
		integerBox_7 = new IntegerBox();
		horizontalPanel_18.add(integerBox_7);
		
		horizontalPanel_19 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_19);
		
		lblConcurrentWrites = new Label("Concurrent writes");
		horizontalPanel_19.add(lblConcurrentWrites);
		
		integerBox_8 = new IntegerBox();
		horizontalPanel_19.add(integerBox_8);
		
		lblMemtable = new Label("Memtable");
		yamlManagementPanel.add(lblMemtable);
		
		horizontalPanel_20 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_20);
		
		lblMemtableTotalSpace = new Label("Memtable total space (mb) (0 = unlimited)");
		horizontalPanel_20.add(lblMemtableTotalSpace);
		
		integerBox_9 = new IntegerBox();
		horizontalPanel_20.add(integerBox_9);
		
		horizontalPanel_21 = new HorizontalPanel();
		yamlManagementPanel.add(horizontalPanel_21);
		
		lblMemtableWriterThreads = new Label("Memtable writer threads (0 = automatic)");
		horizontalPanel_21.add(lblMemtableWriterThreads);
		
		integerBox_10 = new IntegerBox();
		horizontalPanel_21.add(integerBox_10);
		rootPanel.add(tabLayoutPanel, 10, 33);
		tabLayoutPanel.setSize("752px", "500px");
		
		lblHekabeDashboard = new Label("Hekabe Dashboard");
		rootPanel.add(lblHekabeDashboard, 10, 10);
	}
}