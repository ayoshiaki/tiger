/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphictree;

import org.abego.treelayout.NodeExtentProvider;
import org.abego.treelayout.demo.TextInBox;

/**
 *
 * @author hideki
 */
public class TreeExtentProvider implements NodeExtentProvider{

    @Override
    public double getWidth(Object tn) {
        TextInBox node = (TextInBox)tn;
        return node.width;
    }

    @Override
    public double getHeight(Object tn) {
        TextInBox node = (TextInBox)tn;
        return node.height;
    }
    
}
