package gui_elements;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;



public class CurvePanel extends JPanel
{
	ArrayList<Curve> curveList = new ArrayList<Curve>(); 
	
	public void add(Curve curve)
	{
		Rectangle r=this.getBounds();
		
		curve.setDimension((int)r.getWidth(),(int)r.getHeight());
		curveList.add(curve);
	}
	
	public void removeCurves()
	{
		curveList = new ArrayList<Curve>();
	}
	
	public void setScale_y(double min, double max)
	{
		for(int n=0;n<curveList.size();n++)
		{
			curveList.get(n).setScale_y( min, max);
		}
	}
	
	@Override 
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		for(int n=0;n<curveList.size();n++)
		{
			curveList.get(n).setDimension(width, height);
		}
	
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g); // original paintComponent aus JPanel aufrufen

		for(int n=0;n<curveList.size();n++)
		{
			curveList.get(n).paintComponent(g);
		}
		
		// draw a small frame
		g.setColor(Color.DARK_GRAY);
		g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);

	}
	
	public static void main(String[] args) 
	{
		JFrame fenster;
		fenster=new JFrame("curve plot");
	    fenster.setPreferredSize(new Dimension(840, 480));
	    fenster.getContentPane().setLayout(null);

	    fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		CurvePanel cPanel=new CurvePanel();
		cPanel.setBounds(0,0,240,300);
		
		Curve curve=new Curve();
		Curve curve1=new Curve();
		Curve curve2=new Curve();
		Curve curve3=new Curve();
		
		cPanel.add(curve);
		cPanel.add(curve1);
		cPanel.add(curve2);
		cPanel.add(curve3);

		
		fenster.add(cPanel);
		
		//curve.setDimension(200,100);
		
		int sigLen=100;
		double [] signal=new double[sigLen];
		
		for(int n=0;n<sigLen;n++)signal[n]= 10*Math.cos((double)n/10);
		curve3.setScale_y(-15, 15);
			
		

		
		fenster.pack();
		fenster.setVisible(true);
		
		int dataSize=200;
		for(int n=0;n<dataSize;n++)
		{
			curve.addValue(Math.sin((double)n/5));
			//curve1.addValue(Math.cos((double)n/5));
			//curve2.addValue(Math.sin((double)n/7));
			//curve3.addValue(Math.cos((double)n/10));
			curve3.setData(signal);
			cPanel.repaint();
			int t_ms=20;
		    try 
		    {
		        Thread.sleep(t_ms);           
		    } catch(InterruptedException ex) 
		    {
		        Thread.currentThread().interrupt();
		    }
		}

	}

}
