package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {

    public DcMotorEx topRightMotor;
    public DcMotorEx topLeftMotor;
    public DcMotorEx bottomRightMotor;
    public DcMotorEx bottomLeftMotor;

    public float topRightPower;
    public float topLeftPower;
    public float bottomRearPower;

    Drivetrain(HardwareMap hwMap)
    {
        topRightMotor = hwMap.get(DcMotorEx.class, "topRightMotor");
        topLeftMotor = hwMap.get(DcMotorEx.class, "topLeftMotor");
        bottomRightMotor = hwMap.get(DcMotorEx.class, "bottomRightMotor");
        bottomLeftMotor = hwMap.get(DcMotorEx.class, "bottomLeftMotor");

        topRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        topRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void gamePadInputs(Gamepad gmpad)
    {



    }

}
