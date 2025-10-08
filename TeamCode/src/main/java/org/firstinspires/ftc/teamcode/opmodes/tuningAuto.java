package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsytems.Odometry;

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
    }
}
