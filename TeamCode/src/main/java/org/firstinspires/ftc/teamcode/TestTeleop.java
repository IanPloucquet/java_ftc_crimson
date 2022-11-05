package org.firstinspires.ftc.teamcode;

import android.net.http.SslCertificate;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class TestTeleop extends OpMode {
    // frontLeft, frontRight, backLeft, backRight are the names of the motors that will be used

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
    }
    // assigns dc motors as hardware

    @Override
    public void loop() {
       double x = gamepad1.right_stick_x;
       double y = -gamepad1.left_stick_y;
       double r = -gamepad1.left_stick_x;

       frontLeft.setPower(y-x+r);
       frontRight.setPower(y+x-r);
       backLeft.setPower(y-x-r);
       backRight.setPower(y+x+r);


       // allows for input to gamepad to translate to movement;
        // Movement of stick sets power for motors
        if (gamepad1.right_bumper) {
            double c = gamepad1.right_stick_x;
            double d = -gamepad1.left_stick_y;
            double t = -gamepad1.left_stick_x;
            frontLeft.setPower(0.2);
            frontRight.setPower(0.2);
            backLeft.setPower(0.2);
            backRight.setPower(0.2);


            frontLeft.setPower(d-c+t);
            frontRight.setPower(d+c-t);
            backLeft.setPower(d-c-t);
            backRight.setPower(d+c+t);
        }
    }
}


