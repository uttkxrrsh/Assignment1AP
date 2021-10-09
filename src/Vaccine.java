
public class Vaccine {
	String vname;
	int dose;
	int gap;
	public Vaccine(String vname,int dose) {
		this.vname = vname;
		this.dose = dose;
		this.gap = 0;
	}
	public Vaccine(String vname,int dose,int gap) {
		this.vname = vname;
		this.dose = dose;
		this.gap = gap;
	}
}
