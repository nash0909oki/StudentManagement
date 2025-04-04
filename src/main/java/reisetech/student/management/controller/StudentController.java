package reisetech.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;
import reisetech.student.management.service.StudentService;

@RestController
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController (StudentService service){
        this.service=service;
    }

    @GetMapping("/studentList")
    public List<Student> getStudentList() {
        return service.searchStudentList();
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

