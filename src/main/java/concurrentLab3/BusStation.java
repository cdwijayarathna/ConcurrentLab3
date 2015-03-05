package concurrentLab3;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusStation {

	private ArrayList<Passenger> passengers;
	private boolean busAvailable;
	private int passengerCount;
	private final Lock passengersLock = new ReentrantLock();
	private Bus currentBus;
	private final Lock busLock = new ReentrantLock();
	
	public BusStation(){
		passengers = new ArrayList<Passenger>();
		passengerCount = 0;
	}
	
	
	public boolean  newPassenger(Passenger p){
		if(!busLock.tryLock()){
			//System.out.println(p.getpId() + " pasenger fails to get into bus");
			return false;
		}
		passengersLock.lock();
		passengers.add(p);
		passengerCount ++;
		System.out.println(p.getpId() + " pasenger arrived bus station");
		passengersLock.unlock();
		busLock.unlock();
		return true;
		
	}
	
	public Passenger passengerLeave(){
		passengersLock.lock();
		Passenger out = passengers.remove(0);
		passengerCount --;
		out.boardBus();
		System.out.println(out.getpId() + " pasenger left bus station");
		passengersLock.unlock();
		
		return out;
		
	}
	
	public void busArrived(){
		busLock.lock();
	}
	
	public void busLeave(){
		busLock.unlock();
	}
	
	public int getPassengerCount(){
		return passengerCount;
	}
	
	public static void main(String[] args){
		BusStation bs = new BusStation();
		Random rn = new Random();
		int r;
		int busCount = 0;
		int passengerCount = 0;
		while(true){
			r = rn.nextInt(51);
			if(r==50){
				(new Bus(bs,busCount)).start();
				busCount++;
			}else{
				(new Passenger(bs,passengerCount)).start();
				passengerCount++;
			}
			
		}
		
	}
	
	
}
