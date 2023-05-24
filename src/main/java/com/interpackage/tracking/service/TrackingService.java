package com.interpackage.tracking.service;

import com.interpackage.tracking.interfaces.TrackingInterface;
import com.interpackage.tracking.model.Response;
import com.interpackage.tracking.model.Tracking;
import com.interpackage.tracking.repository.TrackingRepository;
import com.interpackage.tracking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrackingService implements TrackingInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingService.class);

    @Autowired
    TrackingRepository trackingRepository;

    @Override
    public ResponseEntity<Response> getTrackingDetail(String orderId) {
        var order = this.trackingRepository.findFirstByIdOrderOrderByDateDesc(orderId);
        return order.map(tracking -> ResponseEntity
                .ok().body(new Response(tracking))).orElseGet(() -> ResponseEntity
                .internalServerError()
                .body(new Response(Constants.NOT_FOUND)));
    }

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
