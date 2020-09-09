package model.offeredPackages;

import enums.PackageTypes;
import service.PackageExtension;

public class EmailFixed extends EmailPackage implements PackageExtension {

    public EmailFixed() {
        this.offeredQuota = 1000;
        this.monthlyPrice = 10;
        this.setType(PackageTypes.emailFixed);
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
