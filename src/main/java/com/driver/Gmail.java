package com.driver;

import java.util.*;

public class Gmail extends Email {

       int inboxCapacity; //maximum number of mails inbox can store
    private List<MailDetails> InboxMails;
    private List<MailDetails>TrashMails;
    private HashMap<String,MailDetails> AllMails;




        //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
        //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
        public Gmail(String emailId, int inboxCapacity) {
            super(emailId);
            this.inboxCapacity = inboxCapacity;
            InboxMails=new ArrayList<>();
            TrashMails=new ArrayList<>();
            AllMails=new HashMap<>();

        }

        public void receiveMail(Date date, String sender, String message) {
            // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
            // It is guaranteed that:
            // 1. Each mail in the inbox is distinct.
            // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
             if(InboxMails.size()==inboxCapacity){
                 String OldMessage=findOldestMessage();
                 MailDetails OldMail=AllMails.get(OldMessage);
                  TrashMails.add(OldMail);
                  InboxMails.remove(OldMail);
             }
             // create object of maildetails and add to inboxmails
                MailDetails m=new MailDetails(date,sender, message);
             // Add in hashmap
                AllMails.put(message,m);
             // Add to inbox
             InboxMails.add(m);
        }


        public void deleteMail(String message) {
            // Each message is distinct
            // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
//                if(AllMails.containsKey(message)){
//                        MailDetails m=AllMails.get(message);
//                       TrashMails.add(m);
//                       InboxMails.remove(m);
//                        AllMails.remove(message);
               for(MailDetails m:InboxMails){
                   if(m.message.equals(message)){
                       TrashMails.add(m);
                       InboxMails.remove(m);
                       break;
                   }
                }

        }

        public String findLatestMessage() {
            // If the inbox is empty, return null
            // Else, return the message of the latest mail present in the inbox
                if(InboxMails.size()==0)return null;
                Date LatestMailDate=null;
                String LatestMessage=null;
                for(MailDetails m:InboxMails){
                    if(LatestMailDate==null||LatestMailDate.before(m.date)){
                        LatestMessage=m.message;
                        LatestMailDate=m.date;
                    }
                }
//                MailDetails m=InboxMails.get(InboxMails.size()-1);

                return LatestMessage;

        }

        public String findOldestMessage() {
            // If the inbox is empty, return null
            // Else, return the message of the oldest mail present in the inbox
                if(InboxMails.size()==0)return null;

            Date oldmailDate=null;
            String OldMessage=null;
                for(MailDetails m:InboxMails) {
                    if (oldmailDate == null || m.date.before(oldmailDate)){
                        oldmailDate = m.date;
                    OldMessage = m.message;
                }
                }
                return OldMessage;

        }

        public int findMailsBetweenDates(Date start, Date end) {
            //find number of mails in the inbox which are received between given dates
            //It is guaranteed that start date <= end date
                // Traverse through the Arraylist which dates arranged in increasing order
                int CountOfMails=0;
                for(MailDetails m:InboxMails){
                        Date PresentDate=m.date;
                        if (PresentDate.compareTo(start)>=0&&PresentDate.compareTo(end)<=0) {
                                CountOfMails++;
                        }

//                        if(PresentDate.after(end))break;
                }
                return CountOfMails;
        }

        public int getInboxSize() {
            // Return number of mails in inbox
                return InboxMails.size();

        }

        public int getTrashSize() {
            // Return number of mails in Trash
                return TrashMails.size();

        }

        public void emptyTrash() {
            // clear all mails in the trash
                TrashMails.clear();

        }

        public int getInboxCapacity() {
            // Return the maximum number of mails that can be stored in the inbox
                return inboxCapacity;
        }
    }
