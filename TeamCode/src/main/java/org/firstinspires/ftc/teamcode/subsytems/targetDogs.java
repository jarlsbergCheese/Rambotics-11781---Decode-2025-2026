package org.firstinspires.ftc.teamcode.subsytems;

public class targetDogs {

    public double x;
    public double y;
    public double theta;

    String eventType = "";

    public targetDogs(double xp, double yp, double thetap){
        x = xp;
        y = yp;
        theta = thetap;
    }

    public targetDogs(double xp, double yp, double thetap, String eventTypep){
        x = xp;
        y = yp;
        theta = thetap;
        eventType = eventTypep;
    }

}
