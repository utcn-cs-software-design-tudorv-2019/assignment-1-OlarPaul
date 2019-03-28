package model;

public class Course {
	private int id;
	private Teacher teacher;
	private String name;

	public Course(Teacher teacher, String name) {
		super();
		this.name = name;
		this.teacher = teacher;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", teacher=" + teacher + ", name=" + name + "]";
	}
	

}
