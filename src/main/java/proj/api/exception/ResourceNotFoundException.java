package proj.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resource not found exception class.
 */
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
  /**
  * Resource name.
  */
  private String resourceName;
  /**
   * Field name.
   */
  private String fieldName;
  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory
  .getLogger(GlobalExceptionHandler.class);

  /**
   * Custom ResourceNotFoundException constructor.
   * @param message for getting the message.
   */
  public ResourceNotFoundException(final String message) {
    super(message);
    logger.error("Resource not found exception caught !!");
  }

  /**
   * Custom ResourceNotFoundException constructor.
   * @param resourceNameValue for getting the resource.
   * @param firstNameValue for getting the error field.
   */
  public ResourceNotFoundException(
  final String resourceNameValue, final String firstNameValue) {
    super(String.format("%s not found with %s", resourceNameValue,
    firstNameValue));
    this.resourceName = resourceNameValue;
    this.fieldName = firstNameValue;
    logger.error("{} not found with {}", resourceName, fieldName);
  }
}
