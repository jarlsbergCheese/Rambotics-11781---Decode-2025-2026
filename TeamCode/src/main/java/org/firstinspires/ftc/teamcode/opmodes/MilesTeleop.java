package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "miles")
public class MilesTeleop extends OpMode {

    Gamepad gmpad;


    DcMotorEx topLeftMotor;
    DcMotorEx topRightMotor;
    DcMotorEx bottomLeftMotor;
    DcMotorEx bottomRightMotor;

    float x;
    float y;
    float rx;

    float topLeftPower;
    float topRightPower;
    float bottomLeftPower;
    float bottomRightPower;

    float max;

    boolean precisionMode;

    public void precisionToggle(Gamepad toggle){
        if (toggle.a){
            precisionMode = true;
        }
        else if (toggle.b){
            precisionMode = false;
        }
    }

    @Override
    public void init() {
        topLeftMotor = hardwareMap.get(DcMotorEx.class,"leftFront");
        topRightMotor = hardwareMap.get(DcMotorEx.class,"rightFront");
        bottomLeftMotor = hardwareMap.get(DcMotorEx.class,"rightFront");
        bottomRightMotor = hardwareMap.get(DcMotorEx.class,"rightRear");

        gmpad = gamepad1;

        topRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        topLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        topRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//To whomever shall be reading this in the near future... i got nothing. GAH CAN THIS THING WORKKKK!!!1!!
// GAAHAAHAHGH PLEASE WORK THIS TIME!!!111!! this is a message fro caleb god save anerica please
    }

    // Runs every frame
    @Override
    public void loop() {

        x = gmpad.left_stick_x;
        y = -gmpad.left_stick_y;
        rx = gmpad.right_stick_x;

        topRightPower = (y-x-rx);
        topLeftPower = (y+x+rx);
        bottomRightPower = (y+x-rx);
        bottomLeftPower = (y-x+rx);

        // Finds the largest value for normalization
        max = Math.max(topRightPower, topLeftPower);
        max = Math.max(max,bottomRightPower);
        max = Math.max(max, bottomLeftPower);

        // aformentioned normalization
        if (max > 1.0 )
        {
            topRightPower /= max;
            topLeftPower /= max;
            bottomRightPower /= max;
            bottomLeftPower /= max;
        }

        precisionToggle(gmpad);
        if (precisionMode){
            topRightMotor.setPower(topRightPower/2);
            topLeftMotor.setPower(topLeftPower/2);
            bottomRightMotor.setPower(bottomRightPower/2);
            bottomLeftMotor.setPower(bottomLeftPower/2);
        }
        else {
            topRightMotor.setPower(topRightPower);
            topLeftMotor.setPower(topLeftPower);
            bottomRightMotor.setPower(bottomRightPower);
            bottomLeftMotor.setPower(bottomLeftPower);
        }
    }
}
