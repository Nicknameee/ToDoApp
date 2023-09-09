package io.uwp.digital.repository;

import io.uwp.digital.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findUserByUsername(String username);
}
