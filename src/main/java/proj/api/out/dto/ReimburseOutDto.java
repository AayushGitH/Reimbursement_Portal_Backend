package proj.api.out.dto;

import java.util.Date;
import java.util.Objects;


import proj.api.entities.Status;

/**
* DTO class of Reimburse.
*/
public class ReimburseOutDto {

  /**
  * Reimburse id field to store the id.
  */
  private int reimburseId;
  /**
   * Expense type field.
   */
  private String expenseType;
  /**
   * Amount field to store the amount.
   */
  private int amount;
  /**
   * Expense date field to store the date of the reimbursement.
   */
  private Date expenseDate;
  /**
   * Reimbursement document field.
   */
  private String documentUrl;
  /**
   * Currency field to store expense currency type.
   */
  private String currency;
  /**
   * Status field.
   */
  private Status status;
  /**
   * Reimburse comment type.
   */
  private String comment;
  /**
   * Employee name.
   */
  private String employeeName;
  /**
   * Status description.
   */
  private String statusDescription;
  /**
   * Reimburse id getter.
   * @return reimburse id.
   */
  public int getReimburseId() {
    return reimburseId;
  }
  /**
  * Reimburse id setter.
  * @param value reimburse id.
  */
  public void setReimburseId(final int value) {
    this.reimburseId = value;
  }
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
  * Status getter.
  * @return status.
  */
  public Status getStatus() {
    return status;
  }
  /**
  * Status setter.
  * @param value status.
  */
  public void setStatus(final Status value) {
    this.status = value;
  }
  /**
  * Status description getter.
  * @return status description.
  */
  public String getStatusDescription() {
    return statusDescription;
  }
  /**
  * Status description setter.
  * @param value status description.
  */
  public void setStatusDescription(final String value) {
    this.statusDescription = value;
  }
  /**
   * Employee name getter.
   * @return employee name.
   */
  public String getEmployeeName() {
    return employeeName;
  }
  /**
  * Employee name setter.
  * @param value employee name.
  */
  public void setEmployeeName(final String value) {
    this.employeeName = value;
  }
  /**
   * No argument constructor.
   */
  public ReimburseOutDto() {
  }
  /**
   * All arguments constructor.
   * @param reimburseIdValue id field.
   * @param expenseTypeValue Expense type field.
   * @param amountValue Amount field.
   * @param expenseDateValue Date field.
   * @param documentUrlValue Document name field.
   * @param currencyValue Currency field.
   * @param commentValue Comment field.
   * @param statusValue Status field.
   * @param statusDescriptionValue Status description field.
   * @param employeeNameValue employee name.
   */
  public ReimburseOutDto(final int reimburseIdValue,
    final String expenseTypeValue,
    final int amountValue,
    final Date expenseDateValue,
    final String documentUrlValue,
    final String currencyValue,
    final Status statusValue,
    final String commentValue,
    final String employeeNameValue,
    final String statusDescriptionValue) {
      super();
      this.reimburseId = reimburseIdValue;
      this.expenseType = expenseTypeValue;
      this.amount = amountValue;
      this.expenseDate = new Date(expenseDateValue.getTime());
      this.documentUrl = documentUrlValue;
      this.currency = currencyValue;
      this.status = statusValue;
      this.comment = commentValue;
      this.employeeName = employeeNameValue;
      this.statusDescription = statusDescriptionValue;
  }
  /**
   * Reimbursement Out DTO hash code method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(amount, comment, currency, documentUrl,
    employeeName, expenseDate, expenseType, reimburseId,
    status, statusDescription);
  }
  /**
  * Reimbursement Out DTO equals method.
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
    ReimburseOutDto other = (ReimburseOutDto) obj;
    return amount == other.amount && Objects.equals(comment,
    other.comment)
    && Objects.equals(currency, other.currency)
    && Objects.equals(documentUrl, other.documentUrl)
    && Objects.equals(employeeName, other.employeeName)
    && Objects.equals(expenseDate, other.expenseDate)
    && Objects.equals(expenseType, other.expenseType)
    && reimburseId == other.reimburseId && status == other.status
    && Objects.equals(statusDescription, other.statusDescription);
  }
  /**
  * toString method.
  * @return reimburseOutDTO object.
  */
  @Override
  public String toString() {
    return "ReimburseOutDto [reimburseId=" + reimburseId
    + ", expenseType=" + expenseType + ", amount="
    + amount + ", expenseDate=" + expenseDate
    + ", documentUrl=" + documentUrl + ", currency="
    + currency + ", status=" + status + ", comment="
    + comment + ", employeeName=" + employeeName
    + ", statusDescription=" + statusDescription + "]";
  }
}
