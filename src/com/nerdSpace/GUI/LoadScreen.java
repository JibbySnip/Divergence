package com.nerdSpace.GUI;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class LoadScreen {
    private JProgressBar progressBar;
    private JLabel titleLabel;
    private JPanel rootPanel;
    private JLabel subtitleLabel;

    private java.util.Timer loadTimer = new Timer();
    int loadTime = 2;
    int currTime = 0;
    GuiDelegator callback;

    LoadScreen(GuiDelegator callback) {
        this.callback = callback;
    }

    public void init() {
        startPBar();
        titleLabel.setFont(GuiDelegator.dungeonFontLarge);
        subtitleLabel.setFont(GuiDelegator.dungeonFontMedium);
        rootPanel.setVisible(true);
        rootPanel.validate();
    }

    public void update() {
        runningPBar();
    }


    private void runningPBar() {
        currTime++;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                progressBar.setValue(currTime * (100 / loadTime));//Setting value of Progress Bar
                progressBar.setString((currTime * (100 / loadTime))+"%");
            }
        });
    }

    public void startPBar() {
        loadTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 1000);
        loadTimer.schedule(new TimerTask() {
            public void run() {
                callback.passSplash();
                loadTimer.cancel();
            }
        }, 1000 * (loadTime-1));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

}
