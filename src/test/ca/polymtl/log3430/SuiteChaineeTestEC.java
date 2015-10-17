package ca.polymtl.log3430;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Cas de test de type boite noire permettant de tester le constructeur de la
 * classe @{link SuiteChainee} selon le critère EC.
 * 
 * @author David Kanaa
 * @author Nicolas Richard
 * @author Adrien Budet
 */
@RunWith(Parameterized.class)
public class SuiteChaineeTestEC {

	private String chemin;
	private String operateur;
	private int val1;
	private int val2;
	private int taille;
	private boolean etatVide;	
	
	@Parameters(name = "EC{index}: {0}-{1}-{2}-{3}-{4}-{5}")
    public static Collection<Object[]> donnees() {
        return Arrays.asList(new Object[][] {     
                 {"liste.properties","addition",2,3,6,true},
                 {"liste.properties","dsfsf",2,3,6,true},
                 {"liste.properties","soustraction",2,3,-1,true},
                 {"f4f4","addition",1,2,5,false},
                 {"liste.properties","division",1,2,5,false},
                 {"liste.properties","division",1,2,0,false},
                 {"liste.properties","division",1,2,10,false},
                 
           });
    }
    
    public SuiteChaineeTestEC(String chemin, String operateur, int val1, int val2,
            int taille, boolean etatVide) {

    	this.chemin = chemin;
    	this.operateur = operateur;
    	this.val1 = val1;
    	this.val2=val2;
    	this.taille=taille;
    	this.etatVide = etatVide;
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
    		assertTrue(e instanceof IllegalArgumentException &&(!operateur.equals("addition")||!operateur.equals("soustraction")||!operateur.equals("multiplication")||!operateur.equals("division")||taille < 2 || taille > 10));
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