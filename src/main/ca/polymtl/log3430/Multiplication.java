package ca.polymtl.log3430;
import static java.lang.Math.abs;

/**
 * Classe permettant de r�aliser la multiplication.
 *
 * @author Chunxia Zhang
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public class Multiplication implements Calculator {

    @Override
    public int operate(int val1, int val2) {
    	int nbFois = abs(val1);
    	int val2Abs = abs(val2);
    	int resultat = 0;
    	for (int i = 0; i < nbFois; i++) {
    		resultat += val2Abs;
    	}
    	resultat = val1 < 0 ^ val2 < 0 ? -resultat : resultat;
    	return resultat;
    }
}