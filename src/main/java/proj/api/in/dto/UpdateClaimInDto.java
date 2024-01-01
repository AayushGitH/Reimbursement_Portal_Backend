package proj.api.in.dto;

/**
 * Update claim in DTO class.
 */
public class UpdateClaimInDto {
  /**
  * Claim Id field.
  */
  private int id;
  /**
  * Update comment field.
  */
  private String comment;
  /**
  * Getter of Id field.
  * @return id.
  */
  public int getId() {
    return id;
  }
  /**
  * Setter of Id field.
  * @param idValue Claim id.
  */
  public void setId(final int idValue) {
    this.id = idValue;
  }
  /**
  * Getter of Comment field.
  * @return id Claim id.
  */
  public String getComment() {
    return comment;
  }
  /**
  * Setter of Comment field.
  * @param commentValue Comment.
  */
  public void setComment(final String commentValue) {
    this.comment = commentValue;
  }
  /**
  * All argument constructor.
  * @param commentValue Comment.
  * @param idValue Claim id.
  */
  public UpdateClaimInDto(final int idValue, final String commentValue) {
    super();
    this.id = idValue;
    this.comment = commentValue;
  }
  /**
  * No argument constructor.
  */
  public UpdateClaimInDto() {
  }
}
