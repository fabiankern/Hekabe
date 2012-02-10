package com.hekabe.dashboard.shared;


import java.io.Serializable;

public class IpExchange implements Serializable {
	
	private static final long serialVersionUID = 2L;
	String ip;
	String password;
	String status;
	
	/**
	 * 
	 * @param ip
	 * @param password
	 * @param status
	 */
	public IpExchange(String ip, String password, String status) {
		this.ip = ip;
		this.password = password;
		this.status = status;
	}
	
	/**
	 * 
	 */
	public IpExchange() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
