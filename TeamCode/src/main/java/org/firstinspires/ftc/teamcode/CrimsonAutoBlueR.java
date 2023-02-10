package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name = "AutoBlueRight")
public class CrimsonAutoBlueR extends LinearOpMode {

    public final static double MIN_POSITION = 0.2;
    public final static double MAX_POSITION = 0.7;

    DcMotor frontL;
    DcMotor frontR;
    DcMotor backL;
    DcMotor backR;
    Servo hinge;
    Servo claw;
    DcMotorEx arm;
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
            arm = hardwareMap.get(DcMotorEx.class, "arm");
            claw = hardwareMap.get(Servo.class, "claw");
            hinge = hardwareMap.get(Servo.class, "hinge");
            S = hardwareMap.get(ColorSensor.class, "S");

            backR.setDirection(DcMotor.Direction.REVERSE);
            frontR.setDirection(DcMotor.Direction.REVERSE);

            frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



            claw.setPosition(.6);
            sleep(100);

            forward(0.3);
            sleep(220);
            rotateR(0.3);
            sleep(1250);
            forward(0.4);
            sleep(970);
            rotateL(0.3);
            sleep(1150);

            arm.setTargetPosition(950);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setVelocity(1000);

            forward(0.3);
            sleep(1350);

            rotateR(0.3);
            sleep(575);
            forward(0.3);
            sleep(100);

            hinge.setPosition(0.9);
            sleep(700);

            hinge.setPosition(.2);
            sleep(700);

            claw.setPosition(.4);
            sleep(300);

            backward(.4);
            sleep(500);

            stop(0);
            sleep(4000);
            /*
            rotateL(0.3);
            sleep(575);
            forward(0.3);
            sleep(500);
            rotateL(0.3);
            sleep(1200);

            arm.setTargetPosition(170);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setVelocity(1000);

            hinge.setPosition(0.1);
            sleep(4000);

            forward(0.4);
            sleep(2800);

            claw.setPosition(0.3);
            sleep(5000);


            */


            /*
            int red = S.red();
            int green = S.green();
            int blue = S.blue();

            if (red > green && red > blue && red > .5) {
                forward(.7);
                sleep(500);
                left(.7);
                sleep(400);
                forward(.7);
                sleep(500);
            }


            else if (blue > green && blue > red && blue > .5) {
                rotateR(0.3);
                sleep(450);
                backward(0.3);
                sleep(1000);
            }



            else if (green > blue && green > red) {
                left(0.5);
                sleep(500);
            }
            */
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

    public void stop(double power) {
        frontL.setPower(0);
        frontR.setPower(0);
        backL.setPower(0);
        backR.setPower(0);
    }
}