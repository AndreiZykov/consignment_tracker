package com.pawntracker.service;

import com.pawntracker.entity.Address;
import com.pawntracker.entity.PhoneNumber;
import com.pawntracker.entity.Role;
import com.pawntracker.entity.User;
import com.pawntracker.repository.AddressRepository;
import com.pawntracker.repository.PhoneNumberRepository;
import com.pawntracker.repository.RoleRepository;
import com.pawntracker.repository.UserRepository;
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
    private AddressRepository addressRepository;

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

    public User addImage(User user, MultipartFile file) throws IOException {
       if (user.getPhotos() == null) {
           user.setPhotos( new ArrayList<>());
       }
       if (user != null) {
           String fileName = user.getFirstName() + "-" + user.getId() + "-" + user.getPhotos().size() + "-" + file.getOriginalFilename();
           Path path = Paths.get(folder + fileName);
           imageService.saveImage(path, file.getBytes());
           ArrayList<String> paths = user.getPhotos();
           paths.add(0, fileName);
           user.setPhotos(paths);

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
}
