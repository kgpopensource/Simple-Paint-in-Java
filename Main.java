//package javaapplication2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Main extends JFrame implements Runnable
{
    static int flag,mousepressed,mousedragged,mousereleased,tempx,tempy,mx,my,mousemoved,rx,ry,mousepress,mousedragg,specialflag;
    JLayeredPane jlp;
    jpanel j1,j2;
    Image ii,ii2;
	
    static int a=50,b=50;
    public void run()
    {
    }

    Main() 
    {
        super("My Paint");
        flag=-1;
        ii=new ImageIcon("pp.gif").getImage();
        ii2=new ImageIcon("kk.gif").getImage();
        MouseListener ml=new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                 mousepressed=1;
                 if(flag==0)
                 {  
                    j1.x=e.getX();
                    j1.y=e.getY();
                    j1.repaint();
                 }
                 if(flag==1)
                 {
                    j1.x=e.getX();
                    j1.y=e.getY();
                    j1.repaint();
                 }
            }
            public void mouseReleased(MouseEvent e)
            {
                 mousereleased=1;
                 if(flag==1)
                 {
                    jlp.setLayer(j1,1);
                    jlp.setLayer(j2,2);
                    j2.repaint();
                    if(Main.specialflag==1)
                    {
                         j2.repaint();
                    }
                 }
            }
        };
        MouseMotionListener mml=new MouseMotionListener()
        {
            public void mouseMoved(MouseEvent e)
            {
                 mousemoved=1;
                 if(flag==1)
                 {
                      jlp.setLayer(j1,2);
                      jlp.setLayer(j2,1);
                      j1.repaint();
                      jlp.setLayer(j1,1);
                      jlp.setLayer(j2,2);
                      j2.x=e.getX();
                      j2.y=e.getY();
                      j2.repaint();
                 }      
            }
            public void mouseDragged(MouseEvent e)
            {
                mousedragged=1;
                 if(flag==0)
                 {
                    j1.x1=e.getX();
                    j1.y1=e.getY();
                    j1.repaint();
                 }
                 if(flag==1)
                 {
                    j1.x1=e.getX();
                    j1.y1=e.getY();
                    jlp.setLayer(j1,2);
                    jlp.setLayer(j2,1);
                    j1.repaint();
                 }
            }

        };
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                 dispose();
                 System.exit(0);
            }
        });
        final String s="kk";
        final Point p=new Point(4,28);
        final Point p1=new Point(0,0);
        ActionListener al=new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getActionCommand()=="Pencil")
                {
                     jlp.setLayer(j1,2);
                     jlp.setLayer(j2,1);
                     flag=0;
                     j1.setCursor(getToolkit().createCustomCursor(ii,p,s));
                     
                }
                else if(e.getActionCommand()=="Eraser")
                {
                     jlp.setLayer(j1,1);
                     jlp.setLayer(j2,2);
                     flag=1;
                     j1.setCursor(getToolkit().createCustomCursor(ii2,p1,s));
                     j2.setCursor(getToolkit().createCustomCursor(ii2,p1,s));
                }
            }
        };
        Font f=new Font("monotype corsiva",3,30);
        GridBagLayout g=new GridBagLayout();
        setLayout(g);
        GridBagConstraints c1=new GridBagConstraints();
        JButton jb1=new JButton("Pencil",new ImageIcon("pp.gif"));
        jb1.setFont(f);
        c1.weightx=1;
        c1.weighty=0.0;
        c1.gridx=0;
        c1.gridy=0;
        c1.fill=GridBagConstraints.HORIZONTAL;
        c1.ipady=30;
        c1.anchor=GridBagConstraints.NORTHWEST;
        g.setConstraints(jb1, c1);
        getContentPane().add(jb1);
        jb1.addActionListener(al);

        GridBagConstraints c2=new GridBagConstraints();
        JButton jb2=new JButton("Eraser",new ImageIcon("eraser.gif"));
        jb1.setFont(f);
        c2.weightx=1;
        c2.gridx=1;
        c2.gridy=0;
        c2.fill=GridBagConstraints.HORIZONTAL;
        c2.ipady=30;
        c2.anchor=GridBagConstraints.NORTHWEST;
        g.setConstraints(jb2, c2);
        getContentPane().add(jb2);
        jb2.addActionListener(al);

        jlp=new JLayeredPane();
        GridBagConstraints c3=new GridBagConstraints();
        c3.gridx=0;
        c3.gridy=1;
        c3.gridwidth=2;
        c3.weighty=1;
        c3.anchor=GridBagConstraints.NORTHWEST;
        c3.fill=GridBagConstraints.BOTH;
        g.setConstraints(jlp, c3);

        j1=new jpanel(1);
        j1.setSize(1920,1080);
        j2=new jpanel(2);
        j2.setSize(1920,1080);

        jlp.add(j1,1);
        jlp.add(j2,2);

        j1.addMouseListener(ml);
        j1.addMouseMotionListener(mml);

        j2.addMouseListener(ml);
        j2.addMouseMotionListener(mml);

        j1.setOpaque(false);
        j2.setOpaque(false);

        this.getContentPane().add(jlp);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        Thread t=new Thread(new Main());
        t.start();
    }    
}
class jpanel extends JPanel
{
    Image i;
    static int check;
    int f;
    int x,y,x1,y1,mx,my;
    jpanel(int a)
    {
         check=0;
         f=a;
    }
    public void update(Graphics g)
    {
        paint(g);
    }
    public void paint(Graphics gg)
    {
         gg.setColor(Color.black);
             //gg.drawOval(Main.a,Main.b,50,50);
             Main.a+=1;
         if(f==1)
         {
             
             Graphics g;
             if(check==0)
             {
                 check=1;
                 i=createImage(1920,1080);
                 g=i.getGraphics();
                 g.setColor(Color.white);
                 g.fillRect(0,0,1920,1080);
             }
             g=i.getGraphics();
             if((Main.flag==0))
             {
                 g.setColor(Color.black);
                 if(Main.mousepressed==1)
                 {
                      Main.mousepressed=0;
                      g.drawLine(x,y,x,y);
                 }
                 if(Main.mousedragged==1)
                 {
                      Main.mousedragged=0;
                      g.drawLine(x,y,x1,y1);
                      x=x1;
                      y=y1;
                 }
             }
             else
             {
                 if(Main.mousepressed==1)
                 {
                      Main.mousepressed=0;
                      Main.mousepress=1;
                      g.setColor(Color.black);
                      g.drawRect(x-26,y-26,51,51);
                      Main.rx=x-26;
                      Main.ry=y-26;
                      g.setColor(Color.white);
                      g.fillRect(x-25, y-25, 50,50);
                 }
                 if(Main.mousedragged==1)
                 {
                      Main.mousedragged=0;
                      Main.mousedragg=1;
                      g.setColor(Color.white);
                      g.drawRect(Main.mx,Main.my,51,51);
                      g.drawRect(Main.rx,Main.ry,51,51);
                      g.fillRect(x1-25, y1-25, 50,50);
                      g.setColor(Color.black);
                      g.drawRect(x1-26, y1-26, 51,51);
                      Main.mx=x1-26;Main.my=y1-26;
                 }
                 if(Main.mousemoved==1)
                 {
                      g.setColor(Color.white);
                      g.drawRect(Main.mx,Main.my,51,51);
                 }
                 if(Main.mousereleased==1)
                 {
                      Main.mousereleased=0;
                      if(Main.mousedragg==0)
                      {
                            Main.specialflag=1;
                            g.setColor(Color.white);
                            g.drawRect(Main.rx,Main.ry,51,51);
                      }
                      else
                      {

                            Main.mousedragg=0;
                      }
                 }
             }
             gg.drawImage(i,0,0, this);
         }
         if(f==2)
         {
             if(Main.mousereleased==1)
             {
                 Main.mousereleased=0;
                 gg.setColor(Color.white);
                 gg.drawRect(Main.tempx, Main.tempy,51,51);
             }
             if(Main.mousemoved==1)
             {
                 Main.mousemoved=0;
                 gg.setColor(Color.black);
                 gg.drawRect(x-26, y-26, 51,51);
                 Main.tempx=x-26;Main.tempy=y-26;
                 gg.setColor(Color.white);
                 gg.fillRect(x-25, y-25, 50,50);
             }
             if(Main.specialflag==1)
             {
                 Main.specialflag=0;
                 gg.drawRect(Main.rx,Main.ry,51,51);
             }
         }
    }
}