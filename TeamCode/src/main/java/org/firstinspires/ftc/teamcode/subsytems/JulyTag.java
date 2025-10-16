package org.firstinspires.ftc.teamcode.subsytems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class JulyTag {

    // Variable that figures out information from april tags
    private AprilTagProcessor johnCamera;

    private VisionPortal bert;

    public int curCode;

    JulyTag(HardwareMap hwMap)
    {
        johnCamera = new AprilTagProcessor.Builder()
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .build();

        bert = new VisionPortal.Builder()
                .setCamera(hwMap.get(WebcamName.class, "Webcam"))
                .addProcessor(johnCamera)
                .build();
    }

    public void update(Gamepad gmpad)
    {
        curCode = johnCamera.getDetections().get(0).id;
    }

//hey

}
