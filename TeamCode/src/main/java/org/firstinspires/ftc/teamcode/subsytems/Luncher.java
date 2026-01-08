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
    public DcMotorEx intake;

    public boolean launching;
    public boolean readyToLaunch;
    public int launchedBalls;

    public String curColor = "";
    static final double servoRestPosition = 1;
    static final double servoHoldPosition = 0.67;

    public int ballQue = 0;


    // IS THERE AN EASIER WAY TO DO THIS PLEASE THIS IS SO JANK
    public boolean justPressedA = false;
    public boolean justPressedX = false;
    public boolean isJustPressedY = false;
    public boolean isJustPressedB = false;

    public Luncher(HardwareMap hwMap)
    {
        mainMotor = hwMap.get(DcMotorEx.class, "shooter");
        launchHolder = hwMap.get(Servo.class, "holder");
        intake = hwMap.get(DcMotorEx.class, "intake");

        launchHolder.setPosition(servoRestPosition);

        mainMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    Timer motorTimer;
    Timer servoHoldTimer;
    Timer servoRestTimer;

    Timer intakeVTimer;

    class MotorTask extends TimerTask{
        @Override
        public void run() {
            mainMotor.setPower(0);
            intake.setPower(0);
            launching = false;
            ballQue = 0;
        }
    };

    class ServoRestPosition extends TimerTask{
        @Override
        public void run()
        {
            readyToLaunch = true;

            launchHolder.setPosition(servoRestPosition);
            intake.setPower(-0.35);
        }
    };

    class ServoHoldPosition extends TimerTask {
        @Override
        public void run() {
            launchHolder.setPosition(servoHoldPosition);
            intake.setPower(0);
        }
    };

    class intakeV extends TimerTask {
        @Override
        public void run() {
            intake.setPower(0);

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

        //intakeVTimer = new Timer();

        if(ballQue>1){
            intake.setPower(-0.35);
        }

        motorTimer = new Timer();
        servoHoldTimer = new Timer();
        servoRestTimer = new Timer();

        motorTimer.schedule(new MotorTask(), (ballQue+1)*4500);
        servoRestTimer.schedule(new ServoRestPosition(), (ballQue+1)*4500);

        launchHolder.setPosition(servoRestPosition);

        for(int x = 0; x < ballQue; x++)
        {
            servoRestTimer.schedule(new ServoRestPosition(), 4250 + x*2500);

            //intake.setPower(-0.35);
            //intakeVTimer.schedule(new intakeV(), 1500);

            servoHoldTimer.schedule(new ServoHoldPosition(), 3500 + x*2500);
        }

        intake.setPower(0);
    }

    public void launchSequence(boolean isAuto)
    {
        mainMotor.setPower(1);

        if(ballQue > 1)
        {
            intake.setPower(-0.60);
        }

        motorTimer = new Timer();
        servoHoldTimer = new Timer();
        servoRestTimer = new Timer();

        motorTimer.schedule(new MotorTask(), ballQue*3500 + 3250);
        servoRestTimer.schedule(new ServoRestPosition(), ballQue*3500 + 3250);

        launchHolder.setPosition(servoRestPosition);

        for(int x = 0; x < ballQue; x++)
        {
            servoRestTimer.schedule(new ServoRestPosition(), 3250 + x*3000);
            servoHoldTimer.schedule(new ServoHoldPosition(), 2500 + x*3000);
        }

        intake.setPower(0);
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
            if(ballQue <= 0)
            {
                ballQue += 1;
            }
            launchSequence();
            isJustPressedY = true;
        }
        if(!gmpad.y && isJustPressedY)
        {
            isJustPressedY = false;
        }

        if(gmpad.b && !isJustPressedB)
        {
            if(ballQue <= 0)
            {
                ballQue += 1;
            }
            launchSequence();
            isJustPressedB = true;
        }
        if(!gmpad.b && isJustPressedB)
        {
            isJustPressedB = false;
        }

 //mm


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