package com.example.HyThon.repository;

import com.example.HyThon.domain.Member;
import com.example.HyThon.domain.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {

    @Query("SELECT t FROM Transmission t WHERE t.receiver = :member AND DATE(t.createdAt) = :date")
    Optional<Transmission> findByMemberAndDate(Member member, @Param("date") LocalDate date);
}
