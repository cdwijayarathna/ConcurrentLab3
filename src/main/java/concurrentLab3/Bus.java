package concurrentLab3;

public class Bus extends Thread{

	private Passenger[] passengers = new Passenger[50];
	private int passengerCount;
	private BusStation currentStation;
	private int busId;
	
	public Bus(BusStation bs, int id){
		passengerCount = 0;
		this.currentStation = bs;
		this.busId = id;
	}
	
	public void arriveStation(){
		currentStation.busArrived();
	}
	
	public void getPassenger(){
		passengers[passengerCount] = currentStation.passengerLeave();
	}
	
	public void leave(){
		currentStation.busLeave();
	}
	
	public void run(){
		arriveStation();
		System.out.println( busId + " bus arrived");
		while(!(passengerCount == 50 || currentStation.getPassengerCount() == 0)){
			getPassenger();
		}
		leave();
		System.out.println( busId + " bus left");
	}
	
	
}
