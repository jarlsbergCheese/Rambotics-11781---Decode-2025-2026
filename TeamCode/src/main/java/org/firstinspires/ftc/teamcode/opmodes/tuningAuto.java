package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;

public class tuningAuto extends OpMode {

    Drivetrain drivetrain;

    //nowifi

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
    }

    @Override
    public void loop() {

        drivetrain.gamePadInputs(gamepad1, 0);
    }
}
