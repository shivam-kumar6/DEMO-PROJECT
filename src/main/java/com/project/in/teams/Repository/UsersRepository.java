package com.project.in.teams.Repository;

import com.project.in.teams.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
   public Users findByEmail(String name);

  public Users getById(Long id);

   public Users findFirstByEmail(String email);

   @Query("SELECT u.ut_fk FROM Users u where u.id=?1")
   public Long getUt_fkById(Long id);
}
