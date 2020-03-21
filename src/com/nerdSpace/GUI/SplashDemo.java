package com.nerdSpace.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class SplashDemo extends JPanel{
    private int timeLeft;
    private Font dungeonFont;
    boolean loadComplete = false;
    private JLabel image=new JLabel(new ImageIcon("book.jpg"));
    private JLabel text=new JLabel("DIVERGENCE");
    private JProgressBar progressBar=new JProgressBar();
    private JLabel message=new JLabel();//Crating a JLabel for displaying the message
    private Timer loadTimer = new Timer();
    SplashDemo(Dimension dimensions, Font dungeonFont)
    {
        setSize(dimensions);
        this.dungeonFont = dungeonFont;
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
        text.setFont(dungeonFont);
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
    public void updateTimeLeft() {
        timeLeft += 1;
        runningPBar();
    }
    private void runningPBar(){

        progressBar.setValue(timeLeft/20);//Setting value of Progress Bar
        message.setText("LOADING "+timeLeft+"%");//Setting text of the message JLabel
    }
    public void startPBar() {
        loadTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTimeLeft();
            }
        }, 1000, 20000);
        loadTimer.schedule(new TimerTask() {
            public void run() {
                loadComplete = true;
            }
        }, 20000);
    }
}
