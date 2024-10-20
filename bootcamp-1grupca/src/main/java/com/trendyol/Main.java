package com.trendyol;

import com.trendyol.enums.LanguageEnum;
import com.trendyol.model.EmailDTO;
import com.trendyol.model.SmsDTO;
import com.trendyol.model.UserDTO;
import com.trendyol.service.EmailInformationServiceImpl;
import com.trendyol.service.InformationService;
import com.trendyol.controller.InformationController;
import com.trendyol.service.SmsInformationServiceImpl;

public class Main {

	public static void main(String[] args) {
		// write your code here

		InformationService emailSender = new EmailInformationServiceImpl(10);
		InformationService smsSender = new SmsInformationServiceImpl(10);

		InformationController informationController = new InformationController();

		UserDTO sender = new UserDTO("ali", "ali@gmail.com", "5051403166", 0, 0, LanguageEnum.TR);
		UserDTO receiver = new UserDTO("veli", "veli@gmail.com", "5051403167", 0, 0,LanguageEnum.TR);

		String content = "Sms content";
		SmsDTO smsDTO = new SmsDTO(sender, receiver, content);
		EmailDTO emailDTO = new EmailDTO(sender, receiver, content);

		informationController.setSmsService(smsSender);
		for(int i = 0; i < 12; i++){
			informationController.sendSms(smsDTO);
		}
		informationController.setEmailService(emailSender);
		for(int i = 0; i < 12; i++){
			informationController.sendEmail(emailDTO);
		}

	}
}
