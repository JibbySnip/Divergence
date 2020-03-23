package com.nerdSpace.GUI;

import javax.swing.*;


public class HomeScreen{
    private JButton startButton;
    private JPanel rootPanel;
    private JButton graveyardButton;
    private JButton updateButton;
    private JButton settingsButton;
    private JLabel titleLabel;
    private JButton[] buttons = new JButton[] {graveyardButton,updateButton,settingsButton,startButton};
    GuiDelegator controller;
    enum switchBoard {
        SETTINGS,
        GAME,
        GRAVEYARD,
        UPDATE
    }

    HomeScreen(GuiDelegator controller) {
        this.controller = controller;
        fontInits();
        startButton.addActionListener(e -> controller.switchMenu(switchBoard.GAME));
        settingsButton.addActionListener(e -> controller.switchMenu(switchBoard.SETTINGS));
        graveyardButton.addActionListener(e -> controller.switchMenu(switchBoard.GRAVEYARD));
        updateButton.addActionListener(e -> controller.switchMenu(switchBoard.UPDATE));
    }

    private void fontInits() {

        for (JButton i : buttons) {
            i.setFont(GuiDelegator.dungeonFontSmall);
        }
        titleLabel.setFont(GuiDelegator.dungeonFontLarge);
    }

    public JPanel getRootPanel() {return rootPanel;}

}
