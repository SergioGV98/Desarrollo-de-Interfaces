package com.sergio98.sendmessage.model.data;

import java.util.Objects;

/**
 * Clase que guarda un mensaje que manda una persona emisor y la recibe otra persona.
 * @author Sergio Garcia Vico
 * @version 1.0
 */
public class Message {

    private String content;
    private Person sender;
    private Person receiver;
    private int id;

    public Message(String content, Person sender, Person receiver, int id) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.id = id;
    }

    //region Metodos SET y GET de la clase Message

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //endregion

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
