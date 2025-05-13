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
        responseCode = "500",
        description = "サーバー内部エラー。ErrorResponseオブジェクトが返されます",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class),
                examples =
                @ExampleObject(
                        name = "ServerError",
                        summary = "予期しないサーバーエラーが発生した場合",
                        value = "{\"message\": \"サーバー内部でのエラー発生\", \"code\": \"INTERNAL_SERVER_ERROR\", \"timestamp\": \"YYYY-MM-DDTHH:mm:ss\",\"path\": \"リクエストされたURLが入ります\"}"
                )
        )
)

public @interface ServerErrorResponse {

}
