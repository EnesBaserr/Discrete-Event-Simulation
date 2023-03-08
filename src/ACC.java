import java.util.*;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import java.lang.reflect.Array;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
public class ACC {
 String[] Hashtable;
 ArrayList<Flight> flightArrayList;
 int time ;
 ArrayList<String> hashArrayList;
 Flight[] queueflFlights;
 int controlTime=0;
 FileWriter fileWriter;
 
public Flight[] getQueueflFlights() {
	return queueflFlights;
}
public void setQueueflFlights(Flight[] queueflFlights) {
	this.queueflFlights = queueflFlights;
}
public ACC( ArrayList<Flight> flightArrayList, int time,String[] hashtable,ArrayList<String> hashArrayList,Flight[] queue,FileWriter fileWriter)throws IOException {
	super();
	Hashtable = hashtable;
	this.flightArrayList = flightArrayList;
	this.time = time;
	this.hashArrayList=hashArrayList;
	this.queueflFlights=queue;
	this.fileWriter=fileWriter;
}
public String[] getHashtable() {
	return Hashtable;
}

public void setHashtable(String[] hashtable)throws IOException {
	hashTable hashTable = new hashTable(0, flightArrayList, Hashtable);
	
	for(int k = 0;k<hashArrayList.size();k++) {
		hashTable.insert(hashArrayList.get(k));
		
	}
	Hashtable = hashtable;
	hashTable.printHashTable();
	
}
public ArrayList<Flight> getFlightArrayList() {
	return flightArrayList;
}
public void setFlightArrayList(ArrayList<Flight> flightArrayList) {
	this.flightArrayList = flightArrayList;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
	
}
public ArrayList<String> getHashArrayList() {
	return hashArrayList;
}
public void setHashArrayList(ArrayList<String> hashArrayList) {
	this.hashArrayList = hashArrayList;
}
public ArrayList<Flight> getArrayList(ArrayList<Flight> flights,int admissionTime){
	ArrayList<Flight> finArrayList= new ArrayList<>();
	for(int i = 0;i<flights.size();i++) {
		if(flights.get(i).getAdmissionTime()==admissionTime) {
			finArrayList.add(flights.get(i));
		}
	}
	return finArrayList;
}
public void main(ArrayList<Flight> flightArrayList)throws IOException {
	ArrayList<Integer> timeArrayList=new ArrayList<>();
		
	boolean flag=true;
	PriorityQueue<Flight> accPriorityQueue = new PriorityQueue<>();
	PriorityQueue<Flight> accQueue = new PriorityQueue<>();	
	ArrayList<ATC> forAtcArrayList = new ArrayList<>();
	ArrayList<String> airArrayList = new ArrayList<>(); 
	for(int i = 0;i<flightArrayList.size();i++) {
		accPriorityQueue.add(flightArrayList.get(i));
	}
	while(flag) {
		
		if(accPriorityQueue.isEmpty()) {
			
			break;
		}
		Flight curFlight=(accPriorityQueue.poll());
		
		if(curFlight.accCounter>=21) {
			continue;
		}
		
		
		if(curFlight.operationTimes[curFlight.accCounter]<=30&&(curFlight.accCounter==0||curFlight.accCounter==2||curFlight.accCounter==10||curFlight.accCounter==12||curFlight.accCounter==20)) {
		curFlight.setAccProcess("a");
			accQueue.add(curFlight);
			Flight newFlight= accQueue.poll();
			 int init =newFlight.admissionTime;
			 
			 init = Math.max(controlTime, init);
			 controlTime=Math.max(controlTime, init);
			 
			 
			 
			
			newFlight.admissionTime=newFlight.operationTimes[newFlight.accCounter]+init;
			controlTime += newFlight.operationTimes[newFlight.accCounter];
			newFlight.accCounter+=1;
			
			if(newFlight.accCounter<21) {
			
			accPriorityQueue.add(curFlight);
			}
			if(newFlight.accCounter>=21) {
				continue;
			}
			continue;
			
		
			
			
		}
		if(curFlight.getOperationTimes()[curFlight.accCounter]>30&&(curFlight.accCounter==0||curFlight.accCounter==2||curFlight.accCounter==10||curFlight.accCounter==12||curFlight.accCounter==20)) {
			curFlight.setAccProcess("b");
			accQueue.add(curFlight);//boolean
			
			Flight newFlight= accQueue.poll();
			int init = newFlight.admissionTime;
			init = Math.max(controlTime, init);
			 controlTime=Math.max(controlTime, init);
			 newFlight.admissionTime=init+30;
			controlTime+=30;
			newFlight.operationTimes[newFlight.accCounter]-=30;
			accPriorityQueue.add(curFlight);
			
			continue;
		}
		if(curFlight.accCounter==1||curFlight.accCounter==11) {
			
			curFlight.admissionTime+=curFlight.operationTimes[curFlight.accCounter];
			curFlight.accCounter+=1;
			accPriorityQueue.add(curFlight);
			
			continue;
			
		}
		if(curFlight.accCounter==3||curFlight.accCounter==5||curFlight.accCounter==7||curFlight.accCounter==9) {
			
			
				
			
				
				if(airArrayList.contains(curFlight.departureAirport)) {
					
					
					int i =airArrayList.indexOf(curFlight.departureAirport);
				
					forAtcArrayList.get(i).flight=curFlight;
					forAtcArrayList.get(i).Main(curFlight, forAtcArrayList.get(i).flights);
					
					accPriorityQueue.add(curFlight);
					
					
					
				}
				else {
				
					
					Deque<Flight> flightDeque=new ArrayDeque<>();
					ATC atc= new ATC(curFlight,flightDeque,0);
					airArrayList.add(curFlight.departureAirport);
					forAtcArrayList.add(atc);
					atc.Main(curFlight, flightDeque);
				
					accPriorityQueue.add(curFlight);
					
					
				}
				
				continue;
			
		}
	
		if(curFlight.accCounter==13||curFlight.accCounter==15||curFlight.accCounter==17||curFlight.accCounter==19) {
			
			
		
			
				
				if(airArrayList.contains(curFlight.arrivalAirport)) {	
					
					int i = airArrayList.indexOf(curFlight.arrivalAirport);
					forAtcArrayList.get(i).flight=curFlight;
					forAtcArrayList.get(i).Main(curFlight, forAtcArrayList.get(i).flights);
					
					accPriorityQueue.add(curFlight);
					
					
					
					
				}
				else {
					Deque<Flight> flightDeque=new ArrayDeque<>();
					ATC atc= new ATC(curFlight,flightDeque,0);
					airArrayList.add(curFlight.arrivalAirport);
					forAtcArrayList.add(atc);
					atc.Main(curFlight, flightDeque);
					
					accPriorityQueue.add(curFlight);
					
					
				}
				
				
				continue;
			
			
			
		}
		if(curFlight.accCounter==4||curFlight.accCounter==6||curFlight.accCounter==8||curFlight.accCounter==14||curFlight.accCounter==16||curFlight.accCounter==18) {
			curFlight.admissionTime+=curFlight.operationTimes[curFlight.accCounter];
			curFlight.accCounter+=1;
			accPriorityQueue.add(curFlight);
			continue;
			
		}
	
		
	}
	for(int i = 0;i<flightArrayList.size();i++) {
		
		timeArrayList.add(flightArrayList.get(i).getAdmissionTime());
	}
	
	
	
		
	if(!timeArrayList.isEmpty()) {
	fileWriter.write(Collections.max(timeArrayList)+" ");
	}
	else {
		fileWriter.write(0+" ");
	}
	
	
	
	
	
}
class Queue {   
    
