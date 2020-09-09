package controller;

import model.MessageDTO;
import service.Channel;
import service.EmailChannel;
import service.SmsChannel;

public class Sender {

    private Channel emailSender;
    private Channel smsSender;

    public Sender(EmailChannel emailSender, SmsChannel smsSender) {
        this.emailSender=emailSender;

        this.smsSender=smsSender;
    }

    public void sendEmail(MessageDTO messageDTO){
        try {
            if(emailSender.canSendNotification(messageDTO)) {
                if(!emailSender.hasEnoughQuota(messageDTO)) {
                    System.out.println(messageDTO.getSender().getLanguage().notEnoughQuota());
                    emailSender.buyAdditionalPackage(messageDTO);
                    System.out.println(messageDTO.getSender().getLanguage().buyingAdditionalPackage());
                }
                emailSender.sendNotification(messageDTO);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendSms(MessageDTO messageDTO) {
        try {
            if(smsSender.canSendNotification(messageDTO)) {
                if(!smsSender.hasEnoughQuota(messageDTO)) {
                    System.out.println(messageDTO.getSender().getLanguage().notEnoughQuota());
                    smsSender.buyAdditionalPackage(messageDTO);
                    System.out.println(messageDTO.getSender().getLanguage().buyingAdditionalPackage());
                }
                smsSender.sendNotification(messageDTO);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendSmsEmail(MessageDTO messageDTO) {
        sendSms(messageDTO);
        sendEmail(messageDTO);
    }

    public Channel getEmailSender() { return emailSender; }

    public void setEmailSender(Channel emailSender) { this.emailSender = emailSender; }

    public Channel getSmsSender() { return smsSender; }

    public void setSmsSender(Channel smsSender) { this.smsSender = smsSender; }

}
