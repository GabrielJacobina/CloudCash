package com.cash.user.repository;

import com.cash.user.dto.UserBalanceDTO;
import com.cash.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new com.cash.user.dto.UserBalanceDTO(u.id, u.name,u.cpfCnpj, u.balance) from User u where u.id = :id")
    Optional<UserBalanceDTO> findUserDTOById(@Param("id") Long id);

    @Query("UPDATE User u SET u.balance = :balance where u.id = :id")
    void updateBalance(@Param("id") Long id, @Param("balance") Double balance);
}
