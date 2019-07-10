package com.pawntracker.service;

import com.pawntracker.entity.PhoneNumber;
import com.pawntracker.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public PhoneNumber saveOrUpdate(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    public PhoneNumber getPhoneNumberById(Long id) {
        return phoneNumberRepository.getPhoneNumberById(id);
    }

    public void deletePhoneNumber(PhoneNumber phoneNumber) {
        phoneNumberRepository.delete(phoneNumber);
    }
}
