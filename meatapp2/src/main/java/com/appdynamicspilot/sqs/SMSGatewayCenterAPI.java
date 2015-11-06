package com.appdynamicspilot.sqs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import com.upcrob.springsecurity.otp.DefaultOtpGenerator;
public class SMSGatewayCenterAPI {
public static void main(String[] args){
try {
Date mydate = new
Date(System.currentTimeMillis());
DefaultOtpGenerator defaultOtpGenerator = new DefaultOtpGenerator(5);

String data = "";
data += "Mask=DEMOSG";
data += "&UserName=sunil9dbit"; // your loginId
data += "&Password=" + URLEncoder.encode("2qnzkuhV", "UTF-8"); // your password
data += "&Message=" + URLEncoder.encode("This is Sunil Sent to Chetan for OTP Testing" + mydate.toString()+"OTP "+defaultOtpGenerator.generateToken(), "UTF-8");
data += "&To=" + URLEncoder.encode("8147415215", "UTF-8"); // a valid 10 digit phone no.
data += "&v=1.1" ;
data += "&Type=Individual"; // Can be "Bulk" or "Group"
URL url = new
URL("http://www.smsgatewaycenter.com/library/send_sms_2.php?" + data);
HttpURLConnection conn = (HttpURLConnection)url.openConnection();
conn.setRequestMethod("GET");
conn.setDoOutput(true);
conn.setDoInput(true);
conn.setUseCaches(false);
conn.connect();
BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
String line;
StringBuffer buffer = new StringBuffer();
while ((line = rd.readLine()) != null){
buffer.append(line).append("\n");
}
System.out.println(buffer.toString());
rd.close();
conn.disconnect();
}
catch(Exception e){
e.printStackTrace();
}
}
}
