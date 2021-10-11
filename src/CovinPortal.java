import java.util.*;

public class CovinPortal {
	
	public static final Scanner sc = new Scanner(System.in);
	
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
		System.out.print("---------------------------------");
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
		System.out.print("---------------------------------");
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
		System.out.print("---------------------------------");
	}
	
	public static void addSlot() {
		int hid;
		int noofslots;
		int daynumber;
		int quantity;
		int vno;
		Vaccine vaccine;
		Hospital hospital = null;
		System.out.print("Enter Hospital ID: ");
		hid = Integer.parseInt(sc.nextLine());
		int flag = 0;
		for(Hospital x : hList) {
			if(x.uhid == hid) {
				hospital = x;
				flag =1;
				break;
			}
		}
		if(flag == 0) {
			System.out.println("Wrong Hospital ID");
			System.out.print("---------------------------------");
			return;
		}
		System.out.print("Enter number of Slots to be added: ");
		noofslots = Integer.parseInt(sc.nextLine());
		for(int i = 0; i<noofslots; i++) {
			System.out.print("\nEnter Day Number: ");
			daynumber = Integer.parseInt(sc.nextLine());
			System.out.print("Enter Quantity: ");
			quantity = Integer.parseInt(sc.nextLine());
			System.out.println("Select Vaccine: ");
			for(int j = 0; j<vList.size(); j++) {
				System.out.println(j + ". " + vList.get(j).vname);
			}
			vno = Integer.parseInt(sc.nextLine());
			vaccine = vList.get(vno);
			Slot slot = new Slot(daynumber, quantity, vaccine);
			hospital.setSlot(slot);
			System.out.println("Slot added by Hospital " + hospital.hname + " for Day: " + daynumber + ", Available Quantity: " + quantity + " of Vaccine " + vaccine.vname);
			System.out.print("---------------------------------");
		}
	}
	
	public static void bookSlot() {
		String uid;
		int choice;
		String pincode;
		String vname;
		Citizen citizen = null;
		Hospital h = null;
		int flag = 0;
		int uhid;
		System.out.print("Enter patient Unique ID: ");
		uid = sc.nextLine();
		for(Citizen c: cList) {
			if(c.uid.equals(uid)) {
				citizen = c;
				flag = 1;
			}
		}
		if(flag == 0) {
			System.out.println("Not Registered");
			System.out.print("---------------------------------");
			return;
		}
		flag=0;
		System.out.print("1. Search by area\n2. Search by Vaccine\n3. Exit\n");
		System.out.print("Enter option: ");
		choice = Integer.parseInt(sc.nextLine());
		switch(choice) {
		case 1:
			System.out.print("Enter PinCode: ");
			pincode = sc.nextLine();
			for(Hospital x: hList) {
				if(x.pincode.equals(pincode)) {
					System.out.println(x.uhid +" " +  x.hname);
					flag = 1;
				}
			}
			if(flag == 0) {
				System.out.println("Wrong pincode");
				System.out.print("---------------------------------");
				return;
			}
			flag = 0;
			System.out.print("Enter hospital id: ");
			uhid = Integer.parseInt(sc.nextLine());
			for(Hospital x: hList) {
				if(x.uhid == uhid) {
					h = x;
					flag =1;
					break;
				}
			}
			if(flag == 0) {
				System.out.println("Wrong hospital id");
				System.out.print("---------------------------------");
				return;
			}
			h.bookSlot(citizen,"0");
			System.out.print("---------------------------------");
			break;
		case 2:
			System.out.print("Enter vaccine name: ");
			vname = sc.nextLine();
			for(Hospital x: hList) {
				if(x.hasVaccine(vname)) {
					System.out.println(x.uhid + " " +x.hname);
				}
			}
			System.out.print("Enter hospital id: ");
			uhid = Integer.parseInt(sc.nextLine());
			for(Hospital x: hList) {
				if(x.uhid == uhid) {
					h = x;
					flag =1;
				}
			}
			if(flag == 0) {
				System.out.println("Wrong hospital id");
				System.out.print("---------------------------------");
				return;
			}
			h.bookSlot(citizen,vname);
			System.out.print("---------------------------------");
			break;
		default:
			System.out.println("Wrong Choice");
			System.out.print("---------------------------------");
			break;
		}
	}
	
	public static void listSlots() {
		int uhid;
		int flag =0;
		System.out.print("Enter Hospital ID: ");
		uhid = Integer.parseInt(sc.nextLine());
		for(Hospital h: hList) {
			if(h.uhid == uhid){
				h.printSlot();
				flag =1;
			}
		}
		if(flag == 0) {
			System.out.println("Wrong Hospital ID");
		}
		System.out.print("---------------------------------");
	}
	
	public static void cStatus() {
		String uid;
		String status;
		int flag =0;
		System.out.print("Enter Patient ID: ");
		uid = sc.nextLine();
		for(Citizen c: cList) {
			if(c.uid.equals(uid)) {
				flag = 1;
				status = c.checkStatus();
				if(status.equals("REGISTERED")) {
					System.out.println("Citizen " + status);
				}
				else {
					String v = c.checkvType();
					System.out.println("Citizen "+status);
					System.out.println("Vaccine Given: "+ v);
					System.out.println("Number of Doses given: "+c.doseadmin);
					if(status.equals("PARTIALLY VACCINATED")) {
						System.out.println("Next Dose due date: "+c.nextdate);
					}
				}
			}
			if(flag==1) {
				break;
			}
		}
		if(flag == 0) {
			System.out.println("Wrong Patient ID");
		}
		System.out.print("---------------------------------");
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String choice="0";
		System.out.println("Cowin portal initialised...");
		System.out.print("---------------------------------");
		while(choice!="8") {
			System.out.println("\n1. Add Vaccine\n2. Register Hospital\n3. Register Citizen\n4. Add Slot for Vaccination\n5. Book Slot for Vaccination\n6. List all slots for a hospital\n7. Check Vaccination Status\n8. Exit\n---------------------------------");
			choice = sc.nextLine();
			switch(choice) {
			case "1":
				addVaccine();
				break;
			case "2":
				regHospital();
				break;
			case "3":
				regCitizen();
				break;
			case "4":
				addSlot();
				break;
			case "5":
				bookSlot();
				break;
			case "6":
				listSlots();
				break;
			case "7":
				cStatus();
				break;
			case "8":
				return;
			default:
				System.out.print("Wrong choice");
			}
		}
	}
}
