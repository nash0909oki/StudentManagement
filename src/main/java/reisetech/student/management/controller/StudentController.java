package reisetech.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reisetech.student.management.controller.converter.StudentConverter;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourses;
import reisetech.student.management.domain.StudentDetail;
import reisetech.student.management.service.StudentService;

@RestController
public class StudentController {

    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        List<Student> students = service.searchStudentList();
        List<StudentCourses> studentCourses = service.searchStudentCoursesList();

        return converter.getStudentDetails(students, studentCourses);
    }

    @GetMapping("/studentCourseList")
    public List<StudentCourses> getStudnetCourseList() {
        return service.searchStudentCoursesList();
    }
}

