package com.nerdSpace.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;


public class GuiDelegator extends JFrame{
    int screen_x = 500;
    int screen_y = 450;
    Font dungeonFont;
    boolean initWSplash;
    private enum status{
        INIT_GUI,
        INIT_SPLASH_SCREEN,
        HOME_SCREEN,
        WAIT
    }
    status currStatus;

    public void setCurrStatus(status newStatus) {
        this.currStatus = newStatus;
        System.out.println(newStatus.name());
    }

    public void reload(){
        switch (currStatus) {
            case INIT_GUI:
                initDungeonFont();
                initUI();
                setCurrStatus(status.INIT_SPLASH_SCREEN);
            case INIT_SPLASH_SCREEN:
                splashDemo.start();
                setCurrStatus(status.WAIT);
            case HOME_SCREEN:
                System.out.println("Completed");
                System.exit(0);
            case WAIT:

        }
    }

    public GuiDelegator(boolean initWithSplashScreen) {
        setCurrStatus(status.INIT_GUI);
        initWSplash = initWithSplashScreen;
    }

    public Dimension getDimensions() {
        return new Dimension(screen_x,screen_y);
    }

    private void initDungeonFont(){
        File fontFile = new File("res/dungeonFont.TTF");
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            font = font.deriveFont(Font.BOLD,30);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (FontFormatException | IOException e) {
            font = Font.decode("SERIF");
        }
        dungeonFont = font;
    }

    public static GuiDelegator init(boolean initWithSplashScreen) {
        return new GuiDelegator(initWithSplashScreen);
    }

    private void initUI() {
        setSize(screen_x, screen_y);

        setTitle("Divergence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Font getDungeonFont() {
        return dungeonFont;
    }

    private Thread splashDemo = new Thread() {
        SplashDemo demo = new SplashDemo(GuiDelegator.this);
        public void run() {
            SwingUtilities.invokeLater(new Runnable() {  // inits the splash screen
                @Override
                public void run() {
                    add(demo);
                    demo.confirm();
                    System.out.println("Stuff's happening");
                }
            });
            for (int i=20; i>0; i=i-1) {
                final int sec = i;
                SwingUtilities.invokeLater(new Runnable() {  // runs the progress bar
                    public void run() {
                        demo.updateTimeLeft(sec);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });


                }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GuiDelegator.this.remove(demo);
                    setCurrStatus(status.HOME_SCREEN);
                }
            });
            System.out.println("I'm in a Thread!");

        }
        };
    }



