import controller.Seller;
import controller.Sender;
import languages.*;
import model.*;
import model.offeredPackages.*;
import service.EmailChannel;
import service.SmsChannel;

public class Main {

    public static void main(String[] args) {

        CompanyDTO meltemsCompany = new CompanyDTO();
        meltemsCompany.setName("meltem");
        meltemsCompany.setSurname("suicmez");
        meltemsCompany.setEmail("ms@meltemcompany.com.tr");
        meltemsCompany.setPhone("05243234243");
        Language chosenLanguage = new EN();
        meltemsCompany.setLanguage(chosenLanguage); //Need to set this!

        Seller seller = new Seller();
        EmailPackage freeEmailPackage = new EmailFree();
        SmsPackage fixedSmsPackage = new SmsFixed();
        seller.sellEmailPackage(meltemsCompany,freeEmailPackage);
        seller.sellSmsPackage(meltemsCompany,fixedSmsPackage);

        EmailChannel emailSender = new EmailChannel();
        SmsChannel smsSender = new SmsChannel();
        Sender sender = new Sender(emailSender,smsSender);

        EmailDTO emailTo2 = new EmailDTO();
        emailTo2.setSender(meltemsCompany);
        PersonDTO receiver1 = new PersonDTO();
        PersonDTO receiver2 = new PersonDTO();
        receiver1.setName("ali"); receiver1.setSurname("bak"); receiver1.setEmail("ali@gmail.com"); receiver1.setPhone("05455445454");
        receiver2.setName("veli"); receiver1.setSurname("bak"); receiver1.setEmail("veli@gmail.com"); receiver1.setPhone("05455445455");
        emailTo2.addReceiver(receiver1); emailTo2.addReceiver(receiver2);
        emailTo2.setContent("first message from my company");

        sender.sendEmail(emailTo2);

    }
}
