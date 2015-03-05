package concurrentLab3;

public class Passenger extends Thread{
	
	private boolean onBus;
	private BusStation station;
	private int passengerId;
	
	public Passenger(BusStation station, int id){
		this.station = station;
		this.passengerId = id;
	}
	
	public void arriveStation(){
		while(!station.newPassenger(this)){}
		
		
	}
	
	public void boardBus(){
		//getIntoBus
	}
	
	public void run(){
		arriveStation();
		
	}
	
	public int getpId(){
		return passengerId;
	}

}
