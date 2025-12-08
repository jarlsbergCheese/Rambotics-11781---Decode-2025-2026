package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import java.sql.Time;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class Luncher {

    public DcMotorEx mainMotor;
    public Servo launchHolder;

    public String curColor = "";
    static final double servoRestPosition = 0.6;
    static final double servoHoldPosition = 0.25;

    public int ballQue = 0;


    // IS THERE AN EASIER WAY TO DO THIS PLEASE THIS IS SO JANK
    public boolean justPressedA = false;
    public boolean justPressedX = false;
    public boolean isJustPressedY = false;

    public Luncher(HardwareMap hwMap)
    {
        mainMotor = hwMap.get(DcMotorEx.class, "shooter");
        launchHolder = hwMap.get(Servo.class, "holder");

        launchHolder.setPosition(0.6);

        mainMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    Timer motorTimer;
    Timer servoHoldTimer;
    Timer servoRestTimer;

    class MotorTask extends TimerTask{
        @Override
        public void run() {
            mainMotor.setPower(0);
        }
    };

    class ServoRestPosition extends TimerTask{
        @Override
        public void run() {
            launchHolder.setPosition(servoRestPosition);
        }
    };

    class ServoHoldPosition extends TimerTask {
        @Override
        public void run() {
            launchHolder.setPosition(servoHoldPosition);
        }
    };



    public void cycle(int num)
    {
        for(int i = 1; i < num; i++)
        {
            mainMotor.setPower(0.3);
            motorTimer.schedule(new MotorTask(), 5000);
        }
    }

    public void mainLaunch(double strength, int time)
    {
        mainMotor.setPower(strength);

        motorTimer = new Timer();
        servoHoldTimer = new Timer();
        servoRestTimer = new Timer();

        motorTimer.schedule(new MotorTask(), time);
        servoHoldTimer.schedule(new ServoHoldPosition(), 2500);
        servoRestTimer.schedule(new ServoRestPosition(), time);

    }

    public void launchSequence()
    {
        mainMotor.setPower(1);

        motorTimer = new Timer();
        servoHoldTimer = new Timer();
        servoRestTimer = new Timer();

        motorTimer.schedule(new MotorTask(), 10000);
        servoRestTimer.schedule(new ServoRestPosition(), 10000);

        launchHolder.setPosition(servoRestPosition);

        for(int x = 0; x < ballQue; x++)
        {
            servoRestTimer.schedule(new ServoRestPosition(), 3250 + x*1500);
            servoHoldTimer.schedule(new ServoHoldPosition(), 2500 + x*1500);
        }
    }


    public void launchGreen()
    {

        if(Objects.equals(curColor, "green"))
        {
            mainLaunch(1, 1000);
        }
        else
        {
            cycle(1);
            launchGreen();
        }

    }

    public void launchPurple()
    {
        if(Objects.equals(curColor, "purple"))
        {
            mainLaunch(1, 5000);
        }
        else
        {
            cycle(1);
            launchPurple();
        }
    }

    public void gamepadInputs(Gamepad gmpad)
    {
        if(gmpad.y && !isJustPressedY)
        {
            launchSequence();
            isJustPressedY = true;
        }
        if(!gmpad.y && isJustPressedY)
        {
            isJustPressedY = false;
        }

        //
        if(gmpad.a && !justPressedA)
        {
            justPressedA = true;
            ballQue += 1;

        }
        if(!gmpad.a && justPressedA){
            justPressedA = false;
        }

        //Launches a purple ball using the X button
        if(gmpad.x && !justPressedX)
        {
            justPressedX = true;


        }
        if(!gmpad.x && justPressedX){
            justPressedX = false;
        }


    }

}