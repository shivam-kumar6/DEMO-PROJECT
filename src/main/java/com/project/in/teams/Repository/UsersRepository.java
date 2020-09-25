package com.project.in.teams.Repository;

import com.project.in.teams.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.event.ListDataEvent;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
   public Users findByEmail(String name);

  public Users getById(Long id);

   public Users findFirstByEmail(String email);

   @Query("SELECT u.ut_fk FROM Users u where u.id=?1")
   public Long getUt_fkById(Long id);

   @Query("SELECT U FROM Users U WHERE CONCAT(U.first_name,' ',U.last_name,' ',U.phone_no,' ',U.email) LIKE %?1%")
    public List<Users> advancedSearch(String keyword);

   @Query("SELECT U FROM Users U WHERE U.first_name LIKE %?1%")
   public List<Users> searchFirstName(String keyword);

    @Query("SELECT U FROM Users U WHERE U.last_name LIKE %?1%")
    public List<Users> searchLastName(String keyword);

    @Query("SELECT U FROM Users U WHERE U.email LIKE %?1%")
    public List<Users> searchEmail(String keyword);

    @Query("SELECT U FROM Users U WHERE U.gender LIKE %?1%")
    public List<Users> searchGender(String keyword);

    @Query("SELECT U FROM Users U WHERE U.designation LIKE %?1%")
    public List<Users> searchDesignation(String keyword);

    @Query("SELECT U FROM Users U WHERE U.phone_no LIKE %?1%")
    public List<Users> searchPhone(String keyword);
}
