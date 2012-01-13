package com.hekabe.dashboard.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.TabSet;

public class NewClusterPresenter implements Presenter {

	public interface Display {
		TabSet getTabSet();
		VLayout asVLayout();
	}
	
	private final CommunicationServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public NewClusterPresenter(CommunicationServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}
	
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asVLayout());
	}

}
