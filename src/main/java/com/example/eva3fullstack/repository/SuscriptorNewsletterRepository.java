package com.example.eva3fullstack.repository;

import com.example.eva3fullstack.entity.SuscriptorNewsletter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuscriptorNewsletterRepository extends JpaRepository<SuscriptorNewsletter, Long> {
}
