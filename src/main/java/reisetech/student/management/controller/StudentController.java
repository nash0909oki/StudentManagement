package reisetech.student.management.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/studentUpdateForm/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        StudentDetail studentDetail = service.findStudent(id);
        model.addAttribute("studentDetail", studentDetail);
        return "updateStudent";
    }


    @PostMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(
            @RequestBody(required = false) StudentDetail studentDetail) {
        if (studentDetail == null) {
            return ResponseEntity.badRequest().body("更新データ入力がされていません");
        }
        service.updateStudentDetail(studentDetail);
        return ResponseEntity.ok(studentDetail);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("データの形式が不正です（JSON構文の間違いなど）");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleServerError(Exception ex) {
        ex.printStackTrace(); // ログ出力
        return ResponseEntity.status(500).body("サーバーエラーが発生しました");
    }

    @GetMapping("/studentDetail/{id}")
    public String updateResult(@PathVariable String id, Model model) {
        StudentDetail studentDetail = service.findStudent(id);
        model.addAttribute("studentDetail", studentDetail);
        return "redirect:/studentList";
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
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudentCourses(Arrays.asList(new StudentCourses()));
        model.addAttribute("studentDetail", studentDetail);
        return "registerStudent";
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(
            @RequestBody(required = false) StudentDetail studentDetail) {
        if (studentDetail == null) {
            return ResponseEntity.badRequest().body("登録データ入力がされていません");
        }
        service.insertStudent(studentDetail);
        return ResponseEntity.ok(studentDetail);
    }
}