    private static int front, rear, capacity;   
    private static Flight queue[];   
     
    Queue(int size) {   
        front = rear = 0;   
        capacity = size;   
        queue = new Flight[capacity];   
    } 
   boolean  queueEmpty() {
    	if(rear==front) {
    		return true;
    	}
    	else {
			return false;
		}
    }
     
    // insert an element into the queue  
     void queueEnqueue(Flight flight)  {   
        // check if the queue is full  
        if (capacity == rear) {   
            System.out.printf("\nQueue is full\n");   
            return;   
        }   
     
        // insert element at the rear   
        else {   
        	
            queue[rear] = flight;   
            rear++;   
        }   
        return;   
    }   
     
    //remove an element from the queue  
     void queueDequeue()  {   
        // check if queue is empty   
        if (front == rear) {   
            System.out.printf("\nQueue is empty\n");   
            return;   
        }   
     
        // shift elements to the right by one place uptil rear   
        else {   
            for (int i = 0; i < rear - 1; i++) {   
                queue[i] = queue[i + 1];   
            }   
     
         
      // set queue[rear] to 0  
            if (rear < capacity)   
                queue[rear] = null;   
     
            // decrement rear   
            rear--;   
        }   
        return;   
    }   
     
    // print queue elements   
     void queueDisplay()   
    {   
        int i;   
        if (front == rear) {   
            System.out.printf("Queue is Empty\n");   
            return;   
        }   
     
        // traverse front to rear and print elements   
        for (i = front; i < rear; i++) {   
            System.out.printf(" %d , ", queue[i]);   
        }   
        return;   
    }   
     
