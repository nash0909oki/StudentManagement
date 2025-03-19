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
    public List<StudentCourses> getStudnetCourseList(){
        return service.searchStudentCoursesList();
    }
}

