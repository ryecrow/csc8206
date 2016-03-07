import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class Track extends JApplet{
	
	private static List<Track> tracks = new ArrayList<Track>();
	private String sectionType;
	private boolean finished = false;
	double count = 1.0;
	String value = "";
	List<String> array = new ArrayList<String>();
	
	public void init() {
	    setBackground(Color.white);
	    setForeground(Color.white);
	  }

	  public void paint(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);

	    g2.setPaint(Color.black);
	    
	   if(!finished){
	    
	   for(int i = 0; i < tracks.size(); i++){
	    	
	    	Track currentSection = tracks.get(i);
	    	
	    	if(currentSection instanceof Block){
	    		((Block) currentSection).draw(g2, tracks);
	    	}
	    	if(currentSection instanceof Point){
	    		((Point) currentSection).draw(g2, tracks);
	    	}
	    	
	    	finished = true;
	    }
	   }
	  }
	  
	  public void read() throws FileNotFoundException{
		  //Get scanner instance
	        Scanner scanner = new Scanner(new File("Map1.txt"));
	         
	        //Set the delimiter used in file
	        scanner.useDelimiter(",");	
	    	sectionType = scanner.next();
	    	
	        while (scanner.hasNext())
	        {	
	        	if(sectionType.equals("Block")){
	        		makeBlock(scanner);
	        	}
	        	else if(sectionType.equals("Point")){
	        		makePoint(scanner);
	        	}
	        	else{
	        		throw new IllegalArgumentException("Bad track type");
	        	}
	        }
	  }
	  
	public void makePoint(Scanner scanner){
		
		int ID = 0;
    	int left = 0;
    	int right = 0;
    	int level = 0;
    	
    	int top = 0;
    	int direction = 0;
    	
		for(int i = 1; i < 7; i++){
			
			switch (i) {
            case 1:  ID = Integer.parseInt(scanner.next());
                     break;
            case 2:  left = Integer.parseInt(scanner.next());
                     break;
            case 3:  right = Integer.parseInt(scanner.next());
                     break;
            case 4:  top = Integer.parseInt(scanner.next());
            		 break;
            case 5:  direction = Integer.parseInt(scanner.next());	          
            		 break;
            case 6:  level = Integer.parseInt(scanner.next());
            		 break;
				}
		}
		
		if(scanner.hasNext()){
			sectionType = scanner.next();
		}
		tracks.add(new Point(ID,left,right,top, direction, level));
	}
	
	public void makeBlock(Scanner scanner){
		
		int ID = 0;
    	int left = 0;
    	int right = 0;
    	int level = 0;
    	
		for(int i = 1; i < 5; i++){
			
			switch (i) {
            case 1:  ID = Integer.parseInt(scanner.next());
                     break;
            case 2:  left = Integer.parseInt(scanner.next());
                     break;
            case 3:  right = Integer.parseInt(scanner.next());
                     break;
            case 4:  level = Integer.parseInt(scanner.next());
                     break;
			}
		}
		
		tracks.add(new Block(ID,left,right,level));
		
		if(scanner.hasNext()){
			sectionType = scanner.next();
		}
		
		while(sectionType.equals("Signal")){
			
			String signalName = null;
			String signalDirection = null;
	    	
	    	for(int i = 1; i < 3; i++){
				
				switch (i) {
	            case 1:  signalName = scanner.next();
	                     break;
	            case 2:  signalDirection = scanner.next();
	                     break;
				}
			}
	    	
	    	Signal currentSignal;
			Block currentBlock;
			Track currentTrack = tracks.get(tracks.size() - 1);
			
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				currentBlock.addSig(new Signal(new String (signalName), new String(signalDirection)));
			}
			
			if(scanner.hasNext()){
				sectionType = scanner.next();
			}
			else{
				sectionType = "";
			}
		}
	}
	
	private ArrayList<Signal> addSignal(Scanner scanner, ArrayList<Signal> signals){
		
		String signalName = null;
    	String signalDirection = null;
    	
    	for(int i = 1; i < 3; i++){
			
			switch (i) {
            case 1:  signalName = scanner.next();
                     break;
            case 2:  signalDirection = scanner.next();
                     break;
			}
		}
    	
		signals.add(new Signal(new String(signalName), new String(signalDirection)));
		
		if(scanner.hasNext()){
			sectionType = scanner.next();
		}
		
		if(scanner.hasNext() && sectionType.equals("Signal")){
			signals = addSignal(scanner, new ArrayList<Signal>(signals));
			
		}

		return new ArrayList<Signal>(signals);		
	}

	//----
	public LinkedList downPath(String source, String destination){
		
		//System.out.print("All available routes for path " + source + " to " + destination + ":\n");
		
		Block sourceBlock = null;
		Block destinationBlock = null;
		boolean notFound = true;
		
		//this for loop finds the block that contains the source signal, which is input into this method via string
		for(int i = 0; i < tracks.size(); i++){
			
			Block currentBlock = null;
			Point currentPoint = null;
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				LinkedList<Signal> currentBlockSignals = currentBlock.getSignals();
				
				for(int x = 0; x < currentBlockSignals.size(); x++){
					if(currentBlockSignals.get(x).getName().equals(source)){
						sourceBlock = currentBlock;
					}
				}
				
			}
		}
		
		for(int i = 0; i < tracks.size(); i++){
			
			Block currentBlock = null;
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				LinkedList<Signal> currentBlockSignals = currentBlock.getSignals();
				
				for(int x = 0; x < currentBlockSignals.size(); x++){
					if(currentBlockSignals.get(x).getName().equals(destination)){
						destinationBlock = currentBlock;
						
					}
				}
				
			}
		}
		double totalLeftPoints = 0;
		
		Track tempTrack;
		Point tempPoint;
		for(int i = 0; i < tracks.size(); i++){
			tempTrack = tracks.get(i);
			
			if(tempTrack instanceof Point){
				tempPoint = ((Point) tempTrack);
				
				if(tempPoint.getDirection() == 0){
					totalLeftPoints++;
				}
			}

		}
		LinkedList<String> signalsforEachRoute = new LinkedList<String>();
		signalsforEachRoute = calculateAllPossibleRoutesDown(totalLeftPoints, sourceBlock, destinationBlock);
		return signalsforEachRoute;
	}
	
	public LinkedList calculateAllPossibleRoutesDown(double totalLeftPoints, Block sourceBlock, Block destinationBlock){
		
		Track tempTrack;
		Block currentBlock;
		Point currentPoint;
		boolean notFound;
		double numberOfAllowedPoints;
		List<Integer> route = new ArrayList<Integer>();
		List<String> completeRoute = new ArrayList<String>();
		
		LinkedList<String> signalsforEachRoute = new LinkedList<String>();
		
		numberOfAllowedPoints = totalLeftPoints;
		
		for(double x = 0; x < totalLeftPoints; x++){
			
		}
		
		calculateRouteVariations(totalLeftPoints);
		
		int iterator;
		String currentSet;
		
		for(double i = array.size()- 1; i >= 0; i--){
			
			currentSet = array.get((int) i);
			iterator = 0;
			
			tempTrack = findNextBlockDown(sourceBlock.getID());
			//System.out.println("ID: " + sourceBlock.getID());
			notFound = true;
			route.clear();
			
			while(notFound){
				
				if(tempTrack instanceof Point){
					currentPoint = ((Point) tempTrack);
					
					//If point is Right facing, just continue going right
					if(currentPoint.getDirection() == 1){
						tempTrack = findNextBlockDown(currentPoint.getLeft());
						if(tempTrack == null){
							notFound = false;
						}
						else{
							route.add(currentPoint.getID());
						}
						
					}
					//If point is left facing, make a choice of whether to  go right or top
					if(currentPoint.getDirection() == 0){
						
						if(currentSet.charAt(iterator) == 49){
							iterator++;
							
							numberOfAllowedPoints = numberOfAllowedPoints - 1;
							tempTrack = findNextBlockDown(currentPoint.getTop());
							if(tempTrack == null){
								notFound = false;
							}
							else{
								route.add(currentPoint.getID());
							}
						}
						else{
							iterator++;
							tempTrack = findNextBlockDown(currentPoint.getLeft());
							if(tempTrack == null){
								notFound = false;
							}
							else{
								route.add(currentPoint.getID());
							}
							
						}
					}
							
				}
				else if(tempTrack instanceof Block){
					currentBlock = ((Block) tempTrack);
					route.add(currentBlock.getID());
					
					//System.out.println("Left ID: " + currentBloc());
					
					//assumption - a block has two signals maximum
					//If we have arrived at the destination block
					if(currentBlock.getID() == destinationBlock.getID()){
						Map<String, Object> map = new HashMap<String, Object>();
						notFound = false;
						
						boolean contains = false;
						String fullRoute = "";
						
						for(int x = 0; x < route.size(); x++){
							
							fullRoute = fullRoute + String.valueOf(route.get(x));
							
						}
						if(completeRoute.size() > 0){
							for(int v = 0; v < completeRoute.size(); v++){
								if(completeRoute.get(v).equals(fullRoute)){
									contains = true;
								}
							}
						}
						if(contains == false){
							completeRoute.add(fullRoute);
							
							String currentSequence  = "";
							for(int v = 0; v < route.size(); v++){
								
								Track trackInstance = findNextBlockDown(route.get(v));
								Block blockInstance;
								
								if (trackInstance instanceof Block){
									blockInstance = ((Block) trackInstance);
									
									LinkedList<Signal> signals = blockInstance.getSignals();
									
									if(signals.size() > 0){
										for(int u = 0; u < signals.size(); u++){
											if(signals.get(u).getDirection().equals("down")){
												currentSequence = currentSequence + signals.get(u).getName() + ";";
											}
											
										}
										
									}
								}
								
							}
							signalsforEachRoute.add(currentSequence);
						}	
					}
					tempTrack = findNextBlockDown(currentBlock.getLeft());
					if(tempTrack == null){
						notFound = false;
					}
				}
			}
			
			
		}
		completeRoute.clear();
		array.clear();
		
		/*for(int z = 0; z < signalsforEachRoute.size(); z++){
			System.out.println(signalsforEachRoute.get(z));
		}*/
		
		return signalsforEachRoute;
	}
	
	public Track findNextBlockDown(int blockID){ //input the ID for the block on the right
		
		Block currentBlock = null;
		Point currentPoint = null;
		int i = 0;
		Track currentTrack = null;
		boolean found = false;
		
		for(i = tracks.size() - 1; i >= 0; i--){
			currentTrack = tracks.get(i);
			found = false;
			
			
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				
				if(currentBlock.getID() == blockID){
					i = -1;
					found = true;
				}
			}
			if(currentTrack instanceof Point){
				currentPoint = ((Point) currentTrack);
				
				if(currentPoint.getID() == blockID){
					i = -1;
					found = true;
				}
			}
		}
		if(found){
			return currentTrack;
		}
		else{
			return null;
		}
		
	}

	
	public static void main(String args[]) throws FileNotFoundException {
		  
		  	Track track = new Track();
		  	track.read();
		  	
		    JFrame f = new JFrame("Train Track Demo");
		    f.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        System.exit(0);
		      }
		    });
		    JApplet applet = new Track();
		    f.getContentPane().add("Center", applet);
		    applet.init();
		    f.pack();
		    f.setSize(new Dimension(1500, 1000));
		    f.setVisible(true);
		    
		    //track.CalculateUpRoutes();
		    /*track.CalculatePoints("s6","s7");
		    track.CalculatePoints("s1","s6");
		    track.CalculatePoints("s1","s7");
		    track.CalculatePoints("s4","s7");
		    track.CalculatePoints("s1","s4");*/
		    
		    //track.CalculatePoints("s2","s3");
		    
		    //System.out.println("PATH");
		    //System.out.print("s1, s13: \n");
		    LinkedList<String> signalsforEachRoute = new LinkedList<String>();
		    signalsforEachRoute = track.upPath("s1", "s7");
		    
		    for(int z = 0; z < signalsforEachRoute.size(); z++){
				System.out.println(signalsforEachRoute.get(z));
			}
		    
		    LinkedList<String> signalsforEachRoute2 = new LinkedList<String>();
		    signalsforEachRoute2 = track.downPath("s8", "s2");
		    
		    
		    System.out.println();
		    for(int z = 0; z < signalsforEachRoute.size(); z++){
				System.out.println(signalsforEachRoute2.get(z));
			}
		    
		    //System.out.print("s1, s7: \n");
		    /*track.upPath("s1","s8");
		    
		    //System.out.print("s6, s7: \n");
		    track.upPath("s6", "s7");
		    
		    //System.out.print("s4, s7: \n");
		    track.upPath("s4", "s7");
		    
		    //System.out.print("s1, s4: \n");
		    track.upPath("s1", "s4");
		    
		    //System.out.print("s7, s14: \n");
		    track.upPath("s8", "s11");
		    
		    //System.out.print("s1, s10: \n");
		    track.upPath("s1", "s10");*/
	 }
	
	public LinkedList upPath(String source, String destination){
		
		//System.out.print("All available routes for path " + source + " to " + destination + ":\n");
		
		Block sourceBlock = null;
		Block destinationBlock = null;
		boolean notFound = true;
		
		//this for loop finds the block that contains the source signal, which is input into this method via string
		for(int i = 0; i < tracks.size(); i++){
			
			Block currentBlock = null;
			Point currentPoint = null;
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				LinkedList<Signal> currentBlockSignals = currentBlock.getSignals();
				
				for(int x = 0; x < currentBlockSignals.size(); x++){
					if(currentBlockSignals.get(x).getName().equals(source)){
						sourceBlock = currentBlock;
						
					}
				}
				
			}
		}
		
		for(int i = 0; i < tracks.size(); i++){
			
			Block currentBlock = null;
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				LinkedList<Signal> currentBlockSignals = currentBlock.getSignals();
				
				for(int x = 0; x < currentBlockSignals.size(); x++){
					if(currentBlockSignals.get(x).getName().equals(destination)){
						destinationBlock = currentBlock;
						
					}
				}
				
			}
		}
		double totalRightPoints = 0;
		
		Track tempTrack;
		Point tempPoint;
		for(int i = 0; i < tracks.size(); i++){
			tempTrack = tracks.get(i);
			
			if(tempTrack instanceof Point){
				tempPoint = ((Point) tempTrack);
				
				if(tempPoint.getDirection() == 1){
					totalRightPoints++;
				}
			}

		}
		LinkedList<String> signalsforEachRoute = new LinkedList<String>();
		signalsforEachRoute = calculateAllPossibleRoutes(totalRightPoints, sourceBlock, destinationBlock);
		return signalsforEachRoute;
	}
	
	public void calculateRouteVariations(double totalRightPoints){
		
		int i;
		for(i = 0; i < 2; i++){
			
			if(count == totalRightPoints){
				value = value + Integer.toString(i);
				array.add(value);
				value = value.substring(0, value.length()-1);
			}
			else{
				value = value + Integer.toString(i);
				count++;
				calculateRouteVariations(totalRightPoints);
			}
			
		}
		if(value.length() > 0){
			value = value.substring(0, value.length()-1);
		}
		
		count--;
	}
	
	public LinkedList calculateAllPossibleRoutes(double totalRightPoints, Block sourceBlock, Block destinationBlock){
		
		Track tempTrack;
		Block currentBlock;
		Point currentPoint;
		boolean notFound;
		double numberOfAllowedPoints;
		List<Integer> route = new ArrayList<Integer>();
		List<String> completeRoute = new ArrayList<String>();
		
		LinkedList<String> signalsforEachRoute = new LinkedList<String>();
		
		numberOfAllowedPoints = totalRightPoints;
		
		for(double x = 0; x < totalRightPoints; x++){
			
		}
		
		calculateRouteVariations(totalRightPoints);
		
		int iterator;
		String currentSet;
		
		for(double i = 0; i < array.size(); i++){
			
			currentSet = array.get((int) i);
			iterator = 0;
			
			tempTrack = findNextBlock(sourceBlock.getID());
			notFound = true;
			route.clear();
			
			while(notFound){
				
				if(tempTrack instanceof Point){
					currentPoint = ((Point) tempTrack);
					
					//If point is left facing, just continue going right
					if(currentPoint.getDirection() == 0){
						tempTrack = findNextBlock(currentPoint.getRight());
						if(tempTrack == null){
							notFound = false;
						}
						else{
							route.add(currentPoint.getID());
						}
						
					}
					//If point is right facing, make a choice of whether to  go right or top
					if(currentPoint.getDirection() == 1){
						
						if(currentSet.charAt(iterator) == 49){
							iterator++;
							
							numberOfAllowedPoints = numberOfAllowedPoints - 1;
							tempTrack = findNextBlock(currentPoint.getTop());
							if(tempTrack == null){
								notFound = false;
							}
							else{
								route.add(currentPoint.getID());
							}
						}
						else{
							iterator++;
							tempTrack = findNextBlock(currentPoint.getRight());
							if(tempTrack == null){
								notFound = false;
							}
							else{
								route.add(currentPoint.getID());
							}
							
						}
					}
							
				}
				else if(tempTrack instanceof Block){
					currentBlock = ((Block) tempTrack);
					route.add(currentBlock.getID());
					
					//assumption - a block has two signals maximum
					//If we have arrived at the destination block
					if(currentBlock.getID() == destinationBlock.getID()){
						Map<String, Object> map = new HashMap<String, Object>();
						//DO SOMTHING - ie print
						notFound = false;
						
						boolean contains = false;
						String fullRoute = "";
						
						for(int x = 0; x < route.size(); x++){
							
							fullRoute = fullRoute + String.valueOf(route.get(x));
							
						}
						if(completeRoute.size() > 0){
							for(int v = 0; v < completeRoute.size(); v++){
								if(completeRoute.get(v).equals(fullRoute)){
									contains = true;
								}
							}
						}
						if(contains == false){
							completeRoute.add(fullRoute);
							
							String currentSequence  = "";
							for(int v = 0; v < route.size(); v++){
								
								Track trackInstance = findNextBlock(route.get(v));
								Block blockInstance;
								
								if (trackInstance instanceof Block){
									blockInstance = ((Block) trackInstance);
									
									LinkedList<Signal> signals = blockInstance.getSignals();
									
									if(signals.size() > 0){
										for(int u = 0; u < signals.size(); u++){
											if(signals.get(u).getDirection().equals("up")){
												currentSequence = currentSequence + signals.get(u).getName() + ";";
											}
											
										}
										
									}
								}
								
							}
							signalsforEachRoute.add(currentSequence);
						}	
					}
					tempTrack = findNextBlock(currentBlock.getRight());
					if(tempTrack == null){
						notFound = false;
					}
				}
			}
			
			
		}
		completeRoute.clear();
		array.clear();
		
		/*for(int z = 0; z < signalsforEachRoute.size(); z++){
			System.out.println(signalsforEachRoute.get(z));
		}*/
		
		return signalsforEachRoute;
	}
	
	public Track findNextBlock(int blockID){ //input the ID for the block on the right
		
		Block currentBlock = null;
		Point currentPoint = null;
		int i = 0;
		Track currentTrack = null;
		boolean found = false;
		
		for(i = 0; i < tracks.size(); i++){
			currentTrack = tracks.get(i);
			found = false;
			
			
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				
				if(currentBlock.getID() == blockID){
					i = tracks.size();
					found = true;
				}
			}
			if(currentTrack instanceof Point){
				currentPoint = ((Point) currentTrack);
				
				if(currentPoint.getID() == blockID){
					i = tracks.size();
					found = true;
				}
			}
		}
		if(found){
			return currentTrack;
		}
		else{
			return null;
		}
		
	}
}
