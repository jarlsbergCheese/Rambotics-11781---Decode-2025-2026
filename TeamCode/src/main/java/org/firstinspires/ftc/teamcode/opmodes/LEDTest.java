package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;

@TeleOp(name="LED Test")
public class LEDTest extends OpMode {

    RevBlinkinLedDriver led;
    RevBlinkinLedDriver.BlinkinPattern pattern = RevBlinkinLedDriver.BlinkinPattern.BLACK;

    private static final Random random = new Random();
    private RevBlinkinLedDriver.BlinkinPattern randomPattern() {
        RevBlinkinLedDriver.BlinkinPattern[] patterns = RevBlinkinLedDriver.BlinkinPattern.values();
        return patterns[random.nextInt(patterns.length)];
    }

    // This @ is so then the code editor doesn't whine when I use scheduleAtFixedRate. Apparently
    // scheduleAtFixedRate is bad, but I lowkey don't care. If the lights tweak then the lights
    // tweak. I can fix it later
    @SuppressLint("DiscouragedApi")

    public void init() {
        led = hardwareMap.get(RevBlinkinLedDriver.class, "led");
        led.setPattern(pattern);

        Timer lightTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                pattern = randomPattern();
            }
        };
        lightTimer.scheduleAtFixedRate(task, 5000, 5000);

    }
    public void loop() {
        led.setPattern(pattern);
        telemetry.addData("Pattern", pattern);
    }
}