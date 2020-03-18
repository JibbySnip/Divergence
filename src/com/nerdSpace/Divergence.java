package com.nerdSpace;

import com.nerdSpace.GUI.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Divergence extends JFrame{

    public Divergence(){
        initUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Divergence ex = new Divergence();
            ex.setVisible(true);
        });
    }

    private void initUI() {
        int screen_x = 500;
        int screen_y = 450;
        add(new MainPanel(screen_x, screen_y));
        setSize(screen_x, screen_y);

        setTitle("Divergence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


}
