package ca.polymtl.log3430.tp3;

import ca.polymtl.log3430.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ListeChaineeTestBB.class, SuiteChaineeTestBB.class, 
	AdditionTestEC.class, DivisionTestEC.class,
	MultiplicationTestEC.class, SoustractionTestEC.class,
	SuiteChaineeTestEC.class })
public class TestSuiteECAvecBB {

}
