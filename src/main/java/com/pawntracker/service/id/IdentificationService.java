package com.pawntracker.service.id;


import com.pawntracker.entity.User;
import com.pawntracker.entity.id.DriversLicense;
import com.pawntracker.entity.id.Identification;
import com.pawntracker.entity.id.IdentificationCard;
import com.pawntracker.entity.id.Passport;
import com.pawntracker.repository.id.DriversLicenseRepository;
import com.pawntracker.repository.id.IdentificationCardRepository;
import com.pawntracker.repository.id.IdentificationRepository;
import com.pawntracker.repository.id.PassportRepository;
import com.pawntracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentificationService {
    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private DriversLicenseRepository driversLicenseRepository;

    @Autowired
    private IdentificationCardRepository identificationCardRepository;

    @Autowired
    private PassportRepository passportRepository;


    @Autowired
    private UserService userService;


    public void addIdCardToUser(IdentificationCard card, String username) {
        User user = userService.findByUsername(username);
        if (user!=null) {
            Identification identification = user.getIdentification();
            List<IdentificationCard> identificationCardList = identification.getIdentificationCardList();
            identificationCardList.add( 0, card);
            card.setIdentification(identification);
            identificationCardRepository.save(card);
            identificationRepository.save(identification);
            userService.saveUserOrUpdate(user);
        }
    }

    public void addDlToUser(DriversLicense dl, String username) {
        User user = userService.findByUsername(username);
        if (user!=null) {
            Identification identification = user.getIdentification();
            List<DriversLicense> driversLicenseList = identification.getDriversLicenseList();
            driversLicenseList.add( 0, dl);

            dl.setIdentification(identification);
            driversLicenseRepository.save(dl);
            identificationRepository.save(identification);
            userService.saveUserOrUpdate(user);
        }
    }


    public void addPassportToUser(Passport passport, String username) {
        User user = userService.findByUsername(username);
        if (user!=null) {
            Identification identification = user.getIdentification();
            List<Passport> passportList = identification.getPassportList();
            passportList.add( 0, passport);

            passport.setIdentification(identification);
            passportRepository.save(passport);
            identificationRepository.save(identification);
            userService.saveUserOrUpdate(user);
        }
    }





}
