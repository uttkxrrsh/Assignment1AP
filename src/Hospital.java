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
	
	public boolean hasVaccine(String vname) {
		for(int i = 0; i<this.hSlot.size(); i++) {
			if(this.hSlot.get(i).vaccine.vname.equals(vname) && this.hSlot.get(i).quantity>0 ) {
				return true;
			}
		}
		return false;
	}
	
	public void printSlot() {
		for(int i =0 ; i<this.hSlot.size(); i++) {
			if(this.hSlot.get(i).quantity>0) {
				System.out.println("Day: "+this.hSlot.get(i).daynumber +" Vaccine: "+ this.hSlot.get(i).vaccine.vname+ " Available Qty: "+ this.hSlot.get(i).quantity);
			}
		}
	}
	
	public void bookSlot(Citizen c, String vname) {
		if(c.vBook() == true) {
			int flag =0;
			for(int i = 0; i<this.hSlot.size(); i++) {
				if(vname == "0") {
					if(this.hSlot.get(i).quantity>0) {
						System.out.println(i+"-> Day: "+this.hSlot.get(i).daynumber+" Available Qty: "+this.hSlot.get(i).quantity+" Vaccine: "+this.hSlot.get(i).vaccine.vname);
						flag =1;
					}
				}
				else {
					if(this.hSlot.get(i).quantity>0 && this.hSlot.get(i).vaccine.vname.equals(vname)) {
						System.out.println(i+"-> Day: "+this.hSlot.get(i).daynumber+" Available Qty: "+this.hSlot.get(i).quantity+" Vaccine: "+vname);
						flag =1;
					}
				}
			}
			if(flag==0) {
				System.out.print("No Slots Available");
				return;
			}
			int choice;
			System.out.print("Choose slot: ");
			choice = Integer.parseInt(CovinPortal.sc.nextLine());
			this.hSlot.get(choice).quantity--;
			c.doseadmin++;
			c.setVaccine(this.hSlot.get(choice).vaccine.vname);
			System.out.println(c.cname + " vaccinated with "+this.hSlot.get(choice).vaccine.vname);
			if(hSlot.get(choice).vaccine.dose == 1) {
				c.setStatus("FULLY VACCINATED");
			}
			else {
				c.setStatus("PARTIALLY VACCINATED");
				c.nextdate = this.hSlot.get(choice).daynumber + this.hSlot.get(choice).vaccine.gap;
			}
		}
		else if(c.checkStatus().equals("PARTIALLY VACCINATED")) {
			int flag =0;
			String vtype = c.checkvType();
			if(!(vtype.equals(vname))) {
				System.out.println("You can't get vaccinated by this vaccine" + vname);
				return;
			}
			for(int i = 0; i<this.hSlot.size(); i++) {
				if(vname == "0") {
					if(this.hSlot.get(i).quantity>0 && this.hSlot.get(i).daynumber == c.nextdate) {
						System.out.println(i+"-> Day: "+this.hSlot.get(i).daynumber+" Available Qty: "+this.hSlot.get(i).quantity+" Vaccine: "+this.hSlot.get(i).vaccine.vname);
						flag =1;
					}
				}
				else {
					if(this.hSlot.get(i).quantity>0 && this.hSlot.get(i).vaccine.vname.equals(vname) &&  this.hSlot.get(i).daynumber == c.nextdate) {
						System.out.println(i+"-> Day: "+this.hSlot.get(i).daynumber+" Available Qty: "+this.hSlot.get(i).quantity+" Vaccine: "+vname);
						flag =1;
					}
				}
			}
			if(flag==0) {
				System.out.print("No Slots Available");
				return;
			}
			int choice;
			System.out.print("Choose slot: ");
			choice = Integer.parseInt(CovinPortal.sc.nextLine());
			this.hSlot.get(choice).quantity--;
			c.doseadmin++;
			System.out.println(c.cname + " vaccinated with "+this.hSlot.get(choice).vaccine.vname);
			if(this.hSlot.get(choice).vaccine.dose == c.doseadmin) {
				c.setStatus("FULLY VACCINATED");
			}
			else {
				c.nextdate = c.nextdate + this.hSlot.get(choice).vaccine.gap;
			}
		}
		return;
	}
}
