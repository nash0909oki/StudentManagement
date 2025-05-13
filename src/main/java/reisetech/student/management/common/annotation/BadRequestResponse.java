package reisetech.student.management.common.annotation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import reisetech.student.management.data.ErrorResponse;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

@ApiResponse(
        responseCode = "400",
        description = "リクエストが不正（例：IDの形式が無効）。ErrorResponseオブジェクトが返されます",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class),
                examples = @ExampleObject(
                        name = "BadRequest",
                        summary = "IDが不正な形式で入力された場合",
                        value = "{\"message\": \"IDは1～3桁の半角数字で入力してください\", \"code\": \"BAD_REQUEST\", \"timestamp\": \"YYYY-MM-DDTHH:mm:ss\",\"path\": \"リクエストされたURLが入ります\"}"
                )
        )
)
public @interface BadRequestResponse {

}