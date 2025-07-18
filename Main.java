package com.leonidas.abb.ui;

import com.leonidas.abb.tree.BinarySearchTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private final BinarySearchTree tree = new BinarySearchTree();
    private final DrawPanel drawPanel = new DrawPanel();

    public Main() {
        setTitle("Simulador de Árvore Binária de Busca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JTextField inputField = new JTextField(10);
        JButton insertButton = new JButton("Inserir");
        JButton clearButton = new JButton("Limpar");

        insertButton.setBackground(new Color(0, 150, 0));
        insertButton.setForeground(Color.WHITE);
        clearButton.setBackground(new Color(180, 0, 0));
        clearButton.setForeground(Color.WHITE);

        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
        insertButton.setFont(buttonFont);
        clearButton.setFont(buttonFont);

        controlPanel.add(new JLabel("Valor:"));
        controlPanel.add(inputField);
        controlPanel.add(insertButton);
        controlPanel.add(clearButton);

        add(controlPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    tree.insert(value);
                    drawPanel.repaint();
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this, "Digite um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tree.clear();
                drawPanel.repaint();
            }
        });
    }

    private class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(new Color(245, 245, 255));
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            tree.draw(g2d, getWidth() / 2, 50, getWidth() / 4);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
