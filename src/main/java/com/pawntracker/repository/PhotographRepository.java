package com.pawntracker.repository;

import com.pawntracker.entity.Photograph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographRepository extends JpaRepository<Photograph, Long> {

}
