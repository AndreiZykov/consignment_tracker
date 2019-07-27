package com.pawntracker.repository.id;


import com.pawntracker.entity.id.DriversLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversLicenseRepository extends JpaRepository<DriversLicense, Long> {
}
