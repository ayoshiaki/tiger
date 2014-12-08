package tiger.graphictree;

import org.abego.treelayout.NodeExtentProvider;
import org.abego.treelayout.demo.TextInBox;

/**
 *
 * @author vinicius
 */
public class TreeExtentProvider implements NodeExtentProvider {

    @Override
    public double getWidth(Object tn) {
        TextInBox tib = (TextInBox) tn;
        return tib.width;
    }

    @Override
    public double getHeight(Object tn) {
        TextInBox tib = (TextInBox) tn;
        return tib.height;
    }

}

