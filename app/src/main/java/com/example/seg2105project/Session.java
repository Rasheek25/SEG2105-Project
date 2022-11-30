package com.example.seg2105project;
// Session Class
public class Session {

    Double startTime;
    Double endTime;
    String day;

    public Session(String day, Double startTime, Double endTime) {

        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Session(String day, String startTime, String endTime) {

        this.day = day;
        this.startTime = AmPmToInt(startTime);
        this.endTime = AmPmToInt(endTime);
    }

    // "day : startTime - endTime" format input
    public Session(String strSession){
        //getting session day
        String[] t = strSession.split(" : ");
        this.day = t[0];
        //getting session startTime and endTime
        String[] se = t[1].split(" - ");
        this.startTime = AmPmToInt(se[0]);
        this.endTime = AmPmToInt(se[1]);

    }


    public Boolean checkSessionConflict(Session o) {
        if(!this.day.equalsIgnoreCase(o.day)){
            return false;
        }

        if( (o.startTime>=this.startTime && o.startTime<=this.endTime) || (o.endTime>=this.startTime && o.endTime<=this.endTime)
            || (this.startTime>=o.startTime && this.startTime<=o.endTime) || (this.endTime>=o.startTime && this.endTime<=o.endTime)
            || (this.endTime == o.endTime)
            || (this.startTime == o.startTime)){

            return true;
        }

        return false;
    }

    public static Double AmPmToInt(String time){
        Double timeNumber = 0.0;
        //removing spaces
        time = time.replaceAll("\\s+","");

        //if time PM
        if(time.contains("PM") || time.contains("Pm")|| time.contains("pM")|| time.contains("pm")){
            //removing PM
            time = time.substring(0, time.length()-1);
            time = time.substring(0, time.length()-1);
            //seperating minutes and hours
            String[] timeArray = time.split(":");
            Integer hour = Integer.parseInt(timeArray[0]);
            Integer minutes = Integer.parseInt(timeArray[1]);

            Double decimal = Double.valueOf(minutes/60.0);
            if(hour == 12.0){
                timeNumber = hour.doubleValue() + decimal;
            }
            else {
                timeNumber = hour.doubleValue() + decimal + 12.0;
            }
        }

        //if time AM
        else if(time.contains("AM") || time.contains("Am")|| time.contains("aM")|| time.contains("am")){
            //removing AM
            time = time.substring(0, time.length()-1);
            time = time.substring(0, time.length()-1);

            //seperating minutes and hours
            String[] timeArray = time.split(":");
            Integer hour = Integer.parseInt(timeArray[0]);
            Integer minutes = Integer.parseInt(timeArray[1]);
            if(hour == 12.0){
                hour = 0;
            }
            Double decimal = Double.valueOf(minutes/60.0);
            timeNumber = hour.doubleValue() + decimal;
        }

        return timeNumber;

    }




}