package ca.polymtl.log3430.tp3;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.polymtl.log3430.*;

/**
 * Tests de type boîte blanche utilisant le critère de couverture des 
 * arrêtes. Les tests présents dans cette classe y sont, car ils ne peuvent
 * être effectués au travers de la classe {@link SuiteChainee}.
 * 
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public class ListeChaineeTestBB {

	/**
	 * Cas de test permettant de couvrir la branche lancant l'exception au sein
	 * de la méthode getAt. Nous utilisons une valeur négative (-5) puisqu'elle
	 * sera toujours hors borne et passera donc par ce segment non-couvert.
	 */
	@Test
	public void testGetAtHorsBornesAvant() {
		boolean exceptionAttrapee = false;
		ListeChainee liste = new ListeChainee();
		try {
			liste.getAt(-5);
		} catch (IndexOutOfBoundsException e) {
			exceptionAttrapee = true;
		}
		assertTrue(exceptionAttrapee);
	}
	
	/**
	 * Cas de test permettant de couvrir la branche lancant l'exception au sein
	 * de la méthode getAt. Nous utilisons une valeur supérieur à la limite
	 * puisqu'elle sera toujours hors borne et passera donc par ce segment 
	 * non-couvert.
	 */
	@Test
	public void testGetAtHorsBornesApres() {
		boolean exceptionAttrapee = false;
		ListeChainee liste = new ListeChainee();
		try {
			liste.getAt(1);
		} catch (IndexOutOfBoundsException e) {
			exceptionAttrapee = true;
		}
		assertTrue(exceptionAttrapee);
	}

	/**
	 * Cas de test permettant de couvrir les branches réassignant les valeurs
	 * de queue de liste. Supprimer le dernier élement d'une liste
	 * à cet effet.
	 */
	@Test
	public void testRemoveAtDernier() {
		ListeChainee liste = new ListeChainee();
		liste.add(6);
		liste.add(5);
		liste.removeAt(1);
		assertEquals(1, liste.getSize());
	}
	
	/**
	 * Cas de test permettant de couvrir les branches réassignant les valeurs
	 * de tête. Supprimer le premier élement d'une liste à cet effet.
	 */
	@Test
	public void testRemoveAtPremier() {
		ListeChainee liste = new ListeChainee();
		liste.add(6);
		liste.add(5);
		liste.removeAt(0);
		assertEquals(1, liste.getSize());
	}
	
	/**
	 * Cas de test permettant de couvrir la boucle et les branches supprimant le
	 * noeud et réaffectant les valeurs de queue et de tête. Pour ce faire, 
	 * nous utilisons une liste ayant un seul élément (5).
	 */
	@Test
	public void testRemoveItemUnSeulElement() {
		Liste liste = new ListeChainee(); 	
		liste.add(5);
		liste.removeItem(5);
		assertEquals(0, liste.getSize());
	}

	/**
	 * Cas de test permettant de couvrir la branche lancant l'exception au sein
	 * de la méthode {@link setAt}. Nous utilisons une valeur négative (-5) 
	 * puisqu'elle sera toujours hors borne et passera donc par ce segment 
	 * non-couvert.
	 */
	@Test
	public void testSetAtHorsBornesAvant() {
		boolean exceptionAttrapee = false;
		ListeChainee liste = new ListeChainee();
		try {
			liste.setAt(8, -5);
		} catch (IndexOutOfBoundsException e) {
			exceptionAttrapee = true;
		}
		assertTrue(exceptionAttrapee);
	}
	
	/**
	 * Cas de test permettant de couvrir la branche lancant l'exception au sein
	 * de la méthode {@link setAt}. Nous utilisons une valeur supérieur à la 
	 * limite puisqu'elle sera toujours hors borne et passera donc par ce 
	 * segment non-couvert.
	 */
	@Test
	public void testSetAtHorsBornesApres() {
		boolean exceptionAttrapee = false;
		ListeChainee liste = new ListeChainee();
		try {
			liste.setAt(8, 3);
		} catch (IndexOutOfBoundsException e) {
			exceptionAttrapee = true;
		}
		assertTrue(exceptionAttrapee);
	}
}
