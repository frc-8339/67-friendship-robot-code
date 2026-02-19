// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class RobotContainer {
  private AnalogEncoder encoder1 = new AnalogEncoder(0);
  private AnalogEncoder encoder2 = new AnalogEncoder(1);
  private AnalogEncoder encoder3 = new AnalogEncoder(2);
  private AnalogEncoder encoder4 = new AnalogEncoder(3);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  long lastLogTime = 0;

  /**
   * ﻿﻿﻿﻿﻿﻿ Encoder 1 angle: 0.4788874664332764 ﻿
﻿﻿﻿﻿﻿﻿ Encoder 2 angle: 0.4670092045216902 ﻿
﻿﻿﻿﻿﻿﻿ Encoder 3 angle: 0.7343405695178871 ﻿
﻿﻿﻿﻿﻿﻿ Encoder 4 angle: 0.3817269693252534 ﻿

   */

  public void periodic() {
    if (System.currentTimeMillis() - lastLogTime > 300) {
      String output = String.format(
          "Encoder 1 angle: %f, Encoder 2 angle: %f, Encoder 3 angle: %f, Encoder 4 angle: %f",
          encoder1.get(), encoder2.get(), encoder3.get(), encoder4.get());

      System.out.println(output);
      lastLogTime = System.currentTimeMillis();
    }
  }
}