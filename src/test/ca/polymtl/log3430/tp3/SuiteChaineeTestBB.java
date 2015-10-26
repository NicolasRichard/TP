package ca.polymtl.log3430.tp3;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import ca.polymtl.log3430.*;

/**
 * Tests de type boîte blanche utilisant le critère de couverture des 
 * arrêtes. La plupart des tests sont conçu pour maximiser la couverture de la
 * classe {@link ListeChainee} en même temps de que celle-ci.
 * 
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public class SuiteChaineeTestBB {

	/**
	 * Cas de test permettant de couvrir la branche lancant l'exception au sein
	 * de la méthode removeAt. Nous utilisons une valeur négative (-5) 
	 * puisqu'elle sera toujours hors borne et passera donc par ce segment 
	 * non-couvert.
	 */
	@Test
	public void testRemoveAtHorsBornesAvant() {
		Suite suite = new SuiteChainee("file.properties","soustraction",5,6,7,true);		
		boolean exceptionAttrapee = false;
		try {
			suite.removeAt(-5);
		} catch (IndexOutOfBoundsException e) {
			exceptionAttrapee = true;
		}
		assertTrue(exceptionAttrapee);
	}
	
	/**
	 * Cas de test permettant de couvrir la branche lancant l'exception au sein
	 * de la méthode removeAt. Nous utilisons une valeur supérieur à la limite 
	 * puisqu'elle sera toujours hors borne et passera donc par ce segment 
	 * non-couvert.
	 */
	@Test
	public void testRemoveAtHorsBornesApres() {
		Suite suite = new SuiteChainee("file.properties","soustraction",5,6,7,true);		
		boolean exceptionAttrapee = false;
		try {
			suite.removeAt(10);
		} catch (IndexOutOfBoundsException e) {
			exceptionAttrapee = true;
		}
		assertTrue(exceptionAttrapee);
	}
	
	/**
	 * Cas de test permettant de couvrir la branche d'un index étant exactement
	 * de 0.
	 */
	@Test
	public void testRemoveAtBorneZero() {
		Suite suite = new SuiteChainee("file.properties","soustraction",5,6,7,true);		
		int sizeBefore = suite.getSize();
		int elementBefore = suite.getAt(0);		
		suite.removeAt(0);		
		int sizeAfter = suite.getSize();
		int elementAfter = suite.getAt(0);		
		assertTrue(sizeAfter == (sizeBefore -1));
		assertTrue(elementAfter!=elementBefore);
	}
	
	/**
	 * Cas de test permettant de couvrir la branche d'un index étant valide.
	 * Nous utilisons une valeur (4) se situant entre 0 et la limite spécifié
	 * dans le constructeur.
	 */
	@Test
	public void testRemoveAtIntraBornes() {
		Suite suite = new SuiteChainee("file.properties","soustraction",5,6,7,true);		
		int sizeBefore = suite.getSize();
		int elementBefore = suite.getAt(4);		
		suite.removeAt(4);		
		int sizeAfter = suite.getSize();
		int elementAfter = suite.getAt(4);		
		assertTrue(sizeAfter == (sizeBefore -1));
		assertTrue(elementAfter!=elementBefore);
	}
	
	/**
	 * Cas de test permettant de couvrir l'unique branche de la méthode ainsi 
	 * que la boucle et la condition indiquant qu'aucune n'a été trouvée au sein
	 * de la méthode {@code RemoveItem}. 
	 */
	@Test
	public void testRemoveItemInexistant() {
		Suite suite = new SuiteChainee("file.properties","soustraction",5,6,7,true);		
		boolean exceptionAttrapee = false;
		try {
			suite.removeItem(11);
		} catch (IllegalArgumentException e) {
			exceptionAttrapee = true;
		}
		assertTrue(exceptionAttrapee);
	}
	
	/**
	 * Cas de test permettant de couvrir les branches permettant de réassigner
	 * les valeurs de queue et de tête de la suite. On utilise une suite ayant
	 * plusieurs éléments
	 */
	@Test
	public void testRemoveItemPlusieurs() {
		Suite suite = new SuiteChainee("file.properties","soustraction",2,3,3,true);		
		suite.removeItem(3);
		assertEquals(2, suite.getSize());
	}
	
	/**
	 * Cas de test permettant de couvrir la méthode {@link reset}. 
	 * Nous construisons une liste et la réinitialisons ensuite. Puisqu'il
	 * n'y a qu'une seule branche, il est inutile de réaliser plusieurs cas de 
	 * test.
	 */
	@Test
	public void testReset() {
		SuiteChainee suite = new SuiteChainee("file.properties","soustraction",5,6,4,true);
		suite.reset();
		assertTrue(suite.getSize()==0);
	}
	
	/**
	 * Cas de test permettant de couvrir la branche principale de la méthode
	 * {@link setAt}, c'est-à-dire celle ne lançant pas d'exceptions. Nous 
	 * créons une liste et choisissons une valeur entre 0 et la limite spécifiée
	 * dans le constructeur. Ici, il s'agit de la position 4.
	 */
	@Test
	public void testSetAtIntraBornes() {
		Suite suite = new SuiteChainee("file.properties","soustraction",5,6,7,true);		
		int sizeBefore = suite.getSize();
		int element = 3;
		int position = 4;
		suite.setAt(element, position);
		int sizeAfter = suite.getSize();
		assertTrue(sizeAfter == sizeBefore);
		assertTrue(suite.getAt(position)==element);
	}
	
	/**
	 * Cas de test permettant de couvrir à la fois la méthode {@link main}, 
	 * mais aussi la branche sélectionnant la multiplication au sein de la 
	 * méthode privée {@link createOperator}. On crée une suite comme si on 
	 * l'appelait à la ligne de commande et on vérifie la sortie standard en
	 * la redirigeant vers une variable interne à la méthode de test.
	 */
	@Test
	public void testMainSuiteValide() {
		ByteArrayOutputStream sortie = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sortie));
		String[] arguments = { "file.properties", "multiplication", "2", "2", 
				               "4", "true" };
		try {
			SuiteChainee.main(arguments);
		} catch (Exception e) {
			fail("Échec du test: " + e.getMessage());
		}
		String resultat = sortie.toString();
		assertEquals("file.properties : 2, 2, 4, 8",  resultat);
	}
	
	/**
	 * Cas de test permettant de couvrir le cas où la taille est trop grande
	 */
	@Test
	public void testSuiteConstructeurHorsBornes() {
		boolean estValide = false;
		try {
			Suite suite = new SuiteChainee("file.properties", "addition", 2, 2, 14, true);
		} catch (IllegalArgumentException e) {
			estValide = true;
		}
		assertTrue(estValide);
	}
	
	/**
	 * Cas de test permettant de couvrir le cas où on appel la méthode {@link 
	 * isValid} et que la taille de la liste est inférieure à deux.
	 */
	@Test
	public void testSuiteIsValide() {
		Suite suite = new SuiteChainee("file.properties", "addition", 2, 2, 2, true);
		suite.removeAt(0);
		assertFalse(suite.isValide());
	}
}
