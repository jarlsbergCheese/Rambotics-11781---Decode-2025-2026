package org.firstinspires.ftc.teamcode.subsytems;

import org.firstinspires.ftc.teamcode.subsytems.targetDogs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Pathfinder
{
    Luncher luncher;

    public double tarPosX;
    public double tarPosY;
    public double tarRotation;

    public double x;
    public double y;
    public double theta = 0.0;

    public double curThetaBase;

    public double buffer = 50;

    public boolean isAtTarPos = false;

    public ArrayList<targetDogs> targetPositions = new ArrayList<targetDogs>();
    boolean completed = false;

    boolean runningEvent = false;
    boolean completedEvent = false;

    Timer eventTimer;

    class eventTask extends TimerTask {
        @Override
        public void run() {
            runningEvent = false;
        }
    };

    // iterator variables
    public int count = 0;

    public Pathfinder()
    {

    }

    public Pathfinder(Luncher luncherp)
    {
        luncher = luncherp;
    }


    public void setTarPos(double xp, double yp, double thetap)
    {
        tarPosX = xp;
        tarPosY = yp;
        tarRotation = thetap;
    }

    public void runToTargetPos(double curX, double curY, double curTheta, double tarX, double tarY, double tarTheta)
    {
        if(Math.abs(curX - tarX) <= buffer && Math.abs(curY - tarY) <= buffer && Math.abs(tarTheta-curThetaBase) <= 20)
        {
            isAtTarPos = true;

            x = 0;

            y = 0;

            theta = 0;

        }
        else{
            isAtTarPos = false;

            if(Math.abs(curX - tarX) <= buffer)
            {
                x = 0;
            }
            else if(curX < tarX-100)
            {
                x = 0.5;
            }
            else if(curX < tarX)
            {
                x = 0.25;
            }
            else if(curX > tarX+100)
            {
                x = -0.5;
            }
            else if(curX > tarX)
            {
                x = -0.25;
            }


            if(Math.abs(curY - tarY) <= buffer)
            {
                y = 0;
            }
            else if(curY < tarY-100)
            {
                y = 0.5;
            }
            else if(curY < tarY)
            {
                y = 0.25;
            }
            else if(curY > tarY+100)
            {
                y = -0.5;
            }
            else if(curY > tarY)
            {
                y = -0.25;
            }

            if(Math.abs(tarTheta-curThetaBase) <= 20)
            {
                theta = 0;
            }
            else if(curThetaBase > tarTheta+90)
            {
                theta = -0.25;
            }
            else if(curThetaBase > tarTheta)
            {
                theta = -0.5;
            }
            else if(curThetaBase < tarTheta-90)
            {
                theta = 0.25;
            }
            else if(curThetaBase < tarTheta)
            {
                theta = 0.5;
            }


        }
    }

    public void autoLaunch(Luncher lunch)
    {
        eventTimer = new Timer();

        runningEvent = true;
        lunch.mainLaunch(1,2100);
        eventTimer.schedule(new eventTask(), 2100);

    }

    public void sequence(ArrayList<targetDogs> targets, double curX, double curY, double curTheta){
        // setTarPos(targets.get(count).x, targets.get(count).y, targets.get(count).theta);

        if(curTheta > 0) {
            curThetaBase = curTheta - Math.floor(Math.abs(curTheta) / 360) * 360;
        }
        if(curTheta < 0) {
            curThetaBase = curTheta + Math.floor(Math.abs(curTheta) / 360) * 360;
        }

        if(!completed && !runningEvent)
        {
            runToTargetPos(curX, curY, curTheta, targets.get(count).x, targets.get(count).y, targets.get(count).theta);
        }

        if(!Objects.equals(targets.get(count).eventType, "") && !runningEvent && !completedEvent)
        {
            x = 0;
            y = 0;

            runningEvent = true;

            switch (targets.get(count).eventType)
            {
                case "launch":
                    autoLaunch(luncher);
                    completedEvent = true;

                case "":
                    break;
            }
        }



        if(isAtTarPos && !runningEvent){
            if(count < targets.size() && !completed)
            {
             count+=1;
            }
            if(completedEvent)
            {
                completedEvent = false;
            }
        }

        if(count >= targets.size())
        {
            completed = true;
        }
    }



    /*
    public void sequence(ArrayList<targetDogs> targetsp, double curx, double cury, double curTheta){
        if(godotPilled < targetsp.size()) {
            setTarPos(targetsp.get(godotPilled).x, targetsp.get(godotPilled).y, targetsp.get(godotPilled).theta);
        }
            if(!isAtTarPos){
                runToTargetPos(curx, cury, curTheta);
            }
            else{
                godotPilled += 1;
            }
    }
*/
}
