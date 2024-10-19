package model.offeredPackages;

import enums.PackageTypes;
import service.PackageExtension;

public class SmsFixed extends SmsPackage implements PackageExtension {

    public SmsFixed() {
        this.offeredQuota = 1000;
        this.monthlyPrice = 20;
        this.setType(PackageTypes.smsFixed);
        setCurrentPrice(getMonthlyPrice());
        setRemainingQuota(getOfferedQuota());
    }

    public void increaseQuota(int extraQuota) {
        do {
            setRemainingQuota(getRemainingQuota()+getOfferedQuota());
            setCurrentPrice(getCurrentPrice()+getMonthlyPrice());
            extraQuota-=getOfferedQuota();
        }
        while(extraQuota>=getOfferedQuota());
    }
}
