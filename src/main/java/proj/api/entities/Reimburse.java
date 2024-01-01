package proj.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.Objects;

/**
* Entity class of Reimburse.
*/
@Entity
@Table(name = "reimburse")
public class Reimburse {
  /**
   * Reimburse id field to store the id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int reimburseId;
  /**
   * Reimburse expense type field to store the type of the expense.
   */
  @Column(length = 30, nullable = false)
  private String expenseType;
  /**
   * Reimburse amount field to store the expense amount.
   */
  @Column(nullable = false)
  private int amount;
  /**
   * Reimburse expense date field to store the date of the expenses.
   */
  @Temporal(TemporalType.DATE)
  private Date expenseDate;
  /**
   * Reimburse  document field to store the document of the expense.
   */
  private String documentUrl;
  /**
   * Reimburse currency type field.
   */
  @Column(length = 30)
  private String currency;
  /**
   * Reimburse comment type.
   */
  private String comment;
  /**
   * Reimburse field for tracking the status.
   */
  @Column
  private Status status;
  /**
   * Reimburse description about the status.
   */
  private String statusDescription;
  /**
   * User field to link the reimburse requests to the particular user.
   */
  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;
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
  * User getter.
  * @return user.
  */
  public User getUser() {
    return user;
  }
  /**
  * User setter.
  * @param value user.
  */
  public void setUser(final User value) {
    this.user = value;
  }
  /**
   * No argument constructor.
   */
  public Reimburse() {
  }
  /**
   * toString method.
   * @return reimburse object.
   */
  @Override
  public String toString() {
    return "Reimburse [reimburseId=" + reimburseId
    + ", expenseType=" + expenseType + ", amount="
    + amount + ", expenseDate=" + expenseDate
    + ", documentUrl=" + documentUrl + ", currency="
    + currency + ", comment=" + comment + ", status="
    + status + ", statusDescription="
    + statusDescription + ", user=" + user + "]";
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
   * @param userValue user object.
   */
  public Reimburse(final int reimburseIdValue,
    final String expenseTypeValue,
    final int amountValue,
    final Date expenseDateValue,
    final String documentUrlValue,
    final String currencyValue,
    final String commentValue,
    final Status statusValue,
    final String statusDescriptionValue,
    final User userValue) {
      super();
      this.reimburseId = reimburseIdValue;
      this.expenseType = expenseTypeValue;
      this.amount = amountValue;
      this.expenseDate = new Date(expenseDateValue.getTime());
      this.documentUrl = documentUrlValue;
      this.currency = currencyValue;
      this.comment = commentValue;
      this.status = statusValue;
      this.statusDescription = statusDescriptionValue;
      this.user = userValue;
  }
  /**
   * Hash Code method of reimburse entity.
   */
  @Override
  public int hashCode() {
    return Objects.hash(amount, comment, currency,
    documentUrl, expenseDate,
    expenseType, reimburseId,
    status, statusDescription, user);
  }
  /**
  * Reimburse object equals method.
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
    Reimburse other = (Reimburse) obj;
    return amount == other.amount && Objects.equals(comment,
    other.comment)
    && Objects.equals(currency, other.currency)
    && Objects.equals(documentUrl, other.documentUrl)
    && Objects.equals(expenseDate, other.expenseDate)
    && Objects.equals(expenseType, other.expenseType)
    && reimburseId == other.reimburseId && status == other.status
    && Objects.equals(statusDescription, other.statusDescription)
    && Objects.equals(user, other.user);
  }
}
