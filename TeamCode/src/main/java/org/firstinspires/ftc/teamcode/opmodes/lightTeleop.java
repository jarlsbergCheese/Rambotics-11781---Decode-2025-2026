package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import org.firstinspires.ftc.teamcode.subsytems.Lights;

@TeleOp(name="Light teleop")
public class lightTeleop extends OpMode {

    Lights light;

    public void init() {
        ServoControllerEx controller = hardwareMap.get(ServoControllerEx.class, "Control Hub");
        light = new Lights(controller, 0);
    }
    public void loop() {
        telemetry.addData("Pattern:", light.pattern);
    }
}