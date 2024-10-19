package service;

import exceptions.CompanyIsOnBlockListException;
import model.MessageDTO;

public interface Channel {

    void sendNotification(MessageDTO messageDTO);

    boolean canSendNotification(MessageDTO messageDTO) throws CompanyIsOnBlockListException;

    boolean hasEnoughQuota(MessageDTO messageDTO);

    void buyAdditionalPackage(MessageDTO messageDTO);

}
