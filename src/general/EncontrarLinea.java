package general;

public class EncontrarLinea implements Behavior {

    static Pilot pilot = new Pilot(5.6f, 10.5f, Motor.B, Motor.C);
    static LightSensor ls = new LightSensor(SensorPort.S1);
    static LightSensor ls2 = new LightSensor(SensorPort.S2);
    static LightSensor ls3 = new LightSensor(SensorPort.S3);
    static String xx = "Buscando linea... ";
    static int i, grados;

    public boolean takeControl() {
        return (ls.readValue() > 50);
    }

    public void suppress() {
        Motor.B.stop();
        Motor.C.stop();
    }

    public void action() {

        LCD.clear();
        LCD.drawString(xx, 0, 0);
        LCD.refresh();

        RobotNavigator.setState(RobotNavigator.ISDEADEND);

        Motor.B.setSpeed(200);
        Motor.C.setSpeed(200);

        // pilot.travel(-2);
        // volver a encontrar la linea
        i = 1;
        grados = 20;
        if (ls2.readValue() <= 50) {
            while (ls.readValue() > 50) {
                // pilot.rotate(-grados * 2);
                Motor.B.forward();
                Motor.C.backward();
            }
        } else if (ls3.readValue() <= 50) {
            while (ls.readValue() > 50) {
                // pilot.rotate(grados * 2);
                Motor.B.backward();
                Motor.C.forward();
            }
        } else {
            i = 1;
            while (ls.readValue() > 50 && ls2.readValue() > 50
                    && ls3.readValue() > 50) {
                if (i % 2 == 0) {
                    pilot.rotate(grados * i);
                } else {
                    pilot.rotate(-grados * i);
                }
                i++;
            }
        }
        // volver a encontrar la linea

    }
}