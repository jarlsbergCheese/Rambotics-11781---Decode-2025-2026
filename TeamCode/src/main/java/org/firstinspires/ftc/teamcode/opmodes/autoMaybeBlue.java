package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsytems.Luncher;
import org.firstinspires.ftc.teamcode.subsytems.Odometry;
import org.firstinspires.ftc.teamcode.subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.subsytems.targetDogs;

import java.util.ArrayList;

@Autonomous(name= "AutoBasicBlue")
public class autoMaybeBlue extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;
    Luncher luncher;

    ArrayList<targetDogs> sequence;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        luncher = new Luncher(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder(luncher, hardwareMap);

        odo.resetEncoders();

        path.targetPositions.add(new targetDogs(-750, 0, 0));
        path.targetPositions.add(new targetDogs(-750, 0, 0, "launch3"));
        path.targetPositions.add(new targetDogs(-750, -500, 0));

    }


    //Change the Y value a couple more mm when the comp comes because the drip buckets

    @Override
    public void loop() {

        path.sequence(path.targetPositions, odo.curX, odo.curY, odo.cur0);
        drivetrain.autoSetter(path.x,path.y,path.theta);
        drivetrain.coordinateBasedState(odo.cur0);


        telemetry.addData("x", path.x);
        telemetry.addData("y", path.y);

        telemetry.addData("what", "-----------------");

        telemetry.addData("curX", odo.curX);
        telemetry.addData("curY", odo.curY);
        telemetry.addData("thetaBase", path.curThetaBase);
        telemetry.addData("theta", odo.cur0);





        odo.updateCurPos();

    }


}
