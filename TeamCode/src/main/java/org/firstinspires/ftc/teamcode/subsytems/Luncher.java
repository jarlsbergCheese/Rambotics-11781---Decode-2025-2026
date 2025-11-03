package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class Luncher {

    public DcMotorEx mainMotor;
    public Servo launchHolder;

    public String curColor = "";


    // IS THERE AN EASIER WAY TO DO THIS PLEASE THIS IS SO JANK
    public boolean justPressedA = false;
    public boolean justPressedX = false;
    public boolean isJustPressedY = false;

    public Luncher(HardwareMap hwMap)
    {
        mainMotor = hwMap.get(DcMotorEx.class, "topLeftMotor");
        launchHolder = hwMap.get(Servo.class, "launchHolder");

        launchHolder.setPosition(0.3);
    }

    Timer timer;

    TimerTask task;

    public void cycle(int num)
    {
        for(int i = 1; i < num; i++)
        {
            mainMotor.setPower(0.3);
            timer.schedule(task, 5000);
        }
    }

    public void mainLaunch(double strength, int time)
    {
        mainMotor.setPower(-strength);
        launchHolder.setPosition(0.6);

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                mainMotor.setPower(0);
                launchHolder.setPosition(0.3);
                timer.cancel();
            }
        };
        timer.schedule(task, time);


    }

    public void launchGreen()
    {

        if(Objects.equals(curColor, "green"))
        {
            mainLaunch(1, 5000);
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
            mainLaunch(1,5000);
            isJustPressedY = true;
        }
        if(!gmpad.y && isJustPressedY)
        {
            isJustPressedY = false;
        }

        // Launches a Green ball using the A button
        if(gmpad.a && !justPressedA)
        {
            justPressedA = true;
            launchGreen();
        }
        if(!gmpad.a && justPressedA){
            justPressedA = false;
        }

        //Launches a purple ball using the X button
        if(gmpad.x && !justPressedX)
        {
            justPressedX = true;
            launchPurple();
        }
        if(!gmpad.x && justPressedX){
            justPressedX = false;
        }


    }

}
