package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsytems.Odometry;

@TeleOp(name="SWISS NEVER MISS")
public class tuningAuto extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;


    @Override
    public void init()
    {
        drivetrain = new Drivetrain(hardwareMap);
        odo = new Odometry(hardwareMap);

        odo.resetEncoders();

    }

    @Override
    public void loop()
    {
        drivetrain.gamePadInputs(gamepad1, odo.cur0);
        odo.gamepadInputs(gamepad1);
        odo.updateCurPos();

        telemetry.addData("Rotation", Math.toDegrees(odo.cur0));

    }
}
