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

    public final static double MIN_POSITION = 0.6;
    public final static double MAX_POSITION = 0.4;

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

       if (gamepad2.a) {
           arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           arm.setTargetPosition(150);
           arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           arm.setVelocity(150);
           arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       }
       else if (gamepad2.b) {
           arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           arm.setTargetPosition(-50);
           arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           arm.setVelocity(-150);
           arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       }
       if (gamepad2.dpad_up) {
           hinge.setPosition(t);
       }

       if (gamepad2.right_bumper) {
            claw.setPosition(MIN_POSITION);
        }

       if (gamepad2.left_bumper) {
            claw.setPosition(MAX_POSITION);
        }

       telemetry.addData("Arm enocder pos: ", arm.getCurrentPosition());
       telemetry.update();
       //telemetry adds data onto the driver hub's screen (also what the get methods are for); very useful for calculations and figuring out certain things

    }
}



