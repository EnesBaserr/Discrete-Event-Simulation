import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Project3 {
	
	public static void main(String[] args)throws IOException {
		
		
		ArrayList<Flight> flightArrayList = new ArrayList<>();
		ArrayList<String> accArrayList=new ArrayList<>();
		
		ArrayList<String> hashArrayListMain = new ArrayList<>();
		File inputsFile = new File(args[0]);
		File outputFile = new File(args[1]);
		FileWriter fileWriter = new FileWriter(outputFile);
		Scanner reader = new Scanner(inputsFile);
		int counter=0;
		int intFlight=0;
		int breaker=0;
		boolean flag = true;
		
		while(reader.hasNextLine()) {
			
			if(counter==0) {

				String inputString =reader.nextLine();
				
				
				String[] inputArray = inputString.split(" ");
				
				
				 intFlight =  Integer.parseInt(inputArray[0]);
				 
				
				 breaker = Integer.parseInt(inputArray[1]);
				 
				
				
				counter++;
				continue;
				
			}
			counter++;
			
			String inputInfo = reader.nextLine();
			String[] inputArrayy = inputInfo.split(" ");
			if(counter<=intFlight+1) {
				accArrayList.add(inputArrayy[0]);
				hashArrayListMain.add(inputArrayy[0]);
				for(int i = 1;i<inputArrayy.length;i++) {
					hashArrayListMain.add(inputArrayy[i]);
					
					
				}
				
				
			}
			
			if(counter>intFlight+1) {
				Flight flightObject = new Flight();
				
			
				flightObject.setAdmissionTime(Integer.parseInt(inputArrayy[0]));
				flightObject.setFlightCode(inputArrayy[1]);
				flightObject.setACCcode(inputArrayy[2]);
				flightObject.setDepartureAirport(inputArrayy[3]);
				flightObject.setArrivalAirport(inputArrayy[4]);
				flightObject.setAccCounter(0);
				flightObject.setAccProcess("a");
				String [] timeArrayx = Arrays.copyOfRange(inputArrayy, 5, 26);
				
				int[] timeArray = new int[timeArrayx.length];
				for(int j = 0; j<timeArrayx.length;j++) {
					timeArray[j]=Integer.parseInt(timeArrayx[j]);
					
				}
				
				flightObject.setOperationTimes(timeArray);
				
				flightArrayList.add(flightObject);
				
				
			
			}
			
			
			
			
			
			if(counter==intFlight+breaker+2)
				break;
		}
		
		
		
		

		for(int k = 0;k<accArrayList.size();k++) {
			int index = hashArrayListMain.indexOf(accArrayList.get(k));
			ArrayList<String> hashArrayList = new ArrayList<>();
			ArrayList<Flight> flights = new ArrayList<>();
			String indetifierString = accArrayList.get(k);
			for(int l = 0;l<flightArrayList.size();l++) {
				
				if(flightArrayList.get(l).getACCcode().equals(indetifierString)) {
					flights.add(flightArrayList.get(l));
					
					
				}
				else {
					continue;
				}
			}
			for(int j = index+1;j<hashArrayListMain.size();j++) {
				if(!accArrayList.contains(hashArrayListMain.get(j))) {
					hashArrayList.add(hashArrayListMain.get(j));
				
					
				}
				if(accArrayList.contains(hashArrayListMain.get(j))){
					break;
				}
				
				
			}
			
			//System.out.println(flights.get(0).operationTimes[21]);
			String[] forhash = new String[1000];
			
			Flight[] queueFlights=new Flight[0];
			
			ACC acc = new ACC(flights, 0,forhash,hashArrayList,queueFlights,fileWriter);
			
			fileWriter.write(indetifierString+" ");
			acc.main(flights);
			acc.setHashtable(forhash);
			fileWriter.write("\n");
			
			
			
			
			
			
		}
		fileWriter.close();
	
		
		
		reader.close();
		
	}
}
