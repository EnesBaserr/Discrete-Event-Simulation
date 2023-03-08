import java.util.Arrays;
public class Flight implements Comparable<Flight>{
	public Integer admissionTime;
	public String flightCode;
	public String ACCcode;
	public String departureAirport;
	public String arrivalAirport;
	public int[] operationTimes;
	public int accCounter;
	public String accProcess;
	public Flight() {
		
	}
	
	public Flight(int admissionTime, String flightCode, String aCCcode, String departureAirport, String arrivalAirport,
			int[] operationTimes,int accCounter,String accProcess) {
		super();
		this.admissionTime = admissionTime;
		this.flightCode = flightCode;
		ACCcode = aCCcode;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.operationTimes = operationTimes;
		this.accCounter=accCounter;
		this.accProcess=accProcess;
		
	}
	

	public String getAccProcess() {
		return accProcess;
	}

	public void setAccProcess(String accProcess) {
		this.accProcess = accProcess;
	}

	public int getAccCounter() {
		return accCounter;
	}

	public void setAccCounter(int accCounter) {
		this.accCounter = accCounter;
	}

	public int getAdmissionTime() {
		return admissionTime;
	}
	public void setAdmissionTime(int admissionTime) {
		this.admissionTime = admissionTime;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public String getACCcode() {
		return ACCcode;
	}
	public void setACCcode(String aCCcode) {
		ACCcode = aCCcode;
	}
	public String getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public int[] getOperationTimes() {
		return operationTimes;
	}
	public void setOperationTimes(int[] operationTimes) {
		this.operationTimes = operationTimes;
	}

	@Override
	public int compareTo(Flight o) {
		if(this.admissionTime.compareTo(o.admissionTime)!=0) {
			return this.admissionTime.compareTo(o.admissionTime);
		}
	
		else {
			if(!this.accProcess.equals(o.accProcess)) {
				return this.accProcess.compareTo(o.accProcess);
				
			}
			else {
				
			
	
			return this.flightCode.compareTo(o.flightCode);
			}
		}
	}
	
}
