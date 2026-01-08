package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "zero")
public class zeroServo extends OpMode {

    Servo wristServo;

    @Override
    public void init() {

        wristServo = hardwareMap.get(Servo.class, "holder");
        wristServo.setPosition(1) ;

    }
    //676767676767676767676767676767676767676767676767676767676767676767676767676767676767676767676
    //6767676767676767    6767676767676767676767                       6767676767676767676767676767
    //676767676767676     67676767676767676767676767676767676767676    6767676767676767676767676767
    //67676767676767     676767676767676767676767676767676767676767    6767676767676767676767676767
    //6767676767676     767676767676767676767676767676767676767676    767676767676767676767
    @Override
    public void loop() {

    if(gamepad1.x)
    {
        wristServo.setPosition(0.67) ;
    }

    }
}
