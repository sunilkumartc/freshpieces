package com.upcrob.springsecurity.otp;

public class Testmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 DefaultOtpGenerator defaultOtpGenerator = new DefaultOtpGenerator(5);
 System.out.println("DefaultOtpGenerator"+defaultOtpGenerator.generateToken());
	}

}
