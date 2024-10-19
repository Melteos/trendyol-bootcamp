package com.trendyol.service;

import com.trendyol.controller.InformationController;
import com.trendyol.exception.InformationSendException;
import com.trendyol.model.EmailDTO;
import static org.assertj.core.api.Assertions.catchThrowable;
import com.trendyol.model.SmsDTO;
import com.trendyol.model.UserDTO;
import org.junit.Test;

public class InformationServiceTest {

    @Test
    public void it_should_throw_exception_when_exceeded_max_email() {
        //Given
        InformationService emailSender = new EmailInformationServiceImpl(10);

        InformationController informationController = new InformationController();

        UserDTO sender = new UserDTO("ali", "ali@gmail.com", "5051403166", 0, emailSender.getMAX_EMAIL_COUNT());
        UserDTO receiver = new UserDTO("veli", "veli@gmail.com", "5051403167", 0, 0);

        String content = "Email content";
        EmailDTO emailDTO = new EmailDTO(sender, receiver, content);

        informationController.setEmailService(emailSender);
        informationController.sendEmail(emailDTO);

        //When
        Throwable throwable = catchThrowable(() -> emailSender.validate(emailDTO));

        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(InformationSendException.class);
        assertThat(throwable).hasMessageContaining("Email validation error occured");
    }

    @Test
    public void it_should_throw_exception_when_exceeded_max_sms() {
        //Given
        InformationService emailSender = new EmailInformationServiceImpl(10);

        InformationController informationController = new InformationController();

        UserDTO sender = new UserDTO("ali", "ali@gmail.com", "5051403166", 0, emailSender.getMAX_EMAIL_COUNT());
        UserDTO receiver = new UserDTO("veli", "veli@gmail.com", "5051403167", 0, 0);

        String content = "Sms content";
        EmailDTO emailDTO = new EmailDTO(sender, receiver, content);

        informationController.setEmailService(emailSender);
        informationController.sendEmail(emailDTO);

        //When
        Throwable throwable = catchThrowable(() -> emailSender.validate(emailDTO));

        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(InformationSendException.class);
        assertThat(throwable).hasMessageContaining("Email validation error occured");
    }




}
