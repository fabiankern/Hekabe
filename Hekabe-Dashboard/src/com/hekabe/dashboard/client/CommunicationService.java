package com.hekabe.dashboard.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.hekabe.dashboard.shared.NewClusterExchange;

@RemoteServiceRelativePath("comm")
public interface CommunicationService extends RemoteService {
	String startInstance(NewClusterExchange ex);
	HashMap<String, String> getStartInstanceStatus();
	Boolean login(HashMap<String, String> loginData);
	ArrayList<NewClusterExchange> getClusterData(String user);
	String startBenchmark(String user, long timestamp, HashMap<String, String> parameters);
	String deleteCluster(String user, long timestamp);
	String startCassandra(String user, long timestamp, String ip, String provider);
	String stopCassandra(String user, long timestamp, String ip, String provider);
	String decommissionCassandra(String user, long timestamp, String ip, String provider);
	String terminateInstance(String user, long timestamp, String ip, String provider);
	String rebalanceToken(String user, long timestamp);
	NewClusterExchange getConfigValues(String user, long timestamp);
	String setConfigValues(String user, long timestamp, HashMap<String, String> parameters);
}
