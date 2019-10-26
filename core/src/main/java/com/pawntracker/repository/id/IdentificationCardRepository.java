package com.pawntracker.repository.id;

import com.pawntracker.entity.id.IdentificationCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentificationCardRepository extends JpaRepository<IdentificationCard, Long> {
}
