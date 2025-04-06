package reisetech.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import reisetech.student.management.controller.converter.StudentConverter;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;
import reisetech.student.management.domain.StudentDetail;
import reisetech.student.management.service.StudentService;

@Controller
public class StudentController {

    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public String getStudentList(Model model) {
        List<Student> students = service.searchStudentList();
        List<StudentCourses> studentCourses = service.searchStudentCoursesList();
        model.addAttribute("studentList", converter.getStudentDetails(students, studentCourses));
        return "studentList";
    }

    @GetMapping("/studentCourseList")
    public String getStudentCourseList(Model model) {
        List<Student> students = service.searchStudentList();
        List<StudentCourses> studentCourses = service.searchStudentCoursesList();
        model.addAttribute("studentCourseList",
                converter.getStudentDetails(students, studentCourses));
        return "studentCourseList";
    }

    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        model.addAttribute("studentDetail", new StudentDetail());
        return "registerStudent";
    }

    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute StudentDetail studentDetail,
            BindingResult result) {
        if (result.hasErrors()) {
            return "registerStudent";
        }
        service.insertStudent(studentDetail.getStudent());
        return "redirect:/studentList";
    }
}
