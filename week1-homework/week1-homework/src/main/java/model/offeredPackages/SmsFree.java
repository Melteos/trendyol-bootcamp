package model.offeredPackages;

import enums.PackageTypes;
import service.PackageExtension;

public class SmsFree extends SmsPackage implements PackageExtension {

    private double nextSmsPrice = 0.10;

    private double getNextSmsPrice() {
        return nextSmsPrice;
    }

    public SmsFree() {
        this.offeredQuota = 2000;
        this.monthlyPrice = 30;
        this.setType(PackageTypes.smsFree);
        setCurrentPrice(getMonthlyPrice());
        setRemainingQuota(getOfferedQuota());
    }

    public void increaseQuota(int extraQuota) {
        setRemainingQuota(getRemainingQuota()+extraQuota);
        setCurrentPrice(getCurrentPrice()+ getNextSmsPrice()*extraQuota);
    }

    public void setNextSmsPrice(double nextSmsPrice) {
        this.nextSmsPrice = nextSmsPrice;
    }
}
