package Richard;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Block extends Track{
	
	private int ID;
	private int left;
	private int right;
	private int level;
	private int x = 100;
	private int y = 500;
	//private int noOfSignals;
	//private String signalDirection;
	private static List<Signal> signals = new ArrayList<Signal>();
	private LinkedList<Signal> signals2 = new LinkedList<Signal>();
	//private static List<Signal> signals;
	//private String block = "block";
	
	//public Block(int ID, int left, int right, int level, List<Signal> signals){
	public Block(int ID, int left, int right, int level){
		
		this.ID = ID;
		this.left = left;
		this.right = right;
		this.level = level;
		this.signals = new ArrayList<Signal>(signals);
		
		//this.noOfSignals = noOfSignals;
		//this.signalDirection = signalDirection;
		/*this.x = x;
		this.y = y;
		this.block = block;*/
	}

 
    public void draw(Graphics2D g2d, List<Track> tracks) {
    //void drawLines(Graphics2D g2d) {
    	
        //Graphics2D g2d = (Graphics2D) g;
        //int x1 = 0;
        
        //for(int i = 1; i < 5; i++){
        
	        /*g2d.drawLine(x, y, x + 100, y);
	        g2d.drawLine(x, y - 5, x, y + 5);
	        g2d.drawLine(x + 100 , y - 5, x + 100, y + 5);*/
    	g2d.setPaint(Color.black);
	        
    	if(ID == 1){
        	g2d.drawLine(x, y, x + 100, y);
	        g2d.drawLine(x, y - 5, x, y + 5);
	        g2d.drawLine(x + 100 , y - 5, x + 100, y + 5);
	        
	        g2d.drawString("b" + Integer.toString(ID), x + 40, y + 30);
    	}
    	else {
    		
    		int f = 0;
    		
    		for(int i = 0; i < tracks.size(); i++){
    			
    			Track currentSection = tracks.get(i);
    			Block currentBlock;
    			Point currentPoint;
    			
    			if(currentSection instanceof Block){
    				currentBlock = ((Block) currentSection);
    				
    				if(currentBlock.getID() == left){
    					
    					x = currentBlock.getXPos() + 100;
    					y = y - (80 * level);
    					
    					//int x1 = currentBlock.getXPos() + 100;
    	    			g2d.drawLine(x, y, x + 100, y);
    	    	        g2d.drawLine(x, y - 5, x, y + 5);
    	    	        g2d.drawLine(x + 100 , y - 5, x + 100, y + 5);
    	    	        
    	    	        g2d.drawString("b" + Integer.toString(ID), x + 40, y + 30);
        			}
    				
    	    	}
    	    	if(currentSection instanceof Point){
    	    		currentPoint = ((Point) currentSection);
    	    		
    	    		
    	    		
    	    		if(currentPoint.getID() == left){
    	    			
    	    			x = currentPoint.getXPos() + 100;
    	    			y = y - (80 * level);
    	    			
    	    			g2d.drawLine(x, y, x + 100, y);
    	    	        g2d.drawLine(x, y - 5, x, y + 5);
    	    	        g2d.drawLine(x + 100 , y - 5, x + 100, y + 5);
    	    	        f = 1;
    	    	        
    	    	        g2d.drawString("b" + Integer.toString(ID), x + 40, y + 30);
    	    	        //return;
        			}
    	    	}
    			//Block currentBlock = ((Block) currentSection);
    			
    			
    		}		
    	}
	        //x1 = x * (i + 1);
        //}
        
        /*g2d.drawLine(x1, y, x1 + 100, y);
        g2d.drawLine(x1 + 30, y, x1 + 80, y - 80);
        g2d.drawLine(x1 + 80, y - 80, x1 + 100, y - 80);
        
        g2d.drawLine(x1 + 100, y - 85, x1 + 100, y - 75);
        g2d.drawLine(x1 + 100, y - 5, x1 + 100, y + 5);*/

    }
 
    /*public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }*/
   
    
    public int getXPos(){
    	return x;
    }
    
    public int getYPos(){
    	return y;
    }
    
    public int getLeft(){
    	return left;
    }
    
    public int getRight(){
    	return right;
    }
    
    public int getID(){
    	return ID;
    }
    
    /*public List<Signal> getSignals(){
    	return new ArrayList<Signal>(signals);
    	//return new ArrayList<Signal>();
    }*/
    
    public LinkedList<Signal> getSignals(){
    	return signals2;
    	//return new ArrayList<Signal>();
    }
 
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new Block(1,0,2,0,100,500).setVisible(true);
                //new Block(1,0,2,0,200,100).setVisible(true);
            }
        });
    }*/
    
    public void addSig(Signal info){
    	
    	//signals.add(info);
    	signals2.add(info);
    }

	

}
