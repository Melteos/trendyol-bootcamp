package com.trendyol.service;

import com.trendyol.enums.ErrorMessage;
import com.trendyol.enums.LanguageEnum;
import com.trendyol.exception.InformationSendException;
import com.trendyol.model.BaseInformationDTO;

public class SmsInformationServiceImpl implements InformationService {

	private int MAX_SMS_COUNT;

	public SmsInformationServiceImpl(int MAX_SMS_COUNT) {
		this.MAX_SMS_COUNT = MAX_SMS_COUNT;
	}


	private int getMAX_SMS_COUNT() {
		return MAX_SMS_COUNT;
	}

	@Override
	public void sendInformation(BaseInformationDTO baseInformationDTO) {
		System.out.println("Sending sms to " + baseInformationDTO.getReceiver().getFullName());
		baseInformationDTO.getSender().incSmsCnt();
	}

	@Override
	public boolean validate(BaseInformationDTO baseInformationDTO) throws InformationSendException {
		if(baseInformationDTO.getSender().getSmsCount() < getMAX_SMS_COUNT()){
			return true;
		}
		if (baseInformationDTO.getSender().getUserLanguage().equals(LanguageEnum.EN)) {
			throw new InformationSendException(ErrorMessage.Sms_Error_En.getMessage());
		} else if (baseInformationDTO.getSender().getUserLanguage().equals(LanguageEnum.TR)) {
			throw new InformationSendException(ErrorMessage.Sms_Error_Tr.getMessage());
		}
		return false;
	}
}
