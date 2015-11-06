package com.meat.service;



public interface UserService {
	
	//registering the user for push notification
	public String saveUserRegistration(UserRegistration userReg);
	public String doUserUnRegistration(UserRegistration userReg);
	public String getUserPreferences(String deviceId,String sellerCode);
	public String saveUserPreferences(UserPreferences userPref);
	public String updateUserPreferences(String deviceId, String sellerCode, String userPref);
}
