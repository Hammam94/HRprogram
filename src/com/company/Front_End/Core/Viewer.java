package com.company.Front_End.Core;

import com.company.Back_End.Core.viewer;

import javax.swing.*;
import java.util.Stack;

/**
 * Created by user on 8/29/2016.
 */
public class Viewer extends viewer {
    protected static JFrame mainFrame = new JFrame();

    private static Stack<JPanel> backwardPanels = new Stack<JPanel>();
    private static Stack<JPanel> forwardPanels = new Stack<JPanel>();
    private static JPanel mainPanel;

    public void design(){
        mainFrame = new JFrame("APP");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setMainPanel(JPanel mainPanel){
        this.mainPanel = mainPanel;
        mainFrame.setContentPane(mainPanel);
        mainFrame.validate();
    }

    public static void openPanel(JPanel currentPanel, JPanel targetPanel){
        backwardPanels.push(currentPanel);
        mainFrame.setContentPane(targetPanel);
        mainFrame.validate();
    }

    public static void backward(JPanel currentPanel){
        forwardPanels.push((JPanel) mainFrame.getContentPane());
        if(backwardPanels.isEmpty()){
            mainFrame.setContentPane(mainPanel);
            mainFrame.validate();
        }else{
            mainFrame.setContentPane(backwardPanels.pop());
            mainFrame.validate();
        }
    }

    public static boolean forward(JPanel currentPanel){
        backwardPanels.push((JPanel) mainFrame.getContentPane());
        if(forwardPanels.isEmpty()){
            return false;
        }else{
            mainFrame.setContentPane(backwardPanels.pop());
            mainFrame.validate();
            return true;
        }
    }

}
