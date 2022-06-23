package com.example.reviewohrid.repository;

import com.example.reviewohrid.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email=?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id=?1")
    public User findById(int id);

    @Query("SELECT u FROM User u WHERE u.email=?1 AND u.password=?2")
    public User findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE u.email=?1")
    public Optional<User> findByUserName(String userName);
}
