package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.*;
import com.qualcomm.robotcore.hardware.ColorSensor;

import static java.lang.Math.PI;


@Autonomous(name = "Auto")
public class CrimsonAutoRight extends LinearOpMode {

    DcMotor frontL;
    DcMotor frontR;
    DcMotor backL;
    DcMotor backR;

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
            S = hardwareMap.get(ColorSensor.class, "S");

            backR.setDirection(DcMotor.Direction.REVERSE);
            frontR.setDirection(DcMotor.Direction.REVERSE);

            frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            rotateR(.46);
            sleep(2000);
        }
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
        backL.setPower(power);
        backR.setPower(-power);
    }

    public void right(double power) {
        frontL.setPower(-power);
        frontR.setPower(power);
        backL.setPower(-power);
        backR.setPower(power);
    }

    public void rotateR(double power) {
        frontL.setPower(power);
        frontR.setPower(-power);
        backL.setPower(power);
        backR.setPower(-power);
    }
    public void rotateL(double power) {
        frontL.setPower(-power);
        frontR.setPower(power);
        backL.setPower(-power);
        backR.setPower(power);
    }
}