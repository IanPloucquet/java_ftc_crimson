package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.ArrayList;

@TeleOp(name = "AprilTagAuto")
public class AprilTagAutonomousInitDetectionExample extends LinearOpMode {
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    DcMotor frontR;
    DcMotor frontL;
    DcMotor backR;
    DcMotor backL;
    CRServo Claw;
    double diameter = 3.5; // inches
    double fullRotationTicks = 1440; // ticks
    // fullRotationTicks : circumference
    // 537.7 / (pi * diameter)
    // basically, for 1 ticks, the robot travels 537.7 ticks / (pi * diameter)
    int ticksPerInch = (int) (fullRotationTicks / Math.PI * diameter); // ticks / inch

    public void forward(double power) {
        frontL.setPower(-power);
        frontR.setPower(-power);
        backL.setPower(-power);
        backR.setPower(-power);
    }

    public void backward(double power) {
        frontL.setPower(power);
        frontR.setPower(power);
        backL.setPower(power);
        backR.setPower(power);
    }

    public void left(double power) {
        frontL.setPower(-power);
        frontR.setPower(power);
        backL.setPower(power);
        backR.setPower(-power);
    }

    public void right(double power) {
        frontL.setPower(power);
        frontR.setPower(-power);
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

    // public void Move_Right(int i, int ticksPerInch) {
    // frontLeft.setTargetPosition(i*ticksPerInch);
    // frontRight.setTargetPosition(-i*ticksPerInch);
    // backLeft.setTargetPosition(i*ticksPerInch);
    // backRight.setTargetPosition(-i*ticksPerInch);}

    // start by closing claw, lift linear slide one stage,
    // can try to read sleeve or just move the bot to a junction

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    //int ID_TAG_OF_INTEREST = 18; // Tag ID 18 from the 36h11 family
    // AprilTags:
    // Parking 1 - ID 277
    // Parking 2 - ID 283
    // Parking 3 - ID 326

    int left = 277;
    int middle = 283;
    int right = 326;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode()
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    // if(tag.id == ID_TAG_OF_INTEREST)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        frontR = hardwareMap.get(DcMotor.class, "frontR");
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        backL = hardwareMap.get(DcMotor.class, "backL");

        backL.setDirection(DcMotor.Direction.REVERSE);
        frontL.setDirection(DcMotor.Direction.REVERSE);

        /* Actually do something useful */
        if(tagOfInterest == null || tagOfInterest.id == middle) {
            forward(0.4);
            sleep(2200);
        } else if(tagOfInterest.id == left) {
            forward(0.4);
            sleep(1100);
            left(0.4);
            sleep(2200);
        } else if (tagOfInterest.id == right) {
            forward(0.4);
            sleep(1100);
            right(0.4);
            sleep(2200);
        }

        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        while (opModeIsActive()) {sleep(20);}
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }
}