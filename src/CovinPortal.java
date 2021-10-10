import java.util.*;

public class CovinPortal {
	
	static Scanner sc = new Scanner(System.in);
	
	public static int uhidgen=100000;
	
	public static ArrayList<Hospital> hList = new ArrayList<>();
	
	public static ArrayList<Citizen> cList = new ArrayList<>();
	
	public static ArrayList<Vaccine> vList = new ArrayList<>();
	
	public static void addVaccine() {
		String vname;
		int dose;
		int gap=0;
		System.out.print("Vaccine Name: ");
		vname = sc.nextLine();
		System.out.print("Number of Doses: ");
		dose = Integer.parseInt(sc.nextLine());
		if(dose > 1) {
			System.out.print("Gap between Doses: ");
			gap = Integer.parseInt(sc.nextLine());
		}
		Vaccine vaccine = new Vaccine(vname, dose, gap);
		System.out.println("Vaccine Name: "+vname+",Number of Doses: "+dose+ ",Gap between Doses: "+gap);
		vList.add(vaccine);
	}
	
	public static void regHospital() {
		String hname;
		String pincode;
		System.out.print("Hospital Name: ");
		hname = sc.nextLine();
		System.out.print("PinCode: ");
		pincode = sc.nextLine();
		Hospital hospital = new Hospital(hname, pincode,uhidgen);
		hList.add(hospital);
		System.out.println("Hospital Name: "+hname+",Pincode: "+pincode+ ",Unique ID: "+uhidgen);
		uhidgen++;
	}
	
	public static void regCitizen() {
		String cname;
		int age;
		String uid;
		System.out.print("Citizen Name: ");
		cname = sc.nextLine();
		System.out.print("Age: ");
		age = Integer.parseInt(sc.nextLine());
		System.out.print("Unique ID: ");
		uid = sc.nextLine();
		System.out.println("Citizen Name: "+cname+",Age: "+age+ ",Unique ID: "+uid);
		if(age<18) {
			System.out.println("Only above 18 are allowed");
			return;
		}
		Citizen citizen = new Citizen(cname,age,uid);
		cList.add(citizen);
	}
	
	public static void addSlot() {
		int hid;
		int noofslots;
		int daynumber;
		int quantity;
		int vno;
		Vaccine vaccine = null;
		Hospital hospital = null;
		System.out.print("Enter Hospital ID: ");
		hid = Integer.parseInt(sc.nextLine());
		for(Hospital x : hList) {
			if(x.uhid == hid) {
				hospital = x;
			}
		}
		System.out.print("Enter number of Slots to be added: ");
		noofslots = Integer.parseInt(sc.nextLine());
		for(int i = 0; i<noofslots; i++) {
			System.out.print("Enter Day Number: ");
			daynumber = Integer.parseInt(sc.nextLine());
			System.out.print("Enter Quantity: ");
			quantity = Integer.parseInt(sc.nextLine());
			System.out.print("Select Vaccine: ");
			for(int j = 0; j<vList.size(); j++) {
				System.out.print(j + ". " + vList.get(j).vname+"\n");
			}
			vno = Integer.parseInt(sc.nextLine());
			vaccine = vList.get(vno);
			Slot slot = new Slot(daynumber, quantity, vaccine);
			hospital.setSlot(slot);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice=0;
		System.out.println("Cowin portal initialised...");
		while(choice!=9) {
			System.out.println("---------------------------------\n1. Add Vaccine\n2. Register Hospital\n3. Register Citizen\n4. Add Slot for Vaccination\n5. Book Slot for Vaccination\n6. List all slots for a hospital\n7. Check Vaccination Status\n8. Exit\n---------------------------------");
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				addVaccine();
				break;
			case 2:
				regHospital();
				break;
			case 3:
				regCitizen();
				break;
			case 4:
				addSlot();
				break;
			case 9:
				return;
			}
		}
	}
}
