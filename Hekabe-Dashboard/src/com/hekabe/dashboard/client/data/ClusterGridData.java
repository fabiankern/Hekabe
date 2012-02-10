package com.hekabe.dashboard.client.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.hekabe.dashboard.shared.NewClusterExchange;
import com.hekabe.dashboard.shared.util.Util;

public class ClusterGridData {
	private static ClusterGridRecord[] records;
	private static ArrayList<NewClusterExchange> exList;
	
	/**
	 * 
	 * @param exList
	 * @return
	 */
	public static ClusterGridRecord[] getRecords(ArrayList<NewClusterExchange> exList) {
		ClusterGridData.exList = exList;
		records = getNewRecords();
		return records;
	}

	/**
	 * 
	 * @return
	 */
	private static ClusterGridRecord[] getNewRecords() {

		ClusterGridRecord[] records = new ClusterGridRecord[exList.size()];
		int i=0;
		
		for(Iterator<NewClusterExchange> exIterator = exList.iterator(); exIterator.hasNext(); ) {
			NewClusterExchange ex = exIterator.next();
			
			String clusterName = ex.getClusterName();
			boolean multiregion = ex.isMultiRegionCluster();
			long timestamp = ex.getTimestamp();
			int numberOfInstances = ex.getNumberOfInstances();
			String ips = Util.getIpString(ex.getIps());
			String status = Util.getStatusString(ex.getIps());
			String region = ex.getRegion();
			String provider = ex.getProvider();
			
			String mrIps;
			String mrRegion;
			String mrProvider;
			String mrStatus;
			int mrNumberOfInstances;
			
			if(multiregion) {
				mrIps = Util.getIpString(ex.getMrIps());
				mrRegion = ex.getMrRegion();
				mrProvider = ex.getMrProvider();
				mrNumberOfInstances = ex.getMrNumberOfInstances();
				mrStatus = Util.getStatusString(ex.getMrIps());
				records[i] = new ClusterGridRecord(clusterName, multiregion, new Date(timestamp), numberOfInstances, ips, region, provider, status, mrIps, mrRegion, mrProvider, mrNumberOfInstances, mrStatus);
			} else {
				records[i] = new ClusterGridRecord(clusterName, multiregion, new Date(timestamp), numberOfInstances, ips, region, provider, status);
			}
			
			i++;
		}
		
		return records;
	}
}
