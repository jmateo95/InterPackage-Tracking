package com.interpackage.tracking.repository;

import com.interpackage.tracking.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, String> {

}
