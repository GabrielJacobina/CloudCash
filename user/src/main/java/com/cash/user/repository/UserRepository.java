package com.cash.user.repository;

import com.cash.user.DTO.UserBalanceDTO;
import com.cash.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new com.cash.user.DTO.UserBalanceDTO(u.id, u.name,u.cpfCnpj, u.balance) from User u where u.id = :id")
    Optional<UserBalanceDTO> findUserDTOById(@Param("id") Long id);
}
