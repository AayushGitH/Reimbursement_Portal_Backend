package proj.api.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import proj.api.out.dto.ErrorResponse;

/**
 * Global exception handler to handler all type of handlers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
  * The logger object.
  */
  private final Logger logger = LoggerFactory
  .getLogger(GlobalExceptionHandler.class);

  /**
  * Method argument related validations handler.
  *
  * @param obj
  *          Method Argument Not Valid type parameter.
  * @return ResponseEntity(ErrorMap)
  */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> methodArgumentHandler(
  final MethodArgumentNotValidException obj) {
    logger.error("Method argument not valid"
    + "exception is caught !!");
    Map<String, String> errorMap =
    new ConcurrentHashMap<String, String>();
    obj.getBindingResult().getAllErrors().forEach(error -> {
      String errorField = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errorMap.put(errorField, errorMessage);
    });

    logger.error("Error is {}", errorMap);
    List<String> list = new ArrayList<String>();
    obj.getBindingResult().getAllErrors().forEach(error -> {
      String errorMessage = error.getDefaultMessage();
      list.add(errorMessage);
    });
    ErrorResponse errorResponse = new ErrorResponse(list,
    HttpStatus.BAD_REQUEST.toString(), Instant.now());
    return new ResponseEntity<ErrorResponse>(errorResponse,
    HttpStatus.BAD_REQUEST);
  }
  /**
  * Resource Not Found Exception handler.
  *
  * @param obj
  *          ResourceNotFoundException type parameter.
  * @return ResponseEntity(Message)
  */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> resourceNotFoundHandler(
  final ResourceNotFoundException obj) {

    logger.error("Resource Not Found exception is caught !!");

    List<String> list = new ArrayList<String>();
    list.add(obj.getMessage());
    ErrorResponse errorResponse = new ErrorResponse(list,
    HttpStatus.NOT_FOUND.toString(), Instant.now());
    return new ResponseEntity<ErrorResponse>(errorResponse,
    HttpStatus.NOT_FOUND);
  }
  /**
   * Data integrity violation Exception handler.
   *
   * @param obj
   *          DataIntegrityViolationException type parameter.
   * @return ResponseEntity(Message)
   */
   @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity<ErrorResponse> dateIntegrityExceptionHandler(
   final DataIntegrityViolationException obj) {

     logger.error("Data integrity violation exception is caught !!");

     List<String> list = new ArrayList<String>();
     list.add(obj.getMessage());
     ErrorResponse errorResponse = new ErrorResponse(list,
     HttpStatus.CONFLICT.toString(), Instant.now());
     return new ResponseEntity<ErrorResponse>(errorResponse,
     HttpStatus.CONFLICT);
   }
  /**
   * Bad Request Exception handler.
   *
   * @param obj
   *          BadRequestException type parameter.
   * @return ResponseEntity(Message)
   */
   @ExceptionHandler(BadRequestException.class)
   public ResponseEntity<ErrorResponse> badRequestExceptionHandler(
   final BadRequestException obj) {

     logger.error("Bad request exception is caught !!");

     List<String> list = new ArrayList<String>();
     list.add(obj.getMessage());
     ErrorResponse errorResponse = new ErrorResponse(list,
     HttpStatus.BAD_REQUEST.toString(), Instant.now());
     System.out.println(errorResponse);
     return new ResponseEntity<ErrorResponse>(errorResponse,
     HttpStatus.BAD_REQUEST);
   }
   /**
    *  User not found Exception handler.
    *
    * @param obj
    *          UserNotFoundException type parameter.
    * @return ResponseEntity(Message)
    */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(
    final UserNotFoundException obj) {

      logger.error("User Not Found exception is caught !!");

      List<String> list = new ArrayList<String>();
      list.add(obj.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(list,
      HttpStatus.NOT_FOUND.toString(), Instant.now());
      System.out.println(errorResponse);
      return new ResponseEntity<ErrorResponse>(errorResponse,
      HttpStatus.NOT_FOUND);
    }
    /**
     *  Invalid credential Exception handler.
     *
     * @param obj
     *          InvalidCredentialException type parameter.
     * @return ResponseEntity(Message)
     */
     @ExceptionHandler(InvalidCredentialException.class)
     public ResponseEntity<ErrorResponse> invalidCredentialsExceptionHandler(
     final InvalidCredentialException obj) {

       logger.error("User Not Found exception is caught !!");

       List<String> list = new ArrayList<String>();
       list.add(obj.getMessage());
       ErrorResponse errorResponse = new ErrorResponse(list,
       HttpStatus.UNAUTHORIZED.toString(), Instant.now());
       System.out.println(errorResponse);
       return new ResponseEntity<ErrorResponse>(errorResponse,
       HttpStatus.UNAUTHORIZED);
     }
     /**
      *  Already Exists Exception handler.
      *
      * @param obj
      *          AlreadyExistException type parameter.
      * @return ResponseEntity(Message)
      */
      @ExceptionHandler(AlreadyExistException.class)
      public ResponseEntity<ErrorResponse> alreadyExistsExceptionHandler(
      final AlreadyExistException obj) {

        logger.error("Already exists exception is caught !!");

        List<String> list = new ArrayList<String>();
        list.add(obj.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(list,
        HttpStatus.CONFLICT.toString(), Instant.now());
        return new ResponseEntity<ErrorResponse>(errorResponse,
        HttpStatus.CONFLICT);
      }
}
