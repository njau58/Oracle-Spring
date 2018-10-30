package org.spu.SendSms;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/spu-fullstack/Pharmacy")
public class SmsRestController {

	@Autowired
	SmsEngine smsEngine;

	@RequestMapping(method = { RequestMethod.POST }, value = "/sendSms")
	public String sendSms(@RequestParam String message, @RequestParam String recipients) {

		String success = smsEngine.sendSms(recipients, message);

		if (success == null)
			return "SMS NOT Sent";
		else if (success.equalsIgnoreCase("success"))
			return "SMS(S) Sent Succesfully to:  " + recipients;
		else
			return success;

	}

}
