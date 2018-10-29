import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.SoftPwm;

public class StabilizationOnly {
/*
 * this code only stabilizes the craft, without any ability for heading changes
 */

	private static int MOTOR_PIN_A =4;
	private static int MOTOR_PIN_B =5;
	
	public static void main(String[] args) {
		// TODO Auto-generated methodstub
		double biasz = 0;      //measurement bias

		//Get handle to GPIO controller
		final GpioController gpio = GpioFactory.getInstance();

		//init soft PWM pins for motor speed control
		SoftPwm.softPwmCreate(MOTOR_PIN_A,0,100);
		SoftPwm.softPwmCreate(MOTOR_PIN_B,0,100);
	
		final GpioPinDigitalOutput motor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "mtr");

	//calibrate gyro data
	for(int i=0;i<100;i++){
		
		//get data from sensor team
		}	
	//biasz /= 100;

	double unbiasedData =0;
	double data,k,motorsepped,cappedMotorSpeed;
	motor.high();
	}

	while(true){

	data = 0;//get data value from sensor team
	unbiasedData = data-biasz;

	//adjust speed of wheel based on current angular velocity
	k=0; //need k value
	motorSpeed = k* unbiasedData;
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
	Thread.sleep(10);

	}

}
