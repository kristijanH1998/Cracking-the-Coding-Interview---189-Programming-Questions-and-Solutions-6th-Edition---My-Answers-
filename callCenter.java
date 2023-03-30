package objectOrientedDesign;
import java.util.ArrayList;

enum Title {respondent, manager, director};
class Center {
	ArrayList<employee> employees = new ArrayList<employee>();
	ArrayList<employee> freeEmployeeList = new ArrayList<employee>();

	public Center(ArrayList<employee> employees) {
		this.employees = employees;
		this.freeEmployeeList = employees;
	}
	public void printFreeEmployees() {
		for(employee e: freeEmployeeList) {
			System.out.println(e.getName());
		}
	}
	
	public void dispatchCall(Call call) {
		if(this.freeEmployeeList.isEmpty()) {
			System.out.println("No available employees!");
		} else {
			int i = 0;
			while((i < freeEmployeeList.size()) && (freeEmployeeList.get(i).getTitle() != Title.respondent ||
					!freeEmployeeList.get(i).canHandle)) {
				i++;
			}
			if(i == freeEmployeeList.size() - 1) {
				i = 0;
				while((i < freeEmployeeList.size()) && (freeEmployeeList.get(i).getTitle() != Title.manager ||
					!freeEmployeeList.get(i).canHandle)) {
					i++;
				}
				if(i == freeEmployeeList.size() - 1) {
					i = 0;
					while((i < freeEmployeeList.size()) && (freeEmployeeList.get(i).getTitle() != Title.director ||
							!freeEmployeeList.get(i).canHandle)) {
							i++;
					}
					freeEmployeeList.get(i).acceptCall(call);
					freeEmployeeList.remove(i);
				} else {
					freeEmployeeList.get(i).acceptCall(call);
					freeEmployeeList.remove(i);
				}
			} else {
				freeEmployeeList.get(i).acceptCall(call);
				freeEmployeeList.remove(i);
			}
			System.out.println("Remaining available employees: ");
			printFreeEmployees();
		}
	}
}

class employee{
	private boolean free = true;
	private String name = "";
	private Title title;
	public boolean canHandle = true;
	private Call callProcessed;
	public employee() {
		
	}
	
	public void acceptCall(Call call) {
		if(this.free){
			this.free = false;
			this.callProcessed = call;
			call.accept(true);
		}	
	}
	public String getName() {
		return this.name;
	}
	public Call getCall() {
		return this.callProcessed;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public Title getTitle() {
		return this.title;
	}
}

class Call{
	private boolean accepted = false;
	
	public Call() {
		
	}
	
	public void accept(boolean accepted) {
		this.accepted = true;
	}
}

public class callCenter {

	public static void main(String[] args) {
		ArrayList<employee> employees= new ArrayList<employee>();
		String[] names = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		for(int i = 0; i < names.length; i++) {
			employee e = new employee();
			if(i < 3) {
				e.setTitle(Title.respondent);
			} else if(i < 8) {
				e.setTitle(Title.manager);
			} else {
				e.setTitle(Title.director);
			}
			e.setName(names[i]);
			employees.add(e);
		}
		employees.get(1).canHandle = false;
		employees.get(7).canHandle = false;

		Center center = new Center(employees);
		Call c1 = new Call();
		Call c2 = new Call();
		Call c3 = new Call();
		ArrayList<Call> calls = new ArrayList<Call>();
		calls.add(c1);
		calls.add(c2);
		calls.add(c3);
		
		for(Call call: calls) {
			center.dispatchCall(call);
		}
		for(employee e: employees) {
			System.out.print(e.getName() + " " + e.getCall());
		}
	}
}
