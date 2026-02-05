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
    DcMotorEx mainMotor;

    public double tarPosX;
    public double tarPosY;
    public double tarRotation;

    public double x;
    public double y;
    public double theta = 0.0;

    public double p_controlX;
    public double p_controlY;
    public double p_controlTheta;

    public double speedMod = 1.0;

    public final double p_tune = (1.0/1000.0);

    public double i_control;
    public double i_tune;

    public double d_control;
    public double d_tune;

    public double curThetaBase;

    public double buffer = 30;

    public boolean isAtTarPos = false;

    public ArrayList<targetDogs> targetPositions = new ArrayList<targetDogs>();
    boolean completed = false;

    public boolean runningEvent = false;
    boolean completedEvent = false;

    Timer eventTimer;
    Timer intakeTimer;

    class eventTask extends TimerTask {
        @Override
        public void run() {
            runningEvent = false;
        }
    };

    class jamTimer extends TimerTask {
        @Override
        public void run() {
            intake.setPower(0);
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
        mainMotor = hwmap.get(DcMotorEx.class, "shooter");
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

            p_controlX = (tarX - curX) * p_tune;

            if(Math.abs(tarX - curX) <= buffer) {
                x = 0;
            }
            else if(Math.abs(p_controlX) > 0.35)
            {
                x = 1*p_controlX*speedMod;
            }
            else if(p_controlX > 0)
            {
                x = 0.35*speedMod;
            }
            else
            {
                x = -0.35*speedMod;
            }

            p_controlY = (tarY - curY)*p_tune;

            if(Math.abs(tarY - curY) <= buffer)
            {
                y = 0;
            }
            else if (Math.abs(p_controlY) > 0.35)
            {
                y = 1*p_controlY*speedMod;
            }
            else if(p_controlY > 0)
            {
                y = 0.35*speedMod;
            }
            else{
                y = -0.35*speedMod;
            }

            p_controlTheta = (tarTheta-curThetaBase)*p_tune*10;

            if(Math.abs(tarTheta-curThetaBase) <= 2)
            {
                theta = 0;
            }
            /* else if(curThetaBase > tarTheta+90)
            {
                theta = -0.5;
            }

             */
            else
            {
                theta = -1*p_controlTheta*speedMod;
            }


        }
    }

    public void autoLaunch(Luncher lunch, int ballQue)
    {
        eventTimer = new Timer();

        luncher.ballQue = ballQue;

        runningEvent = true;
        lunch.rpmLaunch(false);
        eventTimer.schedule(new eventTask(), (6000));
    }

    public void autoLaunch(Luncher lunch, int ballQue, boolean far)
    {
        eventTimer = new Timer();

        luncher.ballQue = ballQue;

        runningEvent = true;
        lunch.rpmLaunch(false);
        eventTimer.schedule(new eventTask(), (6000));
    }

    public void autoIntakeStart()
    {
        runningEvent = true;
            intake.setPower(0.95);
        runningEvent = false;
    }
    public void autoIntakeEnd()
    {
        runningEvent = true;
        intake.setPower(0);
        runningEvent = false;
    }

    public void unJam()
    {
        intakeTimer = new Timer();

        intake.setPower(-0.5);
        intakeTimer.schedule(new jamTimer(), 500);
    }

    public void intakeEndWait()
    {
        intakeTimer = new Timer();
        intakeTimer.schedule(new jamTimer(), 1000);
    }

    public void sequence(ArrayList<targetDogs> targets, double curX, double curY, double curTheta){
        // setTarPos(targets.get(count).x, targets.get(count).y, targets.get(count).theta);

        if(curTheta > 0) {
            curThetaBase = curTheta - Math.floor(Math.abs(curTheta) / 360) * 360;
        }
        if(curTheta < 0) {
            curThetaBase = curTheta + Math.floor(Math.abs(curTheta) / 360) * 360;
        }

        if(!Objects.equals(targets.get(count).eventType, "") && !runningEvent && !completedEvent)
        {
            x = 0;
            y = 0;

            runningEvent = true;

            switch (targets.get(count).eventType)
            {
                case "launch":
                    autoLaunch(luncher, 1,false);
                    break;
                case "launch1":
                    autoLaunch(luncher, 1,false);
                    break;
                case "launch2":
                    autoLaunch(luncher, 2,false);
                    break;
                case "launch3":
                    autoLaunch(luncher, 3,false);
                    break;
                case "farLaunch3":
                    autoLaunch(luncher, 3, true);
                    break;
                case "intakeStart":
                    autoIntakeStart();
                    break;
                case "intakeEnd":
                    autoIntakeEnd();
                    break;
                case "speedChange2":
                    speedMod = 2;
                    runningEvent = false;
                    break;
                case "speedChange1":
                    runningEvent = true;
                    speedMod = 1;
                    runningEvent = false;
                    break;
                case "speedChange0.5":
                    runningEvent = true;
                    speedMod = 0.8;
                    runningEvent = false;
                    break;
                case "constantWheelOn":
                    runningEvent = true;
                    luncher.constantlySpinning = true;
                    runningEvent = false;
                    break;
                case "constantWheelOff":
                    runningEvent = true;
                    luncher.constantlySpinning = false;
                    runningEvent = false;
                    break;
                case "unjam":
                    runningEvent = true;
                    unJam();
                    break;
                case "wait":
                    runningEvent = true;
                    intakeEndWait();
                    break;
                case "":
                    break;
            }

            completedEvent = true;


        }

        if(!completed && !runningEvent)
        {
            runToTargetPos(curX, curY, curTheta, targets.get(count).x, targets.get(count).y, targets.get(count).theta);
            tarRotation = targets.get(count).theta;
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
