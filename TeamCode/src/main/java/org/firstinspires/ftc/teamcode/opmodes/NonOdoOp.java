package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsytems.JulyTag;
import org.firstinspires.ftc.teamcode.subsytems.Luncher;
import org.firstinspires.ftc.teamcode.subsytems.Odometry;

@TeleOp(name = "NoODO?")
public class NonOdoOp extends OpMode
{
    Drivetrain drivetrain;
    Odometry odo;
    JulyTag camera;
    Luncher lunch;

    DcMotorEx intake;

    @Override
    public void init()
    {
        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);
        lunch = new Luncher(hardwareMap);
        //camera = new JulyTag(hardwareMap);

        odo.resetEncoders();

        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    @Override
    public void loop()
    {
        drivetrain.gamePadInputs(gamepad1, 0);
        odo.gamepadInputs(gamepad1);
        odo.updateCurPos();
        lunch.gamepadInputs(gamepad1);
        //camera.update(gamepad1);

        telemetry.addData("Rotation", Math.toDegrees(odo.cur0));

        telemetry.addData("ballque", lunch.ballQue);
        telemetry.addData("launched", lunch.launchedBalls);

        //telemetry.addData("current april tag ids: ", camera.curCode);

        if(gamepad1.left_trigger > 0)
        {
            intake.setPower(gamepad1.left_trigger);
        }
        else
        {
            intake.setPower(0);
        }

    }


}
