package org.firstinspires.ftc.teamcode;

import android.net.http.SslCertificate;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import java.lang.*;

import static java.lang.Math.PI;

import java.lang.Math.*;

import java.lang.Math;
@Autonomous(name = "Auto")
public class TestAuto extends LinearOpMode {

    DcMotorEx frontLeftEx;
    DcMotorEx frontRightEx;
    DcMotorEx backLeftEx;
    DcMotorEx backRightEx;
    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * PI);

    public int inchestoticks(int inches) {
        double tickperInch = 360 / (3.77 * PI);
        return (int) (inches * tickperInch);//diameter of the wheel 3.77
    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if (isStopRequested()) {

        }
        if (opModeIsActive()) {
            frontLeftEx = hardwareMap.get(DcMotorEx.class, "frontLeft");
            frontRightEx = hardwareMap.get(DcMotorEx.class, "frontRight");
            backLeftEx = hardwareMap.get(DcMotorEx.class, "backLeft");
            backRightEx = hardwareMap.get(DcMotorEx.class, "backRight");

            backRightEx.setDirection(DcMotorEx.Direction.REVERSE);
            frontRightEx.setDirection(DcMotorEx.Direction.REVERSE);

            forward(20, .9);
            sleep(2000); // sleep stalls what is written before it for a set amount of time, it won't work if added to a method as a field
        }
    }

    public void forward(int inches, double power) {
        frontRightEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        frontLeftEx.setTargetPosition((int) (inchestoticks(inches)));
        frontRightEx.setTargetPosition((int) (inchestoticks(inches)));
        backRightEx.setTargetPosition((int) (inchestoticks(inches)));
        backLeftEx.setTargetPosition((int) (inchestoticks(inches)));


        frontRightEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeftEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontLeftEx.setVelocity(power);
            frontRightEx.setVelocity(power);
            backLeftEx.setVelocity(power);
            backRightEx.setVelocity(power);

        }
    }

    public void backward(int inches, double power) {
        frontRightEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLeftEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backRightEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        frontLeftEx.setTargetPosition((int) (inchestoticks(inches)));
        frontRightEx.setTargetPosition((int) (inchestoticks(inches)));
        backRightEx.setTargetPosition((int) (inchestoticks(inches)));
        backLeftEx.setTargetPosition((int) (inchestoticks(inches)));


        frontRightEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLeftEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLeftEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backRightEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontLeftEx.setVelocity(-power);
            frontRightEx.setVelocity(-power);
            backLeftEx.setVelocity(-power);
            backRightEx.setVelocity(-power);

        }
    }
        public void right (int inches, double power) {
            frontRightEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            frontLeftEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            backLeftEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            backRightEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


            frontLeftEx.setTargetPosition((int) (inchestoticks(inches)));
            frontRightEx.setTargetPosition((int) (inchestoticks(inches)));
            backRightEx.setTargetPosition((int) (inchestoticks(inches)));
            backLeftEx.setTargetPosition((int) (inchestoticks(inches)));


            frontRightEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            frontLeftEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            backLeftEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            backRightEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            if (inches >= 0) {
                frontLeftEx.setPower(power);
                frontRightEx.setPower(-power);
                backLeftEx.setPower(-power);
                backRightEx.setPower(power);

            }
    }
}