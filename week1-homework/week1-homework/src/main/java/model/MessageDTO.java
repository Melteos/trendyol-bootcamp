package model;

import java.util.*;

public abstract class MessageDTO {

    private CompanyDTO sender;
    private List<PersonDTO> receivers = new ArrayList<PersonDTO>();

    private String content;

    public CompanyDTO getSender() {
        return sender;
    }

    public void setSender(CompanyDTO sender) {
        this.sender = sender;
    }

    public List<PersonDTO> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<PersonDTO> receivers) {
        this.receivers = receivers;
    }

    public void addReceiver(PersonDTO receiver) {
        this.receivers.add(receiver);
    }

    public void addReceivers(List<PersonDTO> receivers) {
        this.receivers.addAll(receivers);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
