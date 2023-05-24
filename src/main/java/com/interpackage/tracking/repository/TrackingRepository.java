package com.interpackage.tracking.repository;

import com.interpackage.tracking.pk.PKTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interpackage.tracking.model.Tracking;

import java.util.Optional;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, PKTracking>{

    Optional<Tracking> findFirstByIdOrderOrderByDateDesc(String idOrder);
}
