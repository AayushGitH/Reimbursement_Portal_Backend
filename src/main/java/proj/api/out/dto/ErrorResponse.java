package proj.api.out.dto;

import java.time.Instant;
import java.util.List;

/**
* Error response class.
*/
public class ErrorResponse {
  /**
  * Error message.
  */
  private List<String> errorMessage;
  /**
  * Status field.
  */
  private String status;
  /**
  * Timestamp.
  */
  private Instant timestamp;
  /**
  * No argument constructor.
  */
  public ErrorResponse() {
  }
  /**
  * All argument constructor.
  * @param errorMessageValue List of error messages.
  * @param statusValue Status.
  * @param timestampValue TimeStamp.
  */
  public ErrorResponse(final List<String> errorMessageValue,
  final String statusValue,
  final Instant timestampValue) {
    this.errorMessage = errorMessageValue;
    this.status = statusValue;
    this.timestamp = timestampValue;
  }
  /**
  * Getter of messages.
  * @return List of messages.
  */
  public List<String> getErrorMessage() {
    return errorMessage;
  }
  /**
  * Setter of messages.
  * @param errorMessageValue List of error messages.
  */
  public void setErrorMessage(final List<String> errorMessageValue) {
    this.errorMessage = errorMessageValue;
  }
  /**
  * Getter of status.
  * @return Error status.
  */
  public String getStatus() {
    return status;
  }
  /**
  * Setter of status.
  * @param statusValue Status.
  */
  public void setStatus(final String statusValue) {
    this.status = statusValue;
  }
  /**
  * Getter of timeStamp.
  * @return timestampValue.
  */
  public Instant getTimestamp() {
    return timestamp;
  }
  /**
  * Setter of timeStamp.
  * @param timestampValue TimeStamp.
  */
  public void setTimestamp(final Instant timestampValue) {
    this.timestamp = timestampValue;
  }
}
