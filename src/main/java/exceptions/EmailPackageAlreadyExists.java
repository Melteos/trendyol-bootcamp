package exceptions;

public class EmailPackageAlreadyExists extends RuntimeException {

    public EmailPackageAlreadyExists(String message) {
        super(message);
    }

}
