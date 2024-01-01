package proj.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Already exists exception class.
 */
public class NullPointerException extends RuntimeException {
  /**
  * Logger instance for logging.
  */
  private final Logger logger = LoggerFactory
  .getLogger(GlobalExceptionHandler.class);
  /**
  * Constructor.
  */
  public NullPointerException() {
    super();
  }
  /**
  * Constructor with message.
  * @param message Message.
  */
  public NullPointerException(final String message) {
    super(message);
    logger.error("Null Pointer exception caught !!");
  }
}
