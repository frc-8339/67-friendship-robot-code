package frc.penrobo;

import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Configs {
    public static final class Shooter {
        public static final class Feed {
            public static final SparkMaxConfig feedConfig = new SparkMaxConfig();

            static {
                feedConfig.closedLoop
                        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                        .pid(0.03, 0, 0)

                ;
            }
        }
    }
}
