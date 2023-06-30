package com.hcc.repositories;

import com.hcc.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
//    @Query("SELECT * FROM assignments a where a.user_id = :user_id")
//    List<Assignment> findByUserId(Long user_id);
    Assignment save(Assignment assignment);
    List<Assignment> findByUserId(Long userId);

}
