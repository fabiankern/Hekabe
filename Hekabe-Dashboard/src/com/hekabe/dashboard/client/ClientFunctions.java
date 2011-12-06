package com.hekabe.dashboard.client;

public class ClientFunctions extends Dashboard {
	
	public void changeProvider(String provider) {
		//Dashboard.cbInstanceSize.setValue("changeme");
		//cbInstanceSize.setTitle("cool");
		if(provider != null) {
			if(provider.equals("Amazon EC2")) {
				Dashboard.cbInstanceSize.setValue("Large (m1.large)");
			}
			if(provider.equals("1&1 Cloud")) {
				Dashboard.cbInstanceSize.setValueMap("1und1 Large","1und1 X-Large", "1und1 XX-Large");
				Dashboard.cbInstanceSize.setValue("1und1 Large");
			}
		}
		
		//String xy = cbInstanceSize.getValueAsString();
		
	}

}
