package com.interpackage.tracking.service;

import com.interpackage.tracking.interfaces.TrackingInterface;
import com.interpackage.tracking.model.Response;
import com.interpackage.tracking.model.Tracking;
import com.interpackage.tracking.repository.TrackingRepository;
import com.interpackage.tracking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrackingService implements TrackingInterface {

    @Autowired
    private TrackingRepository trackingRepository;

    @Override
    public ResponseEntity<Response> saveTracking(Tracking tracking) {
        try {
            return new ResponseEntity<>(
                    new Response(
                            this.trackingRepository.save(tracking)),
                    HttpStatus.CREATED);
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(new Response(Constants.REQUIRED_FIELDS));
        }
    }
}
