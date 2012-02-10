package com.hekabe.dashboard.client.data;

import java.util.Date;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ClusterGridRecord extends ListGridRecord {

	public ClusterGridRecord() {
		
	}
	
	/**
	 * Constructor for MgmtClusterView non-multiregion
	 * @param clusterName
	 * @param multiregion
	 * @param startDate
	 * @param numberOfNodes
	 * @param ip
	 * @param region
	 * @param provider
	 */
	public ClusterGridRecord(String clusterName, boolean multiregion, Date startDate, int numberOfNodes,
			String ip, String region, String provider, String status) {
		setClusterName(clusterName);
		setIsMultiregion(multiregion);
		setStartDate(startDate);
		setNumberOfInstances(numberOfNodes);
		setNumberOfNodes(numberOfNodes);
		setIP(ip);
		setRegion(region);
		setProvider(provider);
		setStatus(status);
	}
	
	/**
	 * Constructor for MgmtClusterView multiregion
	 * @param clusterName
	 * @param multiregion
	 * @param startDate
	 * @param numberOfNodes
	 * @param ip
	 * @param region
	 * @param provider
	 * @param mrIp
	 * @param mrRegion
	 * @param mrProvider
	 * @param mrNumberOfInstances
	 */
	public ClusterGridRecord(String clusterName, boolean multiregion,
			Date startDate, int numberOfNodes, String ip, String region,
			String provider, String status, String mrIp, String mrRegion, String mrProvider, int mrNumberOfInstances, String mrStatus) {
		setClusterName(clusterName);
		setIsMultiregion(multiregion);
		setStartDate(startDate);
		setNumberOfInstances(numberOfNodes);
		setIP(ip);
		setRegion(region);
		setProvider(provider);
		setStatus(status);
		setMrIP(mrIp);
		setMrProvider(mrProvider);
		setMrRegion(mrRegion);
		setMrNumberOfInstances(mrNumberOfInstances);
		setMrStatus(mrStatus);
		setNumberOfNodes(numberOfNodes + mrNumberOfInstances);
	}

	/**
	 * Constructor for MgmtDetailNodeSummaryView
	 * @param ip
	 * @param provider
	 * @param region
	 */
	public ClusterGridRecord(String ip, String provider, String region, Date startDate, int numberOfNodes, String status) {
		setIP(ip);
		setProvider(provider);
		setRegion(region);
		setStartDate(startDate);
		setNumberOfNodes(numberOfNodes);
		setStatus(status);
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		setAttribute("status", status);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return getAttributeAsString("status");
	}
	
	/**
	 * 
	 * @param mrStatus
	 */
	public void setMrStatus(String mrStatus) {
		setAttribute("mrStatus", mrStatus);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMrStatus() {
		return getAttributeAsString("mrStatus");
	}
	
	/**
	 * 
	 * @param numberOfNodes
	 */
	public void setNumberOfNodes(int numberOfNodes) {
		setAttribute("numberOfNodes", numberOfNodes);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfNodes() {
		return getAttributeAsInt("numberOfNodes");
	}
	
	/**
	 * 
	 * @param clusterName
	 */
	public void setClusterName(String clusterName) {
		setAttribute("clusterName", clusterName);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClusterName() {
		return getAttributeAsString("clusterName");
	}
	
	/**
	 * 
	 * @param multiregion
	 */
	public void setIsMultiregion(boolean multiregion) {
		setAttribute("multiregion", multiregion);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getIsMultiregion() {
		return getAttributeAsBoolean("multiregion");
	}
	
	/**
	 * 
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		setAttribute("startDate", startDate);		
	}
	
	/**
	 * 
	 * @return
	 */
	public Date getStartDate() {
		return getAttributeAsDate("startDate");
	}
	
	/**
	 * 
	 * @param numberOfInstances
	 */
	public void setNumberOfInstances(int numberOfInstances) {
		setAttribute("numberOfInstances", numberOfInstances);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfInstances() {
		return getAttributeAsInt("numberOfInstances");
	}
	
	/**
	 * 
	 * @param ip
	 */
	public void setIP(String ip) {
		setAttribute("ip", ip);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getIP() {
		return getAttributeAsString("ip");
	}
	
	/**
	 * 
	 * @param region
	 */
	public void setRegion(String region) {
		setAttribute("region", region);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getRegion() {
		return getAttributeAsString("region");
	}
	
	/**
	 * 
	 * @param provider
	 */
	public void setProvider(String provider) {
		setAttribute("provider", provider);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProvider() {
		return getAttributeAsString("provider");
	}	
	
	/**
	 * 
	 * @param mrIp
	 */
	public void setMrIP(String mrIp) {
		setAttribute("mrIp", mrIp);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMrIP() {
		return getAttributeAsString("mrIp");
	}
	
	/**
	 * 
	 * @param mrRegion
	 */
	public void setMrRegion(String mrRegion) {
		setAttribute("mrRegion", mrRegion);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMrRegion() {
		return getAttributeAsString("mrRegion");
	}
	
	/**
	 * 
	 * @param mrProvider
	 */
	public void setMrProvider(String mrProvider) {
		setAttribute("mrProvider", mrProvider);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMrProvider() {
		return getAttributeAsString("mrProvider");
	}
	
	/**
	 * 
	 * @param mrNumberOfInstances
	 */
	public void setMrNumberOfInstances(int mrNumberOfInstances) {
		setAttribute("mrNumberOfInstances", mrNumberOfInstances);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMrNumberOfInstances() {
		return getAttributeAsInt("mrNumberOfInstances");
	}
}
