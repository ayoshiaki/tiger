/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.graphictree;

import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
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

    public GraphicTree stringToTree(String stringTree) {
        this.stringTree = stringTree;
        relations = new ArrayList<Relation>();
        nodes = new ArrayList<TextInBox>();

        createRelations();
        createGraphicTree();

        return graphicTree;
    }

    private TextInBox newNode(String nome) {
        JLabel label = new JLabel();
        label.setText(nome);
        FontMetrics tamanho =label.getFontMetrics(label.getFont());   
        return new TextInBox(nome, tamanho.stringWidth(nome)+8, 20);
    }    
    
    private void pushNode(TextInBox node) {
        if (!node.text.equals("")) {
            nodes.add(node);
        }
    }

    private TextInBox popNode() {
        return nodes.remove(nodes.size() - 1);
    }

    private void addLeafNodeRelation() {
        Relation relation = new Relation();
        List<TextInBox> filhos = new ArrayList<TextInBox>();
        TextInBox filho = popNode();
        filhos.add(0, filho);
        TextInBox pai = popNode();
        pushNode(pai);
        relation.setPai(pai);
        relation.setFilhos(filhos);
        relations.add(0, relation);
    }
    
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
    
    private void createGraphicTree() {
        graphicTree = new GraphicTree(relations.get(0).getPai());

        int numRelations = relations.size();
        int counter = 0;

        for (counter = 0; counter < numRelations; counter++) {
            Relation relation = relations.get(counter);
            int numFilhos = relation.getFilhos().size();
            int counter2 = 0;
            for (counter2 = 0; counter2 < numFilhos; counter2++) {
                //System.out.println(relation.getPai().text + ", " + relation.getFilhos().get(counter2).text);
                graphicTree.addChild(relation.getPai(), relation.getFilhos().get(counter2));
            }
        }
    }
}
