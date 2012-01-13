package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.client.Dashboard;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class NewClusterView extends VLayout {

	VLayout layout = new VLayout();
	TabSet newClusterTabSet = new TabSet();
	Tab hardwareTab = new Tab("1. Hardware");
	Tab cassandraTab = new Tab("2. Cassandra");
	Tab cassandraConfigTab = new Tab("3. Cassandra Config Tuning");
	Dashboard dashboard;
	CommunicationServiceAsync rpcService;
	
	public NewClusterView(Dashboard dashboard, CommunicationServiceAsync rpcService) {
		this.dashboard = dashboard;
		this.rpcService = rpcService;
		
		NewClusterHardwareView hardware = new NewClusterHardwareView(this, rpcService);
		NewClusterCassandraView cassandra = new NewClusterCassandraView(this, rpcService);
		NewClusterCassandraConfigView cassandraConfig = new NewClusterCassandraConfigView(this, rpcService);
		
		hardwareTab.setPane(hardware);
		cassandraTab.setPane(cassandra);
		cassandraConfigTab.setPane(cassandraConfig);
		
		newClusterTabSet.addTab(hardwareTab);
		newClusterTabSet.addTab(cassandraTab);
		newClusterTabSet.addTab(cassandraConfigTab);
		newClusterTabSet.disableTab(1);
		newClusterTabSet.disableTab(2);
		
		addMember(newClusterTabSet);
	}

	public TabSet getTabSet() {
		return newClusterTabSet;
	}
}
