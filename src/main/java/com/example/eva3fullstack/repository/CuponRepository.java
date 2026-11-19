package com.example.eva3fullstack.repository;

import com.example.eva3fullstack.entity.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Long> {
}
