package com.hekabe.dashboard.client.view;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class MgmtDetailNodeSummaryView extends VLayout {
	
	private ListGrid nodeListGrid;
	private TreeGridField fieldIp;
	private TreeGridField fieldStartCassandraButton;
	private TreeGridField fieldStopCassandraButton;
	private TreeGridField fieldStopInstance;

	public MgmtDetailNodeSummaryView() {
		
		nodeListGrid = new ListGrid();
		nodeListGrid.setAutoDraw(false);
		
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
		
		addMember(nodeListGrid);
	}
}
