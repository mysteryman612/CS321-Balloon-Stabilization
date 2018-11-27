import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;
import com.pi4j.wiringpi.Gpio;

public class StabilizationOnly {
/*
 * this code only stabilizes the craft, without any ability for heading changes
 */

	private static int MOTOR_PIN_A =4;
	private static int MOTOR_PIN_B =5;
	
	public static void main(String[] args) {
		

		//Get handle to GPIO controller
		final GpioController gpio = GpioFactory.getInstance();

		//init soft PWM pins for motor speed control
		SoftPwm.softPwmCreate(MOTOR_PIN_A,0,100);
		SoftPwm.softPwmCreate(MOTOR_PIN_B,0,100);
	
		final GpioPinDigitalOutput motorE = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "mtr");

		int motorOut;
		double data,k,motorSpeed,cappedMotorSpeed;
		motorE.high();
	

		while(true){

			data = 0;//call sensor team for data
		

			//adjust speed of wheel based on current angular velocity
			k=0; //need k value
			motorSpeed = k* data;
			cappedMotorSpeed = Math.min(1.0, Math.abs(motorSpeed));
			motorOut = (int)(255* cappedMotorSpeed);

			//update motor speed and/or direction
			if(motorSpeed <0){
				SoftPwm.softPwmWrite(MOTOR_PIN_A,motorOut);
				SoftPwm.softPwmWrite(MOTOR_PIN_B,0);
			}
			else{
				SoftPwm.softPwmWrite(MOTOR_PIN_A,0);
				SoftPwm.softPwmWrite(MOTOR_PIN_B,motorOut);
			}
		Gpio.delay(20);

		}
	}

}
