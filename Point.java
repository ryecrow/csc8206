import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JFrame;


public class Point extends Track{
	
	private int ID;
	private int left;
	private int right;
	private int top;
	private int direction;
	private int level;
	 // 0 for facing left, and 1 for facing right
	private int x = 100;
	private int y = 500;
	
	public Point(int ID, int left, int right, int top, int direction, int level){
		
		
		
		this.ID = ID;
		this.left = left;
		this.right = right;
		this.top = top;
		this.direction = direction;
		this.level = level;
	}
	
	public void draw(Graphics2D g2d, List<Track> tracks) {
		
		//g2d.setPaint(Color.GREEN);
		
		if(ID == 1){
			g2d.drawLine(x, y, x + 100, y);
			
			g2d.drawLine(x, y - 5, x, y + 5);
	        g2d.drawLine(x + 100 , y - 5, x + 100, y + 5);
			
			g2d.drawLine(x + 30, y, x + 80, y - 80);
	        g2d.drawLine(x + 80, y - 80, x + 100, y - 80);
	        
	        g2d.drawLine(x + 100, y - 85, x + 100, y - 75);
	        g2d.drawLine(x + 100, y - 5, x + 100, y + 5);
    	}
    	if (direction == 1 && ID != 1){
    		
    		for(int i = 0; i < tracks.size(); i++){
    			
    			Track currentSection = tracks.get(i);
    			Block currentBlock;
    			Point currentPoint;
    			
    			if(currentSection instanceof Block){
    				currentBlock = ((Block) currentSection);
    				
    				if(currentBlock.getID() == left){
    					
    					x = currentBlock.getXPos() + 100;
    					
    					g2d.drawLine(x, y, x + 100, y);
    			        g2d.drawLine(x + 30, y, x + 80, y - 80);
    			        g2d.drawLine(x + 80, y - 80, x + 100, y - 80);
    			        
    			        g2d.drawLine(x + 100, y - 85, x + 100, y - 75);
    			        g2d.drawLine(x + 100, y - 5, x + 100, y + 5);
    			        
    			        g2d.drawString("p" + Integer.toString(ID), x + 40, y + 30);
        			}
    				
    	    	}
    	    	if(currentSection instanceof Point){
    	    		currentPoint = ((Point) currentSection);
    	    		
    	    		
    	    		
    	    		if(currentPoint.getID() == left){
    	    			
    	    			x = currentPoint.getXPos() + 100;
    	    			
    	    			g2d.drawLine(x, y, x + 100, y);
    	    	        g2d.drawLine(x + 30, y, x + 80, y - 80);
    	    	        g2d.drawLine(x + 80, y - 80, x + 100, y - 80);
    	    	        
    	    	        g2d.drawLine(x + 100, y - 85, x + 100, y - 75);
    	    	        g2d.drawLine(x + 100, y - 5, x + 100, y + 5);
    	    	        
    	    	        g2d.drawString("p" + Integer.toString(ID), x + 40, y + 30);
        			}
    	    	}
    			//Block currentBlock = ((Block) currentSection);
    			
    			
    		}	
    		
    		
    	}
		
    		if (direction == 0 && ID != 1){
    		
    		for(int i = 0; i < tracks.size(); i++){
    			
    			Track currentSection = tracks.get(i);
    			Block currentBlock;
    			Point currentPoint;
    			
    			if(currentSection instanceof Block){
    				currentBlock = ((Block) currentSection);
    				
    				if(currentBlock.getID() == left){
    					
    					x = currentBlock.getXPos() + 100;
    					
    					g2d.drawLine(x, y, x + 100, y);
    	    	        g2d.drawLine(x + 70, y, x + 20, y - 80);
    	    	        g2d.drawLine(x + 20, y - 80, x, y - 80);
    	    	        
    	    	        g2d.drawLine(x, y - 85, x, y - 75);
    	    	        g2d.drawLine(x + 100, y - 5, x + 100, y + 5);
    	    	        
    	    	        g2d.drawString("p" + Integer.toString(ID), x + 40, y + 30);
        			}
    				
    	    	}
    	    	if(currentSection instanceof Point){
    	    		currentPoint = ((Point) currentSection);
    	    		
    	    		
    	    		
    	    		if(currentPoint.getID() == left){
    	    			
    	    			x = currentPoint.getXPos() + 100;
    	    			
    	    			g2d.drawLine(x, y, x + 100, y);
    	    	        g2d.drawLine(x + 70, y, x + 20, y - 80);
    	    	        g2d.drawLine(x + 20, y - 80, x, y - 80);
    	    	        
    	    	        g2d.drawLine(x, y - 85, x, y - 75);
    	    	        g2d.drawLine(x + 100, y - 5, x + 100, y + 5);
    	    	        
    	    	        g2d.drawString("p" + Integer.toString(ID), x + 40, y + 30);
        			}
    	    	}
    			//Block currentBlock = ((Block) currentSection);
    			
    			
    		}
    		}
		
		
        
        
        //Graphics2D g2d = (Graphics2D) g;
 
        //g2d.drawLine(120, 50, 360, 50);
 
        //g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));
 
        //g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));
        //for(int i = 1; i < 5; i++){
        
	        /*g2d.drawLine(x, y, x + 100, y);
	        g2d.drawLine(x, y - 5, x, y + 5);
	        g2d.drawLine(x + 100 , y - 5, x + 100, y + 5);*/
	        
        	/*g2d.drawLine(x * i, y, x * i + 100, y);
	        g2d.drawLine(x * i, y - 5, x * i, y + 5);
	        g2d.drawLine(x * i + 100 , y - 5, x * i + 100, y + 5);*/
        //}
 
    }
	
	public int getID(){
    	return ID;
    }
	
	public int getXPos(){
    	return x;
    }
	
	public int getRight(){
    	return right;
    }

	public int getTop() {
		return top;
		
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getDirection(){
		return direction;
	}

}
