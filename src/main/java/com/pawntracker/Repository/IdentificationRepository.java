package com.pawntracker.Repository;

import com.pawntracker.Entity.ID.Identification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IdentificationRepository extends CrudRepository<Identification, Long> {
    Identification getIdentificationById(Long id);
}
