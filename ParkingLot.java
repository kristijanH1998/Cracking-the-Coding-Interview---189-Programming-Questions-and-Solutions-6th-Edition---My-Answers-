package objectOrientedDesign;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

enum Type{Regular, Compact, Disabled, Staff};
enum Availability{Free, Occupied};

abstract class ParkingSpot {
	private double length;
	private double width;
	private double area;
	private Availability occupied = Availability.Free;
	private boolean forFree;
	private double dailyCost;
	private Type type;
	
	public ParkingSpot(double length, double width, boolean free, double cost, Type type) {
		if(free) {
			this.forFree = true;
		} else {
			this.forFree = false;
			this.dailyCost = cost;
		}
		this.length = length;
		this.width = width;
		this.area = length * width;
		this.type = type;
	}
	
	public double printArea() {
		return this.area;
	}
	
	public boolean isForFree() {
		return forFree;
	}
	
	public double checkCost() {
		if(this.isForFree()) {
			return this.dailyCost;
		} 
		return 0;
	}
	
	public Availability checkFree() {
		return this.occupied;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public void setAvailability(Availability availability) {
		this.occupied = availability;
	}
}

class Regular extends ParkingSpot{
	public Regular() {
		super(3, 1.7, false, 7, Type.Regular);
	}
}

class Compact extends ParkingSpot{
	public Compact() {
		super(3, 1.2, false, 7, Type.Compact);
	}
}

class Disabled extends ParkingSpot{
	public Disabled() {
		super(3, 1.7, true, 0, Type.Disabled);
	}
}

class Staff extends ParkingSpot{
	public Staff() {
		super(3, 1.7, true, 7, Type.Staff);
	}
}

class Car{
	Type type;
	String manufacturer;
	String model;
	String licensePlate;
	String Colour;
	public Car(String manufacturer, String model, String licensePlate, String colour, Type type) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.licensePlate = licensePlate;
		this.Colour = colour;
		this.type = type;
	}
	public void occupy(List<Vector<ParkingSpot>> lot) {
		outerloop:
		for(int i = 0; i < lot.size(); i++) {
			for(ParkingSpot p: lot.get(i)) {
				if((p.getType() == this.type) && (p.checkFree() == Availability.Free)) {
					p.setAvailability(Availability.Occupied);
					break outerloop;
				}
			}
			if(i == (lot.size() - 1)) {
				System.out.println("No free spots.");
				return;
			}
		}
	}
}

public class ParkingLot {
	public static void main(String[] args) {
		int rows = 4;
		List<Vector<ParkingSpot>> lot = new ArrayList<Vector<ParkingSpot>>();
		while(rows > 0) {
			Vector<ParkingSpot> row = new Vector<ParkingSpot>();
			for(int i = 0; i < 4; i++) {
				if(i < 2) {
					Regular ps = new Regular();
					row.add(ps);
				} else {
					Compact ps = new Compact();
					row.add(ps);
				}
			}
			lot.add(row);
			rows--;
		}
		Car c1 = new Car("Nissan", "Rogue", "12235", "red metallic", Type.Regular);
		Car c2 = new Car("Ford", "Fiesta", "22134", "blue", Type.Compact);
		c1.occupy(lot);
		c2.occupy(lot);
		for(int i = 0; i < lot.size(); i++) {
			for(ParkingSpot p: lot.get(i)) {
				System.out.println(p.printArea() + " " + p.checkFree());
			}
		}
	}
}