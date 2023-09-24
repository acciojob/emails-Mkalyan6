package com.driver;


import java.util.Date;

public class MailDetails {
    public Date date;
    public String sender;
   public  String message;

    public MailDetails(Date date,String sender,String message){
        this.date=date;
        this.message=message;
        this.sender=sender;
    }
}

