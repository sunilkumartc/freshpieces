
package com.meat.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class UserPreferences implements Serializable {
	
	/**
	 * Generated Serial Id
	 */
	private static final long serialVersionUID = -3333046673200530137L;

	@JsonProperty("deviceId")
	private String deviceId;
	
	@JsonProperty("sellerCode")
	private String sellerCode;
	
	public UserPreferences(){
		
	}
	
	public UserPreferences(String userPref) {
		this.userPref = userPref;
	}
	
	public UserPreferences(String deviceId, String sellerCode, String userPref,
			String userPrefTimings) {
		super();
		this.deviceId = deviceId;
		this.sellerCode = sellerCode;
		this.userPref = userPref;
		this.userPrefTimings = userPrefTimings;
	}
	
	

	@JsonProperty("userPref")
	private String userPref;
	
	@JsonProperty("userPrefTimings")
	private String userPrefTimings;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String getUserPref() {
		return userPref;
	}

	public void setUserPref(String userPref) {
		this.userPref = userPref;
	}

	public String getUserPrefTimings() {
		return userPrefTimings;
	}

	public void setUserPrefTimings(String userPrefTimings) {
		this.userPrefTimings = userPrefTimings;
	}

	@Override
	public String toString() {
		return "UserPreferences [deviceId=" + deviceId + ", sellerCode="
				+ sellerCode + ", userPref=" + userPref + ", userPrefTimings="
				+ userPrefTimings + "]";
	}
}
