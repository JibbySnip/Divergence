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
    int screen_x = 728;
    int screen_y = 450;

    public static Font dungeonFontLarge = initDungeonFont(56);
    public static Font dungeonFontMedium = initDungeonFont(32);
    public static Font dungeonFontSmall = initDungeonFont(12);
    boolean initWSplash;
    volatile boolean splashLoaded = false;
    private final Logger logger = Logger.getLogger(this.getName());
    LoadScreen splashScreen;
    private boolean startGame = false;

    private enum status{
        STARTUP,
        INIT_GUI,
        INIT_SPLASH_SCREEN,
        RUN_SPLASH_SCREEN,
        INIT_HOME_SCREEN,
        RUN_HOME_SCREEN,
        SETTINGS,
        GRAVEYARD,
        UPDATING,
        LOADING_GAME
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
                break;
            case INIT_GUI:
                initLog();
                initFrame();
                initPanels();
                setCurrStatus(initWSplash ? status.INIT_SPLASH_SCREEN : status.INIT_HOME_SCREEN);
                logger.info("GUI Initialization complete");
                break;
            case INIT_SPLASH_SCREEN:
                setContentPane(splashScreen.getRootPanel());
                validate();
                setCurrStatus(status.RUN_SPLASH_SCREEN);
                splashScreen.init();
                break;
            case RUN_SPLASH_SCREEN:
//                logger.info("Running splash screen. loadComplete is " + splashScreen.loadComplete);

                if (splashLoaded) {
                    logger.info("Load complete. Switching to home screen...");
                    setCurrStatus(status.INIT_HOME_SCREEN);
                }
                break;
            case INIT_HOME_SCREEN:
                setContentPane((new HomeScreen(this).getRootPanel()));
                validate();
                setCurrStatus(status.RUN_HOME_SCREEN);
            case RUN_HOME_SCREEN:
                break;
            default:
                logger.info("Done!");
                System.exit(0);
        }
    }

    public GuiDelegator(boolean initWithSplashScreen) {
        setCurrStatus(status.INIT_GUI);
        initWSplash = initWithSplashScreen;
    }

    private static Font initDungeonFont(int size){
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/com/nerdSpace/GUI/res/dungeonFont.TTF"));
            font = font.deriveFont(Font.BOLD, size);
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (FontFormatException | IOException e) {
            font = Font.decode("SERIF");
        }
        return font;
    }

    private void initFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setSize(screen_x, screen_y);
                setTitle("Divergence");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setVisible(true);
            }
        });

    }

    private void initLog() {
        FileHandler fh;
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");

        try {
            fh = new FileHandler("src/com/nerdSpace/logs/DevLog_"
                    + format.format(Calendar.getInstance().getTime()) + ".log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {

        }
    }

    public void passSplash() {splashLoaded = true;}
    public void switchMenu(HomeScreen.switchBoard newMenu) {
        switch (newMenu) {

            case SETTINGS:
                setCurrStatus(status.SETTINGS);
            case GAME:
                setCurrStatus(status.LOADING_GAME);
            case GRAVEYARD:
                setCurrStatus(status.GRAVEYARD);
            case UPDATE:
                setCurrStatus(status.UPDATING);
        }
    }

    private void initPanels() {
        if (initWSplash) {
            splashScreen = new LoadScreen(this);
        }
    }
}


