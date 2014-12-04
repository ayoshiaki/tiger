/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.parser;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class Fundo extends JDesktopPane {

    private Image img;

    public Fundo(String caminho) {
        try {
            img = new ImageIcon(getClass().getResource(caminho)).getImage();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro " + ex.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            Dimension dimension = this.getSize();
            int x = (int) (dimension.getWidth() - img.getWidth(this)) / 2;
            int y = (int) (dimension.getHeight() - img.getHeight(this)) / 2;

            g.drawImage(img, x, y, img.getWidth(this), img.getHeight(this), this);
        } else {
            g.drawString("Imagem nao encontrada", 50, 50);
        }
    }
}

   