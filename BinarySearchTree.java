package com.leonidas.abb.tree;

import java.awt.*;

public class BinarySearchTree {
    private class Node {
        int value;
        Node left, right;
        Node(int value) { this.value = value; }
    }

    private Node root;

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.value) node.left = insertRecursive(node.left, value);
        else if (value > node.value) node.right = insertRecursive(node.right, value);
        return node;
    }

    public void clear() {
        root = null;
    }

    public void draw(Graphics2D g, int x, int y, int offset) {
        g.setStroke(new BasicStroke(2));
        if (root != null) drawNode(g, root, x, y, offset);
    }

    private void drawNode(Graphics2D g, Node node, int x, int y, int offset) {
        int nodeSize = 35;
        g.setColor(new Color(135, 206, 250));
        g.fillOval(x - nodeSize/2, y - nodeSize/2, nodeSize, nodeSize);
        g.setColor(Color.BLACK);
        g.drawOval(x - nodeSize/2, y - nodeSize/2, nodeSize, nodeSize);
        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        FontMetrics fm = g.getFontMetrics();
        String text = String.valueOf(node.value);
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        g.drawString(text, x - textWidth/2, y + textHeight/4);

        if (node.left != null) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(x, y, x - offset, y + 60);
            drawNode(g, node.left, x - offset, y + 60, offset / 2);
        }
        if (node.right != null) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(x, y, x + offset, y + 60);
            drawNode(g, node.right, x + offset, y + 60, offset / 2);
        }
    }
}
