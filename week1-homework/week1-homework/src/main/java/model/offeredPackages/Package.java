package model.offeredPackages;

import enums.PackageTypes;

public abstract class Package {

    private PackageTypes type;
    protected int offeredQuota;
    protected double monthlyPrice;

    public int getOfferedQuota() { return offeredQuota; }

    public double getMonthlyPrice() { return monthlyPrice; }

    private int remainingQuota;
    private double currentPrice;

    public PackageTypes getType() { return this.type; }

    public void setType(PackageTypes type) { this.type = type; }

    public int getRemainingQuota() { return remainingQuota; }

    public void setRemainingQuota(int remainingQuota) { this.remainingQuota = remainingQuota; }

    public double getCurrentPrice() { return currentPrice; }

    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }
}
