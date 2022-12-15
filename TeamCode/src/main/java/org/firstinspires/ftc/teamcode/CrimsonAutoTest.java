package org.firstinspires.ftc.teamcode;
//  PLEASE NOTE: this is not final and has ideas that have not been complete
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import java.lang.*;
import com.qualcomm.robotcore.hardware.ColorSensor;

import static java.lang.Math.PI;


@Autonomous(name = "Auto")
public class CrimsonAutoTest extends LinearOpMode {

    DcMotorEx frontLEx;
    DcMotorEx frontREx;
    DcMotorEx backLEx;
    DcMotorEx backREx;

    ColorSensor S;

    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * PI);

    public int inchestoticks(int inches) {
        return (int) (inches * COUNTS_PER_INCH);//diameter of the wheel 3.77

    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if (isStopRequested()) {

        }
        if (opModeIsActive()) {
            frontLEx = hardwareMap.get(DcMotorEx.class, "frontL");
            frontREx = hardwareMap.get(DcMotorEx.class, "frontR");
            backLEx = hardwareMap.get(DcMotorEx.class, "backL");
            backREx = hardwareMap.get(DcMotorEx.class, "backR");
            S = hardwareMap.get(ColorSensor.class, "S");

            backREx.setDirection(DcMotorEx.Direction.REVERSE);
            frontREx.setDirection(DcMotorEx.Direction.REVERSE);

            forward(7, 2000);
            backward(7,2000);

            if (S.blue() < 20) {

            }
            if (S.red() < 20) {

            }
            if (S.green() < 20) {

            }
        }
    }

    public void forward(int inches, double power) {
        frontREx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backREx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontLEx.setTargetPosition(inchestoticks(inches));
        frontREx.setTargetPosition(inchestoticks(inches));
        backREx.setTargetPosition(inchestoticks(inches));
        backLEx.setTargetPosition(inchestoticks(inches));

        frontREx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backREx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            if (inches >= 0) {
                frontLEx.setVelocity(power);
                frontREx.setVelocity(power);
                backLEx.setVelocity(power);
                backREx.setVelocity(power);
            }

            //while loop: all motors are busy -> {}
            while (frontLEx.isBusy() && frontREx.isBusy() && backLEx.isBusy() && backREx.isBusy()) {

            }
        frontLEx.setVelocity(0);
        frontREx.setVelocity(0);
        backLEx.setVelocity(0);
        backREx.setVelocity(0);
    }

    public void backward(int inches, double power) {
        frontREx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backREx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontLEx.setTargetPosition(inchestoticks(-inches));
        frontREx.setTargetPosition(inchestoticks(-inches));
        backREx.setTargetPosition(inchestoticks(-inches));
        backLEx.setTargetPosition(inchestoticks(-inches));

        frontREx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backREx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontLEx.setVelocity(-power);
            frontREx.setVelocity(-power);
            backLEx.setVelocity(-power);
            backREx.setVelocity(-power);
        }
        while (frontLEx.isBusy() && frontREx.isBusy() && backLEx.isBusy() && backREx.isBusy()) {

        }
        frontLEx.setVelocity(0);
        frontREx.setVelocity(0);
        backLEx.setVelocity(0);
        backREx.setVelocity(0);
    }

    public void left(int inches, double power) {
        frontREx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backREx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        frontLEx.setTargetPosition(inchestoticks(inches));
        frontREx.setTargetPosition(inchestoticks(-inches));
        backREx.setTargetPosition(inchestoticks(inches));
        backLEx.setTargetPosition(inchestoticks(-inches));


        frontREx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backREx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            if (inches >= 0) {
                frontLEx.setVelocity(power);
                frontREx.setVelocity(-power);
                backLEx.setVelocity(power);
                backREx.setVelocity(-power);
            }

            while (frontLEx.isBusy() && frontREx.isBusy() && backLEx.isBusy() && backREx.isBusy()) {

            }
        frontLEx.setVelocity(0);
        frontREx.setVelocity(0);
        backLEx.setVelocity(0);
        backREx.setVelocity(0);
    }

    public void right(int inches, double power) {
        frontREx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontLEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backLEx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backREx.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        frontLEx.setTargetPosition(inchestoticks(-inches));
        frontREx.setTargetPosition(inchestoticks(inches));
        backREx.setTargetPosition(inchestoticks(-inches));
        backLEx.setTargetPosition(inchestoticks(inches));


        frontREx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontLEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backLEx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backREx.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

            if (inches >= 0) {
                frontLEx.setVelocity(-power);
                frontREx.setVelocity(power);
                backLEx.setVelocity(-power);
                backREx.setVelocity(power);
            }

            while (frontLEx.isBusy() && frontREx.isBusy() && backLEx.isBusy() && backREx.isBusy()) {

            }
        frontLEx.setVelocity(0);
        frontREx.setVelocity(0);
        backLEx.setVelocity(0);
        backREx.setVelocity(0);
    }
}
