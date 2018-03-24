package com.tutorial.apap.junit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tutorial.apap.junit.model.StudentModel;
import com.tutorial.apap.junit.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }


    @RequestMapping("/student/add")
    public String add ()
    {
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa);
        studentDAO.addStudent (student);

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);

        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    		StudentModel student = studentDAO.selectStudent (npm);
    		if (student != null) {
    			studentDAO.deleteStudent (npm);
    			return "delete";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }
    
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, 
    		@PathVariable(value = "npm") String npm)
    {
    	
    		StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
        		model.addAttribute ("student", student);
        		return "form-update";
        } else {
            model.addAttribute ("npm", npm);
            return "not-found";
        }
    }
    
    /**
     * Method to update data student with params declared
     * @param model
     * @param npm
     * @param name
     * @param gpa
     * @return
     */
   
//    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
//    public String updateSubmit (Model model, 
//            @RequestParam(value = "npm", required = false) String npm,
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "gpa", required = false) double gpa)
//    {
//    		StudentModel student = studentDAO.selectStudent (npm);
//	    	if (student != null) {
//	        studentDAO.updateStudent(npm, name, gpa);
//	        return "success-update";
//	    }else {
//	    		model.addAttribute ("npm", npm);
//            return "not-found";
//	    }
//    }
    
    
    
    /**
     * Well, this method is using StudentModel as the parameter
     * So, the method updateSubmit will only need 1 object StudentModel
     * And that object's variable will be used to update the selected data student
     * @param student
     * @param model
     * @return
     */
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (@ModelAttribute("student") StudentModel student, ModelMap model)
    {
    		if(student.getName() == null || student.getName() == "") {
    			return "error";
    		}
    		
    		StudentModel studentValid = studentDAO.selectStudent(student.getNpm());
	    	if (studentValid != null) {
	        studentDAO.updateStudent(student.getNpm(), student.getName(), student.getGpa());
	        return "success-update";
	    }else {
	    		model.addAttribute ("npm", student.getNpm());
            return "not-found";
	    }
    }
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.GET)
    public String updateSubmitGET (@ModelAttribute("student") StudentModel student, ModelMap model)
    {
    		if(student.getName() == null || student.getName() == "") {
    			return "error";
    		}
    		
    		StudentModel studentValid = studentDAO.selectStudent(student.getNpm());
	    	if (studentValid != null) {
	        studentDAO.updateStudent(student.getNpm(), student.getName(), student.getGpa());
	        return "success-update";
	    }else {
	    		model.addAttribute ("npm", student.getNpm());
            return "not-found";
	    }
    }

}
