package com.hekabe.dashboard.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.client.event.NewClusterSwitchToTabTwoEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.HasClickHandlers;
import com.smartgwt.client.widgets.layout.VLayout;

public class NewClusterHardwarePresenter implements Presenter {
	
	public interface Display {
		HasClickHandlers getNextButton();
		String getProvider();
		String getAccessKey();
		String getSecretAccessKey();
		int getNumberOfInstances();
		String getRegion();
		String getInstanceSize();
		VLayout asVLayout();
	}
	
	private final CommunicationServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public NewClusterHardwarePresenter(CommunicationServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}
	
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asVLayout());
	}

	private void bind() {
		display.getNextButton().addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new NewClusterSwitchToTabTwoEvent());
			}
		});
		
		
	}

}
