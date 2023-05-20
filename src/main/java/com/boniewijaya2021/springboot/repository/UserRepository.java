package com.boniewijaya2021.springboot.repository;

import com.boniewijaya2021.springboot.entity.TblUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TblUsers,Long> {
}
