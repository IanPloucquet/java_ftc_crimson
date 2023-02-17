package org.firstinspires.ftc.teamcode;
//  PLEASE NOTE: this is not final and contains some incomplete ideas
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.*;
import com.qualcomm.robotcore.hardware.ColorSensor;

import static java.lang.Math.PI;


@Autonomous(name = "Auto")
public class CrimsonAutoTest extends LinearOpMode {

    DcMotorEx frontL;
    DcMotorEx frontR;
    DcMotorEx backL;
    DcMotorEx backR;
    DcMotorEx arm;
    Servo claw;
    Servo hinge;

    ColorSensor S;

    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 3.5;     // For figuring circumference
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
            frontL = hardwareMap.get(DcMotorEx.class, "frontL");
            frontR = hardwareMap.get(DcMotorEx.class, "frontR");
            backL = hardwareMap.get(DcMotorEx.class, "backL");
            backR = hardwareMap.get(DcMotorEx.class, "backR");
            arm = hardwareMap.get(DcMotorEx.class, "arm");
           // S = hardwareMap.get(ColorSensor.class, "S");

            backR.setDirection(DcMotorEx.Direction.REVERSE);
            frontR.setDirection(DcMotorEx.Direction.REVERSE);
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            /*claw.setPosition(.6);
            sleep(100);
            forward(1, 500);
            right(20,1440);
            forward(24,1440);
            rotateR(4, 1440);

            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            arm.setTargetPosition(950);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setVelocity(1000);

            hinge.setPosition(0.9);
            sleep(700);

            hinge.setPosition(.2);
            sleep(700);

            claw.setPosition(.4);
            sleep(300);

            */
            /*
            forward(3, 1200);
            left(2, 1400);
            forward(1, 1200);
            */
            //variable power is used to tell the motors how many rotations it should go in a second (1440 ticks is a full 360 degrees)
            /*
            int red = S.red();
            int green = S.green();
            int blue = S.blue();
            telemetry.addData("Red: ", S.red());
            telemetry.update();
            telemetry.addData("Blue: ", S.blue());
            telemetry.update();
            telemetry.addData("Green: ", S.green());
            telemetry.update();

            if (red > blue && red > green) {
                right(1, 500);
                forward(2, 500);
                right(5, 500);
                forward(2, 500);
            }


            if (blue > red && blue > green) {
                right(1, 500);
                forward(3, 500);
            }

            if (green > blue && green > red) {
                right(1, 500);
                forward(2, 500);
                left(5, 500);
                forward(2, 500);
            }
             */

        }
    }

    public void forward(int inches, double power) {
        frontR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        //stops and resets the encoders in order for them to read and execute code from your current position
        frontL.setTargetPosition(inchestoticks(inches));
        frontR.setTargetPosition(inchestoticks(inches));
        backR.setTargetPosition(inchestoticks(inches));
        backL.setTargetPosition(inchestoticks(inches));
        //set the target position to go a distance in inches via the inchestoticks class created before
        frontR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontL.setVelocity(power);
            frontR.setVelocity(power);
            backL.setVelocity(power);
            backR.setVelocity(power);
        }

        //while loop: all motors are busy -> {}
        while (frontL.isBusy() && frontR.isBusy() && backL.isBusy() && backR.isBusy()) {

        }

        frontL.setVelocity(0);
        frontR.setVelocity(0);
        backL.setVelocity(0);
        backR.setVelocity(0);
    }

    public void backward(int inches, double power) {
        frontR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontL.setTargetPosition(inchestoticks(-inches));
        frontR.setTargetPosition(inchestoticks(-inches));
        backR.setTargetPosition(inchestoticks(-inches));
        backL.setTargetPosition(inchestoticks(-inches));

        frontR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontL.setVelocity(-power);
            frontR.setVelocity(-power);
            backL.setVelocity(-power);
            backR.setVelocity(-power);
        }
        while (frontL.isBusy() && frontR.isBusy() && backL.isBusy() && backR.isBusy()) {

        }
        frontL.setVelocity(0);
        frontR.setVelocity(0);
        backL.setVelocity(0);
        backR.setVelocity(0);
    }

    public void left(int inches, double power) {
        frontR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontL.setTargetPosition(inchestoticks(inches));
        frontR.setTargetPosition(inchestoticks(-inches));
        backR.setTargetPosition(inchestoticks(inches));
        backL.setTargetPosition(inchestoticks(-inches));

        frontR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontL.setVelocity(power);
            frontR.setVelocity(-power);
            backL.setVelocity(-power);
            backR.setVelocity(power);
        }

        while (frontL.isBusy() && frontR.isBusy() && backL.isBusy() && backR.isBusy()) {

        }

        frontL.setVelocity(0);
        frontR.setVelocity(0);
        backL.setVelocity(0);
        backR.setVelocity(0);
    }

    public void right(int inches, double power) {
        frontR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontL.setTargetPosition(inchestoticks(-inches));
        frontR.setTargetPosition(inchestoticks(inches));
        backR.setTargetPosition(inchestoticks(-inches));
        backL.setTargetPosition(inchestoticks(inches));

        frontR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontL.setVelocity(-power);
            frontR.setVelocity(power);
            backL.setVelocity(power);
            backR.setVelocity(-power);
        }

        while (frontL.isBusy() && frontR.isBusy() && backL.isBusy() && backR.isBusy()) {

        }

        frontL.setVelocity(0);
        frontR.setVelocity(0);
        backL.setVelocity(0);
        backR.setVelocity(0);
    }
    public void rotateR(int inches, double power) {
        frontR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontL.setTargetPosition(inchestoticks(-inches));
        frontR.setTargetPosition(inchestoticks(inches));
        backR.setTargetPosition(inchestoticks(-inches));
        backL.setTargetPosition(inchestoticks(inches));

        frontR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontL.setVelocity(-power);
            frontR.setVelocity(power);
            backL.setVelocity(-power);
            backR.setVelocity(power);
        }

        while (frontL.isBusy() && frontR.isBusy() && backL.isBusy() && backR.isBusy()) {

        }

        frontL.setVelocity(0);
        frontR.setVelocity(0);
        backL.setVelocity(0);
        backR.setVelocity(0);
    }
    public void rotateL(int inches, double power) {
        frontR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        frontL.setTargetPosition(inchestoticks(-inches));
        frontR.setTargetPosition(inchestoticks(inches));
        backR.setTargetPosition(inchestoticks(-inches));
        backL.setTargetPosition(inchestoticks(inches));

        frontR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        frontL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backL.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        backR.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        if (inches >= 0) {
            frontL.setVelocity(power);
            frontR.setVelocity(-power);
            backL.setVelocity(power);
            backR.setVelocity(-power);
        }

        while (frontL.isBusy() && frontR.isBusy() && backL.isBusy() && backR.isBusy()) {

        }

        frontL.setVelocity(0);
        frontR.setVelocity(0);
        backL.setVelocity(0);
        backR.setVelocity(0);
    }
}
