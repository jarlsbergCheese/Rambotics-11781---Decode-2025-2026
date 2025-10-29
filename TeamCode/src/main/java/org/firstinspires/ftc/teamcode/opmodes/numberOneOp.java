package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsytems.Odometry;

@TeleOp (name = "kentucky fried chicken is all i see")
public class numberOneOp extends OpMode
{
    Gamepad youCanThinkPad = gamepad1;

    public DcMotorEx rightFront;
    public DcMotorEx leftFront;
    public DcMotorEx rightRear;
    public DcMotorEx leftRear;

    Odometry odo;

    public void init()
    {
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");

        odo = new Odometry(hardwareMap);
    }

    public void loop()
    {
        float lx = youCanThinkPad.left_stick_x;
        float ly = youCanThinkPad.left_stick_y;
        float rx = youCanThinkPad.right_stick_x;

        if (ly > 0) {
            rightFront.setPower(ly);
            leftFront.setPower(ly);
            rightRear.setPower(ly);
            leftRear.setPower(ly);
        }
        else if (ly < 0)
        {
            rightFront.setPower(-ly);
            leftFront.setPower(-ly);
            rightRear.setPower(-ly);
            leftRear.setPower(-ly);
        }

        if (lx > 0)
        {
            rightFront.setPower(-lx);
            leftFront.setPower(lx);
            rightRear.setPower(lx);
            leftRear.setPower(-lx);
        }
        else if (lx < 0)
        {
            rightFront.setPower(lx);
            leftFront.setPower(-lx);
            rightRear.setPower(-lx);
            leftRear.setPower(lx);
        }

        if (rx > 0)
        {
            rightFront.setPower(-rx);
            leftFront.setPower(rx);
            rightRear.setPower(-rx);
            leftRear.setPower(rx);
        }
        else if (rx < 0)
        {
            rightFront.setPower(rx);
            leftFront.setPower(-rx);
            rightRear.setPower(rx);
            leftRear.setPower(-rx);
        }

        if (youCanThinkPad.a)
        {
            if (odo.cur0 <= 180)
            {
                rightFront.setPower(-lx);
                leftFront.setPower(lx);
                rightRear.setPower(lx);
                leftRear.setPower(-lx);
            }
            else
            {
                System.out.println("skibidi toilet");
            }
        }

    }
}