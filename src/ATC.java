import java.util.ArrayList;
import java.util.Deque;

public class ATC {
Flight flight;
String airportName;
int timex;
Deque<Flight> flights;
int control;



public ATC(Flight flight, Deque<Flight> flights, int control) {
	super();
	this.flight = flight;
	this.flights = flights;
	this.control = control;
}



public void Main(Flight curFlight,Deque<Flight> flights) {
	flights.add(curFlight);
	Flight newFlight = flights.poll();
	int init = newFlight.admissionTime;
	init = Math.max(control, init);
	 control=Math.max(control, init);
	newFlight.admissionTime=(newFlight.operationTimes[newFlight.accCounter]+init);
	control += newFlight.operationTimes[newFlight.accCounter];
	newFlight.accCounter+=1;
	
	
	
	
}
}

