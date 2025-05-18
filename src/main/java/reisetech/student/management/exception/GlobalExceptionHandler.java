package reisetech.student.management.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import reisetech.student.management.data.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(IdNotFoundException ex,
            HttpServletRequest request) {
        logger.error(
                "リクエストで送られてきた、受講生Id'{}'がDBに存在しないため、IdNotFoundExceptionをスロー",
                ex.getId());
        ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getCode(),
                request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            ConstraintViolationException ex, HttpServletRequest request) {
        String message = "バリデーションエラー";
        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            message = v.getMessage();
            break;
        }
        logger.error("入力値エラー", ex);
        ErrorResponse error = new ErrorResponse(message, "BAD_REQUEST", request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDbException(DataAccessException ex,
            HttpServletRequest request) {
        String path = request.getRequestURI();
        logger.error("DataAccessException発生", ex);
        ErrorResponse error = new ErrorResponse("サーバー内部でのエラー発生",
                "INTERNAL_SERVER_ERROR", path);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<ErrorResponse> handleTransactionError(CannotCreateTransactionException ex,
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse("サーバー内部の予期しないエラー",
                "INTERNAL_SERVER_ERROR", request.getRequestURI());
        logger.error("InsertStudent,updateStudentメソッドのトランザクション失敗", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleFoundException(NoHandlerFoundException ex) {
        ErrorResponse error = new ErrorResponse("存在しないエンドポイントです", "NOT_FOUND",
                ex.getRequestURL());
        logger.error("存在しないエンドポイントへのアクセス", ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleArgumentException(IllegalArgumentException ex,
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "BAD_REQUEST",
                request.getRequestURI());
        logger.error("不正なリクエストが送られた:{}", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleReadableException(HttpMessageNotReadableException ex,
            HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse("リクエストボディが空、または不正です。",
                "BAD_REQUEST", request.getRequestURI());
        logger.error("リクエストボディの入力値の不正", ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        String messge = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResponse error = new ErrorResponse(messge, "BAD_REQUEST",
                request.getRequestURI());
        logger.error("バリデーション違反", ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse("サーバー内部の予期しないエラー",
                "INTERNAL_SERVER_ERROR", request.getRequestURI());
        logger.error("予期しない例外", ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
