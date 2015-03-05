package concurrentLab3;

public class Bus extends Thread{

	private Passenger[] passengers = new Passenger[50];
	private int passengerCount;
	private BusStation currentStation;

	public int getBusId() {
		return busId;
	}

	private int busId;
	
	public Bus(BusStation bs, int id){
		passengerCount = 0;
		this.currentStation = bs;
		this.busId = id;
	}
	
	public void arriveStation(){
		currentStation.busArrived(this);
	}
	
	public void getPassenger(){
		passengers[passengerCount] = currentStation.passengerLeave();
	}
	
	public void leave(){
		currentStation.busLeave(this);
	}
	
	public void run(){
		arriveStation();

		while(!(passengerCount == 50 || currentStation.getPassengerCount() == 0)){
			getPassenger();
		}
		leave();
	}
	
	
}
