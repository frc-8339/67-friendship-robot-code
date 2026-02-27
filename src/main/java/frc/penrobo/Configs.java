package frc.penrobo;

import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Configs {
    public static final class Shooter {
        public static final class Feed {
            public static final SparkMaxConfig config = new SparkMaxConfig();

            static {
                config.closedLoop
                        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                        .pid(0.03, 0, 0)

                ;
            }
        }
    }

    public static final class Lift {
        public static final class Extrude {
            public static final SparkFlexConfig config = new SparkFlexConfig();

            static {
                config.closedLoop
                        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                        .pidf(2.1, 0, 0, 0.01);
            }

            // 0 to 0.3

        }
    }
}
