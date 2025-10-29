package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsytems.Luncher;

@TeleOp(name="nomnomnom")
public class luncherTest extends OpMode {

    public Luncher lunch;

    @Override
    public void init() {

    lunch = new Luncher(hardwareMap);

    }

    @Override
    public void loop() {

        lunch.gamepadInputs(gamepad1);

    }
}
