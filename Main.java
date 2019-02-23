package org.firstinspires.ftc.teamcode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware2018;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name="Main", group="OpMode")

public class Main extends LinearOpMode{

    /* Declare OpMode members. */
    Hardware2018 robot = new Hardware2018(); // Use Hardware Code
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say:", "Hello Driver");    //
        telemetry.update();
        
        robot.scoop.setPosition(0.35);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //DRIVE CONTROL P1
            if (gamepad1.left_bumper){
                robot.back_left.setPower(1);
                robot.front_left.setPower(1);
                robot.back_right.setPower(-1);
                robot.front_right.setPower(-1);
            }
            else if (gamepad1.right_bumper){
                robot.back_left.setPower(-1);
                robot.front_left.setPower(-1);
                robot.back_right.setPower(1);
                robot.front_right.setPower(1);
            }
            else{
                float left = gamepad1.left_stick_y;
                float right = gamepad1.right_stick_y;
                float spin_left = gamepad1.left_trigger;
                float spin_right = gamepad1.right_trigger;
                robot.front_right.setPower(right/2);
                robot.front_left.setPower(left/2);
                robot.back_right.setPower(right/2);
                robot.back_left.setPower(left/2);
            }

            //WINCH CONTROL P2
            if (gamepad2.left_bumper){
                robot.winch.setPower(-1);
            }
            else if (gamepad2.left_trigger > 0) {
                robot.winch.setPower(1);
            }
            else {
                robot.winch.setPower(0);
            }
            
            //SLIDE CONTROL P2
            if (gamepad2.right_bumper){
                robot.slide.setPower(-1);
            }
            else if (gamepad2.right_trigger > 0) {
                robot.slide.setPower(1);
            }
            else {
                robot.slide.setPower(0);
            }
            
            //INTAKE CONTROL P2
            if (gamepad2.right_stick_y > 0.5){
                robot.intake.setPower(-1);
            }
            else if (gamepad2.right_stick_y < -0.5){
                robot.intake.setPower(1);
            }
            else{
                robot.intake.setPower(-0.09);
            }
            
            //SCOOP ANGLE CONTROL P2
            if (gamepad2.left_stick_y > 0){
                robot.scoop.setPosition(0.35);
            }
            if (gamepad2.left_stick_y < 0){
                robot.scoop.setPosition(0.6);
            }
            if (gamepad2.a){
                robot.scoop.setPosition(0);
            }
        }
    }
}
