package log3430.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import log3430.source.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Cas de test de type boite noire permettant de tester le constructeur de la
 * classe @{link SuiteChainee} selon le critère AC.
 * 
 * @author David Kanaa
 * @author Nicolas Richard
 * @author Adrien Budet
 */
@RunWith(Parameterized.class)
public class SuiteChaineeTestAC {

	private String chemin;
	private String operateur;
	private int val1;
	private int val2;
	private int taille;
	private boolean etatVide;
	private boolean estErreur;
	
	/**
	 * Génère des données de tests. Chaque paire de données correspond à un cas
	 * de test. Les données sont générées à la manière d'un produit cartésien.
	 * 
	 * @return Les données de test.
	 */
	@Parameters(name = "AC{index}: {0},{1},{2},{3},{4}-{5}-{6}")
    public static Collection<Object[]> donnees() {    	
    	boolean estVide = false;
    	boolean estPasOp = false;
    	boolean estAvantBornes = false;
    	boolean estApresBornes = false;
    	Object[][] choix = new Object[6][];
        choix[0] = new Object[] { "liste.properties", "f4f4", ""};
        choix[1] = new Object[] { "addition", "soustraction", "multiplication", "division", "dsfsf" };
        choix[2] = new Object[] { Integer.MIN_VALUE, -100, -1, 0, 1, 100, Integer.MAX_VALUE };
        choix[3] = new Object[] { Integer.MIN_VALUE, -100, -1, 0, 1, 100, Integer.MAX_VALUE };
        choix[4] = new Object[] { 1, 2, 5, 10, 11 };
        choix[5] = new Object[] { true, false };
        ArrayList<Object[]> vals = new ArrayList<Object[]>();
        for (int i = 0; i < 4410; i++) {
        	Object[] temp = new Object[7];
        	for (int j = 0; j < 6; j++) {
        		temp[j] = choix[j][i % choix[j].length];
        	}       
        	if (temp[0].toString() == "" && estVide == false) {
        		if (estVide) {
        			continue;
        		} else {
            		temp[6] = true;
            		estVide = true;
        		}
        	}
        	else if ((int)temp[3] == 0 && 
        	    (temp[1].toString() == "division" || temp[1].toString() == "dsfsf")) {
        		if (!estPasOp)
        			estPasOp = true;
        		continue;
        	}
        	else if (temp[1] == "dsfsf") {
        		if (estPasOp) {
        			continue;
        		} else {
            		temp[6] = true;
            		estPasOp = true;
        		}
        	}
        	else if ((int)temp[4] == 1) {
        		if (estAvantBornes) {
        			continue;
        		} else {
            		temp[6] = true;
            		estAvantBornes = false;
        		}
        	}
        	else if ((int)temp[4] == 11) {
        		if (estApresBornes) {
        			continue;
        		} else {
            		temp[6] = true;
            		estApresBornes = false;
        		}
        	}
        	else {
        		temp[6] = false;
        	}
        	vals.add(temp);
        } 
        return vals;
    }

    /**
     * @param chemin    Chemin menant vers le fichier
     * @param operateur Opérateur à utiliser
     * @param val1      Première valeur de la suite.
     * @param val2      Seconde valeur de la suite.
     * @param taille    Taille maximale de la suite originale
     * @param etatVide  Si l'on doit charger les anciennes valeurs
     * @param erreur    Si une erreur est attendue.
     */
    public SuiteChaineeTestAC(String chemin, String operateur, int val1, int val2,
            int taille, boolean etatVide, boolean erreur) {
    	this.chemin = chemin;
    	this.operateur = operateur;
    	this.val1 = val1;
    	this.val2=val2;
    	this.taille=taille;
    	this.etatVide = etatVide;
    	this.estErreur = erreur;
    }
    
    /**
     * Créer un nouveau fichier contenant une liste valide
     */
    @Before
    public void creerFichier() {
    	new SuiteChainee("liste.properties", "addition", 1, 2, 5, true);
    }
    
    /**
     * Détruit le fichier générer par l'appel au constructeur de la suite.
     */
    @After
    public void detruireFichier() {
    	File f = new File(chemin);
    	if (f.exists()) {
    		f.delete();
    	}
    }
    
    /**
     * Vérifie qu'un fichier existe suite à l'exécution du constructeur et qu'il est bien formé"
     */
    @Test
    public void verifierFichier() {
    	try {
    		File f = new File(chemin);
    		InputStream s = null;
    		Properties p = null;;
    		String pOp = operateur;
    		String pVal1 = Integer.toString(val1);
    		String pVal2 = Integer.toString(val2);
    		if (f.exists() && !etatVide) {
        		s = new FileInputStream(f);
        		p = new Properties();
        		p.load(s);
        		pOp = p.getProperty("Parametre3");
        		pVal1 = p.getProperty("Parametre1");
        		pVal2 = p.getProperty("Parametre2");
        		s.close();
    		}
    		p = new Properties();
    		SuiteChainee suite = new SuiteChainee(chemin,operateur,val1,val2,taille,etatVide);
    		s = new FileInputStream(f);
    		p.load(s);
    		s.close();
    		assertEquals(p.getProperty("Parametre1"), pVal1);
    		assertEquals(p.getProperty("Parametre2"), pVal2);
    		assertEquals(p.getProperty("Parametre3"), pOp);
    		assertEquals(p.getProperty("Parametre4"), Integer.toString(suite.getSize()));
    		assertEquals(p.getProperty("Parametre5"), Integer.toString(taille));
    		String[] vals = p.getProperty("Parametre6").split(", ");
    		for (int i = 0; i < vals.length; i++) {
    			assertEquals(vals[i], Integer.toString(suite.getAt(i)));
    		}
    	} catch(Exception e){
    		assertTrue(estErreur);
		}
    }    
    
    /**
     * Vérifie si la suite est correctement bâtie et valide.
     */
    @Test
	public void test() {    	
    	SuiteChainee suite = null;
		try{
			suite = new SuiteChainee(chemin,operateur,val1,val2,taille,etatVide);	
			assertTrue(suite.isValide());
			File f = new File(chemin);
			assertTrue(f.exists() && !f.isDirectory());
		} catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException &&(operateur.equals("dsfsf")||taille < 2 || taille > 10));
		}
	}    
}
