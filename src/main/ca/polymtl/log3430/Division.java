package ca.polymtl.log3430;
import static java.lang.Math.abs;

/**
 * Classe permettant de r�aliser la division.
 *
 * @author Chunxia Zhang
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public class Division implements Calculator {

    @Override
    public int operate(int val1, int val2) {
        int res = 0;
        long absVal1 = abs((long)val1);
        long absVal2 = abs((long)val2);
        if (val2 == 0) {
            throw new IllegalArgumentException("Division par zéro");
        } else {
        	while (absVal1 >= absVal2) {
        		absVal1 -= absVal2;
        		res++;
        	}
        }
        if (val1 < 0 ^ val2 < 0) {
            res *= -1;
        }
        return res;
    }
}
