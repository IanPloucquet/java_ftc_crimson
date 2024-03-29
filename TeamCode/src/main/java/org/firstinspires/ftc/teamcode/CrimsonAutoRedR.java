package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name = "AutoRedRight")
public class CrimsonAutoRedR extends LinearOpMode {

    public final static double MIN_POSITION = 0.2;
    public final static double MAX_POSITION = 0.7;

    DcMotor frontL;
    DcMotor frontR;
    DcMotor backL;
    DcMotor backR;


    Servo claw;
    DcMotorEx arm;
    Servo hinge;


    ColorSensor S;

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
            arm = hardwareMap.get(DcMotorEx.class, "arm");
            hinge = hardwareMap.get(Servo.class,"hinge");


            S = hardwareMap.get(ColorSensor.class, "S");

            backR.setDirection(DcMotor.Direction.REVERSE);
            frontR.setDirection(DcMotor.Direction.REVERSE);

            frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


            claw.setPosition(MIN_POSITION);
            sleep(2800);

            forward(0.2);
            sleep(200);

            left(0.4);
            sleep(1000);

            forward(0.4);
            sleep(1000);

            rotateL(0.2);
            sleep(200);

            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            arm.setTargetPosition(900);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setVelocity(1000);

            hinge.setPosition(0.7);
            sleep(4000);
            forward(0.2);
            sleep(400);

            claw.setPosition(MAX_POSITION);
            sleep(200);

                while (S.red() > S.blue() + S.green()) {
                    backward(.7);
                    sleep(500);
                    left(.7);
                    sleep(400);
                    forward(.7);
                    sleep(500);
                    forward(0);

                }
                while (S.blue() > 0) {
                    forward(0.5);
                    sleep(500);
                }
                while (S.green() > 0) {
                left(0.5);
                sleep(500);
                }
                {

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