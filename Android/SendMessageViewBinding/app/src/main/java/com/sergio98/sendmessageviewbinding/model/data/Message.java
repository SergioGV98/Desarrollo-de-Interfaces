package com.sergio98.sendmessageviewbinding.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que guarda un mensaje que manda una persona emisor y la recibe otra persona.
 * @author Sergio Garcia Vico
 * @version 1.0
 */
public class Message implements Serializable, Parcelable {

    public static final String KEY = "message";
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeParcelable(sender, flags);
        dest.writeParcelable(receiver, flags);
        dest.writeInt(id);
    }
    protected Message(Parcel in) {
        content = in.readString();
        sender = in.readParcelable(Person.class.getClassLoader());
        receiver = in.readParcelable(Person.class.getClassLoader());
        id = in.readInt();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
