package proj.api.services;

import java.util.List;

import proj.api.in.dto.ReimburseInDto;
import proj.api.out.dto.ReimburseOutDto;

/**
 * Reimburse service for declaring the reimbursement repository methods.
 */
public interface ReimburseService {
  /**
  * Save reimburse method declaration.
  *
  * @param reimburse ReimburseInDto type parameter.
  * @param email User email.
  * @return Reimburse.
  */
  ReimburseOutDto saveReimburse(ReimburseInDto reimburse, String email);
  /**
   * Get all reimbursements method declaration.
   *
   * @return List of reimbursements.
   */
  List<ReimburseOutDto> getAllReimbursements();
  /**
   * Get all reimbursements method declaration.
   *
   * @param status Status.
   * @return List of reimbursements.
   */
  List<ReimburseOutDto> getAllReimbursementsByStatus(String status);
  /**
   * Get all reimbursements method declaration.
   *
   * @param managerId Manager Id.
   * @param status Status field.
   * @return List of reimbursements.
   */
  List<ReimburseOutDto> getAllReimbursementsByStatusForManager(int managerId,
  String status);
  /**
   * Get reimbursements method declaration.
   *
   * @param email Email of particular person.
   * @return List of reimbursements.
   */
  List<ReimburseOutDto> getReimbursementsByUserEmail(String email);
  /**
   * Get reimbursements method declaration.
   *
   * @param managerId Manager id.
   * @return List of reimbursements.
   */
  List<ReimburseOutDto> getReimbursementsByManagerId(int managerId);
  /**
   * Get reimbursements method declaration.
   *
   * @param email Email of particular person.
   * @param status Status or claim.
   * @return List of reimbursements.
   */
  List<ReimburseOutDto> getReimbursementsByUserEmailAndStatus(String email,
  String status);
  /**
   * Update reimburse method declaration.
   *
   * @param reimburseId Reimburse Id.
   * @param comment Comment.
   * @return Message.
   */
   ReimburseOutDto updateReimburse(int reimburseId, String comment);
}
