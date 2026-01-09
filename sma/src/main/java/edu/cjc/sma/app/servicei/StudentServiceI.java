package edu.cjc.sma.app.servicei;

import java.util.List;

import edu.cjc.sma.app.model.Student;

public interface StudentServiceI {
	
	public void saveStudent(Student s);
	public List<Student> getAllStudents();
	public List<Student> searchStudentByBatchMode(String batchNumber,String batchMode);
	public List<Student> pagingStudent(int pageNumber, int i);
	public Student getSingleStudent(int studentId);
	public void updateStudentFees(int studentId, double ammount);

}
