
public class Citizen {
	String cname;
	int age;
	String uid;
	private String status = "REGISTERED";
	int nextdate=0;
	int doseadmin=0;
	String vaccine = null;
	public Citizen(String cname,int age, String uid) {
		this.cname = cname;
		this.age = age;
		this.uid = uid;
	}
	public boolean vBook() {
		if(this.doseadmin == 0) {
			return true;
		}
		return false;
	}
	public String checkvType() {
		return vaccine;
	}
	public String checkStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setVaccine(String vname) {
		this.vaccine = vname;
	}
}
