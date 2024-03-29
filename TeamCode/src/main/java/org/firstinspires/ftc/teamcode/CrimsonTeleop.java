 package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp
public class CrimsonTeleop extends OpMode {
    // frontLeft, frontRight, backLeft, backRight are the names of the motors that will be used
    public final static double CMIN_POSITION = 0.3;
    public final static double CMAX_POSITION = 0.6;
/*
    public final static double HMAX_POSITION = 1;
    public final static double HMIN_POSITION = 0;
    */


    private DcMotor frontL;
    private DcMotor frontR;
    private DcMotor backL;
    private DcMotor backR;
    private DcMotorEx arm;

    private Servo hinge;
    private Servo claw;

    private ColorSensor S;

    private double speed = 0.6;

    @Override
    public void init() {
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        backL = hardwareMap.get(DcMotor.class, "backL");
        backR = hardwareMap.get(DcMotor.class, "backR");

        arm = hardwareMap.get(DcMotorEx.class, "arm");
        hinge = hardwareMap.get(Servo.class,"hinge");
        claw = hardwareMap.get(Servo.class,"claw");

        backR.setDirection(DcMotor.Direction.REVERSE);
        frontR.setDirection(DcMotor.Direction.REVERSE);
        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }
    // assigns dc motors as hardware

    @Override
    public void loop() {

        double x = gamepad1.right_stick_x * speed;
        double y = gamepad1.left_stick_y * speed;
        double r = gamepad1.left_stick_x * speed;

        frontL.setPower(-y - x - r);
        frontR.setPower(-y + x + r);
        backL.setPower(-y - x + r);
        backR.setPower(-y + x - r);
        if (gamepad1.right_bumper && speed < 0.8) {
            speed += 0.01;
        }
        if (gamepad1.left_bumper && speed > 0.3) {
            speed -= 0.01;
        }
        // allows for input to gamepad to translate to movement;
        // Movement of stick sets power for motors

        if (gamepad2.a) {
            arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            arm.setTargetPosition(270);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setVelocity(1000);
        }
        if (gamepad2.b) {
            arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            arm.setTargetPosition(520);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setVelocity(1000);
        }
        if (gamepad2.y) {
            arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            arm.setTargetPosition(950);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setVelocity(1000);
        }
        if (gamepad2.x) {
            arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            arm.setTargetPosition(arm.getCurrentPosition() - 50);
            arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            arm.setVelocity(1000);
        }

        telemetry.addData("Arm encoder pos", arm.getCurrentPosition());

        if (gamepad2.left_stick_y > 0) {
            hinge.setPosition(.2);
        }

        if (gamepad2.left_stick_y < 0) {
            hinge.setPosition(.9);
        }

        if (gamepad2.left_stick_y == 0) {
            hinge.setPosition(.65);
        }

        if (gamepad2.right_bumper) {
            claw.setPosition(.35);
        }

        if (gamepad2.left_bumper) {
            claw.setPosition(.55);
        }

        telemetry.addData("Hinge position", hinge.getPosition());
//        telemetry.addData("Color Value", S)
        telemetry.update();
    }
}



