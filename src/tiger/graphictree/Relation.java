/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.graphictree;

import java.util.List;
import org.abego.treelayout.demo.TextInBox;

/**
 *
 * @author Mollon
 */
public class Relation {

    public TextInBox pai;
    public List<TextInBox> filhos;

    public Relation() {
        pai = null;
        filhos = null;
    }

    public TextInBox getPai() {
        return pai;
    }

    public void setPai(TextInBox pai) {
        this.pai = pai;
    }

    public List<TextInBox> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<TextInBox> filhos) {
        this.filhos = filhos;
    }
}
