package exceptions;

public class SmsPackageAlreadyExists extends RuntimeException {

    public SmsPackageAlreadyExists(String message) {
        super(message);
    }
}
