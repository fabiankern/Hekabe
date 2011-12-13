package com.hekabe.dashboard.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("comm")
public interface CommunicationService extends RemoteService {
	String startInstance(Integer anzahlInstanzen);
	String demo(String amiID, String instanceSize, int numberOfInstances);
}
