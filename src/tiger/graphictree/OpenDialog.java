/**
 * Classe implementada usando como referência a classe TreeViewer.java do projeto ANTLR/ANTLR 4
 */
/*
 * [The "BSD license"]
 *  Copyright (c) 2012 Terence Parr
 *  Copyright (c) 2012 Sam Harwell
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tiger.graphictree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import org.antlr.v4.runtime.misc.JFileChooserConfirmOverwrite;

/**
 *
 * @author Helder e Daniel
 */
public class OpenDialog {
    
    public static void showInDialog(final JComponent panel) {
        final JDialog dialog = new JDialog();
        dialog.setTitle("Árvore Gráfica");

        Container mainPane = new JPanel(new BorderLayout(5, 5));
        mainPane.setBackground(Color.WHITE);

        mainPane.add(panel, BorderLayout.CENTER);

        JPanel wrapper = new JPanel(new FlowLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout(0, 0));
        mainPane.add(bottomPanel, BorderLayout.SOUTH);

        JButton ok = new JButton("OK");
        ok.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.setVisible(false);
                        dialog.dispose();
                    }
                }
        );
        wrapper.add(ok);

        // Add an export-to-png button right of the "OK" button
        JButton png = new JButton("Exportar .png");
        png.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        generatePNGFile(panel, dialog);
                    }
                }
        );
        wrapper.add(png);

        bottomPanel.add(wrapper, BorderLayout.SOUTH);

        dialog.setContentPane(mainPane);

        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.pack();

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    private static void generatePNGFile(JComponent viewer, JDialog dialog) {
        BufferedImage bi = new BufferedImage(viewer.getSize().width,
                viewer.getSize().height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        viewer.paint(g);
        g.dispose();

        try {
            File suggestedFile = generateNonExistingPngFile();
            JFileChooser fileChooser = new JFileChooserConfirmOverwrite();
            fileChooser.setCurrentDirectory(suggestedFile.getParentFile());
            fileChooser.setSelectedFile(suggestedFile);
            FileFilter pngFilter = new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    if (pathname.isFile()) {
                        return pathname.getName().toLowerCase().endsWith(".png");
                    }

                    return true;
                }

                @Override
                public String getDescription() {
                    return "PNG Files (*.png)";
                }
            };

            fileChooser.addChoosableFileFilter(pngFilter);
            fileChooser.setFileFilter(pngFilter);

            int returnValue = fileChooser.showSaveDialog(dialog);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File pngFile = fileChooser.getSelectedFile();
                ImageIO.write(bi, "png", pngFile);

                try {
                    // Try to open the parent folder using the OS' native file manager.
                    Desktop.getDesktop().open(pngFile.getParentFile());
                } catch (Exception ex) {
                    // We could not launch the file manager: just show a popup that we
                    // succeeded in saving the PNG file.
                    JOptionPane.showMessageDialog(dialog, "Saved PNG to: "
                            + pngFile.getAbsolutePath());
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialog,
                    "Could not export to PNG: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private static File generateNonExistingPngFile() {

        final String parent = ".";
        final String name = "arvore_grafica";
        final String extension = ".png";

        File pngFile = new File(parent, name + extension);

        int counter = 1;

        // Keep looping until we create a File that does not yet exist.
        while (pngFile.exists()) {
            pngFile = new File(parent, name + "_" + counter + extension);
            counter++;
        }

        return pngFile;
    }    
}