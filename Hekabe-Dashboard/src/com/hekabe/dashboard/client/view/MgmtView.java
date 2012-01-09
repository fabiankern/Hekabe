package com.hekabe.dashboard.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.widgets.layout.VLayout;

public class MgmtView extends Composite {

	VLayout layout;
	MgmtClustersView cluster;
	MgmtDetailView detail;
	
	public MgmtView() {
		layout = new VLayout();
		layout.setAutoDraw(false);
		
		cluster = new MgmtClustersView();
		detail = new MgmtDetailView();
		
		layout.addMember(cluster.getPane());
		layout.addMember(detail.getPane());
		
		initWidget(layout);
	}
	
	public VLayout getPane() {
		return layout;
	}

}
