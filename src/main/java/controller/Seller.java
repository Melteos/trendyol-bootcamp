package controller;

import model.CompanyDTO;
import model.offeredPackages.EmailPackage;
import model.offeredPackages.SmsPackage;

public class Seller {

    public void sellSmsPackage(CompanyDTO to, SmsPackage smsPackage) {
        try{
            to.setBoughtSmsPackage(smsPackage);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void sellEmailPackage(CompanyDTO to, EmailPackage emailPackage) {
        try {
            to.setBoughtEmailPackage(emailPackage);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
