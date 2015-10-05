package log3430.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import log3430.source.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.io.File;


@RunWith(Parameterized.class)
public class SuiteChaineeTestEC {

	private String chemin;
	private String operateur;
	private int val1;
	private int val2;
	private int taille;
	private boolean etatVide;
	
	
	@Parameters
    public static Collection<Object[]> donnees() {
        return Arrays.asList(new Object[][] {     
                 {"liste.properties","addition",2,3,6,true},
                 {"liste.properties","dhwdjwd",2,3,6,true},
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
	
   
    
    @Test
	public void test() {
    	
    	SuiteChainee suite = null;
    	boolean continuer = true;
    	
		try{
			
			suite = new SuiteChainee(chemin,operateur,val1,val2,taille,etatVide);
			

			
		}
		
		catch(Exception e){ 
		
			// verifie le comportement en fonction de la validite de l'operateur et de la taille de la suite
			assertTrue(e instanceof IllegalArgumentException &&(!operateur.equals("addition")||!operateur.equals("soustraction")||!operateur.equals("multiplication")||!operateur.equals("division")||taille < 2 || taille > 10));
			continuer = false;
			
		}
		
		if(continuer){
		assertTrue(suite.isValide()); // on verifie que la suite est valide
		File f = new File(chemin); // on verifie que le fichier est bien cree
		assertTrue(f.exists() && !f.isDirectory());
		}
		
	}
}