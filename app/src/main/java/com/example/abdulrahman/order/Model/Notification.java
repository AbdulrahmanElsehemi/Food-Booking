package com.example.abdulrahman.order.Model;

/**
 * Created by Abdulrahman on 12/17/2017.
 */

 public class Notification {
    public  String body;
    public  String title;

    public Notification(String body, String title) {
        this.body = body;
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
