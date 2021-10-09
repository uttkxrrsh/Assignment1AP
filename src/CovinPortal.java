import java.util.*;

public class CovinPortal {
	
	static Scanner sc = new Scanner(System.in);
	
	public static int uhidgen=100000;
	
	public static ArrayList<Hospital> hList = new ArrayList<>();
	
	public static ArrayList<Citizen> cList = new ArrayList<>();
	
	public static ArrayList<Slot> slots = new ArrayList<>();
	
	public static ArrayList<Vaccine> vaccine = new ArrayList<>();
	
	public static void regHospital() {
		String hname;
		String pincode;
		System.out.println("Hospital Name: ");
		hname = sc.nextLine();
		System.out.println("PinCode: ");
		pincode = sc.nextLine();
		Hospital hospital = new Hospital(hname, pincode,uhidgen);
		hList.add(hospital);
		uhidgen++;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
