package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsytems.Luncher;

@TeleOp(name="nomnomnom")
public class luncherTest extends OpMode {

    public Luncher lunch;

    public Servo holder;

    @Override
    public void init() {

        holder = hardwareMap.get(Servo.class, "holder");

        lunch = new Luncher(hardwareMap);

    }

    @Override
    public void loop() {

        lunch.gamepadInputs(gamepad1);

    }
}
