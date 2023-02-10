package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name = "AutoBlueLeft")
public class CrimsonAutoBlueL extends LinearOpMode {

    public final static double MIN_POSITION = 0.2;
    public final static double MAX_POSITION = 0.7;

    private DcMotor frontL;
    private DcMotor frontR;
    private DcMotor backL;
    private DcMotor backR;
    private Servo hinge;
    private Servo claw;

    private ColorSensor S;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if (isStopRequested()) {

        }
        if (opModeIsActive()) {
            frontL = hardwareMap.get(DcMotor.class, "frontL");
            frontR = hardwareMap.get(DcMotor.class, "frontR");
            backL = hardwareMap.get(DcMotor.class, "backL");
            backR = hardwareMap.get(DcMotor.class, "backR");
            claw = hardwareMap.get(Servo.class, "claw");
            S = hardwareMap.get(ColorSensor.class, "S");

            backR.setDirection(DcMotor.Direction.REVERSE);
            frontR.setDirection(DcMotor.Direction.REVERSE);

            frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            hinge.setPosition(MAX_POSITION);
            sleep(5000);
            claw.setPosition(MAX_POSITION);
            sleep(5000);      //to keep claw and hinge in place

            left();                     //get to closest ground junction
            sleep();
            forward();
            sleep();                    //position the claw for ground junction

            hinge.setPosition();
            sleep();
            claw.setPosition(MIN_POSITION);
            sleep();
            hinge.setPosition(MAX_POSITION);
            sleep();                    //place preloaded cone and reset hinge

            right();
            sleep();
            forward();
            sleep();                    //move to colored sleeve to read

            if (S.red() > 0) {
                backward(.7);
                sleep(500);
                left(.7);
                sleep(400);
                forward(.7);
                sleep(500);
            }
            else if (S.blue() > 0) {

            }
            else if (S.green() > 0) {
                left(0.5);
                sleep(500);
            }
            else {

            }
        }
    }

            /*for 90 degree rotation in any direction
              set power to around .5
              for 640 milliseconds*/
            /*for 180 degree rotation in any direction
              set power to around .5
              for 1630 milliseconds*/
            /*for 270 degree rotation in any direction
              set power to around .5
              for 1860 milliseconds*/


    public void forward(double power) {
        frontL.setPower(power);
        frontR.setPower(power);
        backL.setPower(power);
        backR.setPower(power);
    }

    public void backward(double power) {
        frontL.setPower(-power);
        frontR.setPower(-power);
        backL.setPower(-power);
        backR.setPower(-power);
    }

    public void left(double power) {
        frontL.setPower(power);
        frontR.setPower(-power);
        backL.setPower(-power);
        backR.setPower(power);
    }

    public void right(double power) {
        frontL.setPower(-power);
        frontR.setPower(power);
        backL.setPower(power);
        backR.setPower(-power);
    }

    public void rotateR(double power) {
        frontL.setPower(-power);
        frontR.setPower(power);
        backL.setPower(-power);
        backR.setPower(power);
    }
    public void rotateL(double power) {
        frontL.setPower(power);
        frontR.setPower(-power);
        backL.setPower(power);
        backR.setPower(-power);
    }
}