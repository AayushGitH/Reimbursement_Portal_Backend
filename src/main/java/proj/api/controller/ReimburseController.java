package proj.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import proj.api.in.dto.ReimburseInDto;
import proj.api.in.dto.UpdateClaimInDto;
import proj.api.out.dto.ReimburseOutDto;
import proj.api.services.ReimburseService;
import proj.api.utils.Constants;

/**
* Controller for firing the url's related Reimbursements.
*/
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.CLAIM_BASE_URL)
public class ReimburseController {

  /**
  * The reimburseService object.
  */
  @Autowired
  private ReimburseService reimburseService;
  /**
   * The objectMapper object.
   */
  @Autowired
  private ObjectMapper objectMapper;
  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory
  .getLogger(CategoryController.class);
  /**
   * Save the category method.
   *
   * @param claim for saving reimburse.
   * @param file for saving file.
   * @param email for getting user.
   * @return ResponseEntity(String)
   * @throws IOException
   */
  @PostMapping(value = "/create/{email}",
  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<ReimburseOutDto> saveClaim(
  final @RequestParam("claim") String claim,
  final @RequestParam("file") MultipartFile file,
  final @PathVariable("email") String email) throws IOException {
  logger.info("Entered the saving reimburse method with the given claim"
  + "- {}", claim);
  ReimburseInDto reimbursement = this.objectMapper
  .readValue(claim, ReimburseInDto.class);
  logger.info("The file given is {}", file.getOriginalFilename());
  System.out.println("The email came is " + email);
  String uploadDir = Constants.UPLOAD_DIR;
  String fileName = System.currentTimeMillis() + "_"
  + file.getOriginalFilename();
  Files.copy(file.getInputStream(), Paths.get(uploadDir
  + File.separator + fileName),
  StandardCopyOption.REPLACE_EXISTING);
  reimbursement.setDocumentUrl(fileName);
  ReimburseOutDto reimburseOutDto = this.reimburseService
  .saveReimburse(reimbursement, email);
  logger.info("Successfully added the claim...");
  return ResponseEntity.status(HttpStatus.CREATED)
  .body(reimburseOutDto);
  }
  /**
   * Get the claims method. [EMPLOYEE, MANAGER]
   *
   * @param email User email.
   * @return ResponseEntity(Reimbursements).
   */
  @GetMapping("/viewClaims/{email}")
  public ResponseEntity<List<ReimburseOutDto>> getClaim(
  final @PathVariable("email") String email) {
    logger.info("View all claims for the specific user method"
    + " is running...");
    List<ReimburseOutDto> reimbursementOutDTOList = this.reimburseService
    .getReimbursementsByUserEmail(email);
    logger.info("Successfully fetched the claims for {} ", email);
    return ResponseEntity.status(HttpStatus.OK)
    .body(reimbursementOutDTOList);
  }
  /**
   * Get all claims by status method. [ADMIN]
   *
   * @param status Status field.
   * @return ResponseEntity(Reimbursements).
   */
  @GetMapping("/getClaimsByStatus/{status}")
  public ResponseEntity<List<ReimburseOutDto>> getAllClaimsByStatus(
  final @PathVariable("status") String status) {
    logger.info("View all claims according to status is running...");
    List<ReimburseOutDto> reimbursementOutDTOList = this.reimburseService
    .getAllReimbursementsByStatus(status);
    logger.info("Successfully fetched all the claims regarding the status"
    + "- {} ", status);
    return ResponseEntity.status(HttpStatus.OK)
    .body(reimbursementOutDTOList);
  }
  /**
   * Get all claims by status method. [MANAGER]
   *
   * @param managerId Manger id.
   * @return ResponseEntity(Reimbursements).
   */
  @GetMapping("/getClaimsManager/{managerId}")
  public ResponseEntity<List<ReimburseOutDto>> getAllClaimsByManagerId(
  final @PathVariable("managerId") int managerId) {
    logger.info("Entered the claims method for the specific manager with"
    + "the id ", managerId);
    List<ReimburseOutDto> reimbursementOutDTOList = this.reimburseService
    .getReimbursementsByManagerId(managerId);
    logger.info("Fetched all claims regarding the managerid - {}", managerId);
    return ResponseEntity.status(HttpStatus.OK)
    .body(reimbursementOutDTOList);
  }
  /**
   * Get all claims by status method. [MANAGER]
   *
   * @param managerId Manger id.
   * @param status Status of claims.
   * @return ResponseEntity(Reimbursements).
   */
  @GetMapping("/getClaimsManager/{managerId}/{status}")
  public ResponseEntity<List<ReimburseOutDto>> getClaimsByManagerIdAndStatus(
  final @PathVariable("managerId") int managerId,
  final @PathVariable("status") String status) {
    logger.info("Claims is fetching relating {} manager id and"
    + "the status {} ", managerId, status);
    List<ReimburseOutDto> reimbursementOutDTOList = this.reimburseService
    .getAllReimbursementsByStatusForManager(managerId, status);
    logger.info("Fetched all the claims regarding {} manager id and {} ",
    managerId, status);
    return ResponseEntity.status(HttpStatus.OK)
    .body(reimbursementOutDTOList);
  }
  /**
   * Update claim method.
   *
   * @param updateClaimInDto Update claim object.
   * @return Message.
   */
  @PutMapping(value = "/updateClaim")
  public ResponseEntity<ReimburseOutDto> updateClaim(
  final @RequestBody UpdateClaimInDto updateClaimInDto) {
    logger.info("Updating claim with the claim id - {}",
    updateClaimInDto.getId());
    ReimburseOutDto updatedReimburse = this.reimburseService
    .updateReimburse(updateClaimInDto.getId(), updateClaimInDto.getComment());
    logger.info("Successfully updated the claim with comment - {}",
    updateClaimInDto.getComment());
    return ResponseEntity.status(HttpStatus.OK)
    .body(updatedReimburse);
  }
  /**
   * Get all claims handler. [ADMIN]
   *
   * @return reimbursement list.
   */
  @GetMapping(value = "/getClaims")
  public ResponseEntity<List<ReimburseOutDto>> getAllClaims() {
    logger.info("Entered fetching all the claims from the"
    + "records method...");
    List<ReimburseOutDto> reimbursementOutDTOList =
    this.reimburseService.getAllReimbursements();
    logger.info("Successfully fetched all the claims for Admin...");
    return ResponseEntity.status(HttpStatus.OK)
    .body(reimbursementOutDTOList);
  }
  /**
   * Get all claims handler for specific user. [EMPLOYEE, MANAGER]
   *
   * @param email User email.
   * @param status Reimburse status.
   * @return reimbursement list.
   */
  @GetMapping(value = "/viewClaims/{email}/{status}")
  public ResponseEntity<List<ReimburseOutDto>> getSpecificClaims(
    final @PathVariable("email") String email,
    final @PathVariable("status") String status) {
      logger.info("Fetching all the claims for {} with status {}",
      email, status);
      List<ReimburseOutDto> reimbursementOutDTOList = this.reimburseService.
      getReimbursementsByUserEmailAndStatus(email, status);
      logger.info("Successfully fetched all the claims for {} with status {}",
      email, status);
      return ResponseEntity.status(HttpStatus.OK)
      .body(reimbursementOutDTOList);
  }
  /**
   * Get image handler. [EMPLOYEE, MANAGER]
   *
   * @param img Image.
   * @return reimbursement list.
   */
  @GetMapping(value = "/image/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<Map<String, byte[]>> getImage(
  final @PathVariable("img") String img) throws IOException {
    ClassPathResource imgFile = new ClassPathResource("images/" + img);
    byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
    Map<String, byte[]> map = new HashMap<String, byte[]>();
    map.put("image", bytes);
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
    .body(map);
  }
}
