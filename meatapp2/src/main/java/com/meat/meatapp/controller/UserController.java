package com.meat.meatapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangofactory.swagger.plugin.EnableSwagger;
import com.meat.model.Status;


@RestController
@EnableSwagger
public class UserController {

	Logger logger= LoggerFactory.getLogger(UserController.class);
	
	//unregister from push notification
	@RequestMapping(value="/meat/user/test" ,method=RequestMethod.GET,
			headers = "content-type=application/vnd.v0+json" ,produces={"application/json"})
	 public @ResponseBody ResponseEntity<Status>  unregisterForPushNotification(){
	        //active = 0 
		    String status = "test";
	        return new ResponseEntity<Status>(new Status(true,status),HttpStatus.OK);
	 }
	
		/*//getting the user preferences
		@RequestMapping(value="/sdpush/user/preferences" ,method=RequestMethod.GET,
				headers = "content-type=application/vnd.v0+json" ,produces={"application/json"})
		 public @ResponseBody ResponseEntity<UserPreferences> getUserPreferences(@RequestParam(value="deviceToken") String deviceId,
				 @RequestParam(value="sellerCode") String sellerCode){
		        //active = 0 
				String pref = userService.getUserPreferences(deviceId,sellerCode);
		        return new ResponseEntity<UserPreferences>(new UserPreferences(pref),HttpStatus.OK);
		 }
		*/


}
