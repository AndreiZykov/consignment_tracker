package com.pawntracker.service;


import com.pawntracker.entity.Address;
import com.pawntracker.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address saveOrUpdate(Address address) {
        return addressRepository.save(address);
    }

    public Address getAddressById(Long id) {
        return addressRepository.getAddressById(id);
    }

    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }

 }
