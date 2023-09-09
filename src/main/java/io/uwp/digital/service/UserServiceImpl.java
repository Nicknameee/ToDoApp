package io.uwp.digital.service;

import io.uwp.digital.dto.UserDTO;
import io.uwp.digital.entity.UserModel;
import io.uwp.digital.mapper.UserMapper;
import io.uwp.digital.repository.UserRepository;
import io.uwp.digital.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Nullable
    public static Long getCurrentlyAuthenticatedUserId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getAuthentication() != null &&
                securityContext.getAuthentication().isAuthenticated()) {
            if (securityContext.getAuthentication().getPrincipal() instanceof UserModel) {
                return ((UserModel) securityContext.getAuthentication().getPrincipal()).getId();
            } else {
                return null;
            }
        }
        return null;
    }

    public void saveUser(@NotNull(message = "User data is null") UserDTO userDTO) {
        UserModel abstractUserModel = userMapper.dtoToEntity(userDTO);

        userRepository.save(abstractUserModel);
    }
}
