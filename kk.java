//package javaapplication2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
        

public class kk extends JFrame
{
    int k=0;
    JPanel jp1,jp2;
    GridBagLayout gbl;
    public kk()
    {
         super("My first Application");
         final int a=0;
         gbl=new GridBagLayout();
         this.getContentPane().setLayout(gbl);
         addWindowListener(new WindowAdapter() 
         {
             public void windowClosing(WindowEvent e)
             {
                 k=1;
                 dispose();
                 System.exit(0);
             }
         });
         jp1=new JPanel();
         jp1.setBackground(Color.red);
         GridBagConstraints gbc1=new GridBagConstraints();
         gbc1.gridx=0;
         gbc1.gridy=0;
         gbc1.weightx=1;
         gbc1.weighty=0.5;
         gbc1.fill=GridBagConstraints.BOTH;
         gbc1.anchor=GridBagConstraints.NORTHWEST;
         gbl.setConstraints(jp1, gbc1);
         this.getContentPane().add(jp1);
         
         jp2=new JPanel();
         jp2.setBackground(Color.blue);
         GridBagConstraints gbc2=new GridBagConstraints();
         gbc2.gridx=0;
         gbc2.gridy=1;
         gbc2.weightx=1;
         gbc2.weighty=0.5;
         gbc2.fill=GridBagConstraints.BOTH;
         gbc2.anchor=GridBagConstraints.NORTHWEST;
         gbl.setConstraints(jp2, gbc2);
         this.getContentPane().add(jp2);
         
         Thread t=new Thread()
         {
             public void run()
             {
                 System.out.println("Thread printing");
             }
         };
         t.start();
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
         setSize(900,900);
    }
    public static void main(String[] args)
    {
         thad t=new thad();
         Thread tt=new Thread(new Runnable()
         {
             public void run()
             {
                 
             }
         });
    }   
}
class thad
{
    int y=7;
    static public class mm
    {
       int p=9; 
       public void display()
       { 
           System.out.println("static nested mm"); 
       }
    }
    class kk 
    {
       int a=0;
       public void disp()
       { 
           System.out.println("inner kk");
           mm obj=new mm();
       }                  
    }
    public void display()
    {
       int k=1;
       
    }
}
