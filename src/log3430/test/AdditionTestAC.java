package log3430.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;

import log3430.source.Addition;
import static org.junit.Assert.*;


import org.junit.Test;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Cas de test permettant de tester la classse {@link Addition} à l'aide de la 
 * méthode AC.
 * 
 * @author David Kanaa
 * @author Nicolas Richard
 * @author Adrien Budet
 */
@RunWith(Parameterized.class)
public class AdditionTestAC {

	private int val1 = 0;
	private int val2 = 0;
	
	/**
	 * Génère des données de tests. Chaque paire de données correspond à un cas
	 * de test. Les données sont générées à la manière d'un produit cartésien.
	 * 
	 * @return Les données de test.
	 */
	@Parameters(name = "AC{index}: {0} + {1}")
    public static Collection<Object[]> donnees() {
    	Object[][] parameters = new Object[49][];
    	int[] vals = new int[] { Integer.MIN_VALUE, -100, -1, 0, 1, 100, 
    			                 Integer.MAX_VALUE };
    	for (int i = 0; i < 7; i++) {
    		for (int j = 0; j < 7; j++) {
    			parameters[i*7+j] = new Object[] {vals[i], vals[j]};
    		}
    	}
    	return Arrays.asList(parameters);
    }
    
    /**
     * @param val1 Première opérande de l'addition
     * @param val2 Seconde opérande de l'addition
     */
    public AdditionTestAC(int val1, int val2) {
    	this.val1 = val1;
    	this.val2 = val2;
    }
	
    /**
     * Méthode de test vérifiant que {@link Addition} fonctionne de manière 
     * identique à l'opérateur natif d'addition.
     */
	@Test
	public void test() {
		Addition additionneur = new Addition();
		int resultat = additionneur.operate(val1, val2);
		assertEquals(resultat, val1 + val2);
	}
}
