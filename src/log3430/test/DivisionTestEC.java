package log3430.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import log3430.source.ICommand;
import log3430.source.Division;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DivisionTestEC {

	private boolean erreurAttendue = false;
	private int val1 = 0;
	private int val2 = 0;
	
	@Parameters(name = "EC{index}: {0} / {1}")
    public static Collection<Object[]> donnees() {
        return Arrays.asList(new Object[][] {     
                 { Integer.MIN_VALUE, 0 , true }, 
                 { -100, -1, false }, 
                 { 0, Integer.MAX_VALUE, false }, 
                 { 1, -100, false }, 
                 { 100, Integer.MIN_VALUE, false }, 
                 { Integer.MAX_VALUE, 100, false }
           });
    }
    
    public DivisionTestEC(int val1, int val2, boolean erreur) {
    	this.val1 = val1;
    	this.val2 = val2;
    	this.erreurAttendue = erreur;
    }
	
	@Test
	public void test() {
		ICommand operateur = new Division();
		try {
			int resultat = operateur.operate(val1, val2);
			assertEquals(resultat, val1 / val2);
		} catch (Exception e) {
			assertTrue(erreurAttendue);
		}
	}
}