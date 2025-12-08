package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsytems.Luncher;
import org.firstinspires.ftc.teamcode.subsytems.Odometry;

import java.util.ArrayList;

import org.firstinspires.ftc.teamcode.subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.subsytems.targetDogs;

@Autonomous(name = "...")
public class AutoMaybe extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;
    Luncher luncher;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        luncher = new Luncher(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder(luncher);

        odo.resetEncoders();

        ArrayList<targetDogs> sequence = new ArrayList<>();

        path.targetPositions.add(new targetDogs(0, 0, 90));


    }

    @Override
    public void loop() {

        path.sequence(path.targetPositions, odo.curX, odo.curY, odo.cur0);
        drivetrain.autoSetter(path.x,path.y,path.theta);
        drivetrain.coordinateBasedState(odo.cur0);

        telemetry.addData("curX", odo.curX);
        telemetry.addData("curY", odo.curY);
        telemetry.addData("thetaBase", path.curThetaBase);
        telemetry.addData("theta", Math.toDegrees(odo.cur0));





        odo.updateCurPos();

    }
}
