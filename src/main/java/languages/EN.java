package languages;

public class EN implements Language {

    public String onBlackList() {
        return "You haven't made payment for 2 months. You can not send notifications.";
    }

    @Override
    public String notEnoughQuota() {
        return "You do not have enough quota.";
    }

    @Override
    public String buyingAdditionalPackage() {
        return "Buying additional package...";
    }

    @Override
    public String cantBuySecondEmailPackage() {
        return "You can not buy another Email package when you bought one already.";
    }

    @Override
    public String cantBuySecondSmsPackage() {
        return "You can not buy another Sms package when you bought one already.";
    }
}
