package reisetech.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reisetech.student.management.domain.StudentDetail;
import reisetech.student.management.service.StudentService;


/**
 * REST APIのリクエストを処理するコントローラー(受講生に関する操作（検索・登録・更新）を行う)。
 */


@RestController
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * 受講生一覧検索。全件検索のため、条件指定は行わない。
     *
     * @return　受講生一覧（全件）
     */
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    /**
     * 一人の受講生検索。idに紐づく任意の受講生情報を取得する。
     *
     * @return　単一の受講生
     */
    @GetMapping("/studentDetail/{id}")
    public StudentDetail getOneStudent(@PathVariable String id) {
        return service.findStudent(id);
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

    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(
            @RequestBody(required = false) StudentDetail studentDetail) {
        if (studentDetail == null) {
            return ResponseEntity.badRequest().body("登録データ入力がされていません");
        }
        StudentDetail responseStudentDetail = service.insertStudent(studentDetail);
        return ResponseEntity.ok(responseStudentDetail);
    }
}
