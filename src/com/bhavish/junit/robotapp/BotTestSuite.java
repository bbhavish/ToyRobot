package com.bhavish.junit.robotapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardJunit.class, BotGameJunit.class, RobotJunit.class })
public class BotTestSuite {

}
