package com.hekabe.dashboard.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class NewClusterView extends Composite {

	TabSet newClusterTabSet = new TabSet();
	Tab hardwareTab = new Tab("1. Hardware");
	Tab cassandraTab = new Tab("2. Cassandra");
	Tab cassandraConfigTab = new Tab("3. Cassandra Config Tuning");
	
	public NewClusterView() {
		NewClusterHardwareView hardware = new NewClusterHardwareView();
		NewClusterCassandraView cassandra = new NewClusterCassandraView();
		NewClusterCassandraConfigView cassandraConfig = new NewClusterCassandraConfigView();
		
		hardwareTab.setPane(hardware.getPane());
		cassandraTab.setPane(cassandra.getPane());
		cassandraConfigTab.setPane(cassandraConfig.getPane());
		
		newClusterTabSet.addTab(hardwareTab);
		newClusterTabSet.addTab(cassandraTab);
		newClusterTabSet.addTab(cassandraConfigTab);
		newClusterTabSet.disableTab(1);
		newClusterTabSet.disableTab(2);
		
		initWidget(newClusterTabSet);
	}

	public Canvas getPane() {
		return newClusterTabSet;
	}
}
