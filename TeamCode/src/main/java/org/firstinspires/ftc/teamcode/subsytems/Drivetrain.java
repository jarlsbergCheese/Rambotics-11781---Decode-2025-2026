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

    double xt;
    double yt;

    double x;
    double y;
    double rx;

    double max;

    public Drivetrain(HardwareMap hwMap)
    {
        topRightMotor = hwMap.get(DcMotorEx.class, "topRightMotor");
        topLeftMotor = hwMap.get(DcMotorEx.class, "topLeftMotor");
        bottomRightMotor = hwMap.get(DcMotorEx.class, "bottomRightMotor");
        bottomLeftMotor = hwMap.get(DcMotorEx.class, "bottomLeftMotor");

        topRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        topRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void gamePadInputs(Gamepad gmpad, double heading)
    {

    xt = gmpad.left_stick_x;
    yt = -gmpad.left_stick_y;
    rx = gmpad.right_stick_x;

    x = (xt * Math.cos(heading) - (yt * Math.sin(heading)));
    y = (xt * Math.sin(heading) + (yt * Math.cos(heading)));

    double topRightPower = y+x+rx;
    double topLeftPower = y-x-rx;
    double bottomRightPower = y-x+rx;
    double bottomLeftPower = y+x-rx;

    max = Math.max(Math.abs(topLeftPower), Math.abs(topRightPower));
    max = Math.max(max, Math.abs(bottomLeftPower));
    max = Math.max(max, Math.abs(bottomRightPower));

    if (max > 1.0) {
        topLeftPower  /= max;
        topRightPower /= max;
        bottomRightPower  /= max;
        bottomLeftPower  /= max;
    }

    topRightMotor.setPower(topRightPower*0.9);
    topLeftMotor.setPower(topLeftPower*0.9);
    bottomRightMotor.setPower(bottomRightPower*0.9);
    bottomLeftMotor.setPower(bottomLeftPower*0.9);

    }

}
