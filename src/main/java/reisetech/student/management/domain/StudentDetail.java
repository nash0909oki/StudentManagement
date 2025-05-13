package reisetech.student.management.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reisetech.student.management.data.Student;
import reisetech.student.management.data.StudentCourse;

@Schema(name = "StudentDetail", description = "受講生詳細")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

    @Valid
    @NotNull(message = "受講生情報は必須です")
    @Schema(description = "受講生の詳細情報(名前、年齢など）")
    private Student student;

    @Valid
    @NotNull(message = "受講生コース情報は必須です")
    @Schema(description = "受講生コース情報の詳細（コース名、受講開始日など）")
    private List<StudentCourse> studentCourseList;
}
