package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name = "AutoRedLeft")
public class CrimsonAutoRedL extends LinearOpMode {
    public final static double MIN_POSITION = 0.2;
    public final static double MAX_POSITION = 0.7;

    DcMotor frontL;
    DcMotor frontR;
    DcMotor backL;
    DcMotor backR;
    Servo claw;

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
            S = hardwareMap.get(ColorSensor.class, "S");

            backR.setDirection(DcMotor.Direction.REVERSE);
            frontR.setDirection(DcMotor.Direction.REVERSE);

            frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            claw.setPosition(MAX_POSITION);
            sleep(5000);
            // claw.setPosition is speed

            forward(.4);
            sleep(150);
            rotateL(.5);
            sleep(640);

            forward(.7);
            sleep(500);
            backward(.7);
            sleep(300);

            rotateR(.4);
            sleep(1630);
            backward(.5);
            sleep(600);

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
    }

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