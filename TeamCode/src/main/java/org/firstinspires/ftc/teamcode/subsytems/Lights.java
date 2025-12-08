package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

public class Lights {
    public Lights(ServoControllerEx lightController, int physicalPort) {
        RevBlinkinLedDriver ledZeppelin = new RevBlinkinLedDriver(lightController, physicalPort);
        ledZeppelin.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }
}