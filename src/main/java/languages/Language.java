package languages;

public interface Language {
    String onBlackList();

    String notEnoughQuota();

    String buyingAdditionalPackage();

    String cantBuySecondEmailPackage();

    String cantBuySecondSmsPackage();
}
