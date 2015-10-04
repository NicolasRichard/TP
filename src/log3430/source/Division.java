package log3430.source;
import static java.lang.Math.abs;

/**
 * Classe permettant de r�aliser la division.
 *
 * @author Chunxia Zhang
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public class Division implements ICommand {

    @Override
    public int operate(int val1, int val2) {
        int res = 0;
        int absVal1 = abs(val1);
        int absVal2 = abs(val2);
        Multiplication mul = new Multiplication();
        if (val2 == 0) {
            throw new IllegalArgumentException("Division par zéro");
        } else {
            int count = res;
            int temp = mul.operate(count, absVal2);
            while(temp <= absVal1) {
                res = count;
                count++;
                temp = mul.operate(count, absVal2);
            }
        }
        if (!((val1 > 0 && val2 > 0) || (val1 < 0 && val2 < 0))) {
            res *= -1;
        }
        return res;
    }
}
