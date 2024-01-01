package proj.api.in.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* Reimbursement in DTO of Reimburse.
*/
public class ReimburseInDto {
  /**
  * Reimbursement expense type field to store the type of the expense.
  */
  @NotBlank(message = "Expense type is required")
  private String expenseType;
  /**
   * Reimbursement amount field to store the expense amount.
   */
  @NotNull(message = "Amount is required")
  private Integer amount;
  /**
   * Reimbursement expense date field to store the date of the expenses.
   */
  @NotNull(message = "Expense date is required")
  private Date expenseDate;
  /**
   * Reimbursement  document field to store the document of the expense.
   */
  private String documentUrl;
  /**
   * Reimbursement currency type field.
   */
  private String currency;
  /**
   * Reimbursement comment type.
   */
  @NotBlank(message = "Comment is required")
  private String comment;
  /**
   * Expense type getter.
   * @return expense type.
   */
   public String getExpenseType() {
     return expenseType;
   }
   /**
   * Expense type setter.
   * @param value expense type.
   */
   public void setExpenseType(final String value) {
     this.expenseType = value;
   }
   /**
   * Amount getter.
   * @return expense amount.
   */
   public int getAmount() {
     return amount;
   }
   /**
   * Amount setter.
   * @param value expense amount.
   */
   public void setAmount(final int value) {
     this.amount = value;
   }
   /**
   * Expense date getter.
   * @return expense date.
   */
   public Date getExpenseDate() {
    Date date = new Date(expenseDate.getTime());
    return date;
   }
   /**
   * Expense date setter.
   * @param value expense date.
   */
   public void setExpenseDate(final Date value) {
    Date date = new Date(value.getTime());
    this.expenseDate = date;
   }
   /**
   * Document URL getter.
   * @return expense date.
   */
   public String getDocumentUrl() {
     return documentUrl;
   }
   /**
   * Document URL setter.
   * @param value document URL.
   */
   public void setDocumentUrl(final String value) {
     this.documentUrl = value;
   }
   /**
   * Currency getter.
   * @return expense currency.
   */
   public String getCurrency() {
     return currency;
   }
   /**
   * Currency setter.
   * @param value expense currency.
   */
   public void setCurrency(final String value) {
     this.currency = value;
   }
   /**
   * Comment getter.
   * @return comment.
   */
   public String getComment() {
     return comment;
   }
   /**
   * Comment setter.
   * @param value comment.
   */
   public void setComment(final String value) {
     this.comment = value;
   }
   /**
    * All argument constructor.
    */
  public ReimburseInDto() {
  }
  /**
  * All arguments constructor.
  * @param expenseTypeValue Expense type field.
  * @param amountValue Amount field.
  * @param expenseDateValue Date field.
  * @param documentUrlValue Document name field.
  * @param currencyValue Currency field.
  * @param commentValue Comment field.
  */
  public ReimburseInDto(
    final String expenseTypeValue,
    final int amountValue,
    final Date expenseDateValue,
    final String documentUrlValue,
    final String currencyValue,
    final String commentValue) {
      super();
      this.expenseType = expenseTypeValue;
      this.amount = amountValue;
      this.expenseDate = new Date(expenseDateValue.getTime());
      this.documentUrl = documentUrlValue;
      this.currency = currencyValue;
      this.comment = commentValue;
  }
  /**
   * Reimburse In DTO hash code method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(amount, comment, currency,
      documentUrl, expenseDate,
      expenseType);
  }
  /**
  * Department In DTO equals method.
  * @param obj object.
  */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    ReimburseInDto other = (ReimburseInDto) obj;
    return Objects.equals(amount, other.amount) && Objects.equals(comment,
    other.comment)
    && Objects.equals(currency, other.currency)
    && Objects.equals(documentUrl, other.documentUrl)
    && Objects.equals(expenseDate, other.expenseDate)
    && Objects.equals(expenseType, other.expenseType);
  }
  /**
  * toString method.
  * @return reimburseInDTO object.
  */
  @Override
  public String toString() {
    return "ReimburseInDto [expenseType=" + expenseType
    + ", amount=" + amount + ", expenseDate="
    + expenseDate + ", documentUrl=" + documentUrl
    + ", currency=" + currency + ", comment="
    + comment + "]";
  }
}
