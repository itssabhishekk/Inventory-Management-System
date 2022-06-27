/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class Main {
    
    public Main(){
        
    }
    public static void main(String[] args) {
        
        splash();
        
        MainFrame login = new MainFrame();
        login.setIconImage(new ImageIcon("src/Main/Images/logo.png").getImage());
        login.setVisible(true);
    }
    public static void splash(){
        
       System.setProperty("sun.java2d.uiScale", "1"); // Setting the UI Scale to default 100% resolution 
        
       SplashScreen startScreen = new SplashScreen();
       startScreen.setIconImage(new ImageIcon("src/Main/Images/logo.png").getImage());
       startScreen.setVisible(true);
       int count = 3;
       String dot = " .";
        
        try{
            for(int i = 0; i <101; i++){
                Thread.sleep(15);
                startScreen.jProgressBar1.setValue(i);
                startScreen.jLabelPercentage.setText(String.valueOf(i)+" %");
                if(i % 13 == 0){
                    count ++;
                    if(count>3)count=0;
                    startScreen.jLabelLoading.setText("Loading"+dot.repeat(count));
                }
            }
            Thread.sleep(400);
            startScreen.dispose();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
}
