package com.stephaniescr.jpa.repository;

import com.stephaniescr.jpa.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByCountryId(Long countryId, Pageable pageable);
}