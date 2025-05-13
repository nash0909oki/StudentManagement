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
@ApiResponse(responseCode = "404", description = "リソースが見つからない。ErrorResponseオブジェクトが返されます",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class),
                examples =
                @ExampleObject(
                        name = "ID_NOT_FOUND",
                        summary = "受講生IDが見つからない場合",
                        value = "{\"message\": \"受講生IDが見つかりません\", \"code\": \"ID_NOT_FOUND\",\"timestamp\": \"YYYY-MM-DDTHH:mm:ss\",\"path\": \"リクエストされたURLが入ります\"}"
                )
        )
)

public @interface IdNotFoundResponse {

}
