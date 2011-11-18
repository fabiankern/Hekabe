package com.hekabe.dashboard.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CommunicationServiceAsync {
	void startInstance(Integer anzahlInstanzen, AsyncCallback<String> callback);
}
