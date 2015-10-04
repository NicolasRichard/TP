package log3430.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import log3430.source.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class AdditionTestEC {

	private int val1 = 0;
	private int val2 = 0;
	
	@Parameters(name = "EC{index}: {0} + {1}")
    public static Collection<Object[]> donnees() {
        return Arrays.asList(new Object[][] {     
                 { Integer.MIN_VALUE, 0 }, 
                 { -100, -1 }, 
                 { 0, Integer.MAX_VALUE }, 
                 { 1, -100 }, 
                 { 100, Integer.MIN_VALUE}, 
                 { Integer.MAX_VALUE, 100 }
           });
    }
    
    public AdditionTestEC(int val1, int val2) {
    	this.val1 = val1;
    	this.val2 = val2;
    }
	
	@Test
	public void test() {
		Addition additionneur = new Addition();
		int resultat = additionneur.operate(val1, val2);
		assertEquals(resultat, val1 + val2);
	}
}
