package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.presenter.NewClusterPresenter;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class NewClusterView extends VLayout implements NewClusterPresenter.Display {

	VLayout layout = new VLayout();
	TabSet newClusterTabSet = new TabSet();
	Tab hardwareTab = new Tab("1. Hardware");
	Tab cassandraTab = new Tab("2. Cassandra");
	Tab cassandraConfigTab = new Tab("3. Cassandra Config Tuning");
	
	public NewClusterView() {		
		NewClusterHardwareView hardware = new NewClusterHardwareView();
		NewClusterCassandraView cassandra = new NewClusterCassandraView();
		NewClusterCassandraConfigView cassandraConfig = new NewClusterCassandraConfigView();
		
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

	public VLayout getPane() {
		return layout;
	}

	public TabSet getTabSet() {
		return newClusterTabSet;
	}

	public VLayout asVLayout() {
		return this;
	}
}
