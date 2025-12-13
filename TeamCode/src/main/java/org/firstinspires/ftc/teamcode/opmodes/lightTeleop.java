package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Light teleop")
public class lightTeleop extends OpMode {

    RevBlinkinLedDriver led;
    RevBlinkinLedDriver.BlinkinPattern pattern = RevBlinkinLedDriver.BlinkinPattern.HOT_PINK;

    public void init() {
        led = hardwareMap.get(RevBlinkinLedDriver.class, "RevBlinkinLedDriver");
        led.setPattern(pattern);
    }
    public void loop() {
        telemetry.addData("Pattern: ", pattern);
    }
}