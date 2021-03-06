package com.pawntracker.repository;

import com.pawntracker.entity.PhoneNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {

    PhoneNumber getPhoneNumberById(Long id);

}
