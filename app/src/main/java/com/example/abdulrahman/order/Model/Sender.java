package com.example.abdulrahman.order.Model;

/**
 * Created by Abdulrahman on 12/17/2017.
 */

public class Sender {
    public String to;
    public Notification notification;

    public Sender(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
