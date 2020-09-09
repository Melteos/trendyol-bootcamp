package model;

import exceptions.EmailPackageAlreadyExists;
import exceptions.SmsPackageAlreadyExists;
import languages.Language;
import model.offeredPackages.EmailPackage;
import model.offeredPackages.SmsPackage;

public class CompanyDTO extends PersonDTO {

    private int notPaidSinceMonths;
    private SmsPackage boughtSmsPackage;
    private EmailPackage boughtEmailPackage;
    private boolean isOnBlackList;
    private Language language;

    public int getNotPaidSinceMonths() {
        return notPaidSinceMonths;
    }

    public void setNotPaidSinceMonths(int notPaidSinceMonths) {
        this.notPaidSinceMonths = notPaidSinceMonths;
    }

    public SmsPackage getBoughtSmsPackage() { return boughtSmsPackage; }

    public void setBoughtSmsPackage(SmsPackage boughtSmsPackage) throws SmsPackageAlreadyExists {
        if(this.boughtSmsPackage!=null)
            throw new SmsPackageAlreadyExists(language.cantBuySecondSmsPackage());
        else
            this.boughtSmsPackage = boughtSmsPackage;
    }

    public EmailPackage getBoughtEmailPackage() { return boughtEmailPackage; }

    public void setBoughtEmailPackage(EmailPackage boughtEmailPackage) throws EmailPackageAlreadyExists {
        if(this.boughtEmailPackage!=null)
            throw new EmailPackageAlreadyExists(language.cantBuySecondEmailPackage());
        else
            this.boughtEmailPackage = boughtEmailPackage;
    }

    public boolean isOnBlackList() {
        return notPaidSinceMonths>=2;
    }

    public void setOnBlackList(boolean onBlackList) {
        isOnBlackList = onBlackList;
    }

    public Language getLanguage() { return language; }

    public void setLanguage(Language language) { this.language = language; }
}
