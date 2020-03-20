package com.nerdSpace.GUI;

import javax.swing.*;
import java.awt.*;

public class SplashDemo extends JPanel{
    int timeLeft;
    GuiDelegator commander;
    JLabel image=new JLabel(new ImageIcon("book.jpg"));
    JLabel text=new JLabel("DIVERGENCE");
    JProgressBar progressBar=new JProgressBar();
    JLabel message=new JLabel();//Crating a JLabel for displaying the message
    SplashDemo(GuiDelegator commander)
    {
        this.commander = commander;
        setSize(commander.getDimensions());
        addImage();
        addText();
        addProgressBar();
        addMessage();
        runningPBar();
    }

    public void addImage(){
        image.setSize(600,200);
        add(image);
    }
    public void addText()
    {
        text.setFont(commander.getDungeonFont());
        text.setBounds(170,220,600,40);
        text.setForeground(Color.BLUE);
        add(text);
    }
    public void addMessage()
    {
        message.setBounds(250,320,200,40);//Setting the size and location of the label
        message.setForeground(Color.black);//Setting foreground Color
        message.setFont(new Font("arial",Font.BOLD,15));//Setting font properties
        add(message);//adding label to the frame
    }
    public void addProgressBar(){
        progressBar.setBounds(100,280,400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLACK);
        progressBar.setValue(0);
        add(progressBar);
    }
    public void updateTimeLeft(int time) {
        timeLeft = time;
        runningPBar();
    }
    public void runningPBar(){

        progressBar.setValue(timeLeft);//Setting value of Progress Bar
        message.setText("LOADING "+timeLeft+"%");//Setting text of the message JLabel



        }
    public void confirm(){
        System.out.println("Confirmed start!");
    }
    }
