package Richard;

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
	    //Graphics2D g2e = new Graphics e;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);

	    g2.setPaint(Color.black);
	    
	   if(!finished){
	    
	   for(int i = 0; i < tracks.size(); i++){
	    	
	    	Track currentSection = tracks.get(i);
	    	
	    	if(currentSection instanceof Block){
	    		//System.out.println("here");
	    		//System.out.println("Size: " + tracks.size());
	    		((Block) currentSection).draw(g2, tracks);
	    	}
	    	if(currentSection instanceof Point){
	    		//System.out.println("here");
	    		((Point) currentSection).draw(g2, tracks);
	    	}
	    	
	    	finished = true;
	    }
	   }
	    
	    /*int x = 5;
	    int y = 7;

	    g2.draw(new Line2D.Double(x, y, 200, 200));*/
	    //g2.drawString("Line2D", x, 250);

	  }
	  
	  public void read() throws FileNotFoundException{
		//Get scanner instance
	        Scanner scanner = new Scanner(new File("./resource/Map1.txt"));
	         
	        //Set the delimiter used in file
	        scanner.useDelimiter(",");
	         
	        //Get all tokens and store them in some data structure
	        //I am just printing them
	        
	        int ID = 0;
	    	int left = 0;
	    	int right = 0;
	    	int level = 0;
	    	
	    	int top = 0;
	    	int direction = 0;
	    	
	    	sectionType = scanner.next();
	    	
	        while (scanner.hasNext())
	        {
	        	
	        	//System.out.println(sectionType);
	        	//System.out.println("here");
	        	
	        	if(sectionType.equals("Block")){
	        		makeBlock(scanner);
	        	}
	        	else if(sectionType.equals("Point")){
	        		makePoint(scanner);
	        		
	        		/*Block currentBlock;
	        		for(int i = 0; i < tracks.size(); i++){
	        			
	        			Track currentTrack = tracks.get(i);
	        					
	        			if(currentTrack instanceof Block){
	        				currentBlock = ((Block) currentTrack);
	        				
	        				System.out.println("ID: " + currentBlock.getID());
	        				
	        				System.out.println(currentBlock.getSignals().size());
	        				System.out.println("\n");
	        			}
	        		}*/
	        	}
	        	else{
	        		throw new IllegalArgumentException("Bad track type");
	        	}
	        	/*if(sectionType.equals("Block")){
	        		
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
	        		
	        		if(scanner.hasNext()){
        				sectionType = scanner.next();
        				System.out.println("Type 1: " + sectionType);
        			}
	        		System.out.println(ID);
	        		System.out.println(left);
	        		System.out.println(right);
	        		System.out.println(level);
	        		
	        		tracks.add(new Block(ID,left,right,level));
	        	}
	        	
	        	if(sectionType.equals("Point")){
	        		
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
        				System.out.println("Type 2: " + sectionType);
        			}
	                    
	        		System.out.println(ID);
	        		System.out.println(left);
	        		System.out.println(right);
	        		System.out.println(top);
	        		System.out.println(direction);
	        		System.out.println(level);
	        		
	        		tracks.add(new Point(ID,left,right,top, direction, level));	
	        	}*/
	        	
	        	
	        }
	            //System.out.print(scanner.next() + "|");
	        
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
			//System.out.println("Type 2: " + sectionType);
		}
            
		/*System.out.println(ID);
		System.out.println(left);
		System.out.println(right);
		System.out.println(top);
		System.out.println(direction);
		System.out.println(level);*/
		//System.out.println("Direction: " + direction);
		tracks.add(new Point(ID,left,right,top, direction, level));
	}
	
	public void makeBlock(Scanner scanner){
		
		int ID = 0;
    	int left = 0;
    	int right = 0;
    	int level = 0;
    	int noOfSignals = 0;
    	
    	
    	int top = 0;
    	int direction = 0;
    	
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
            /*case 5:  noOfSignals = Integer.parseInt(scanner.next());
            		 break;
            case 6:  signalDirection = scanner.next();
   		 			 break;*/
			}
		}
		
		tracks.add(new Block(ID,left,right,level));
		
		if(scanner.hasNext()){
			sectionType = scanner.next();
			//System.out.println("Type 1: " + sectionType);
		}
		
		//ArrayList<Signal> signals = new ArrayList<Signal>();
		
		//if(sectionType.equals("Signal")){
		while(sectionType.equals("Signal")){
			
			String signalName = null;
			String signalDirection = null;
	    	
	    	for(int i = 1; i < 3; i++){
				
				switch (i) {
	            case 1:  signalName = scanner.next();
	            		 //System.out.println(signalName);
	                     break;
	            case 2:  signalDirection = scanner.next();
	                     break;
				}
			}
	    	
	    	Signal currentSignal;
			Block currentBlock;
	    	
	    	//tracks.add(new Block(ID,left,right,level));
			
	    	//HERE
			Track currentTrack = tracks.get(tracks.size() - 1);
			
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
			
			currentBlock.addSig(new Signal(new String (signalName), new String(signalDirection)));
	    	
	    	
			//signals = addSignal(scanner, new ArrayList<Signal>());
			//signals = addSignal(scanner, signals);
			//tracks.add(new Block(ID,left,right,level, addSignal(scanner, new ArrayList<Signal>())));
			//tracks.get(0).getSignals().getSize();
			}
			
			if(scanner.hasNext()){
				sectionType = scanner.next();
				//System.out.println("Type 1: " + sectionType);
			}
			else{
				sectionType = "";
			}
		/*else{
			//System.out.println("Here");
			tracks.add(new Block(ID,left,right,level, signals));
		}*/
		/*System.out.println(ID);
		System.out.println(left);
		System.out.println(right);
		System.out.println(level);*/
		
		//tracks.add(new Block(ID,left,right,level, noOfSignals, signalDirection));
		/*for(int i = 0; i < signals.size(); i++){
			System.out.println(signals.get(i).getName());
		}*/
		/*Signal currentSignal;
		Block currentBlock;*/
		
		/*for(int i = 0; i < tracks.size(); i++){
			
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				
				System.out.println("ID: " + currentBlock.getID());
				
				System.out.println(currentBlock.getSignals().size());
			
				
				
				
			}
		}*/
		
		/*int sigSize = signals.size();
		
		for(int i = 0; i < signals.size(); i ++){
			
			
			
				
			tracks.add(new Block(ID,left,right,level));
			
			Track currentTrack = tracks.get(tracks.size() - 1);
			
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				
			String name = signals.get(i).getName();
			String direction2 = signals.get(i).getDirection();
			
			currentBlock.addSig(new Signal(new String (name), new String(direction2)));
			
			
			}*/
			//signals.clear();
			
		}
		
		
		
	}
	
	//private ArrayList<Signal> addSignal(Scanner scanner, ArrayList<Signal> signals){
	private ArrayList<Signal> addSignal(Scanner scanner, ArrayList<Signal> signals){
		
		String signalName = null;
    	String signalDirection = null;
    	
    	for(int i = 1; i < 3; i++){
			
			switch (i) {
            case 1:  signalName = scanner.next();
            		 //System.out.println(signalName);
                     break;
            case 2:  signalDirection = scanner.next();
                     break;
			}
		}
    	
		signals.add(new Signal(new String(signalName), new String(signalDirection)));
		
		if(scanner.hasNext()){
			sectionType = scanner.next();
			//System.out.println("Type 1: " + sectionType);
		}
		
		if(scanner.hasNext() && sectionType.equals("Signal")){
			//signals = addSignal(scanner, signals);
			signals = addSignal(scanner, new ArrayList<Signal>(signals));
			
		}
		
		
		//return signals;
		return new ArrayList<Signal>(signals);
		
	}

	public void CalculateUpRoutes(){
		
		Signal currentSignal;
		Block currentBlock;
		System.out.println("Track size: " + tracks.size());
		boolean right = true;
		
		for(int i = 0; i < tracks.size(); i++){
			
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				
				LinkedList<Signal> signals = currentBlock.getSignals();
				//int next = currentBlock.getRight();
				//System.out.println("Next: " + next);
				if(signals.size() > 0){
					
					/*for(int x = 0; x < signals.size(); x ++){
						System.out.println("ID: " + currentBlock.getID());
						System.out.println("here: " + x + ": " + signals.get(x).getName());
					}*/
					
					for(int x = 0; x < signals.size(); x++){
						
						int next = currentBlock.getRight();
						//System.out.println("Next: " + next);
						
						currentSignal = signals.get(x);
						
						if(currentSignal.getDirection().equals("up")){
						
							for(int y = 0; y < tracks.size(); y++){
							
								//int next = currentBlock.getRight();
								
								
								Block rightBlock;
								Point rightPoint;
								Track currentRightTrack = tracks.get(y);
								
								if(currentRightTrack instanceof Block){
									rightBlock = ((Block) currentRightTrack);
									LinkedList<Signal> signalsRight = rightBlock.getSignals();
									
									if(rightBlock.getID() == currentBlock.getID()){
										right = false;
									}
	
									//System.out.println("Next: " + next);
									int nextID = rightBlock.getID();
									if(nextID == next){
									
										//System.out.print(next);
										
										for(int o = 0; o < signalsRight.size(); o++){
											if(signalsRight.get(o).getDirection().equals("up")){
												
												System.out.println(currentSignal.getName() + ", " + signalsRight.get(o).getName());
											}
										}
										next = rightBlock.getRight();

									}
									//next = rightBlock.getRight();
								}
								
								if(currentRightTrack instanceof Point){
									rightPoint = ((Point) currentRightTrack);
									int nextID = rightPoint.getID();
									
									//System.out.println("Top " + rightPoint.getTop());
									
									//breakOff(rightPoint, currentSignal.getName());
									if(rightPoint.getDirection() == 1 && calculateLeftRight(rightPoint, currentBlock)){
										breakOff(rightPoint, currentSignal.getName());
										right = false;
									}
									
									if(nextID == next){
										next = rightPoint.getRight();
									}
								}
								
							}
							
						}
					}
				}
			}
		}
		
		
		
		
		
		
		
		calculateUpSignalsPoints();
		
		
		
	}
	
	public void breakOff(Point rightPoint, String name){
		
		//System.out.println(name);
		int level = rightPoint.getLevel();
		int top = rightPoint.getTop();
		int caseValue = 1;
		int sectionValue = 1;
		int y = 0;
		int x = 0;
		int i = 0;
		
		Signal currentSignal;
		Block currentBlock;
		
		/*for(i = 0; i < tracks.size(); i++){
			
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				
				/*if(sectionValue == 1){
					currentBlock = rightPoint;
					sectionValue++;
				}
				else{
					currentBlock = ((Block) currentTrack);
					
				}
				currentBlock = ((Block) currentTrack);
				
				LinkedList<Signal> signals = currentBlock.getSignals(); //first block on level 1
				
				if(signals.size() > 0){
					
						for(x = 0; x < signals.size(); x++){
						
							
							// case here?
							//int next = currentBlock.getRight();
							int next;
							if(caseValue == 1 && rightPoint.getTop() == currentBlock.getLeft()){ //this if outsode the for-loop with x?
								next = top;
								caseValue++;
							}
							else{
								next = currentBlock.getRight();
							}
							
							currentSignal = signals.get(x);
						
							if(currentSignal.getDirection().equals("up")){*/
								
								
								
								
								
								
								
								
								
								
								
								
								int next = top;
								
								for(y = 0; y < tracks.size(); y++){

									Block rightBlock; // ie currentBlock in this case
									Track currentRightTrack = tracks.get(y);
									
									if(currentRightTrack instanceof Block){
										rightBlock = ((Block) currentRightTrack);
										LinkedList<Signal> signalsRight = rightBlock.getSignals();
		
										int nextID = rightBlock.getID();
										//System.out.println("Next ID " + nextID);
										if(nextID == next){
											//System.out.println("here");
											for(int o = 0; o < signalsRight.size(); o++){
												if(signalsRight.get(o).getDirection().equals("up")){
													
													System.out.println(name + ", " + signalsRight.get(o).getName());
												}
											}
											next = rightBlock.getRight();

										}
										//next = rightBlock.getRight();
									}
									
									if(currentRightTrack instanceof Point){
										rightPoint = ((Point) currentRightTrack);
										int nextID = rightPoint.getID();
										
										//breakOff(rightPoint); MIGHT NEED LATER----------------------------------------------------------
										
										//I THINK I NEED A STOP FOR A LEFT FACING POINT - UNLESS THIS IS A NATURAL STOP
										/*if(rightPoint.getDirection() == 0){
											//System.out.println("here");
											y = tracks.size();
											x = 10000;
											i = tracks.size();
											
										}*/
										
										//REMOVIGN THE BELOW IF COULD BE ANATURAL STOP?
										if(nextID == next){
											next = rightPoint.getRight();
										}
									}
									
								}
							/*}
						}
				}
			}
		}*/
	}
	
	public boolean calculateLeftRight(Point rightPoint, Block block){
		
		int pointID = rightPoint.getID();
		int blockID = block.getID();
		//int next = block.getRight();
		 
		//int next 
		/*if(tracks.get() instanceof Block){
			Block currentBlock = ((Block) currentTrack);
		}*/
			
		int next = 0;
		//int next = tracks.get(0).getId;
		boolean left = true;
		int id;
				
		Signal currentSignal;
		Block currentBlock;
		Point currentPoint;
		
		int levels = 0;
		
		int i = 0;
		boolean go = true;
		
		int allow = 1;
		
		while(go){
			
			for(i = 0; i < tracks.size(); i++){
				
				Track currentTrack = tracks.get(i);
				
				if(currentTrack instanceof Block){
					currentBlock = ((Block) currentTrack);
					
					if(currentBlock.getID() == next || allow == 1){
						allow++;
						if(currentBlock.getID() == blockID){
							//blockID = currentBlock.getID();
							
							go = false;
							i = tracks.size();
						}
						else{
							//blockID = currentBlock.getRight();
							next = currentBlock.getRight();
						}
					}
					
					
					
					
					//next = currentBlock.getRight();
					//next = block.getRight();

				}
				if(currentTrack instanceof Point){
					currentPoint = ((Point) currentTrack);
					
					if(currentPoint.getID() == pointID){
						go = false;
						i = tracks.size();
						left = false;
					}
				}
			}
		}
			
		
		
		/*for(i = 0; i < tracks.size(); i++){
			
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				
				int currentBlockID = currentBlock.getID();
				
				if(currentBlockID == next){
					if(currentBlockID == blockID){
						i = tracks.size();
					}
				}
				
				
				
				
			}
			if(currentTrack instanceof Point){
				currentPoint = ((Point) currentTrack);
				if(currentPoint.getID() == pointID){
					left = false;
				}
				
			}
		}*/
		
		return left;
	}
	
	public void calculateUpSignalsPoints(){
		Signal currentSignal;
		Block currentBlock;
		Point currentPoint;
		
		int levels = 0;
		
		for(int i = 0; i < tracks.size(); i++){
			
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
			}
			if(currentTrack instanceof Point){
				currentPoint = ((Point) currentTrack);
				
				if(currentPoint.getTop() > levels){
					levels = currentPoint.getTop();
				}	
			}
		}
	}
	
	public Block getBlockWithID(int id){
		Block currentBlock = null;
		Block foundBlock = null;
		
		for(int i = 0; i < tracks.size(); i++){
			
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				if(currentBlock.getID() == id){
					i = tracks.size();
					foundBlock = currentBlock;
				}
			}
		}
		return foundBlock;
	}
	
	public void CalculatePoints(String source, String destination){
		
		for(int i = 0; i < tracks.size(); i++){
			
			Block currentBlock = null;
			Point currentPoint = null;
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				
				LinkedList<Signal> signals = currentBlock.getSignals();
				
				for(int x = 0; x < signals.size(); x++){
					if(signals.get(x).getName().equals(destination)){
						
						
					}
					if(signals.get(x).getName().equals(source)){
						findUpwardsPath(i + 1, destination);
					}
				}
			}
			if(currentTrack instanceof Point){
				currentPoint = ((Point) currentTrack);
			}
		}
	}
	
	public void findUpwardsPath(int sourceBlock, String destination){
		
		System.out.println("\nPath: ");
		for(int i = sourceBlock; i < tracks.size(); i++){
			
			Block currentBlock = null;
			Point currentPoint = null;
			Track currentTrack = tracks.get(i);
					
			if(currentTrack instanceof Block){
				currentBlock = ((Block) currentTrack);
				
				LinkedList<Signal> signals = currentBlock.getSignals();
				
				System.out.print("b" + currentBlock.getID() + " ");
				
				for(int x = 0; x < signals.size(); x++){
					
					if(signals.get(x).getName().equals(destination)){
						i = tracks.size();
					}
				}
				
			}
			
			if(currentTrack instanceof Point){
				currentPoint = ((Point) currentTrack);

				System.out.print("p" + currentPoint.getID() + " ");
			}
			
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
