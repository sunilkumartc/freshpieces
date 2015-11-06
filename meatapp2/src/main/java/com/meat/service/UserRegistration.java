
package com.meat.service;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class UserRegistration implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 995484679930186854L;

	@JsonProperty("deviceToken")
	private String deviceToken;
	
	
	@JsonProperty("sellerCode")
	private String sellerCode;
	
	@JsonProperty("appVersion")
	private String appVersion;
	
	@JsonProperty("endpointArn")
	private String platformEndpointArn;
	
	
	public UserRegistration(){
		
	}
	
	public String getPlatformEndpointArn() {
		return platformEndpointArn;
	}
	public void setPlatformEndpointArn(String platformEndpointArn) {
		this.platformEndpointArn = platformEndpointArn;
	}

	public String getDeviceToken() {
		return deviceToken;
	}
	
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	

	public String getSellerCode() {
		return sellerCode;
	}
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	
	public String getAppVersion() {
		return appVersion;
	}
	
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	

}
