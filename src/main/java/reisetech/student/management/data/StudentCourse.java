package reisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報の詳細（コース名、受講開始日など）")
@Getter
@Setter

public class StudentCourse {

    @Schema(description = "一意のId、DB側で自動採番される", example = "1", readOnly = true)
    private String id;

    @Schema(description = "受講生Id、Studentクラスのidと一致するように、DB側で外部キー制約をしている", example = "1", readOnly = true)
    private String studentId;

    @Schema(description = "コース名", requiredMode = RequiredMode.REQUIRED, example = "Java")
    @NotBlank(message = "コース名は必須です")
    private String courseName;

    @Schema(description = "コース受講開始日、自動で入力される", example = "2025-04-01T09:00:00", readOnly = true)
    private LocalDateTime startDate;

    @Schema(description = "コース受講終了日、開始日から自動でちょうど一年後となるよう制御している", example = "2026-04-01T09:00:00", readOnly = true)
    private LocalDateTime endDate;
}
