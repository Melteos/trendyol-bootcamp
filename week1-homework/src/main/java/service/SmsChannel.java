package service;

import exceptions.CompanyIsOnBlockListException;
import model.MessageDTO;

public class SmsChannel implements Channel {

    public void sendNotification(MessageDTO messageDTO) {
        messageDTO.getSender().getBoughtSmsPackage().setRemainingQuota(messageDTO.getSender().getBoughtSmsPackage().getRemainingQuota() - messageDTO.getReceivers().size());
        System.out.println("Sms \"" + messageDTO.getContent() + "\" sent to " + messageDTO.getReceivers().size() + " people.");
    }

    public boolean canSendNotification(MessageDTO messageDTO) throws CompanyIsOnBlockListException {
        if(messageDTO.getSender().isOnBlackList())
            throw new CompanyIsOnBlockListException(messageDTO.getSender().getLanguage().onBlackList());
        else
            return true;
    }

    public boolean hasEnoughQuota(MessageDTO messageDTO) {
        return ( messageDTO.getSender().getBoughtSmsPackage().getRemainingQuota() >= messageDTO.getReceivers().size() );
    }

    public void buyAdditionalPackage(MessageDTO messageDTO) { //göndereceği mesajı sağlıyacak kadar
        messageDTO.getSender().getBoughtSmsPackage().increaseQuota( messageDTO.getReceivers().size() - messageDTO.getSender().getBoughtSmsPackage().getRemainingQuota() );
    }

}
