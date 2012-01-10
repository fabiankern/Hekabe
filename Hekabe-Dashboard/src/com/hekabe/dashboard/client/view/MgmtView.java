package com.hekabe.dashboard.client.view;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.VLayout;

public class MgmtView extends VLayout {

	MgmtClustersView cluster;
	MgmtDetailView detail;
	
	public MgmtView() {
		
		cluster = new MgmtClustersView();
		detail = new MgmtDetailView();
		
		addMember(cluster);
		addMember(detail);
		setOverflow(Overflow.VISIBLE);
		setAutoHeight();
	}
}
