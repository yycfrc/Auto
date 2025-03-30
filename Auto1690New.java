// Copyright (c) 2025 FRC 6907, The G.O.A.T
package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auto.routines.PreloadRoutine;
import frc.robot.auto.routines.Cycle1690Routine;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.superstructure.Superstructure;
import frc.robot.subsystems.superstructure.SuperstructureState;
import frc.robot.util.TagIdUtil.ReefFace;

public class Auto1690New {
  private final Drive drive;
  private final Superstructure superstructure;
  private final PreloadRoutine preloadRoutine;
  private final Cycle1690Routine cycle1690Routine;

  public Auto1690New(Drive drive, Superstructure superstructure) {
    this.drive = drive;
    this.superstructure = superstructure;
    preloadRoutine = new PreloadRoutine(drive, superstructure);
    cycle1690Routine = new Cycle1690Routine(drive, superstructure);
  }

  public Command getAutoCommand() {
    return new SequentialCommandGroup(
        // 预加载路径 - 使用 PreloadRoutine 的 L4 模式
        PreloadRoutine.build(drive, superstructure, SuperstructureState.L4_CORAL),
        
        // 第一个循环 - Far LS 到 Reef 12
        AutoCommandFactory.buildCycle(
            drive,
            superstructure,
            "Reef 3 To Far LS To Reef 12",
            ReefFace.LEFT,
            false,
            SuperstructureState.L4_CORAL),
        
        // 第二个循环 - Far LS 到 Reef 11
        AutoCommandFactory.buildCycle(
            drive,
            superstructure,
            "Reef 12 To Far LS To Reef 11",
            ReefFace.LEFT,
            true,
            SuperstructureState.L4_CORAL),
        
        // 第三个循环 - Upper Mid 到 Reef 10
        AutoCommandFactory.buildCycle(
            drive,
            superstructure,
            "Reef 11 To Upper Mid To Reef 10",
            ReefFace.LEFT,
            true,
            SuperstructureState.L4_CORAL),
        
        // 第四个循环 - Lower Mid 到 Reef 9
        AutoCommandFactory.buildCycle(
            drive,
            superstructure,
            "Reef 10 To Lower Mid To Reef 9",
            ReefFace.LEFT,
            true,
            SuperstructureState.L4_CORAL),
            
        // 结束时收回机构
        superstructure.runGoal(SuperstructureState.STOW)
    );
  }
}