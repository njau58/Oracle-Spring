package org.spu.SendSms;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsEngine {

	@Autowired
	Properties prop;
	InputStream input = null;

	 @Autowired
	 AfricasTalkingGateway gateway;

	public String sendSms(String recipients, String message) {
		
		System.out.println("recipients 2>>>>>>>>>>>>>>"+recipients);
		System.out.println("message 2>>>>>>>>>>>>>>"+message);

		String filename = "application.properties";
		input = getClass().getClassLoader().getResourceAsStream(filename);
		try {
			prop.load(input);
		} catch (IOException e2) {

			e2.printStackTrace();
			return "SMS NOT Sent.  : Error loading application.properties " + e2.getMessage();
		}

		String AFRICASTALKING_USERNAME = prop.getProperty("AFRICASTALKING_USERNAME");
		String AFRICASTALKING_API_KEY = prop.getProperty("AFRICASTALKING_API_KEY");

		// Specify your login credentials
		String username = AFRICASTALKING_USERNAME;
		String apiKey = AFRICASTALKING_API_KEY;
		// NOTE: If connecting to the sandbox, please use your sandbox login
		// credentials
		// Specify the numbers that you want to send to in a comma-separated
		// list

		 gateway.set_username(username);
		 gateway.set_apiKey(apiKey);
		 gateway.set_environment("production");

		//AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
		

		// NOTE: If connecting to the sandbox, please add the sandbox flag to
		// the constructor:
		/*************************************************************************************
		 **** SANDBOX**** $gateway = new AfricasTalkingGateway(username, apiKey,
		 * "sandbox");
		 **************************************************************************************/

		// Thats it, hit send and we'll take care of the rest. Any errors will
		// be captured in the Exception class below
		try {
			JSONArray results = gateway.sendMessage(recipients, message);

			for (int i = 0; i < results.length(); ++i) {
				JSONObject result = results.getJSONObject(i);
				System.out.print(result.getString("status") + ","); 
				// status is either "Success" or "error message"
				System.out.print(result.getString("number") + ",");
				System.out.print(result.getString("messageId") + ",");
				System.out.println(result.getString("cost"));
			}
		}

		catch (Exception e) {
			return ("Encountered an error while sending " + e.getMessage());
		}

		return "success";

	}
}