package proj.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Already exists exception class.
 */
@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {
  /**
  * The logger object.
  */
  private final Logger logger = LoggerFactory
  .getLogger(GlobalExceptionHandler.class);
  /**
  * Constructor.
  */
  public BadRequestException() {
    super();
  }
  /**
  * Constructor with message.
  * @param message for getting the message.
  */
  public BadRequestException(final String message) {
    super(message);
    logger.error("Bad request exception caught !!");
  }
}
