package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Hardware2018
{
    /* Public OpMode members. */
    public DcMotor front_right = null;
    public DcMotor front_left = null;
    public DcMotor back_right = null;
    public DcMotor back_left = null;
    public DcMotor winch = null;
    public DcMotor lights = null;
    public DcMotor slide = null;
    public CRServo intake = null;
    public Servo scoop = null;

    /* local OpMode members. */
    HardwareMap hwMap =  null;

    /* Constructor */
    public Hardware2018(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        // Define and Initialize Motors
        //DRIVE
        front_right = hwMap.get(DcMotor.class, "front_right");
        front_left = hwMap.get(DcMotor.class, "front_left");
        back_right = hwMap.get(DcMotor.class, "back_right");
        back_left = hwMap.get(DcMotor.class, "back_left");
        
        front_right.setDirection(DcMotor.Direction.FORWARD);
        front_left.setDirection(DcMotor.Direction.REVERSE);
        back_right.setDirection(DcMotor.Direction.FORWARD);
        back_left.setDirection(DcMotor.Direction.REVERSE);
        
        front_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        front_right.setPower(0);
        front_left.setPower(0);
        back_right.setPower(0);
        back_left.setPower(0);
        
        front_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back_left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        //UTILITY
        winch = hwMap.get(DcMotor.class, "winch");
        slide = hwMap.get(DcMotor.class, "slide");
        lights = hwMap.get(DcMotor.class, "lights");

        winch.setDirection(DcMotor.Direction.FORWARD);
        slide.setDirection(DcMotor.Direction.FORWARD);

        winch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        winch.setPower(0);
        slide.setPower(0);
        lights.setPower(0.1);
        
        winch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        intake = hwMap.get(CRServo.class, "intake");
        scoop = hwMap.get(Servo.class, "scoop");
        
        intake.setPower(-0.09);
        scoop.setPosition(0);
        
        // 20kg Servo Information 
        // .36 = 90 deg
        // .73 = 180 deg
        // 1 = 270 deg
        //CR Servo -0.09 = STOP, 1 = FORWARD, -1 = BACKWARD
    }
 }

