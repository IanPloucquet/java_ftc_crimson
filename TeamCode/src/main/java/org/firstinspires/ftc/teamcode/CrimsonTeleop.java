package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class CrimsonTeleop extends OpMode {
    // frontLeft, frontRight, backLeft, backRight are the names of the motors that will be used

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor arm;

    Servo hinge;
    Servo claw;

    double speed = 0.6;
    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");

        hinge = hardwareMap.get(Servo.class,"hinge");
        claw = hardwareMap.get(Servo.class,"claw");

        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
    }
    // assigns dc motors as hardware

    @Override
    public void loop() {

        double x = gamepad1.right_stick_x * speed;
        double y = gamepad1.left_stick_y * speed;
        double r = gamepad1.left_stick_x * speed;

        frontLeft.setPower(-y-x-r);
        frontRight.setPower(-y+x+r);
        backLeft.setPower(-y-x+r);
        backRight.setPower(-y+x-r);
        if (gamepad1.right_bumper && speed < 0.8) {
            speed += 0.01;
        }
        if (gamepad1.left_bumper && speed > 0.3) {
            speed -= 0.01;
        }
        // allows for input to gamepad to translate to movement;
        // Movement of stick sets power for motors

        double m = gamepad2.left_stick_y * speed;
        double t = gamepad2.right_stick_y * speed;

        arm.setPower(m);


        hinge.setPosition(t); 


    }
}


