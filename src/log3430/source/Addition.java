package log3430.source;
/**
 * Classe permettant de r�aliser l'addition.
 *
 * @author Chunxia Zhang
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public class Addition implements ICommand {

    @Override
    public int operate(int val1, int val2) {
        int res = val1;
        if (val2 > 0) {
            while(val2-- != 0) {
                res++;
            }
        } else if (val2 < 0) {
            while(val2++ != 0) {
                res--;
            }
        }
        return res;
    }
}
