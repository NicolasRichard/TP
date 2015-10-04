package log3430.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import log3430.source.ICommand;
import log3430.source.Multiplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MultiplicationTestAC {

	private int val1 = 0;
	private int val2 = 0;
	
	@Parameters(name = "AC{index}: {0} * {1}")
    public static Collection<Object[]> donnees() {
    	Object[][] parameters = new Object[49][];
    	int[] vals = new int[] { Integer.MIN_VALUE, -100, -1, 0, 1, 100, Integer.MAX_VALUE };
    	for (int i = 0; i < 7; i++) {
    		for (int j = 0; j < 7; j++) {
    			parameters[i*7+j] = new Object[] {vals[i], vals[j]};
    		}
    	}
    	return Arrays.asList(parameters);
    }
    
    public MultiplicationTestAC(int val1, int val2) {
    	this.val1 = val1;
    	this.val2 = val2;
    }
	
	@Test
	public void test() {
		ICommand operateur = new Multiplication();
		int resultat = operateur.operate(val1, val2);
		assertEquals(resultat, val1 * val2);
	}
}
