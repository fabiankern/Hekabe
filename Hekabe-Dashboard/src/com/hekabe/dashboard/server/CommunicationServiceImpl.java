package com.hekabe.dashboard.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.hekabe.dashboard.client.CommunicationService;
import com.hekabe.dashboard.server.util.ProgessObserver;
import com.hekabe.dashboard.shared.NewClusterExchange;


public class CommunicationServiceImpl extends RemoteServiceServlet implements
		CommunicationService {
	private static final long serialVersionUID = 1L;
	private NewClusterImpl newClusterImpl;

	/**
	 * Starts a cluster.
	 */
	public String startInstance(NewClusterExchange ex) {
		newClusterImpl = new NewClusterImpl(ex, new ProgessObserver());
		newClusterImpl.startInstance();
		
		MgmtImpl m = new MgmtImpl();
		m.saveCluster(ex);
		
		return "WELL DONE!";
	}

	/**
	 * Returns status and progress of starting a new cluster.
	 */
	public HashMap<String, String> getStartInstanceStatus() {
		while(newClusterImpl == null) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while(!newClusterImpl.getObserver().hasChanged()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("action", newClusterImpl.getObserver().getStatus());
		map.put("progress", newClusterImpl.getObserver().getProgress());
		
		return map;
	}	

	/**
	 * Checks login Data
	 */
	public Boolean login(HashMap<String, String> loginData) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean result = false;
		if(loginData.containsKey("user") && loginData.containsKey("password")) {
			String user = loginData.get("user");
			String password = loginData.get("password");
			if(user != null && password != null) {
				if(user.equals("admin")) result = true;
				if(user.equals("fabi")) result = true;
				if(user.equals("max") && password.equals("max")) result = true;				
			}			
		}
		
		return result;
	}

	/**
	 * Returns ClusterData of a given user.
	 */
	public ArrayList<NewClusterExchange> getClusterData(String user) {
		MgmtImpl m = new MgmtImpl();
		return m.getClusterExchangeList(user);
	}

	/**
	 * starts benchmarking
	 */
	public String startBenchmark(String user, long timestamp, HashMap<String, String> parameters) {
		MgmtImpl m = new MgmtImpl();
		String result;
		try {
			result = m.startBenchmark(user, timestamp, parameters);
		} catch (IOException e) {
			e.printStackTrace();
			result = "";
		}
		
		
		return result;
	}

	/**
	 * deletes cluster
	 */
	public String deleteCluster(String user, long timestamp) {
		MgmtImpl m = new MgmtImpl();
		String result = m.deleteCluster(user, timestamp); 
		
		return result;
	}

	/**
	 * starts cassandra
	 */
	public String startCassandra(String user, long timestamp, String ip, String provider) {
		MgmtImpl m = new MgmtImpl();
		String result = m.startCassandra(user, timestamp, ip, provider);
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * stopps cassandra
	 */
	public String stopCassandra(String user, long timestamp, String ip, String provider) {
		MgmtImpl m = new MgmtImpl();
		String result = m.stopCassandra(user, timestamp, ip, provider);
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * decommissions node
	 */
	public String decommissionCassandra(String user, long timestamp, String ip, String provider) {
		MgmtImpl m = new MgmtImpl();
		String result = m.decommissionCassandra(user, timestamp, ip, provider);
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * terminates instance
	 */
	public String terminateInstance(String user, long timestamp, String ip, String provider) {
		MgmtImpl m = new MgmtImpl();
		String result = m.terminateInstance(user, timestamp, ip, provider);
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * rebalace tokens
	 */
	public String rebalanceToken(String user, long timestamp) {
		MgmtImpl m = new MgmtImpl();
		String result = m.rebalanceToken(user, timestamp);
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * returns config values
	 */
	public NewClusterExchange getConfigValues(String user, long timestamp) {
		MgmtImpl m = new MgmtImpl();
		
		return m.getConfigData(user, timestamp);
	}

	/**
	 * set config values
	 */
	public String setConfigValues(String user, long timestamp, HashMap<String, String> parameters) {
		MgmtImpl m = new MgmtImpl();
		
		return m.setConfigValues(user, timestamp, parameters);
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
/*	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}*/
}
