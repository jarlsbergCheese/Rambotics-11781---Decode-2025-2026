package org.firstinspires.ftc.teamcode.opmodes.competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsytems.Luncher;
import org.firstinspires.ftc.teamcode.subsytems.Odometry;
import org.firstinspires.ftc.teamcode.subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.subsytems.targetDogs;

@Autonomous(name="autoCloseBlue")
public class autoCloseBlue extends OpMode {

    Drivetrain drivetrain;
    Odometry odo;
    Pathfinder path;
    Luncher luncher;
    DcMotorEx intake;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        luncher = new Luncher(hardwareMap);
        odo = new Odometry(hardwareMap);
        path = new Pathfinder(luncher, hardwareMap);

        odo.resetEncoders();

        path.targetPositions.add(new targetDogs(-1200, 0, 0));
        path.targetPositions.add(new targetDogs(-1200, 0, 0, "launch3"));
        path.targetPositions.add(new targetDogs(-1200, -500, 0, "launch3"));



        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {
        path.sequence(path.targetPositions, odo.curX, odo.curY, odo.cur0);
        drivetrain.autoSetter(path.x,path.y,path.theta);
        drivetrain.coordinateBasedState(odo.cur0);

        luncher.autoLaunching();
        odo.newUpdateCurPos();

        telemetry.addData("targetTheta", path.targetPositions.get(path.count).theta);
        telemetry.addData("thetaBase", path.curThetaBase);

        telemetry.addData("Theta Power", path.theta);


        telemetry.addData("thetaDistance", (path.targetPositions.get(path.count).theta)-path.curThetaBase);
        telemetry.addData("yOffset", (path.targetPositions.get(path.count).y - odo.curY));
        telemetry.addData("xOffset", (path.targetPositions.get(path.count).x - odo.curX));
    }

}
