// Copyright (c) 2025 FRC 6907, The G.O.A.T
package frc.robot.auto.routines;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auto.AutoCommandFactory;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.superstructure.Superstructure;
import frc.robot.subsystems.superstructure.SuperstructureState;
import frc.robot.util.TagIdUtil.ReefFace;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

public class Cycle1690Routine {
  private final Drive drive;
  private final Superstructure superstructure;
  private final LoggedDashboardChooser<Command> mCycleChooser;

  public Cycle1690Routine(Drive drive, Superstructure superstructure) {
    this.drive = drive;
    this.superstructure = superstructure;
    mCycleChooser = new LoggedDashboardChooser<>("1690CycleMode");
    mCycleChooser.addDefaultOption("FarLS12", buildFarLS12Cycle());
    mCycleChooser.addOption("FarLS11", buildFarLS11Cycle());
    mCycleChooser.addOption("UpperMid", buildUpperMidCycle());
    mCycleChooser.addOption("LowerMid", buildLowerMidCycle());
  }

  public Command getSelected() {
    return mCycleChooser.get();
  }

  private Command buildFarLS12Cycle() {
    return AutoCommandFactory.buildCycle(
        drive,
        superstructure,
        "Reef 3 To Far LS To Reef 12",
        ReefFace.LEFT,
        false,
        SuperstructureState.L4_CORAL);
  }

  private Command buildFarLS11Cycle() {
    return AutoCommandFactory.buildCycle(
        drive,
        superstructure,
        "Reef 12 To Far LS To Reef 11",
        ReefFace.LEFT,
        true,
        SuperstructureState.L4_CORAL);
  }

  private Command buildUpperMidCycle() {
    return AutoCommandFactory.buildCycle(
        drive,
        superstructure,
        "Reef 11 To Upper Mid To Reef 10",
        ReefFace.LEFT,
        true,
        SuperstructureState.L4_CORAL);
  }

  private Command buildLowerMidCycle() {
    return AutoCommandFactory.buildCycle(
        drive,
        superstructure,
        "Reef 10 To Lower Mid To Reef 9",
        ReefFace.LEFT,
        true,
        SuperstructureState.L4_CORAL);
  }
}