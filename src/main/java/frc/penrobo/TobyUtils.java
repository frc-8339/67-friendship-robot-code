package frc.penrobo;

public class TobyUtils {
    /**
     * Map a value from one range to another.
     *
     * @param value  The value to map.
     * @param inMin  The minimum value of the input range.
     * @param inMax  The maximum value of the input range.
     * @param outMin The minimum value of the output range.
     * @param outMax The maximum value of the output range.
     * @return The mapped value.
     */
    public static double map(double value, double inMin, double inMax, double outMin, double outMax) {
        return (value - inMin) / (inMax - inMin) * (outMax - outMin) + outMin;
    }
}
