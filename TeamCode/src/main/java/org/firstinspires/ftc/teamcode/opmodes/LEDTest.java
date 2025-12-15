package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.Timer;
import java.util.TimerTask;

@TeleOp(name="LED Test")
public class LEDTest extends OpMode {

    RevBlinkinLedDriver led;
    RevBlinkinLedDriver.BlinkinPattern pattern = RevBlinkinLedDriver.BlinkinPattern.RAINBOW_OCEAN_PALETTE;

    public void init() {
        led = hardwareMap.get(RevBlinkinLedDriver.class, "led");
        led.setPattern(pattern);
        Timer lightTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                pattern = RevBlinkinLedDriver.BlinkinPattern.BLACK;
            }
        };
        lightTimer.schedule(task, 5000);
    }
    public void loop() {
        led.setPattern(pattern);
        telemetry.addData("Pattern", pattern);
    }
}