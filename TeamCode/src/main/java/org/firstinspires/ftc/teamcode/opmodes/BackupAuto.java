package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsytems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsytems.Luncher;
import org.firstinspires.ftc.teamcode.subsytems.Odometry;
import org.firstinspires.ftc.teamcode.subsytems.Pathfinder;
import org.firstinspires.ftc.teamcode.subsytems.targetDogs;



@Autonomous(name="backup!!")
public class BackupAuto extends OpMode {

        Drivetrain drivetrain;
        Odometry odo;
        Pathfinder path;
        Luncher luncher;

        @Override
        public void init() {

            drivetrain = new Drivetrain(hardwareMap);
            luncher = new Luncher(hardwareMap);
            odo = new Odometry(hardwareMap);
            path = new Pathfinder(luncher, hardwareMap);

            odo.resetEncoders();

            path.targetPositions.add(new targetDogs(525, 250, 0));


        }

        @Override
        public void loop() {

            path.sequence(path.targetPositions, odo.curX, odo.curY, odo.cur0);
            drivetrain.autoSetter(path.x,path.y,path.theta);
            drivetrain.coordinateBasedState(odo.cur0);

            odo.updateCurPos();

        }
    }

