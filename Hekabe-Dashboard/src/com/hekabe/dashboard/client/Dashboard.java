/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hekabe.dashboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Dashboard implements EntryPoint {
	private VerticalPanel verticalPanel;
	private Label lblHekabeDashboardFor;
	private HorizontalPanel horizontalPanel;
	private Label lblAnzahlInstanzen;
	private TextBox textBoxAnzahlInstanzen;
	private Button btnInstanzenStarten;
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final CommunicationServiceAsync communicationService = GWT
			.create(CommunicationService.class);
	
	@Override
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		
		verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel, 10, 10);
		verticalPanel.setSize("226px", "136px");
		
		lblHekabeDashboardFor = new Label("Hekabe Dashboard for Cassandra");
		verticalPanel.add(lblHekabeDashboardFor);
		
		horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		
		lblAnzahlInstanzen = new Label("Anzahl Instanzen");
		horizontalPanel.add(lblAnzahlInstanzen);
		
		textBoxAnzahlInstanzen = new TextBox();
		horizontalPanel.add(textBoxAnzahlInstanzen);
		textBoxAnzahlInstanzen.setWidth("117px");
		
		btnInstanzenStarten = new Button("Instanzen starten");
		btnInstanzenStarten.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendRequest();
			}
		});
		verticalPanel.add(btnInstanzenStarten);
	}
	
	private void sendRequest() {
		int anzahlInstanzen = Integer.parseInt(textBoxAnzahlInstanzen.getText());
		communicationService.startInstance(anzahlInstanzen, 
				new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println(SERVER_ERROR);
						System.out.println(caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						System.out.println("Rückgabe vom Server: "+result);
					}
				});
	}
}