package service;

import exceptions.CompanyIsOnBlockListException;
import model.MessageDTO;

public class EmailChannel implements Channel {



    public void sendNotification(MessageDTO messageDTO) {
        messageDTO.getSender().getBoughtEmailPackage().setRemainingQuota(messageDTO.getSender().getBoughtEmailPackage().getRemainingQuota() - messageDTO.getReceivers().size());
        System.out.println("Email \"" + messageDTO.getContent() + "\" sent to " + messageDTO.getReceivers().size() + " people.");
    }

    public boolean canSendNotification(MessageDTO messageDTO) throws CompanyIsOnBlockListException {
        if(messageDTO.getSender().isOnBlackList())
            throw new CompanyIsOnBlockListException(messageDTO.getSender().getLanguage().onBlackList());
        else
            return true;
    }

    public boolean hasEnoughQuota(MessageDTO messageDTO) {
        return ( messageDTO.getSender().getBoughtEmailPackage().getRemainingQuota() >= messageDTO.getReceivers().size() );
    }

    public void buyAdditionalPackage(MessageDTO messageDTO) { //göndereceği mesajı sağlıyacak kadar
        messageDTO.getSender().getBoughtEmailPackage().increaseQuota( messageDTO.getReceivers().size() - messageDTO.getSender().getBoughtEmailPackage().getRemainingQuota() );
    }
}
