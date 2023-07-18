package com.hcc.repositories;

import com.hcc.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
    List<Career> findAll();

    @Override
    Optional<Career> findById(Long id);
}
