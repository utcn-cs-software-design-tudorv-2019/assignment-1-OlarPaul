package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person {
	private int group;
	private List<Course> enrolments;
	private Map<Course, Double> grades;

	public Student(String name, String adress, int cnp, String mail, int group, String password) {
		super(name, adress, cnp, mail, password);
		this.group = group;
		this.enrolments = new ArrayList<>();
		this.grades = new HashMap<>();
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public List<Course> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(List<Course> enrolments) {
		this.enrolments = enrolments;
	}

	public Map<Course, Double> getGrades() {
		return grades;
	}

	public void setGrades(Map<Course, Double> grades) {
		this.grades = grades;
	}
	//TODO adauga metoda care adauga cursuri
	//TODO metoda care da note
	@Override
	public String toString() {
		return "Student [group=" + group + ", enrolments=" + enrolments + ", grades=" + grades + "]";
	}

}
