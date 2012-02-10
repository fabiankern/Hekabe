package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.client.Dashboard;
import com.hekabe.dashboard.shared.NewClusterExchange;
import com.hekabe.dashboard.shared.parameter.StringParameter;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class NewClusterView extends VLayout {

	VLayout layout = new VLayout();
	TabSet newClusterTabSet = new TabSet();
	Tab hardwareTab = new Tab(StringParameter.FIRST_HARDWARE);
	Tab cassandraTab = new Tab(StringParameter.SECOND_CASSANDRA);
	Tab cassandraConfigTab = new Tab(StringParameter.THIRD_CASSANDRA_CONFIG_TUNING);
	Dashboard dashboard;
	NewClusterHardwareView hardware;
	NewClusterCassandraView cassandra;
	NewClusterCassandraConfigView cassandraConfig;
	CommunicationServiceAsync rpcService;
	NewClusterExchange ex;
	
	/**
	 * Creates NewClusterView
	 * @param dashboard Dashboard
	 * @param rpcService CommunicationServiceAsync
	 */
	public NewClusterView(Dashboard dashboard, CommunicationServiceAsync rpcService) {
		this.dashboard = dashboard;
		this.rpcService = rpcService;
		
		this.setMargin(10);
		
		ex = new NewClusterExchange();
		hardware = new NewClusterHardwareView(this, rpcService);
		cassandra = new NewClusterCassandraView(this, rpcService);
		cassandraConfig = new NewClusterCassandraConfigView(this, rpcService, ex);
		
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

	/**
	 * 
	 * @return
	 */
	public TabSet getTabSet() {
		return newClusterTabSet;
	}
	
	/**
	 * 
	 * @return
	 */
	public NewClusterHardwareView getNewClusterHardwareView() {
		return hardware;
	}
	
	/**
	 * 
	 * @return
	 */
	public NewClusterCassandraView getNewClusterCassandraView() {
		return cassandra;
	}
	
	/**
	 * 
	 * @return
	 */
	public NewClusterCassandraConfigView getNewClusterCassandraConfigView() {
		return cassandraConfig;
	}
	
	/**
	 * 
	 * @return
	 */
	public NewClusterExchange getExchangeObject() {
		return ex;
	}
	
	/**
	 * 
	 * @return
	 */
	public Dashboard getDashboard() {
		return dashboard;
	}
}
