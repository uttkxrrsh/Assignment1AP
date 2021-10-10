import java.util.*;

public class Hospital {
	String hname;
	String pincode;
	int uhid;
	private ArrayList<Slot> hslot = new ArrayList<>();
	public Hospital(String hname, String pincode, int uhid) {
		this.hname = hname;
		this.pincode = pincode;
		this.uhid = uhid;
	}
	public void setSlot(Slot slot) {
		this.hslot.add(slot);
	}
	
}
