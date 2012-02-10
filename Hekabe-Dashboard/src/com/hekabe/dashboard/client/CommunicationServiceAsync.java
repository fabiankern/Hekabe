package com.hekabe.dashboard.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.hekabe.dashboard.shared.NewClusterExchange;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CommunicationServiceAsync {
	void startInstance(NewClusterExchange ex, AsyncCallback<String> callback);
	void getStartInstanceStatus(AsyncCallback<HashMap<String, String>> callback);
	void login(HashMap<String, String> loginData, AsyncCallback<Boolean> callback);
	void getClusterData(String user, AsyncCallback<ArrayList<NewClusterExchange>> callback);
	void startBenchmark(String user, long timestamp, HashMap<String, String> parameters, AsyncCallback<String> callback);
	void deleteCluster(String user, long timestamp, AsyncCallback<String> callback);
	void startCassandra(String user, long timestamp, String ip, String provider, AsyncCallback<String> callback);
	void stopCassandra(String user, long timestamp, String ip, String provider, AsyncCallback<String> callback);
	void decommissionCassandra(String user, long timestamp, String ip, String provider, AsyncCallback<String> callback);
	void terminateInstance(String user, long timestamp, String ip, String provider, AsyncCallback<String> callback);
	void rebalanceToken(String user, long timestamp, AsyncCallback<String> callback);
	void getConfigValues(String user, long timestamp, AsyncCallback<NewClusterExchange> callback);
	void setConfigValues(String user, long timestamp, HashMap<String, String> parameters, AsyncCallback<String> callback);
}
