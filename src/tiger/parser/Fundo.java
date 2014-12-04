/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiger.parser;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JDesktopPane;

class Fundo extends JDesktopPane {  
  
        private Image img;  
  
        public Fundo() {  
            try {  
                img = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("/tiger/src/img.jpg"), "img.jpg"));  
            } catch (Exception ex) {  
            }  
        }  
  
        @Override
                public void paintComponent(Graphics g) {  
            super.paintComponent(g);  
            if (img != null) {  
                Dimension dimension = this.getSize();  
                int x = (int)(dimension.getWidth() - img.getWidth(this)) / 2;  
                int y = (int)(dimension.getHeight() - img.getHeight(this)) / 2;  
  
                g.drawImage(img, x, y, img.getWidth(this), img.getHeight(this), this);  
            } else {  
                g.drawString("Imagem nao encontrada", 50, 50);  
            }  
        }  
    }  