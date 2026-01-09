package edu.cjc.sma.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import edu.cjc.sma.app.model.Student;
import edu.cjc.sma.app.repositary.StudentRepositary;
import edu.cjc.sma.app.servicei.StudentServiceI;

@Service
public class StudentServiceImpl implements StudentServiceI
{

	@Autowired
	StudentRepositary sr;
	
	@Override
	public void saveStudent(Student s) {
		sr.save(s);
		
	}

	@Override
	public List<Student> getAllStudents() {
		
		return sr.findAll();
	}

	@Override
	public List<Student> searchStudentByBatchMode(String batchNumber, String batchMode) {
		
		return sr.findAllByBatchNumberAndBatchMode(batchNumber, batchMode);
	}

	@Override
	public List<Student> pagingStudent(int pageNumber, int i) {
	                  
		 
		return sr.findAll(PageRequest.of(pageNumber, i)).getContent();
	}

	@Override
	public Student getSingleStudent(int studentId) {
		
		return sr.findById(studentId).get();
	}

	@Override
	public void updateStudentFees(int studentId, double ammount) {
		  Student s= sr.findById(studentId).get();
		        s.setFeesPaid(ammount+s.getFeesPaid());
		        sr.save(s);
	}

}
