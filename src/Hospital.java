import java.util.*;

public class Hospital {
	String hname;
	String pincode;
	int uhid;
	private ArrayList<Slot> hSlot = new ArrayList<>();
	public Hospital(String hname, String pincode, int uhid) {
		this.hname = hname;
		this.pincode = pincode;
		this.uhid = uhid;
	}
	public void setSlot(Slot slot) {
		this.hSlot.add(slot);
	}
	public void printSlots() {
		for(int i =0; i<hSlot.size();i++) {
			
		}
	}
	
	public boolean hasVaccine(String vname) {
		for(int i = 0; i<hSlot.size(); i++) {
			if(hSlot.get(i).vaccine.vname.equals(vname) && hSlot.get(i).quantity>0 ) {
				return true;
			}
		}
		return false;
	}
	public boolean bookSlot(Citizen c, String vname) {
		if(!c.vBook()) {
			int flag =0;
			for(int i = 0; i<hSlot.size(); i++) {
				if(vname == "0") {
					if(hSlot.get(i).quantity>0) {
						System.out.println(i+"-> Day: "+hSlot.get(i).daynumber+"Available Qty: "+hSlot.get(i).quantity+"Vaccine: "+hSlot.get(i).vaccine.vname);
						flag =1;
					}
				}
				else {
					if(hSlot.get(i).quantity>0 && hSlot.get(i).vaccine.vname.equals(vname)) {
						System.out.println(i+"-> Day: "+hSlot.get(i).daynumber+"Available Qty: "+hSlot.get(i).quantity+"Vaccine: "+vname);
						flag =1;
					}
				}
			}
			if(flag==0) {
				System.out.print("No Slots Available");
				return false;
			}
			int choice;
			System.out.print("Choose slot: ");
			choice = Integer.parseInt(CovinPortal.sc.nextLine());
			hSlot.get(choice).quantity--;
			c.doseadmin++;
			System.out.println(c.cname + " vaccinated with "+hSlot.get(choice).vaccine.vname);
			if(hSlot.get(choice).vaccine.dose == 1) {
				c.setStatus("FULLY VACCINATED");
			}
			else {
				c.setStatus("PARTIALLY VACCINATED");
				c.nextdate = c.nextdate + hSlot.get(choice).vaccine.gap;
			}
		}
		else if(c.checkStatus().equals("PARTIALLY VACCINATED")) {
			int flag =0;
			for(int i = 0; i<hSlot.size(); i++) {
				if(vname == "0") {
					if(hSlot.get(i).quantity>0 && hSlot.get(i).daynumber == c.nextdate) {
						System.out.println(i+"-> Day: "+hSlot.get(i).daynumber+"Available Qty: "+hSlot.get(i).quantity+"Vaccine: "+hSlot.get(i).vaccine.vname);
						flag =1;
					}
				}
				else {
					if(hSlot.get(i).quantity>0 && hSlot.get(i).vaccine.vname.equals(vname) &&  hSlot.get(i).daynumber == c.nextdate) {
						System.out.println(i+"-> Day: "+hSlot.get(i).daynumber+"Available Qty: "+hSlot.get(i).quantity+"Vaccine: "+vname);
						flag =1;
					}
				}
			}
			if(flag==0) {
				System.out.print("No Slots Available");
				return false;
			}
			int choice;
			System.out.print("Choose slot: ");
			choice = Integer.parseInt(CovinPortal.sc.nextLine());
			hSlot.get(choice).quantity--;
			c.doseadmin++;
			System.out.println(c.cname + " vaccinated with "+hSlot.get(choice).vaccine.vname);
			if(hSlot.get(choice).vaccine.dose == c.doseadmin) {
				c.setStatus("FULLY VACCINATED");
			}
			else {
				c.nextdate = c.nextdate + hSlot.get(choice).vaccine.gap;
			}
		}
		return true;
	}
}
