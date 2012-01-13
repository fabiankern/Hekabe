package com.hekabe.dashboard.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.hekabe.dashboard.client.presenter.Presenter;

public class AppController implements Presenter, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final CommunicationServiceAsync rpcService;
	private HasWidgets container;
	
	public AppController(CommunicationServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}
	
	
	private void bind() {
		History.addValueChangeHandler(this);
		
	}


	public void go(HasWidgets container) {
		this.container = container;
	}


	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		
	}

}
