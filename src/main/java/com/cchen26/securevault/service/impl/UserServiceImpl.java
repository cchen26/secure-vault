package com.cchen26.securevault.service.impl;

import com.cchen26.securevault.entity.ConfirmationEntity;
import com.cchen26.securevault.entity.CredentialEntity;
import com.cchen26.securevault.entity.RoleEntity;
import com.cchen26.securevault.entity.UserEntity;
import com.cchen26.securevault.enumeration.Authority;
import com.cchen26.securevault.enumeration.EventType;
import com.cchen26.securevault.event.UserEvent;
import com.cchen26.securevault.exception.ApiException;
import com.cchen26.securevault.repository.ConfirmationRepository;
import com.cchen26.securevault.repository.CredentialRepository;
import com.cchen26.securevault.repository.RoleRepository;
import com.cchen26.securevault.repository.UserRepository;
import com.cchen26.securevault.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.cchen26.securevault.utils.UserUtils.createUserEntity;

/**
 * @author Chao
 * @version 1.0
 * @email cache234@gmail.com
 * @since 2024-06-24
 */
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CredentialRepository credentialRepository;
    private final ConfirmationRepository confirmationRepository;
    //private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var userEntity = userRepository.save(createNewUser(firstName, lastName, email));
        var credentialEntity = new CredentialEntity(userEntity, password);
        credentialRepository.save(credentialEntity);
        var confirmationEntity = new ConfirmationEntity(userEntity);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(userEntity, EventType.REGISTRATION, Map.of("key", confirmationEntity.getKey())));
    }

    @Override
    public RoleEntity getRoleName(String name){
        var role = roleRepository.findByNameIgnoreCase(name);
        return role.orElseThrow(() -> new ApiException("Role not found"));
    }

    private UserEntity createNewUser(String firstName, String lastName, String email) {
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }
}
