package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import org.firstinspires.ftc.teamcode.subsytems.Lights;

@TeleOp(name="Light teleop")
public class lightTeleop extends OpMode {

    Lights light;

    public void init() {
        RevBlinkinLedDriver light = hardwareMap.get(RevBlinkinLedDriver.class, "light");
        light.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);

    }
    public void loop() {

    }
}
