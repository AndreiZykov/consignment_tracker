package com.pawntracker.config;

import com.pawntracker.entity.Privilege;
import com.pawntracker.entity.Role;
import com.pawntracker.entity.User;
import com.pawntracker.repository.PrivilegeRepository;
import com.pawntracker.repository.RoleRepository;
import com.pawntracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        Privilege ownerPrivilege
                = createPrivilegeIfNotFound("OWNER_PRIVILEGE");

        Set<Privilege> userPriviliges = new HashSet<>();
        userPriviliges.add(readPrivilege);

        Set<Privilege> adminPrivileges = new HashSet<>();
        adminPrivileges.add(readPrivilege);
        adminPrivileges.add(writePrivilege);

        Set<Privilege> ownerPrivileges = new HashSet<>();
        ownerPrivileges.add(readPrivilege);
        ownerPrivileges.add(writePrivilege);
        ownerPrivileges.add(ownerPrivilege);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPriviliges);
        createRoleIfNotFound("ROLE_OWNER", ownerPrivileges);

        Role ownerRole = roleRepository.getByName("ROLE_OWNER");
        Set<Role> roles = new HashSet<>();
        roles.add(ownerRole);

        User user1 = userRepository.getUserByEmail("test@test.com");
        if (user1 == null) {
            User user = new User();
            user.setFirstName("Owner");
            user.setLastName("Owner");
            user.setPassword(passwordEncoder.encode("test@test.com"));
            user.setEmail("test@test.com");
            user.setRoles(roles);
            user.setApproved(true);
            userRepository.save(user);
        }

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Set<Privilege> privileges) {

        Role role = roleRepository.getByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }

        return role;
    }
}