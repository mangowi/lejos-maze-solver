package general;
import lejos.subsumption.*;
import lejos.navigation.Pilot;
import lejos.nxt.*;

public class RobotNavigator extends AbstractMazeNavigator {
	static Pilot pilot = new Pilot(5.6f, 10.5f, Motor.B, Motor.C);
	static LightSensor ls = new LightSensor(SensorPort.S1);
	static int i, aux, min, max;
	final static int ISLINE = 0;
	final static int ISTINVIZQ = 1;// Es cuando se da que uno de los sensores esta
	final static int ISTINVDER = 2;// Es cuando se da que uno de los sensores esta
	// en blanco y otro en negro
	final static int ISTCROSS = 4;
	final static int ISXCROSS = 8;
	final static int ISEXIT = 16;
	final static int ISDEADEND = 32;

	private static int state = 0;

	public RobotNavigator() {

		while (!Button.ESCAPE.isPressed()) {

			// creamos el comportamiento de seguir la linea
			Behavior b1 = new SeguirLinea();
			Behavior tid = new EncontrarTInvDer();
			// creamos el comportamiento de reencontrar una X
			Behavior tii = new EncontrarTInvIzq();
			// creamos el comportamiento de reencontrar una X
			Behavior ex = new EncontrarX();
			// creamos el comportamiento de reencontrar una T
			Behavior et = new EncontrarT();
			// creamos el comportamiento de reencontrar la linea
			Behavior b2 = new EncontrarLinea();
			// definimos un vector con ambos comportamientos
			Behavior[] bArray = { b1, tid,tii, et, ex, b2 };
			// definimos el orden de comportamientos mediante un arbitro
			Arbitrator arby = new Arbitrator(bArray);
			// declaramos la velocidad de los motores
			Motor.B.setSpeed(200);
			Motor.C.setSpeed(200);
			// antes de iniciar el arbitro, calibramos el sensor de luz
			// i = 36;
			// min = ls.readValue();
			// max = ls.readValue();
			// while (i <= 360) {
			// pilot.rotate(i);
			// aux = ls.readValue();
			// if (aux < min) {
			// ls.calibrateLow();
			// min = aux;
			// }
			// if (aux > max) {
			// ls.calibrateHigh();
			// max = aux;
			// }
			// i += 36;
			// }
			// inicializamos los comportamientos mediante la inicializacion del
			// arbitro
			arby.start();

		}

	}

	public static int getState() {
		return state;
	}

	public static void setState(int state) {
		RobotNavigator.state = state;
	}


	@Override
	boolean back() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean forward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isDeadend() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isExit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isLCross() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isOnLine() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isTCross() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isXCross() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	boolean turnLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean turnRight() {
		// TODO Auto-generated method stub
		return false;
	}
}