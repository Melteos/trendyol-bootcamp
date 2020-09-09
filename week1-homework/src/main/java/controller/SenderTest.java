package controller;

import languages.EN;
import languages.Language;
import model.CompanyDTO;
import model.EmailDTO;
import model.PersonDTO;
import model.SmsDTO;
import model.offeredPackages.EmailFree;
import model.offeredPackages.EmailPackage;
import model.offeredPackages.SmsFixed;
import model.offeredPackages.SmsPackage;
import org.junit.jupiter.api.Test;
import service.EmailChannel;
import service.SmsChannel;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SenderTest {

    @Test
    public void it_should_buy_extra_free_email_package_when_quota_exceeded() {
        //Given
        CompanyDTO meltemsCompany = new CompanyDTO();
        meltemsCompany.setName("meltem");
        meltemsCompany.setSurname("suicmez");
        meltemsCompany.setEmail("ms@meltemcompany.com.tr");
        meltemsCompany.setPhone("05243234243");
        Language chosenLanguage = new EN();
        meltemsCompany.setLanguage(chosenLanguage);

        Seller seller = new Seller();
        EmailFree freeEmailPackage = new EmailFree();
        freeEmailPackage.setRemainingQuota(1);
        seller.sellEmailPackage(meltemsCompany,freeEmailPackage);

        EmailChannel emailSender = new EmailChannel();
        Sender sender = new Sender(emailSender,null);

        EmailDTO emailTo2 = new EmailDTO();
        emailTo2.setSender(meltemsCompany);
        PersonDTO receiver1 = new PersonDTO();
        PersonDTO receiver2 = new PersonDTO();
        receiver1.setName("ali"); receiver1.setSurname("bak"); receiver1.setEmail("ali@gmail.com"); receiver1.setPhone("05455445454");
        receiver2.setName("veli"); receiver1.setSurname("bak"); receiver1.setEmail("veli@gmail.com"); receiver1.setPhone("05455445455");
        emailTo2.addReceiver(receiver1); emailTo2.addReceiver(receiver2);
        emailTo2.setContent("first message from my company");

        //When
        sender.sendEmail(emailTo2);

        //Then
        assertEquals(freeEmailPackage.getMonthlyPrice()+freeEmailPackage.getNextEmailPrice(), emailTo2.getSender().getBoughtEmailPackage().getCurrentPrice());
        assertEquals(0,emailTo2.getSender().getBoughtEmailPackage().getRemainingQuota());
    }

    @Test
    public void it_should_buy_extra_fixed_sms_package_when_quota_exceeded() {
        //Given
        CompanyDTO meltemsCompany = new CompanyDTO();
        meltemsCompany.setName("meltem");
        meltemsCompany.setSurname("suicmez");
        meltemsCompany.setEmail("ms@meltemcompany.com.tr");
        meltemsCompany.setPhone("05243234243");
        Language chosenLanguage = new EN();
        meltemsCompany.setLanguage(chosenLanguage);

        Seller seller = new Seller();
        SmsFixed fixedSmsPackage = new SmsFixed();
        fixedSmsPackage.setRemainingQuota(1);
        seller.sellSmsPackage(meltemsCompany,fixedSmsPackage);

        SmsChannel smsSender = new SmsChannel();
        Sender sender = new Sender(null,smsSender);

        SmsDTO smsTo2 = new SmsDTO();
        smsTo2.setSender(meltemsCompany);
        PersonDTO receiver1 = new PersonDTO();
        PersonDTO receiver2 = new PersonDTO();
        receiver1.setName("ali"); receiver1.setSurname("bak"); receiver1.setEmail("ali@gmail.com"); receiver1.setPhone("05455445454");
        receiver2.setName("veli"); receiver1.setSurname("bak"); receiver1.setEmail("veli@gmail.com"); receiver1.setPhone("05455445455");
        smsTo2.addReceiver(receiver1); smsTo2.addReceiver(receiver2);
        smsTo2.setContent("first message from my company");

        //When
        sender.sendSms(smsTo2);

        //Then
        assertEquals(fixedSmsPackage.getMonthlyPrice()*2, smsTo2.getSender().getBoughtSmsPackage().getCurrentPrice());
        assertEquals(fixedSmsPackage.getOfferedQuota()-1,smsTo2.getSender().getBoughtSmsPackage().getRemainingQuota());
    }
}
