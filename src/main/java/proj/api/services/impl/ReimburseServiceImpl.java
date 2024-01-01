package proj.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import proj.api.entities.Reimburse;
import proj.api.entities.Status;
import proj.api.entities.User;
import proj.api.exception.BadRequestException;
import proj.api.exception.ResourceNotFoundException;
import proj.api.exception.UserNotFoundException;
import proj.api.in.dto.ReimburseInDto;
import proj.api.out.dto.ReimburseOutDto;
import proj.api.repository.ReimburseRepository;
import proj.api.repository.UserRepository;
import proj.api.services.ReimburseService;
import proj.api.utils.Constants;
import proj.api.utils.ErrorConstants;

/**
 * Reimbursement service implementation class.
 * for defining the methods from Reimburse service.
 */
@Service
public class ReimburseServiceImpl implements ReimburseService {
  /**
   * The reimburseRepository object.
   */
  @Autowired
  private ReimburseRepository reimburseRepository;
  /**
  * The userRepository object.
  */
  @Autowired
  private UserRepository userRepository;
  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory
  .getLogger(CategoryServiceImpl.class);
  /**
   * Save claim method definition for saving the reimbursement.
   * @param reimburse for saving the reimburse.
   * @return ReimburseOutDto.
   */
  @Override
  public ReimburseOutDto saveReimburse(
  final @Valid ReimburseInDto reimburse, final String email) {
    logger.info("Reimburse/claim recieved is {} with the email {}",
    reimburse, email);
    if (reimburse.getExpenseType() == null) {
      throw new BadRequestException(
      ErrorConstants.EXPENSE_TYPE_REQUIRED);
    }
    if (reimburse.getAmount() == 0) {
      throw new BadRequestException(
      ErrorConstants.AMOUNT_TYPE_REQUIRED);
    }
    if (reimburse.getComment() == null) {
      throw new BadRequestException(
      ErrorConstants.COMMENT_TYPE_REQUIRED);
    }
    Reimburse tosaveReimburse = this.reimburseInDtoToReimburse(reimburse);
    User user = this.userRepository.findByEmail(email)
    .orElseThrow(() -> new UserNotFoundException(
    ErrorConstants.USER_NOT_FOUND));
    tosaveReimburse.setUser(user);
    tosaveReimburse.setStatus(Status.PENDING);
    Reimburse savedReimburse = this.reimburseRepository
    .save(tosaveReimburse);
    this.userRepository.save(user);
    ReimburseOutDto reimburseOutDto =
    this.reimburseToReimburseOutDto(savedReimburse);
    logger.info("Successfully saved the claim {} with the email {}",
    reimburse, email);
    return reimburseOutDto;
  }
  /**
  * Get reimbursements method definition.
  * @param email user email.
  * @return List of ReimburseOutDto.
  */
  @Override
  public List<ReimburseOutDto> getReimbursementsByUserEmail(
  final String email) {
    logger.info("Fetching the claims for the specific user with email - {}",
    email);
    User user = this.userRepository.findByEmail(email)
    .orElseThrow(() -> new UserNotFoundException(
    ErrorConstants.USER_NOT_FOUND));
    List<Reimburse> reimbursements = this.reimburseRepository
    .findByUserId(user.getUserId());
    System.out.println("The length of the reimbursements is "
    + reimbursements);
    List<ReimburseOutDto> reimburseOutDtoList = reimbursements.stream().map(
    reimburse -> this.reimburseToReimburseOutDto(reimburse))
    .collect(Collectors.toList());
    logger.info("Successfully fetched all the claims related email - {}",
    email);
    return reimburseOutDtoList;
  }
  /**
   * Reimburse get all method.
   *
   * @return List of reimbursements;
   */
  @Override
  public List<ReimburseOutDto> getAllReimbursementsByStatus(
  final String status) {
  logger.info("Fetching the claims with respective status - {}", status);
  List<Reimburse> reimbursementList = null;
    if (status.equals("Pending")) {
      reimbursementList = this.reimburseRepository
      .findByStatus(Status.PENDING);
    } else if (status.equals("Accepted")) {
      reimbursementList = this.reimburseRepository
      .findByStatus(Status.ACCEPTED);
    } else if (status.equals("Rejected")) {
      reimbursementList = this.reimburseRepository
      .findByStatus(Status.REJECTED);
    } else {
      throw new BadRequestException(ErrorConstants.INVALID_REQUEST);
    }
    List<ReimburseOutDto> reimburseOutDtoList = reimbursementList.stream().map(
    reimburse -> this.reimburseToReimburseOutDto(reimburse))
    .collect(Collectors.toList());
    logger.info("Successfully fetched all the claims with status - {}",
    status);
    return reimburseOutDtoList;
  }
  /**
   * Reimburse update status method.
   *
   * @param reimburseId Id of Claim.
   * @param comment Comment.
   * @return Message;
   */
  @Override
  public ReimburseOutDto updateReimburse(
  final int reimburseId, final String comment) {
    logger.info("Updating the claim with the id - {}", reimburseId);
    Reimburse reimburse = this.reimburseRepository.findById(reimburseId)
    .orElseThrow(() -> new ResourceNotFoundException("Claim",
    String.valueOf(reimburseId)));
    if (comment.equals(Constants.ACCEPTED)) {
      reimburse.setStatus(Status.ACCEPTED);
      reimburse.setStatusDescription(comment);
      Reimburse savedReimburse = this.reimburseRepository.save(reimburse);
      ReimburseOutDto finalreimburse =
      this.reimburseToReimburseOutDto(savedReimburse);
      return finalreimburse;
    } else {
      reimburse.setStatus(Status.REJECTED);
      reimburse.setStatusDescription(comment);
      Reimburse savedReimburse = this.reimburseRepository.save(reimburse);
      ReimburseOutDto finalreimburse =
      this.reimburseToReimburseOutDto(savedReimburse);
      logger.info("Updated the claim with the id - {} with the comment - {}",
      reimburseId, comment);
      return finalreimburse;
    }
  }
  /**
   * Get all reimbursements method.
   *
   * @return list of reimbursements;
   */
  @Override
  public List<ReimburseOutDto> getAllReimbursements() {
    logger.info("Fetching all the claims from the database for the Admin");
    List<Reimburse> reimbursementList = this.reimburseRepository.findAll();
    List<ReimburseOutDto> reimbursementOutDtoList = reimbursementList.stream()
    .map(reimburse -> this.reimburseToReimburseOutDto(reimburse))
    .collect(Collectors.toList());
    logger.info("Successfully fetched all the claims for Admin");
    return reimbursementOutDtoList;
  }
  /**
  * Get all reimbursements method.
  *
  * @param email User email.
  * @param status Claim status.
  * @return list of reimbursements;
  */
  @Override
  public List<ReimburseOutDto> getReimbursementsByUserEmailAndStatus(
  final String email, final String status) {
    logger.info("Fetching all the claims for {} with the status {}",
    email, status);
    List<Reimburse> reimbursementList = null;
    if (status.equals(Constants.PENDING)) {
      reimbursementList = this.reimburseRepository.findByStatusAndEmail(
      Status.PENDING, email);
    } else if (status.equals(Constants.ACCEPTED)) {
      reimbursementList = this.reimburseRepository.findByStatusAndEmail(
      Status.ACCEPTED, email);
    } else if (status.equals(Constants.REJECTED)) {
      reimbursementList = this.reimburseRepository.findByStatusAndEmail(
      Status.REJECTED, email);
    } else {
      throw new BadRequestException(ErrorConstants.INVALID_REQUEST);
    }
    List<ReimburseOutDto> reimbursementOutDtoList = reimbursementList
    .stream().map(reimburse -> this.reimburseToReimburseOutDto(reimburse))
    .collect(Collectors.toList());
    logger.info("Successfully fetched all the claims for {} with the status {}",
    email, status);
    return reimbursementOutDtoList;
  }
  /**
  * Get all reimbursements method.
  *
  * @param managerId Manager id.
  * @return list of reimbursements;
  */
  @Override
  public List<ReimburseOutDto> getReimbursementsByManagerId(
  final int managerId) {
    logger.info("Fetching the claims for manager with id - {} ", managerId);
    List<Reimburse> reimbursementList = this.reimburseRepository
    .findByManagerId(managerId);
    List<ReimburseOutDto> reimbursementOutDtoList = reimbursementList
    .stream().map(
    reimburse -> this.reimburseToReimburseOutDto(reimburse))
    .collect(Collectors.toList());
    logger.info("Successfully fetched the claims for manager with id - {} ",
    managerId);
    return reimbursementOutDtoList;
  }
  /**
   * Get all reimbursements method for managers by status.
   *
   * @param managerId Manager id.
   * @return list of reimbursements;
   */
  @Override
  public List<ReimburseOutDto> getAllReimbursementsByStatusForManager(
  final int managerId, final String status) {
    logger.info("Fetching the claims for manager with id -"
    + "{} and {} status",
    managerId, status);
    List<Reimburse> reimbursementList = new ArrayList<Reimburse>();
    if (status.equals(Constants.PENDING)) {
      reimbursementList = this.reimburseRepository.findByManagerIdAndStatus(
      managerId, Status.PENDING);
    } else if (status.equals(Constants.ACCEPTED)) {
      reimbursementList = this.reimburseRepository.findByManagerIdAndStatus(
      managerId, Status.ACCEPTED);
    } else if (status.equals(Constants.REJECTED)) {
      reimbursementList = this.reimburseRepository.findByManagerIdAndStatus(
      managerId, Status.REJECTED);
    }
    List<ReimburseOutDto> reimbursementOutDTOList = reimbursementList
    .stream().map(reimburse -> this.reimburseToReimburseOutDto(reimburse))
    .collect(Collectors.toList());
    logger.info("Successfully fetched all the claims for manager with id -"
    + "{} and {} status", managerId, status);
    return reimbursementOutDTOList;
  }
  /**
  * Reimburse model mapper.
  * @param reimburseInDto for getting the reimburseInDto.
  * @return reimburse;
  */
  public Reimburse reimburseInDtoToReimburse(
  final ReimburseInDto reimburseInDto) {
    Reimburse reimburse = new Reimburse();
    reimburse.setExpenseType(reimburseInDto.getExpenseType());
    reimburse.setAmount(reimburseInDto.getAmount());
    reimburse.setExpenseDate(reimburseInDto.getExpenseDate());
    reimburse.setDocumentUrl(reimburseInDto.getDocumentUrl());
    reimburse.setCurrency(reimburseInDto.getCurrency());
    reimburse.setComment(reimburseInDto.getComment());
    return reimburse;
  }
  /**
  * Reimburse model mapper.
  * @param reimburse for getting the reimburse.
  * @return reimburseoutdto;
  */
  public ReimburseOutDto reimburseToReimburseOutDto(
  final Reimburse reimburse) {
    ReimburseOutDto reimburseOutDto = new ReimburseOutDto();
    reimburseOutDto.setReimburseId(reimburse.getReimburseId());
    reimburseOutDto.setExpenseType(reimburse.getExpenseType());
    reimburseOutDto.setAmount(reimburse.getAmount());
    reimburseOutDto.setExpenseDate(reimburse.getExpenseDate());
    reimburseOutDto.setDocumentUrl(reimburse.getDocumentUrl());
    reimburseOutDto.setCurrency(reimburse.getCurrency());
    reimburseOutDto.setStatus(reimburse.getStatus());
    reimburseOutDto.setComment(reimburse.getComment());
    reimburseOutDto.setEmployeeName(reimburse.getUser().getName());
    reimburseOutDto.setStatusDescription(reimburse.getStatusDescription());
    return reimburseOutDto;
  }
}
