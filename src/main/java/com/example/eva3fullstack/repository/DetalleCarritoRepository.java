package com.example.eva3fullstack.repository;

import com.example.eva3fullstack.entity.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {
}
