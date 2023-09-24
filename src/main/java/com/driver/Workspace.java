package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Workspace extends Gmail {

    private ArrayList<Meeting> calendar; // Stores all the meetings


    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting) {
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings() {
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        //First sort the arraylist according to start time of the meeting and
        // Traverse throught he  lsits and find the meetings colliding or not  and count  the attending orpossible meetings
        calendar.sort((a, b) -> {
            return a.getStartTime().compareTo(b.getStartTime());
        });
        if (calendar.size() == 0) return 0;

        int CountOfPossMeet = 1;
        LocalTime PresentStartTime = calendar.get(0).getStartTime();
        LocalTime PresentEndTime = calendar.get(0).getEndTime();


        for (Meeting m : calendar) {
            if (m.getStartTime() == PresentStartTime) {
                // It means the start timings of the meetings are same ,so in this case
                // select the meeting with less ending time , so that you can attend another meeting , if it is there.
                if (m.getEndTime().isBefore(PresentEndTime)) {
                    PresentEndTime = m.getEndTime();
                }
            } else {
                if (m.getStartTime().isAfter(PresentEndTime)) {
                    CountOfPossMeet++;
                    PresentStartTime = m.getStartTime();
                    PresentEndTime = m.getEndTime();
                }
            }
        }
        return CountOfPossMeet;
    }
}
