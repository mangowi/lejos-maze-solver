package general;

public class EncontrarTInvDer implements Behavior {

    static Pilot pilot = new Pilot(5.6f, 10.5f, Motor.B, Motor.C);
    static LightSensor ls = new LightSensor(SensorPort.S1);
    static LightSensor ls2 = new LightSensor(SensorPort.S2);
    static LightSensor ls3 = new LightSensor(SensorPort.S3);
    static String xx = "Encontre una TInv";

    public boolean takeControl() {
        return (ls.readValue() <= 50 && ((ls2.readValue() > 50 && ls3
                .readValue() <= 50)));
    }

    public void suppress() {
        Motor.B.stop();
        Motor.C.stop();
    }

    public void action() {
        LCD.clear();
        LCD.drawString(xx, 0, 0);
        LCD.refresh();
        RobotNavigator.setState(RobotNavigator.ISTINVDER);
    }
}
