package org.firstinspires.ftc.teamcode.opmodes.competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ClassUtil;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsytems.Luncher;
import org.firstinspires.ftc.teamcode.subsytems.Odometry;
import org.firstinspires.ftc.teamcode.subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.subsytems.targetDogs;

@Autonomous(name="autoCloseRed")
public class autoCloseRed extends OpMode {

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

        path.targetPositions.add(new targetDogs(0,0,0, "speedChange2"));
        path.targetPositions.add(new targetDogs(-500, -500, 47, "constantWheelOn"));
        path.targetPositions.add(new targetDogs(-500, -500, 47));
        path.targetPositions.add(new targetDogs(-500, -500, 47, "launch3"));

        path.targetPositions.add(new targetDogs(-630, -500, 90));
        path.targetPositions.add(new targetDogs(-630, -500, 90, "speedChange0.5"));
        path.targetPositions.add(new targetDogs(-630, -500, 90, "intakeStart"));
        path.targetPositions.add(new targetDogs(-630, 120, 90));
        path.targetPositions.add(new targetDogs(-630, 120, 90, "intakeEnd"));
        path.targetPositions.add(new targetDogs(-630, 120, 90, "speedChange2"));
        path.targetPositions.add(new targetDogs(-630, -500, 90));
        path.targetPositions.add(new targetDogs(-630, -500, 90, "unjam"));
        path.targetPositions.add(new targetDogs(-500, -500, 47));
        path.targetPositions.add(new targetDogs(-500, -500, 47, "launch3"));


        path.targetPositions.add(new targetDogs(-975, -500, 90));
        path.targetPositions.add(new targetDogs(-975, -500, 90, "speedChange0.5"));
        path.targetPositions.add(new targetDogs(-975, -500, 90, "intakeStart"));
        path.targetPositions.add(new targetDogs(-975, 225, 90));
        path.targetPositions.add(new targetDogs(-975, 225, 90, "intakeEnd"));
        path.targetPositions.add(new targetDogs(-975, 225, 90, "speedChange2"));
        path.targetPositions.add(new targetDogs(-975, -500, 90));
        path.targetPositions.add(new targetDogs(-975, -500, 90, "unjam"));
        path.targetPositions.add(new targetDogs(-500, -500, 45));
        path.targetPositions.add(new targetDogs(-500, -500, 45, "launch3"));


        path.targetPositions.add(new targetDogs(-1475, -500, 90));
        path.targetPositions.add(new targetDogs(-1475, -500, 90, "speedChange1"));
        path.targetPositions.add(new targetDogs(-1475, -500, 90, "intakeStart"));
        path.targetPositions.add(new targetDogs(-1475, 250, 90));
        path.targetPositions.add(new targetDogs(-1475, -500, 90));
        path.targetPositions.add(new targetDogs(-1475, -500, 90, "wait"));
        path.targetPositions.add(new targetDogs(-1475, -500, 90, "unjam"));
        path.targetPositions.add(new targetDogs(-1475, -500, 90, "speedChange2"));
        path.targetPositions.add(new targetDogs(-500, -500, 45));

        path.targetPositions.add(new targetDogs(-500, -500, 45, "launch3"));

















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

        telemetry.addData("eventing?", path.runningEvent);
    }

}
