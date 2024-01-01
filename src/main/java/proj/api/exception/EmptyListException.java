package proj.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Already exists exception class.
 */
@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException {
  /**
  * Logger instance for logging.
  */
  private final Logger logger = LoggerFactory
  .getLogger(GlobalExceptionHandler.class);
  /**
  * Constructor.
  */
  public EmptyListException() {
    super();
    logger.error("Empty list exception caught !!");
  }
  /**
  * Constructor with message.
  * @param message Message.
  */
  public EmptyListException(final String message) {
    super(message);
    logger.error("Empty list exception caught !!");
  }
}
