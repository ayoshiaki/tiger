/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.graphictree;

import java.util.ArrayList;
import java.util.List;
import org.abego.treelayout.demo.TextInBox;

/**
 *
 * @author hideki, daniel, helder
 */
public class StringToTree {

    private String stringTree = null;
    private List<Relation> relations = null;
    private List<TextInBox> nodes = null;
    private GraphicTree graphicTree = null;
    private boolean flagLeafNodeRelation = false;

    private void addRelation() {
        Relation relation = new Relation();
        List<TextInBox> filhos = new ArrayList<TextInBox>();
        boolean condition = true;

        while (condition) {
            TextInBox node = popNode();

            if (node.text.equals("(")) {
                TextInBox keepNode = popNode();
                pushNode(keepNode);
                relation.setPai(keepNode);
                relation.setFilhos(filhos);
                condition = false;
            } else {
                filhos.add(0, node);
            }
        }

        relations.add(0, relation);
    }

    private void createRelations() {
        flagLeafNodeRelation = false;
        String word = "";
        int counter = 0;
        int size = stringTree.length();

        for (counter = 0; counter < size; counter++) {
            String character = stringTree.substring(counter, counter + 1);

            if (character.equals("(")) {
                pushNode(newNode(word));
                word = "";
                if (flagLeafNodeRelation) {
                    addLeafNodeRelation();
                    flagLeafNodeRelation = false;
                }
                pushNode(newNode("("));
            } else if (character.equals(",")) {
                pushNode(newNode(word));
                word = "";
                if (flagLeafNodeRelation) {
                    addLeafNodeRelation();
                    flagLeafNodeRelation = false;
                }
            } else if (character.equals(" ")) {
                pushNode(newNode(word));
                word = "";
                if (flagLeafNodeRelation) {
                    addLeafNodeRelation();
                    flagLeafNodeRelation = false;
                }
                flagLeafNodeRelation = true;
            } else if (character.equals(")")) {
                pushNode(newNode(word));
                word = "";
                if (flagLeafNodeRelation) {
                    addLeafNodeRelation();
                    flagLeafNodeRelation = false;
                }
                addRelation();
            } else {
                word += character;
            }
        }
    }

}
