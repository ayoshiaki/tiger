/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.graphictree;

import org.abego.treelayout.Configuration;
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;

/**
 *
 * @author Thales
 */
public class CreateGraphicTree {

    private GraphicTree gt = null;
    private StringToTree stt = null;
    private TreeExtentProvider tep = new TreeExtentProvider();
    private Configuration con = null;
    
    public CreateGraphicTree() {
        this.stt = new StringToTree();
        tep = new TreeExtentProvider();
        con = new DefaultConfiguration(30, 30, Configuration.Location.Top, Configuration.AlignmentInLevel.AwayFromRoot);
        
    }

    public String createString(String str) {
        String strTree;
        String strs[];
        strs = str.split(":", 2);
        strTree = strs[1];
        strs = strTree.split("\n");
        strTree = "";
        for (int i = 0; i < strs.length; i++) {
            strTree += strs[i].trim();
        }
        System.out.println(strTree);
        return strTree;
    }
    
    public GraphicTreePane createGraphicTreePane(String str) {
        gt = stt.stringToTree(str);
        TreeLayout tl = new TreeLayout(gt, tep, con);
        GraphicTreePane treePane = new GraphicTreePane(tl);
        
        return treePane;
    }
}
