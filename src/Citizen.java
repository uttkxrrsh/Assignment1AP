
public class Citizen {
	String cname;
	int age;
	String uid;
	private String status = "Registered";
	int nextdate=0;
	int doseadmin=0;
	private Vaccine vaccine = null;
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
	public Vaccine checkvType() {
		return vaccine;
	}
	public String checkStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
