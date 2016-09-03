package com.company.Front_End.Core;

import com.company.Back_End.core.viewer;
import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.Stack;

/**
 * Created by user on 8/29/2016.
 */
public class Viewer extends viewer {
    private JFrame jFrame;
    private JFXPanel mainPanel;
    private Stack<JFXPanel> backwardPanels = new Stack<JFXPanel>();
    private Stack<JFXPanel> forwardPanels = new Stack<JFXPanel>();
    private String currentTitle;

    public Viewer(String currentName){
        this.currentTitle = currentName;
        initFrame();
    }

    private void changeFrameTitle(String newTitle){
        this.currentTitle = newTitle;
    }

    private void initFrame() {
        jFrame = new JFrame();
        jFrame.setTitle(currentTitle);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
    }

    public void setMainPanel (JFXPanel mainPanel){
        this.mainPanel = mainPanel;
        this.mainPanel.setVisible(true);
        jFrame.setContentPane(this.mainPanel);
        jFrame.validate();
    }

    public void openPanel(JFXPanel currentPanel, JFXPanel targetPanel){
        backwardPanels.push(currentPanel);
        jFrame.setContentPane(targetPanel);
        jFrame.validate();
    }

    public void backward(JFXPanel currentPanel){
        forwardPanels.push((JFXPanel) jFrame.getContentPane());
        if(backwardPanels.isEmpty()){
            jFrame.setContentPane(mainPanel);
            jFrame.validate();
        }else{
            jFrame.setContentPane(backwardPanels.pop());
            jFrame.validate();
        }
    }

    public boolean forward(JFXPanel currentPanel){
        backwardPanels.push((JFXPanel) jFrame.getContentPane());
        if(forwardPanels.isEmpty()){
            return false;
        }else{
            jFrame.setContentPane(backwardPanels.pop());
            jFrame.validate();
            return true;
        }
    }
}
