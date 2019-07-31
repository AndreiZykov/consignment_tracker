package com.pawntracker.service;

import com.pawntracker.entity.*;
import com.pawntracker.entity.id.Identification;
import com.pawntracker.repository.*;
import com.pawntracker.repository.id.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserService {

    @Value("${upload.path}")
    private String folder;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PhotographRepository photographRepository;

    public User saveUserOrUpdate(User newUser) {
        User user = userRepository.getUserByUsername(newUser.getUsername());
        if (user == null) {
            Role role = roleRepository.getByName("USER");
            if (role == null) role = new Role();
            role.setName("USER");

            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            Set< Role> roles = new HashSet<>();
            roles.add(roleRepository.getByName("ROLE_USER"));
            newUser.setRoles(roles);
            newUser.setConfirmPassword("");
            Identification identification = new Identification();
            identification.setUser(newUser);
            newUser.setIdentification(identification);
            Photograph photograph = new Photograph();
            newUser.setPhotograph(photograph);
            photographRepository.save(photograph);
            identificationRepository.save(identification);
            return userRepository.save(newUser);

        } else {
            userRepository.save(user);
        }

        return null;

    }

    public User findByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User addImage(String username, MultipartFile front, MultipartFile profile) throws IOException {
       User user = userRepository.getUserByUsername(username);
       if (user != null) {
           String frontFileName = user.getFirstName() + "-" + user.getId() + "-" + user.getPhotograph().getPhotoHistory().size() + "-" + front.getOriginalFilename();
           Path frontPath = Paths.get(folder + frontFileName);
           imageService.saveImage(frontPath, front.getBytes());


           String profileFileName = user.getFirstName() + "-" + user.getId() + "-" + user.getPhotograph().getPhotoHistory().size() + "-" + profile.getOriginalFilename();
           Path profilePath = Paths.get(folder + profileFileName);
           imageService.saveImage(profilePath, profile.getBytes());

           Photograph photograph = user.getPhotograph();
           List<String> paths = photograph.getPhotoHistory();
           paths.add(frontFileName);
           paths.add(profileFileName);
           photograph.setFrontPhotograph(frontFileName);
           photograph.setProfilePhotograph(profileFileName);
           photographRepository.save(photograph);

       }
        return user;
    }

    public void addAddresAndPhoneNumberToUser(Address address, PhoneNumber phoneNumber, String username) {
        User user = userRepository.getUserByUsername(username);
        List addressList = user.getAddressList();
        addressList.add(address);
        user.setAddressList(addressList);
        List<PhoneNumber> phoneNumberList = user.getPhoneNumberList();
        phoneNumberList.add(phoneNumber);
        user.setPhoneNumberList(phoneNumberList);
        address.setUser(user);
        phoneNumber.setUsers(user);

        addressRepository.save(address);
        phoneNumberRepository.save(phoneNumber);
        userRepository.save(user);

    }

    public void approveUser(Long id) {
        User user = userRepository.getUserById(id);
        if (user!= null) {
            user.setApproved(!user.isApproved());
            userRepository.save(user);
        }
    }
    public List<User> userListToApprove() {
        return userRepository.findByApprovedFalse();
    }

    public String getStageOfRegistration(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user!=null) {

            if(user.getAddressList().isEmpty() || user.getPhoneNumberList().isEmpty()) {
                return "address";
            }
            if(user.getIdentification() != null) {
                if (user.getIdentification().getIdentificationCardList().isEmpty() &&
                        user.getIdentification().getDriversLicenseList().isEmpty() &&
                            user.getIdentification().getPassportList().isEmpty()
                ) {
                    return "document";
                }
            }
            if (user.getPhotograph().getFrontPhotograph() == null || user.getPhotograph().getProfilePhotograph() == null ) {
                return "photograph";
            }
            if (user.isApproved()) {
                return "approved";
            }
            if (!user.isApproved()) {
                return "nonapproved";
            }
        }
        return "";
    }
}
