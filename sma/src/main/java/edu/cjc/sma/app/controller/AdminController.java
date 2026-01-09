package edu.cjc.sma.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.cjc.sma.app.model.Student;
import edu.cjc.sma.app.servicei.StudentServiceI;

@Controller
public class AdminController {
	
	@Autowired
	StudentServiceI studServiceI;

	@RequestMapping("/")
	public String preLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String onLogin(@RequestParam("username") String username,
			@RequestParam("password") String password,Model m) {
		if(username.equals("admin") && password.equals("admin")) {
			List<Student> list=studServiceI.getAllStudents();
			m.addAttribute("data", list);
			return "adminscreen";
		}else {
			m.addAttribute("login_fail","invalid creaditial...");
			return "login";
		}
	}
	
	@RequestMapping("/enroll_student")
	public String saveStudentDetails(@ModelAttribute Student s,Model m) {
		studServiceI.saveStudent(s);
		List<Student> list=studServiceI.getAllStudents();
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/search")
	public String getStudentByBatchMode(@RequestParam("batchNumber")String batchNumber,@RequestParam("batchMode") String batchMode,Model m) {
		List<Student> result=studServiceI.searchStudentByBatchMode(batchNumber, batchMode);
		
		if(result.size()>0) {
			m.addAttribute("data", result);
		}else {
			List<Student> list=studServiceI.getAllStudents();
			m.addAttribute("data", list);
			m.addAttribute("message","No record availabel for the batch "+batchNumber+" "+batchMode);
		}
		
		return "adminscreen";
	}
	
	@RequestMapping("/paging")
	public String paging(@RequestParam("pageNo") int pageNumber,Model m) {
		
		List<Student> list=studServiceI.pagingStudent(pageNumber,2);
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	@RequestMapping("/fees")
	public String onFees(@RequestParam("rollno") int studentId,Model m)
	{
		   Student stu = studServiceI.getSingleStudent(studentId);
		
		m.addAttribute("st",stu);
		return "fees";
	}
	@RequestMapping("/payfees")
	public String updateFees(@RequestParam("studentid")int studentId,@RequestParam("ammount") double ammount,Model m) {
		
		studServiceI.updateStudentFees(studentId,ammount);
		List<Student> list=studServiceI.getAllStudents();
		m.addAttribute("data", list);
		return "adminscreen";
	}
	
	
	
	
}
