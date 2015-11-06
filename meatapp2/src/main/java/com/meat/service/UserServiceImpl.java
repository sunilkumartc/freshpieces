package com.meat.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Configuration
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Resource
	private Environment environment;
	
	@Override
	public String doUserUnRegistration(UserRegistration userReg) {
		String status;
		try{
//			String query = "update sn_register SET active=0 where device_id=? and seller_code=?";
			jdbcTemplate.update(environment.getProperty("sql.doUserUnRegistration"),new Object[]{userReg.getDeviceToken(),userReg.getSellerCode()});
			status = "USER UNREGISTERED SUCCESSFULLY";
		}catch(Exception ex){
			System.out.println(ex);
			status="FAILED IN USER UNREGISTRATION";
		}
		
		return status;
	}


	@Override
	public String saveUserRegistration(UserRegistration userReg) {
		String status;
		System.out.println(userReg.toString());
		try{
			//insert if not exists, else update active = 1 
//			String query = "insert into sn_register(device_id,seller_code,device_platform,app_version,platform_endpoint_arn) VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE active=1";
			
			jdbcTemplate.update(environment.getProperty("sql.saveUserRegistration"),new Object[]{userReg.getDeviceToken(),userReg.getSellerCode(),"toString()",userReg.getAppVersion(),
					userReg.getPlatformEndpointArn()});
			
			System.out.println("deviceId:"+userReg.getDeviceToken());
			status = "USER REGISTERED SUCCESSFULLY";
			
		}
		catch(Exception ex){
			System.out.println(ex);
			status="FAILED IN USER REGISTRATION";
		}
		return status;
	}


	@Override
	public String getUserPreferences(String deviceId,String sellerCode) {
		String pref = null;
		try{
			//String query = "select user_pref as userPref from sn_user_pref where device_id=? and seller_code=?";
			pref = jdbcTemplate.queryForObject(environment.getProperty("sql.getUserPreferences"),new Object[]{deviceId,sellerCode}, String.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return pref;
	}


	@Override
	public String saveUserPreferences(UserPreferences userPref) {
		String status;
		try{
			//String query = "insert into sn_user_pref(device_id,seller_code) values(?,?)";
			jdbcTemplate.update(environment.getProperty("sql.saveUserPreferences"),new Object[]{userPref.getDeviceId(),userPref.getSellerCode()});
			
			status = "USER PREFERENCES SAVED SUCCESSFULLY";
			
		}catch(Exception e){
			e.printStackTrace();
			status = "FAILED IN SAVING USER PREFERENCES";
		}
		
		return status;
		
	}


	@Override
	public String updateUserPreferences(String deviceId, String sellerCode,String userPref) {
		String status;
		
		try{
			//String query = "update sn_user_pref set user_pref=? WHERE device_id=? and seller_code=?";
			jdbcTemplate.update(environment.getProperty("sql.updateUserPreferences"),new Object[]{userPref,deviceId,sellerCode});
			
			status = "USER PREFERENCES UPDATED SUCCESSFULLY";
			
		}catch(Exception e){
			e.printStackTrace();
			status = "FAILED IN SAVING USER PREFERENCES";
		}
		return status;
	}

}