    // print front of queue   
    Flight queueFront()   
    {   
        if (front == rear) {   
            System.out.printf("Queue is Empty\n");   
              
        }   
        return queue[front];   
          
    }   
}   
class hashTable{
	private int currentsize;
	ArrayList<Flight> flightkeys;
	String[] tableArray;
	public hashTable(int currentsize, ArrayList<Flight> flightkeys, String[] tableArray) {
		super();
		this.currentsize = currentsize;
		this.flightkeys = flightkeys;
		this.tableArray = tableArray;
	}
	public int getCurrentsize() {
		return currentsize;
	}
	public void setCurrentsize(int currentsize) {
		this.currentsize = currentsize;
	}
	public ArrayList<Flight> getFlightkeys() {
		return flightkeys;
	}

	public String[] getTableArray() {
		return tableArray;
	}
	boolean isFull() {
		return currentsize==1000;
	}
	 public boolean contains(String airport)
	    {
	        return get(airport) != null;
	    }
	 public int hash(String airport)
	    {
	        return hashCode(airport) % 1000;
	    }
	 public int hashCode( String airport) {
		 
		 double  a=0;
		 for(int i = 0;i<airport.length();i++) {
			 int b = airport.charAt(i);
			 a+=Math.pow(31, i)*b;
			 
			 
		 }
		 int initialSlot=(int) a;
		 return initialSlot;
		 
		 
		 
	 }
	 public String get(String airport)
	    {
	        int i = hash(airport);
	        while (tableArray[i] != null) {
	            if (tableArray[i].equals(airport))
	                return tableArray[i];
	            i = (i + 1) % 1000;
	        }
	        return null;
	    }
	 public void insert( String airport)
	    {
	        int tmp = hash(airport);
	        int i = tmp;
	 
	       
	        do {
	            if (tableArray[i] == null) {
	               tableArray[i]=airport;
	                currentsize++;
	                return;
	            }
	 
	            if (tableArray[i].equals(airport)) {
	                
	                return;
	            }
	 
	            i = (i + 1) % 1000;
	 
	        }
	 
		
	        while (i != tmp);
	    }
	 public void printHashTable()throws IOException
	    {
	        
	        for (int i = 0; i < 1000; i++)
	            if (tableArray[i] != null) {
	               // System.out.println(tableArray[i] + " " + i);
	            	if(i>=0 && i<10) {
	            		fileWriter.write(tableArray[i]+"00"+i+" ");
	            		
	            	}
	            	if(i>=10 && i<100) {
	            		fileWriter.write(tableArray[i]+"0"+i+" ");
	            	}
	            	if(i>=100 && i<1000) {
						fileWriter.write(tableArray[i]+i+" ");
						
					}
	        	
	       
	    }
	    }
	 
	
}
public int getMin(ArrayList<Flight> flights) {
	int min=flights.get(0).getAdmissionTime();
	int n =flights.size();
	for (int i = 1; i < n; i++) {
        if (flights.get(i).getAdmissionTime() < min) {
            min = flights.get(i).getAdmissionTime();
        }
    }
	return min;
}




}

