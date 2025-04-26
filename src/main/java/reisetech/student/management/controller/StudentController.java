package reisetech.student.management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reisetech.student.management.domain.StudentDetail;
import reisetech.student.management.service.StudentService;


/**
 * REST APIのリクエストを処理するコントローラー(受講生に関する操作（検索・登録・更新）を行う)。
 */

@Validated
@RestController
public class StudentController {

    private StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * 受講生詳細一覧検索。全件検索のため、条件指定は行わない。
     *
     * @return　受講生詳細一覧（全件）
     */
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    /**
     * 一人の受講生詳細検索。idに紐づく任意の受講生情報を取得する。
     *
     * @return　単一の受講生詳細
     */
    @GetMapping("/studentDetail/{id}")
    public StudentDetail getOneStudent(@PathVariable
    @NotBlank(message = "IDは必須です")
    @Pattern(regexp = "^[0-9]{1,3}$", message = "IDは1〜3桁の半角数字で入力をしてください")
    String id) {
        return service.findStudent(id);
    }

    /**
     * 受講生詳細の更新処理。論理削除処理もここで行う。
     *
     * @param studentDetail 　単一の受講生詳細
     * @return　実行結果
     */

    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(
            @RequestBody(required = false) @Valid StudentDetail studentDetail) {
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

    /**
     * 受講生詳細と受講生コース情報登録。
     *
     * @param studentDetail
     * @return　実行結果
     */

    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(
            @RequestBody(required = false) @Valid StudentDetail studentDetail) {
        if (studentDetail == null) {
            return ResponseEntity.badRequest().body("登録データ入力がされていません");
        }
        StudentDetail responseStudentDetail = service.insertStudent(studentDetail);
        return ResponseEntity.ok(responseStudentDetail);
    }
}
