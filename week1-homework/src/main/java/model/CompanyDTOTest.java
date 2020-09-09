package model;

import exceptions.EmailPackageAlreadyExists;
import exceptions.SmsPackageAlreadyExists;
import languages.EN;
import languages.Language;
import model.offeredPackages.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class CompanyDTOTest {

    @Test
    public void it_should_return_in_black_list() {
        //Given
        CompanyDTO meltemsCompany = new CompanyDTO();
        meltemsCompany.setName("meltem");
        meltemsCompany.setSurname("suicmez");
        meltemsCompany.setEmail("ms@meltemcompany.com.tr");
        meltemsCompany.setPhone("05243234243");

        //When
        meltemsCompany.setNotPaidSinceMonths(5);

        //Then
        assertTrue(meltemsCompany.isOnBlackList());
    }

    @Test
    public void it_should_throw_exception_when_buying_second_email_package() {
        //Given
        CompanyDTO meltemsCompany = new CompanyDTO();
        meltemsCompany.setName("meltem");
        meltemsCompany.setSurname("suicmez");
        meltemsCompany.setEmail("ms@meltemcompany.com.tr");
        meltemsCompany.setPhone("05243234243");
        meltemsCompany.setLanguage(new EN());
        EmailPackage freeEmailPackage = new EmailFree();
        meltemsCompany.setBoughtEmailPackage(freeEmailPackage);

        //When
        EmailPackage fixedEmailPackage = new EmailFixed();
        Throwable throwable = catchThrowable( ()-> meltemsCompany.setBoughtEmailPackage(fixedEmailPackage));

        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(EmailPackageAlreadyExists.class);
        assertThat(throwable).hasMessageContaining(meltemsCompany.getLanguage().cantBuySecondEmailPackage());
    }

    @Test
    public void it_should_throw_exception_when_buying_second_sms_package() {
        //Given
        CompanyDTO meltemsCompany = new CompanyDTO();
        meltemsCompany.setName("meltem");
        meltemsCompany.setSurname("suicmez");
        meltemsCompany.setEmail("ms@meltemcompany.com.tr");
        meltemsCompany.setPhone("05243234243");
        meltemsCompany.setLanguage(new EN());
        SmsPackage freeSmsPackage = new SmsFree();
        meltemsCompany.setBoughtSmsPackage(freeSmsPackage);

        //When
        SmsPackage fixedSmsPackage = new SmsFixed();
        Throwable throwable = catchThrowable( ()-> meltemsCompany.setBoughtSmsPackage(fixedSmsPackage));

        //Then
        assertThat(throwable).isNotNull();
        assertThat(throwable).isInstanceOf(SmsPackageAlreadyExists.class);
        assertThat(throwable).hasMessageContaining(meltemsCompany.getLanguage().cantBuySecondSmsPackage());
    }
}
