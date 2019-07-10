package com.pawntracker.Repository;

import com.pawntracker.Entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository  extends CrudRepository<Address, Long> {
    Address getAddressById(Long id);
}
