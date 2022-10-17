package com.example.wallet.ewallet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByPhoneNumber(String phoneNumber);
}
