package model.offeredPackages;

import enums.PackageTypes;

public class EmailFree extends EmailPackage {

    private double nextEmailPrice = 0.03;

    public EmailFree() {
        this.offeredQuota = 2000;
        this.monthlyPrice = 7.5;
        setType(PackageTypes.emailFree);
        setCurrentPrice(getMonthlyPrice());
        setRemainingQuota(getOfferedQuota());
    }

    public void increaseQuota(int extraQuota) {
        setRemainingQuota(getRemainingQuota()+extraQuota);
        setCurrentPrice(getCurrentPrice()+getNextEmailPrice()*extraQuota);
    }

    public double getNextEmailPrice() {
        return nextEmailPrice;
    }


    public void setNextEmailPrice(double nextEmailPrice) {
        this.nextEmailPrice = nextEmailPrice;
    }
}
