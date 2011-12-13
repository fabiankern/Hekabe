package com.hekabe.dashboard.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CommunicationServiceAsync {
	void startInstance(Integer anzahlInstanzen, AsyncCallback<String> callback);
	void demo(String amiID, String instanceSize, int numberOfInstances, AsyncCallback<String> callback);
}
