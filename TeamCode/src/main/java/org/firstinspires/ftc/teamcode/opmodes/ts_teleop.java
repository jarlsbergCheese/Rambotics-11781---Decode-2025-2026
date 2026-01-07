package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class ts_teleop extends OpMode {

    Gamepad gmpad;
    DcMotorEx topLeftMotor;

    DcMotorEx topRightMotor;

    DcMotorEx bottomLeftMotor;

    DcMotorEx bottomRightMotor;

    boolean x;
    float topLeftPower;

    float topRightPower;

    float bottomLeftPower;

    float bottomRightPower;
    public void runAll(){
        topLeftMotor.setMotorEnable();
        topRightMotor.setMotorEnable();
        bottomLeftMotor.setMotorEnable();
        bottomRightMotor.setMotorEnable();
    }

    @Override
    public void init() {
        topLeftMotor = hardwareMap.get(DcMotorEx.class,"topLeftMotor");
        topRightMotor = hardwareMap.get(DcMotorEx.class,"topRightMotor");
        bottomLeftMotor = hardwareMap.get(DcMotorEx.class,"bottomLeftMotor");
        bottomRightMotor = hardwareMap.get(DcMotorEx.class,"bottomRightMotor");

        if (x == true){
            runAll();
        }

    }

    @Override
    public void loop() {
        x = gmpad.x;


    }
}

