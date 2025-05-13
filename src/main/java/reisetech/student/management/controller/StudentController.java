package reisetech.student.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reisetech.student.management.common.annotation.BadRequestResponse;
import reisetech.student.management.common.annotation.IdNotFoundResponse;
import reisetech.student.management.common.annotation.ServerErrorResponse;
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
    @Operation(summary = "一覧検索", description = "受講生の一覧検索をします。",
            tags = {"受講生"})
    @ServerErrorResponse
    @ApiResponse(responseCode = "200", description = "検索成功。StudentDetailオブジェクトが返されます",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = StudentDetail.class)),
                    examples = @ExampleObject(
                            name = "成功例",
                            summary = "受講生詳細情報")))

    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    /**
     * 一人の受講生詳細検索。idに紐づく任意の受講生情報を取得する。
     *
     * @return　単一の受講生詳細
     */
    @Operation(summary = "一人の受講生検索", description = "idで指定された一人の受講生を検索します", tags = {
            "受講生"})
    @ServerErrorResponse
    @IdNotFoundResponse
    @BadRequestResponse
    @ApiResponse(responseCode = "200", description = "検索成功。StudentDetailオブジェクトが返されます。",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = StudentDetail.class),
                    examples = @ExampleObject(
                            name = "Success_StudentDetail",
                            summary = "指定された受講生IDが存在し、情報が取得できた場合")))

    @GetMapping("/studentDetail/{id}")
    public StudentDetail getOneStudent(
            @Parameter(description = "受講生ID", example = "2")
            @PathVariable
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

    @Operation(summary = "受講生更新", description = "idで指定された一人の受講生の情報を更新します。※ `id` はリクエストボディに含めてください（下のRequest Bodyには表示されませんが必須です）。", tags = {
            "受講生"})
    @ServerErrorResponse
    @ApiResponse(responseCode = "200", description = "更新成功。更新された受講生詳細(StudentDetailオブジェクト)が返されます。",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = StudentDetail.class),
                    examples = @ExampleObject(
                            name = "Success_StudentDetail",
                            summary = "更新処理が成功した場合")))

    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(
            @RequestBody @Valid StudentDetail studentDetail) {

        service.updateStudentDetail(studentDetail);
        return ResponseEntity.ok(studentDetail);
    }

    /**
     * 受講生詳細と受講生コース情報登録。
     *
     * @param studentDetail
     * @return　実行結果
     */
    @Operation(summary = "受講生登録", description = "受講生を登録します", tags = {"受講生"})
    @ServerErrorResponse
    @ApiResponse(responseCode = "200", description = "登録成功。登録された受講生詳細(StudentDetailオブジェクト)が返されます",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = StudentDetail.class),
                    examples = @ExampleObject(
                            name = "Success_StudentDetail",
                            summary = "登録処理が成功した場合")))
    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(
            @RequestBody @Valid StudentDetail studentDetail) {

        StudentDetail responseStudentDetail = service.insertStudent(studentDetail);
        return ResponseEntity.ok(responseStudentDetail);
    }
}
