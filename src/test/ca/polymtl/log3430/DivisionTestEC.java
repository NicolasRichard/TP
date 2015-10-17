package ca.polymtl.log3430;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Cas de test permettant de tester la classse {@link Division} à l'aide de la 
 * méthode EC.
 * 
 * @author David Kanaa
 * @author Nicolas Richard
 * @author Adrien Budet
 */
@RunWith(Parameterized.class)
public class DivisionTestEC {

	private boolean erreurAttendue = false;
	private int val1 = 0;
	private int val2 = 0;
	
	/**
	 * Génère des données de tests. Chaque 3-tuple de données correspond à un 
	 * cas de test. Le dernier élément du tuple indique si l'opération devrait
	 * produire une erreur.
	 * 
	 * @return Les données de test.
	 */
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
    
    /**
     * @param val1   Première opérande de l'addition
     * @param val2   Seconde opérande de l'addition
     * @param erreur Si l'opérateur devrait lancer une erreur.
     */
    public DivisionTestEC(int val1, int val2, boolean erreur) {
    	this.val1 = val1;
    	this.val2 = val2;
    	this.erreurAttendue = erreur;
    }
	
    /**
     * Méthode de test vérifiant que {@link Division} fonctionne de manière 
     * identique à l'opérateur natif d'addition.
     */
	@Test
	public void test() {
		Calculator operateur = new Division();
		try {
			int resultat = operateur.operate(val1, val2);
			assertEquals(resultat, val1 / val2);
		} catch (Exception e) {
			assertTrue(erreurAttendue);
		}
	}
}