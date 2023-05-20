package com.boniewijaya2021.springboot.repository;

import com.boniewijaya2021.springboot.entity.TblProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductsRepository extends JpaRepository<TblProducts, UUID> {

}
