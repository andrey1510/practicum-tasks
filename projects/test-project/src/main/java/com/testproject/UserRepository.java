package com.testproject;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}