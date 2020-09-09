package service;

import controller.Seller;
import controller.Sender;
import exceptions.CompanyIsOnBlockListException;
import exceptions.EmailPackageAlreadyExists;
import languages.EN;
import languages.Language;
import languages.TR;
import model.CompanyDTO;
import model.EmailDTO;
import model.PersonDTO;
import model.offeredPackages.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class EmailChannelTest {

    @Test
    public void it_should_throw_exception_when_company_is_on_black_list(){
        //Given
        CompanyDTO meltemsCompany = new CompanyDTO();
        meltemsCompany.setName("meltem");
        meltemsCompany.setSurname("suicmez");
        meltemsCompany.setEmail("ms@meltemcompany.com.tr");
        meltemsCompany.setPhone("05243234243");
        meltemsCompany.setNotPaidSinceMonths(3);
        meltemsCompany.setLanguage(new EN());

        Seller seller = new Seller();
        EmailPackage freeEmailPackage = new EmailFree();
        seller.sellEmailPackage(meltemsCompany,freeEmailPackage);

        EmailChannel emailSender = new EmailChannel();

        EmailDTO email = new EmailDTO();
        email.setSender(meltemsCompany);
        PersonDTO receiver = new PersonDTO();
        receiver.setName("ali"); receiver.setSurname("bak"); receiver.setEmail("ali@gmail.com"); receiver.setPhone("05455445454");
        email.addReceiver(receiver);
        email.setContent("first message from my company");

        //When
        Throwable throwable = catchThrowable( ()-> emailSender.canSendNotification(email));

        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(CompanyIsOnBlockListException.class);
        assertThat(throwable).hasMessageContaining(meltemsCompany.getLanguage().onBlackList());
    }

    @Test
    public void it_should_throw_exception_with_turkish_message() {
        //Given
        CompanyDTO meltemsCompany = new CompanyDTO();
        meltemsCompany.setName("meltem");
        meltemsCompany.setSurname("suicmez");
        meltemsCompany.setEmail("ms@meltemcompany.com.tr");
        meltemsCompany.setPhone("05243234243");
        meltemsCompany.setNotPaidSinceMonths(3);
        meltemsCompany.setLanguage(new TR());

        Seller seller = new Seller();
        EmailPackage freeEmailPackage = new EmailFree();
        seller.sellEmailPackage(meltemsCompany,freeEmailPackage);

        EmailChannel emailSender = new EmailChannel();

        EmailDTO email = new EmailDTO();
        email.setSender(meltemsCompany);
        PersonDTO receiver = new PersonDTO();
        receiver.setName("ali"); receiver.setSurname("bak"); receiver.setEmail("ali@gmail.com"); receiver.setPhone("05455445454");
        email.addReceiver(receiver);
        email.setContent("first message from my company");

        //When
        Throwable throwable = catchThrowable( ()-> emailSender.canSendNotification(email));

        //Then
        assertThat(throwable).hasMessageContaining(new TR().onBlackList());
    }
}
