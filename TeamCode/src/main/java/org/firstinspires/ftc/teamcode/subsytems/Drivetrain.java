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

    double Xmov = 0.0;
    double Ymov = 0.0;
    double rXmov = 0.0;

    double max;

    public Drivetrain(HardwareMap hwMap)
    {
        topRightMotor = hwMap.get(DcMotorEx.class, "topRightMotor");
        topLeftMotor = hwMap.get(DcMotorEx.class, "topLeftMotor");
        bottomRightMotor = hwMap.get(DcMotorEx.class, "bottomRightMotor");
        bottomLeftMotor = hwMap.get(DcMotorEx.class, "bottomLeftMotor");

        topLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

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

    x = (xt * Math.cos(Math.toRadians(heading)) - (yt * Math.sin(Math.toRadians(heading))));
    y = (xt * Math.sin(Math.toRadians(heading)) + (yt * Math.cos(Math.toRadians(heading))));

    double topLeftPower = y+x+rx;
    double topRightPower = y-x-rx;
    double bottomLeftPower = y-x+rx;
    double bottomRightPower = y+x-rx;


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

    public void autoSetter(double xp, double yp, double rXp){
        Xmov = xp;
        Ymov = yp;
        rXmov = rXp;
    }

    public void coordinateBasedState(double cur0p)
    {

        double Xmov2 = (Xmov * Math.cos(Math.toRadians(cur0p)) - (Ymov * Math.sin(Math.toRadians(cur0p))));
        double Ymov2 = (Xmov * Math.sin(Math.toRadians(cur0p)) + (Ymov * Math.cos(Math.toRadians(cur0p))));

        double leftFrontPower = Ymov2+Xmov2+rXmov;
        double rightFrontPower = Ymov2-Xmov2-rXmov;
        double leftBackPower = Ymov2-Xmov2+rXmov;
        double rightBackPower = Ymov2+Xmov2-rXmov;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

            topLeftMotor.setPower(leftFrontPower*0.9);
            topRightMotor.setPower(rightFrontPower*0.9);
            bottomLeftMotor.setPower(leftBackPower*0.9);
            bottomRightMotor.setPower(rightBackPower*0.9);

        /*
        LMFront.setPower(leftFrontPower);
        RMFront.setPower(rightFrontPower);
        LMBack.setPower(leftBackPower);
        RMBack.setPower(rightBackPower);
*/
    }

}
