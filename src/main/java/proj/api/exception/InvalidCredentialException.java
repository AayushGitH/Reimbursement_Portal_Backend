package proj.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Invalid credential exception class.
 */
@SuppressWarnings("serial")
public class InvalidCredentialException extends RuntimeException {
  /**
  * The logger object.
  */
  private final Logger logger = LoggerFactory
  .getLogger(GlobalExceptionHandler.class);
  /**
  * Custom InvalidCredentialException constructor.
  */
  public InvalidCredentialException() {
    super();
    logger.error("Invalid credential exception caught !!");
  }
  /**
  * Custom InvalidCredentialException constructor with message argument.
  * @param message for getting the error field.
  */
  public InvalidCredentialException(final String message) {
    super(message);
    logger.error("Invalid credential exception caught with the message - {}",
    message);
  }
}
