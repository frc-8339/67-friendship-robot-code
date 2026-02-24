package frc.penrobo;

import edu.wpi.first.math.util.Units;

public class Constants {
    public static final class OperatorConstants {
        public static final int driverControllerPort = 0;
        public static final double deadband = 0.08;
    }

    public static final class SwerveConstants {
        public static final double maxSpeed = Units.feetToMeters(16.8); // meters per second
        // public static final double maxSpeed = Units.feetToMeters(2.5); // meters per
        // second
    }
}
