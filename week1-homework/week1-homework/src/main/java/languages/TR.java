package languages;

public class TR implements Language{

    public String onBlackList() {
        return "2 aydan fazla ödeme yapmadığınız için kara listedesiniz. Bildirim gönderemezsiniz.";
    }

    @Override
    public String notEnoughQuota() {
        return "Yeterli kotaniz bulunmamaktadir.";
    }

    @Override
    public String buyingAdditionalPackage() {
        return "Ekstra paket aliniyor...";
    }

    @Override
    public String cantBuySecondEmailPackage() {
        return "Birden fazla email paketi alamazsınız.";
    }

    @Override
    public String cantBuySecondSmsPackage() {
        return "Birden fazla sms paketi alamazsınız.";
    }
}
