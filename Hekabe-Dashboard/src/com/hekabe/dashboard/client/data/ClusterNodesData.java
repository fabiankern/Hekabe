package com.hekabe.dashboard.client.data;

import java.util.Date;

import com.smartgwt.client.data.Record;


public class ClusterNodesData {
	private static ClusterGridRecord[] records;
	private static int numberOfInstances;
	private static String[] ips;
	private static String[] mrIps;
	private static String[] _status;
	private static String[] _mrStatus;
	private static int mrNumberOfInstances;
	private static boolean isMultiregion;
	
	/**
	 * Creates records for ClusterGridView
	 * @param record
	 * @return
	 */
	public static ClusterGridRecord[] getRecords(Record record) {
		ips = record.getAttributeAsString("ip").split(";");
		_status = record.getAttributeAsString("status").split(";");
		
		numberOfInstances = record.getAttributeAsInt("numberOfInstances");
		
		isMultiregion = record.getAttributeAsBoolean("multiregion");
		
		if(isMultiregion) {
			mrNumberOfInstances = record.getAttributeAsInt("mrNumberOfInstances");
			mrIps = record.getAttributeAsString("mrIp").split(";");
			_mrStatus = record.getAttributeAsString("mrStatus").split(";");
		} else {
			mrNumberOfInstances = 0;
			mrIps = null;
			_mrStatus = null;
		}

		records = getNewRecords(record);
		return records;
	}

	/**
	 * 
	 * @param record
	 * @return
	 */
	private static ClusterGridRecord[] getNewRecords(Record record) {
		records = new ClusterGridRecord[numberOfInstances + mrNumberOfInstances];
		
		int i = 0;
		
		for(; i < numberOfInstances; i++) {
			String ip = ips[i];
			String status = _status[i];
			String provider = record.getAttributeAsString("provider");
			String region = record.getAttributeAsString("region");
			Date startDate = record.getAttributeAsDate("startDate");
			int numberOfNodes = record.getAttributeAsInt("numberOfNodes");
			
			ClusterGridRecord entry = new ClusterGridRecord(ip, provider, region, startDate, numberOfNodes, status);
			records[i] = entry;
		}
		
		if(isMultiregion) {
			for(; i < numberOfInstances + mrNumberOfInstances; i++) {
				String mrIp = mrIps[i - numberOfInstances];
				String mrStatus = _mrStatus[i - numberOfInstances];
				String mrProvider = record.getAttributeAsString("mrProvider");
				String mrRegion = record.getAttributeAsString("mrRegion");
				Date startDate = record.getAttributeAsDate("startDate");
				int numberOfNodes = record.getAttributeAsInt("numberOfNodes");
				
				ClusterGridRecord entry = new ClusterGridRecord(mrIp, mrProvider, mrRegion, startDate, numberOfNodes, mrStatus);
				records[i] = entry;
			}
		}
		
		return records;
	}
}
