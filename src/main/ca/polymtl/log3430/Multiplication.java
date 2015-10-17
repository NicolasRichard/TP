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
        Addition add = new Addition();
        int absVal1 = abs(val1);
        int res = 0;
        int absVal2 = abs(val2);
        if (absVal2 == 0) {
            res = 0;
        } else {
            while(absVal2-- != 0) {
                res += add.operate(0, absVal1);

            }
        }
        if(!((val1 > 0 && val2 > 0) || (val1 < 0 && val2 < 0))) {
            res *= -1;
        }
        return res;
    }
}