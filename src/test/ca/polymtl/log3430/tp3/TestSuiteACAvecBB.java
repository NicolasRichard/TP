package ca.polymtl.log3430.tp3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.polymtl.log3430.AdditionTestAC;
import ca.polymtl.log3430.DivisionTestAC;
import ca.polymtl.log3430.MultiplicationTestAC;
import ca.polymtl.log3430.SoustractionTestAC;
import ca.polymtl.log3430.SuiteChaineeTestAC;

@RunWith(Suite.class)
@SuiteClasses({ ListeChaineeTestBB.class, SuiteChaineeTestBB.class, 
	AdditionTestAC.class, DivisionTestAC.class, 
	MultiplicationTestAC.class, SoustractionTestAC.class,
	SuiteChaineeTestAC.class })
public class TestSuiteACAvecBB {

}
