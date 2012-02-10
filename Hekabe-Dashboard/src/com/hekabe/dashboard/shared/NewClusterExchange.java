package com.hekabe.dashboard.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class NewClusterExchange implements Serializable {
	private static final long serialVersionUID = 2L;

	String username;
	long timestamp;
	/*
	 * NewClusterHardwareiew
	 */
	String provider;
	String accessKey;
	String secretAccessKey;
	ArrayList<IpExchange> ips = new ArrayList<IpExchange>();
	String login;
	String pass;
	int numberOfInstances;
	String region;
	String instanceSize;	
	boolean multiRegionCluster;
	String mrProvider;
	String mrAccessKey;
	String mrSecretAccessKey;
	ArrayList<IpExchange> mrIps = new ArrayList<IpExchange>();
	String mrLogin;
	String mrPass;
	int mrNumberOfInstances;
	String mrRegion;
	String mrInstanceSize;	
	
	/*
	 * NewClusterCassandraView
	 */
	String clusterName;
	String cassandraVersion;
	String partitioner;
	
	/*
	 * NewClusterCassandraConfig
	 */
	boolean hintedHandoff;
	int maxWindowTime;
	int throttleDelay;
	String syncType;
	int timeWindow;
	int commitlogTotalSpace;
	float reduceCacheAt;
	float reduceCacheCapacity;
	int concurrentReads;
	int concurrentWrites;
	int memtableTotalSpace;
	int memtableWriterThreads;
	float flushFraction;
	
	/*
	 * Benchmarking
	 */
	boolean isPreparedForBenchmarking;
	
	/**
	 * Standard constructor
	 */
	public NewClusterExchange() {
		
	}

	/**
	 * 
	 * @return timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * 
	 * @param provider
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * 
	 * @return
	 */
	public String getAccessKey() {
		return accessKey;
	}

	/**
	 * 
	 * @param accessKey
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	/**
	 * 
	 * @return
	 */
	public String getSecretAccessKey() {
		return secretAccessKey;
	}

	/**
	 * 
	 * @param secretAccessKey
	 */
	public void setSecretAccessKey(String secretAccessKey) {
		this.secretAccessKey = secretAccessKey;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<IpExchange> getIps() {
		return ips;
	}

	/**
	 * 
	 * @param ips
	 */
	public void setIps(ArrayList<IpExchange> ips) {
		this.ips = ips;
	}

	/**
	 * 
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * 
	 * @return
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * 
	 * @param pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumberOfInstances() {
		return numberOfInstances;
	}

	/**
	 * 
	 * @param numberOfInstances
	 */
	public void setNumberOfInstances(int numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}

	/**
	 * 
	 * @return
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * 
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * 
	 * @return
	 */
	public String getInstanceSize() {
		return instanceSize;
	}

	/**
	 * 
	 * @param instanceSize
	 */
	public void setInstanceSize(String instanceSize) {
		this.instanceSize = instanceSize;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isMultiRegionCluster() {
		return multiRegionCluster;
	}

	/**
	 * 
	 * @param multiRegionCluster
	 */
	public void setMultiRegionCluster(boolean multiRegionCluster) {
		this.multiRegionCluster = multiRegionCluster;
	}

	/**
	 * 
	 * @return
	 */
	public String getMrProvider() {
		return mrProvider;
	}

	/**
	 * 
	 * @param mrProvider
	 */
	public void setMrProvider(String mrProvider) {
		this.mrProvider = mrProvider;
	}

	/**
	 * 
	 * @return
	 */
	public String getMrAccessKey() {
		return mrAccessKey;
	}

	/**
	 * 
	 * @param mrAccessKey
	 */
	public void setMrAccessKey(String mrAccessKey) {
		this.mrAccessKey = mrAccessKey;
	}

	/**
	 * 
	 * @return
	 */
	public String getMrSecretAccessKey() {
		return mrSecretAccessKey;
	}

	/**
	 * 
	 * @param mrSecretAccessKey
	 */
	public void setMrSecretAccessKey(String mrSecretAccessKey) {
		this.mrSecretAccessKey = mrSecretAccessKey;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<IpExchange> getMrIps() {
		return mrIps;
	}

	/**
	 * 
	 * @param mrIps
	 */
	public void setMrIps(ArrayList<IpExchange> mrIps) {
		this.mrIps = mrIps;
	}

	/**
	 * 
	 * @return
	 */
	public String getMrLogin() {
		return mrLogin;
	}

	/**
	 * 
	 * @param mrLogin
	 */
	public void setMrLogin(String mrLogin) {
		this.mrLogin = mrLogin;
	}

	/**
	 * 
	 * @return
	 */
	public String getMrPass() {
		return mrPass;
	}

	/**
	 * 
	 * @param mrPass
	 */
	public void setMrPass(String mrPass) {
		this.mrPass = mrPass;
	}

	/**
	 * 
	 * @return
	 */
	public int getMrNumberOfInstances() {
		return mrNumberOfInstances;
	}

	/**
	 * 
	 * @param mrNumberOfInstances
	 */
	public void setMrNumberOfInstances(int mrNumberOfInstances) {
		this.mrNumberOfInstances = mrNumberOfInstances;
	}

	/**
	 * 
	 * @return
	 */
	public String getMrRegion() {
		return mrRegion;
	}

	/**
	 * 
	 * @param mrRegion
	 */
	public void setMrRegion(String mrRegion) {
		this.mrRegion = mrRegion;
	}

	/**
	 * 
	 * @return
	 */
	public String getMrInstanceSize() {
		return mrInstanceSize;
	}

	/**
	 * 
	 * @param mrInstanceSize
	 */
	public void setMrInstanceSize(String mrInstanceSize) {
		this.mrInstanceSize = mrInstanceSize;
	}

	/**
	 * 
	 * @return
	 */
	public String getClusterName() {
		return clusterName;
	}

	/**
	 * 
	 * @param clusterName
	 */
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	/**
	 * 
	 * @return
	 */
	public String getCassandraVersion() {
		return cassandraVersion;
	}

	/**
	 * 
	 * @param cassandraVersion
	 */
	public void setCassandraVersion(String cassandraVersion) {
		this.cassandraVersion = cassandraVersion;
	}

	/**
	 * 
	 * @return
	 */
	public String getPartitioner() {
		return partitioner;
	}

	/**
	 * 
	 * @param partitioner
	 */
	public void setPartitioner(String partitioner) {
		this.partitioner = partitioner;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isHintedHandoff() {
		return hintedHandoff;
	}

	/**
	 * 
	 * @param hintedHandoff
	 */
	public void setHintedHandoff(boolean hintedHandoff) {
		this.hintedHandoff = hintedHandoff;
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxWindowTime() {
		return maxWindowTime;
	}

	/**
	 * 
	 * @param maxWindowTime
	 */
	public void setMaxWindowTime(int maxWindowTime) {
		this.maxWindowTime = maxWindowTime;
	}

	/**
	 * 
	 * @return
	 */
	public int getThrottleDelay() {
		return throttleDelay;
	}

	/**
	 * 
	 * @param throttleDelay
	 */
	public void setThrottleDelay(int throttleDelay) {
		this.throttleDelay = throttleDelay;
	}

	/**
	 * 
	 * @return
	 */
	public String getSyncType() {
		return syncType;
	}

	/**
	 * 
	 * @param syncType
	 */
	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}

	/**
	 * 
	 * @return
	 */
	public int getTimeWindow() {
		return timeWindow;
	}

	/**
	 * 
	 * @param timeWindow
	 */
	public void setTimeWindow(int timeWindow) {
		this.timeWindow = timeWindow;
	}

	/**
	 * 
	 * @return
	 */
	public int getCommitlogTotalSpace() {
		return commitlogTotalSpace;
	}

	/**
	 * 
	 * @param commitlogTotalSpace
	 */
	public void setCommitlogTotalSpace(int commitlogTotalSpace) {
		this.commitlogTotalSpace = commitlogTotalSpace;
	}

	/**
	 * 
	 * @return
	 */
	public float getReduceCacheAt() {
		return reduceCacheAt;
	}

	/**
	 * 
	 * @param reduceCacheAt
	 */
	public void setReduceCacheAt(float reduceCacheAt) {
		this.reduceCacheAt = reduceCacheAt;
	}

	/**
	 * 
	 * @return
	 */
	public float getReduceCacheCapacity() {
		return reduceCacheCapacity;
	}

	/**
	 * 
	 * @param reduceCacheCapacity
	 */
	public void setReduceCacheCapacity(float reduceCacheCapacity) {
		this.reduceCacheCapacity = reduceCacheCapacity;
	}

	/**
	 * 
	 * @return
	 */
	public int getConcurrentReads() {
		return concurrentReads;
	}

	/**
	 * 
	 * @param concurrentReads
	 */
	public void setConcurrentReads(int concurrentReads) {
		this.concurrentReads = concurrentReads;
	}

	/**
	 * 
	 * @return
	 */
	public int getConcurrentWrites() {
		return concurrentWrites;
	}

	/**
	 * 
	 * @param concurrentWrites
	 */
	public void setConcurrentWrites(int concurrentWrites) {
		this.concurrentWrites = concurrentWrites;
	}

	/**
	 * 
	 * @return
	 */
	public int getMemtableTotalSpace() {
		return memtableTotalSpace;
	}

	/**
	 * 
	 * @param memtableTotalSpace
	 */
	public void setMemtableTotalSpace(int memtableTotalSpace) {
		this.memtableTotalSpace = memtableTotalSpace;
	}

	/**
	 * 
	 * @return
	 */
	public int getMemtableWriterThreads() {
		return memtableWriterThreads;
	}

	/**
	 * 
	 * @param memtableWriterThreads
	 */
	public void setMemtableWriterThreads(int memtableWriterThreads) {
		this.memtableWriterThreads = memtableWriterThreads;
	}

	/**
	 * 
	 * @return
	 */
	public float getFlushFraction() {
		return flushFraction;
	}

	/**
	 * 
	 * @param flushFraction
	 */
	public void setFlushFraction(float flushFraction) {
		this.flushFraction = flushFraction;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPreparedForBenchmarking() {
		return isPreparedForBenchmarking;
	}

	/**
	 * 
	 * @param isPreparedForBenchmarking
	 */
	public void setPreparedForBenchmarking(boolean isPreparedForBenchmarking) {
		this.isPreparedForBenchmarking = isPreparedForBenchmarking;
	}
	
}
