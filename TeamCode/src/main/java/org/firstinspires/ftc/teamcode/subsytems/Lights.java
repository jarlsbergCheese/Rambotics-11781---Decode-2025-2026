package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

public class Lights {



    public RevBlinkinLedDriver.BlinkinPattern pattern = RevBlinkinLedDriver.BlinkinPattern.HOT_PINK;

    public Lights(ServoControllerEx lightController, int physicalPort) {
        RevBlinkinLedDriver ledZeppelin = new RevBlinkinLedDriver(lightController, physicalPort);
        ledZeppelin.setPattern(pattern);
    }
}