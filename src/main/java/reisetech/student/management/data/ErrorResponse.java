package reisetech.student.management.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class ErrorResponse {

    @Schema(description = "エラーメッセージ", readOnly = true)
    private String message;

    @Schema(description = "ステータスコードに対応するメッセージ", readOnly = true)
    private String code;

    @Schema(description = "エラーが発生した日時", example = "2025-05-08T23:04:09", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Tokyo")
    private LocalDateTime timestamp;

    @Schema(description = "リクエストされたURL", example = "/studentDetail/9999", readOnly = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String message, String code, String path) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }
}
