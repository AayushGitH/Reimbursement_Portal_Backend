package proj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import proj.api.entities.Reimburse;
import proj.api.entities.Status;


/**
 * Reimburse repository calling the data from the database.
 */
public interface ReimburseRepository extends JpaRepository<Reimburse, Integer> {
  /**
  * Reimbursements list.
  * @param userId User id.
  * @return List of reimbursements.
  */
  @Query("select r from Reimburse r where r.user.userId=:userId")
  List<Reimburse> findByUserId(@Param("userId") int userId);
  /**
   * Reimbursements list.
   * @param managerId Manager id.
   * @return List of reimbursements.
   */
   @Query("select r from Reimburse r where r.user.managerId=:managerId")
   List<Reimburse> findByManagerId(@Param("managerId") int managerId);
   /**
    * Reimbursements list.
    * @param managerId Manager id.
    * @param status Reimburse status.
    * @return List of reimbursements.
    */
    @Query("select r from Reimburse r where"
    + " r.user.managerId=:managerId and r.status=:status")
    List<Reimburse> findByManagerIdAndStatus(@Param("managerId") int managerId,
    @Param("status") Status status);
  /**
   * Reimbursements list.
   * @param status Reimburse status.
   * @param email Users email
   * @return List of reimbursements.
   */
   @Query("select r from Reimburse r where r.user.email=:email and "
   + "r.status=:status")
   List<Reimburse> findByStatusAndEmail(@Param("status") Status status,
   @Param("email") String email);
  /**
  * Reimbursements list.
  * @param status Status of claims.
  * @return List of reimbursements.
  */
  List<Reimburse> findByStatus(Status status);
}
