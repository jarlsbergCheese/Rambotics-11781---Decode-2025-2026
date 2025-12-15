package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsytems.targetDogs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Pathfinder
{
    Luncher luncher;
    DcMotorEx intake;

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

    public Pathfinder(Luncher luncherp, HardwareMap hwmap)
    {
        luncher = luncherp;
        intake = hwmap.get(DcMotorEx.class, "intake");
    }


    public void setTarPos(double xp, double yp, double thetap)
    {
        tarPosX = xp;
        tarPosY = yp;
        tarRotation = thetap;
    }

    public void runToTargetPos(double curX, double curY, double curTheta, double tarX, double tarY, double tarTheta)
    {
        if(Math.abs(tarX - curX) <= buffer && Math.abs(tarY - curY) <= buffer && Math.abs(tarTheta-curThetaBase) <= 10)
        {
            isAtTarPos = true;

            x = 0;

            y = 0;

            theta = 0;

        }
        else{
            isAtTarPos = false;

            if(Math.abs(tarX - curX) <= buffer)
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


            if(Math.abs(tarY - curY) <= buffer)
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

            if(Math.abs(tarTheta-curThetaBase) <= 10)
            {
                theta = 0;
            }
            else if(curThetaBase > tarTheta+90)
            {
                theta = -0.5;
            }
            else if(curThetaBase > tarTheta)
            {
                theta = -0.25;
            }
            else if(curThetaBase < tarTheta-90)
            {
                theta = 0.5;
            }
            else if(curThetaBase < tarTheta)
            {
                theta = 0.25;
            }


        }
    }

    public void autoLaunch(Luncher lunch, int ballQue)
    {
        eventTimer = new Timer();

        luncher.ballQue = ballQue;

        runningEvent = true;
        lunch.launchSequence(true);
        eventTimer.schedule(new eventTask(), ballQue*3500 + 3500);
    }

    public void autoIntake(boolean inwards)
    {

        eventTimer = new Timer();

        if(inwards)
        {
            intake.setPower(-0.75);
        }
        if(!inwards)
        {
            intake.setPower(0.75);
        }
        eventTimer.schedule(new eventTask(), 1000);

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
            tarRotation = targets.get(count).theta;
        }

        if(!Objects.equals(targets.get(count).eventType, "") && !runningEvent && !completedEvent)
        {
            x = 0;
            y = 0;

            runningEvent = true;

            switch (targets.get(count).eventType)
            {
                case "launch":
                    autoLaunch(luncher, 1);
                    completedEvent = true;

                case "launch1":
                    autoLaunch(luncher, 1);
                    completedEvent = true;

                case "launch2":
                    autoLaunch(luncher, 2);
                    completedEvent = true;

                case "launch3":
                    autoLaunch(luncher, 3);
                    completedEvent = true;

                case "intakeStart":
                    intake.setPower(0.4);
                    completedEvent = true;

                case "intakeEnd":
                    intake.setPower(0);
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
