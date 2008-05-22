package general;

public class SeguirLinea implements Behavior {

    static Pilot pilot = new Pilot(5.6f, 10.5f, Motor.B, Motor.C);
    static LightSensor ls = new LightSensor(SensorPort.S1);
    static LightSensor ls2 = new LightSensor(SensorPort.S2);
    static LightSensor ls3 = new LightSensor(SensorPort.S3);
    static String xx = "Siguiendo linea... ";

    public boolean takeControl() {
        return (ls.readValue() <= 50 && ls2.readValue() > 50 && ls3.readValue() > 50);
    }

    public void suppress() {
        // pilot.travel(2);
        Motor.B.stop();
        Motor.C.stop();
    }

    public void action() {

        Motor.B.setSpeed(200);
        Motor.C.setSpeed(200);
        // avanzar por la linea
        Motor.B.forward();
        Motor.C.forward();
        LCD.clear();
        LCD.drawString(xx, 0, 0);
        LCD.refresh();
        RobotNavigator.setState(RobotNavigator.ISLINE);
    }
}
