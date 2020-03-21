package com.nerdSpace.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class GuiDelegator extends JFrame{
    int screen_x = 500;
    int screen_y = 450;
    Font dungeonFont;
    boolean initWSplash;
    private final Logger logger = Logger.getLogger(this.getName());
    SplashDemo splashScreen;
    private enum status{
        STARTUP,
        INIT_GUI,
        INIT_SPLASH_SCREEN,
        RUN_SPLASH_SCREEN,
        HOME_SCREEN,
    }
    status currStatus = status.STARTUP;

    private void setCurrStatus(status newStatus) {
        logger.info("Moved state from " + this.currStatus.name() + " to " + newStatus.name());
        this.currStatus = newStatus;

    }

    public void reload(){
        switch (currStatus) {
            case STARTUP:
                setCurrStatus(status.INIT_GUI);
            case INIT_GUI:
                initLog();
                initDungeonFont();
                initUI();
                initPanels();
                setCurrStatus(initWSplash ? status.INIT_SPLASH_SCREEN : status.HOME_SCREEN);
                logger.info("GUI Initialization complete");
            case INIT_SPLASH_SCREEN:
                add(splashScreen);
                splashScreen.startPBar();
                setCurrStatus(status.RUN_SPLASH_SCREEN);
            case RUN_SPLASH_SCREEN:
                logger.info("Running splash screen. loadComplete is " + splashScreen.loadComplete);
                if (splashScreen.loadComplete) {
                    logger.info("Load complete. Switching to home screen...");
                    setCurrStatus(status.HOME_SCREEN);
                }
            case HOME_SCREEN:
                logger.info("Reached home screen. Finishing... ");
                System.exit(0);
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
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/com/nerdSpace/GUI/res/dungeonFont.TTF"));
            font = font.deriveFont(Font.BOLD,30);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (FontFormatException | IOException e) {
            logger.warning("Could not initialize dungeonFont. Switching to Serif...");
            font = Font.decode("SERIF");
        }
        dungeonFont = font;
    }

    private void initUI() {
        setSize(screen_x, screen_y);
        setTitle("Divergence");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initLog() {
        FileHandler fh;
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");

        try {
            fh = new FileHandler("src/com/nerdSpace/logs/AppLog_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {

        }
    }

    public Font getDungeonFont() {
        return dungeonFont;
    }

    private void initPanels() {
        if (initWSplash) {  // TODO Create a panel parent class
            splashScreen = new SplashDemo(getDimensions(), getDungeonFont());
        }
    }
}


