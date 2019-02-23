package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware2018;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous(name = "Crater Side", group = "Autonomous")
public class CraterSide extends LinearOpMode {
    // The IMU sensor object
    BNO055IMU imu;

    // State used for updating telemetry
    Orientation angles;
    
    Hardware2018 robot = new Hardware2018(); // Use Hardware Code
    
    public void runOpMode() {
        
        telemetry.addData("Init", "Calibrating IMU...");
        telemetry.update();

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        
        telemetry.addData("Init", "IMU Calibrated!");
        telemetry.update();
        
        // INIT
        robot.init(hardwareMap);
        telemetry.addData("Init", "Resetting Encoders...");
        telemetry.update();
        resetEncoders();
        telemetry.addData("Init", "Encoders Ready!");
        telemetry.update();

        //WAIT FOR START
        telemetry.addData("Start", "Waiting For Start...");
        telemetry.update();
        waitForStart();
        
        //----------------------------------------------------------
        //--INSTRUCTIONS--
        //----------------------------------------------------------
        
        
    }
    
    public void turn(int degrees){
        int startDegree = getHeading();
        encodersOff();
        if (degrees > 0){
            while (getHeading() < startDegree + degrees && opModeIsActive()){
                robot.front_right.setPower(-0.25);
                robot.front_left.setPower(0.25);
                robot.back_right.setPower(-0.25);
                robot.back_left.setPower(0.25);
            }
        }
        else {
            while (getHeading() > startDegree - degrees && opModeIsActive()){
                robot.front_right.setPower(0.15);
                robot.front_left.setPower(-0.15);
                robot.back_right.setPower(0.15);
                robot.back_left.setPower(-0.15);
            }
        }
        zeroPower();
        resetEncoders();
    }
    
    public int getHeading(){
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return Math.round(angles.firstAngle);
    }
    
    public void resetEncoders(){
        robot.front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(100);
        robot.front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(100);
    }
    
    public void encodersOff(){
        robot.front_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        robot.front_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        robot.back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        robot.back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);
        sleep(100);
    }
        
    public void goStraight(int position, double power){
        robot.front_left.setTargetPosition(position);
        robot.front_right.setTargetPosition(position);
        robot.back_left.setTargetPosition(position);
        robot.back_right.setTargetPosition(position);
        robot.front_left.setPower(power-.01);
        robot.front_right.setPower(power);
        robot.back_left.setPower(power);
        robot.back_right.setPower(power);
        
        while (robot.front_left.isBusy() && opModeIsActive()){
        }
        sleep(500);
        zeroPower();
        resetEncoders();
    }
    
    public void lowerFromLander(){
        robot.winch.setPower(-1);
        sleep(4300);
        robot.winch.setPower(0);
        robot.front_left.setTargetPosition(-100);
        robot.front_left.setPower(0.2);
        sleep(500);
        zeroPower();
        resetEncoders();
    }
    
    public void dropTeamMarker(){
        robot.scoop.setPosition(.35);
        robot.slide.setPower(1);
        sleep(1000);
        robot.intake.setPower(1);
        sleep(1000);
        robot.scoop.setPosition(0);
        robot.intake.setPower(-0.09);
        robot.slide.setPower(-1);
        sleep(2000);
    }
    
    public void zeroPower(){
        robot.front_left.setPower(0);
        robot.front_right.setPower(0);
        robot.back_left.setPower(0);
        robot.back_right.setPower(0);
    }
}
