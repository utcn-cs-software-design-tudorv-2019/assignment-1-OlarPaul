package model;

public class Teacher extends Person {

	private double salary;

	public Teacher(String name, String adress, int cnp, String mail, double salary, String password) {
		super(name, adress, cnp, mail, password);
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Teacher [salary=" + salary + "]";
	}


}
