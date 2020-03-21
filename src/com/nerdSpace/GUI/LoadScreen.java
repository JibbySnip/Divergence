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
    public boolean loadComplete = false;
    int loadTime = 5;
    int currTime = 0;


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
        currTime += 1;
        progressBar.setValue(currTime * (100 / loadTime));//Setting value of Progress Bar
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
                loadComplete = true;
                System.out.println(loadComplete);
                loadTimer.cancel();
            }
        }, 1000 * loadTime);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

}
