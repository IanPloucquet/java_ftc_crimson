package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
public class Test extends OpMode {
    // frontLeft, frontRight, backLeft, backRight are the names of the motors that will be used


    DcMotorEx arm;
    Servo hinge;
    Servo claw;

    double speed = 0.6;

    @Override
    public void init() {
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        hinge = hardwareMap.get(Servo.class, "hinge");
        claw = hardwareMap.get(Servo.class, "claw");
        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop() {
        double m = gamepad2.left_stick_y;
        double t = gamepad2.dpad_up?0.4:0.6;
        double k = gamepad2.right_trigger * speed;
        double l = gamepad2.left_trigger * speed;

       /*do {
           arm.setPower(speed);
           arm.setTargetPosition(100);
       }
       while (gamepad2.a);*/
       if (gamepad2.a) {
           arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
           arm.setTargetPosition(120);
           arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           arm.setVelocity(120);
           while (arm.isBusy()) {

           }
           arm.setVelocity(0);
       }
       else if (gamepad2.b) {
           arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
           arm.setTargetPosition(200);
           arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           arm.setVelocity(200);
           while (arm.isBusy()) {

           }
           arm.setVelocity(0);
       }

       if (gamepad2.dpad_up) {
           hinge.setPosition(t);
       }

        if (gamepad2.left_bumper) {
            claw.setPosition(0.5);
        }

        if (gamepad2.right_bumper) {
            claw.setPosition(0.8);
        }
    }
}



