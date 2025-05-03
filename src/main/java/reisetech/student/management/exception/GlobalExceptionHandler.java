package reisetech.student.management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(StudentSearchDbException.class)
    public ResponseEntity<String> handleSearchDbError(StudentSearchDbException ex) {
        logger.error(
                "StudentServiceクラスのsearchStudentListメソッド実行時にDataAccessException発生",
                ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentInsertDbException.class)
    public ResponseEntity<String> handleInsertDbError(StudentInsertDbException ex) {
        logger.error("StudentServiceクラスのinsertStudentメソッド実行時にDataAccessException発生",
                ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(IdNotFoundException ex) {
        logger.error(
                "リクエストで送られてきた、受講生Id'{}'がDBに存在しないため、IdNotFoundExceptionをスロー",
                ex.getId());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OneStudentSearchDbException.class)
    public ResponseEntity<String> handleSearchOneError(OneStudentSearchDbException ex) {
        logger.error("StudentServiceクラスのfindStudentメソッド実行時にDataAccessException発生",
                ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentUpdateDbException.class)
    public ResponseEntity<String> handleUpdateDbError(StudentUpdateDbException ex) {
        logger.error(
                "StudentServiceクラスのupdateStudentDetailメソッド実行中にDataAccessException発生",
                ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<String> handleTransactionError(CannotCreateTransactionException ex) {
        logger.error("InsertStudent,updateStudentメソッドのトランザクション失敗", ex);
        return new ResponseEntity<>("トランザクション失敗", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
