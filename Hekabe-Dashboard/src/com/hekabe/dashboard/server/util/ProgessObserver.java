package com.hekabe.dashboard.server.util;

public class ProgessObserver {
	private String status;
	private String progress;
	private boolean hasChanged;
	
	/**
	 * 
	 */
	public ProgessObserver() {
		this.progress = "0";
		this.status = "";
		this.hasChanged = false;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatus() {
		this.hasChanged = false;
		return status;
	}
	
	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
		this.hasChanged = true;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasChanged() {
		return hasChanged;
	}

	/**
	 * 
	 * @param hasChanged
	 */
	public void setHasChanged(boolean hasChanged) {
		this.hasChanged = hasChanged;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProgress() {
		return progress;
	}
	
	/**
	 * 
	 * @param progress
	 */
	public void setProgress(int progress) {
		this.progress = String.valueOf(progress);
	}
}
