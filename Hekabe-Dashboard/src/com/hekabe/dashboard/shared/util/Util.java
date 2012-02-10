package com.hekabe.dashboard.shared.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.hekabe.dashboard.shared.IpExchange;
import com.hekabe.dashboard.shared.parameter.InstanceStatusParameter;

public class Util {
	
	/**
	 * Returns an ArrayList of IpExchange objects
	 * @param ipsString ips separated with ;
	 * @param passwordsString passwords separated with ;
	 * @return
	 */
	public static ArrayList<IpExchange> getIps(String ipsString, String passwordsString) {
		ArrayList<IpExchange> ipList = new ArrayList<IpExchange>();
		
		if(ipsString != null && passwordsString != null) {
			String[] ips = ipsString.split(";"); 
			String[] passwords = passwordsString.split(";");
			
			for(int i = 0; i < ips.length; i++) {
				ipList.add(new IpExchange(ips[i], passwords[i], InstanceStatusParameter.RUNNING));
			}
		}
		
		return ipList;
	}
	
	/**
	 * Return a HashMap<String,String> with 'ip' as key and 'password' as value
	 * @param ipList
	 * @param status status from InstanceStatusParameter. If status == null, every ip will be returned
	 * @return
	 */
	public static HashMap<String, String> getIpPasswordMap(ArrayList<IpExchange> ipList, String status) {			
		HashMap<String, String> ipPasswordMap = new HashMap<String, String>();
		
		for(IpExchange ipEx : ipList) {
			if(ipEx.getStatus().equals(status)) {
				ipPasswordMap.put(ipEx.getIp(), ipEx.getPassword());
			} else if(status == null) {
				ipPasswordMap.put(ipEx.getIp(), ipEx.getPassword());
			}
		}
		
		return ipPasswordMap;
	}
	
	/**
	 * Returns a string with ip's separated with ;
	 * @param ipList
	 * @return
	 */
	public static String getIpString(ArrayList<IpExchange> ipList) {
		StringBuilder sb = new StringBuilder();
		for(IpExchange ipEx : ipList) {
			sb.append(ipEx.getIp()).append(";");			
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	/**
	 * Returns a string with the status of the instances separated by ;
	 * @param ipList
	 * @return
	 */
	public static String getStatusString(ArrayList<IpExchange> ipList) {
		StringBuilder sb = new StringBuilder();
		for(IpExchange ipEx : ipList) {
			sb.append(ipEx.getStatus()).append(";");			
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
