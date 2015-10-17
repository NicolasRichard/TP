package ca.polymtl.log3430;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Cas de test permettant de tester la classse {@link Soustraction} à l'aide de la 
 * méthode EC.
 * 
 * @author David Kanaa
 * @author Nicolas Richard
 * @author Adrien Budet
 */
@RunWith(Parameterized.class)
public class SoustractionTestEC {

	private int val1 = 0;
	private int val2 = 0;
	
	/**
	 * Génère des données de tests. Chaque paire de données correspond à un cas
	 * de test. 
	 * 
	 * @return Les données de test.
	 */
	@Parameters(name = "EC{index}: {0} - {1}")
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
    
    /**
     * @param val1 Première opérande de l'addition
     * @param val2 Seconde opérande de l'addition
     */
    public SoustractionTestEC(int val1, int val2) {
    	this.val1 = val1;
    	this.val2 = val2;
    }
	
    /**
     * Méthode de test vérifiant que {@link Soustraction} fonctionne de manière 
     * identique à l'opérateur natif d'addition.
     */
	@Test
	public void test() {
		Calculator operateur = new Soustraction();
		int resultat = operateur.operate(val1, val2);
		assertEquals(resultat, val1 - val2);
	}
}
