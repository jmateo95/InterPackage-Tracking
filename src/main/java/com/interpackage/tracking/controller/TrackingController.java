package com.interpackage.tracking.controller;

import com.interpackage.tracking.aspect.RequiredRole;
import com.interpackage.tracking.model.Response;
import com.interpackage.tracking.model.Tracking;
import com.interpackage.tracking.repository.TrackingRepository;
import com.interpackage.tracking.service.EventService;
import com.interpackage.tracking.service.TrackingService;
import com.interpackage.tracking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping(Constants.API_TRACKING_V1)
public class TrackingController {

    @Autowired
    private EventService eventService;

    @Autowired
    private TrackingService trackingService;

    @PostMapping("/tracking/")
    @RequiredRole({Constants.ADMIN_ROL, Constants.OPERATOR_ROL})
    public ResponseEntity<Response> tracking(final @RequestBody Tracking tracking){
        try {
            // Lógica para el tracking
            tracking.setDate(LocalDateTime.now());
            ResponseEntity<Response> trackingSaved = trackingService.saveTracking(tracking);

            // eventService.sendNotification(tracking);
            eventService.sendNotification(
                    (Tracking) Objects
                            .requireNonNull(trackingSaved.getBody())
                            .getResponseObject());
            return trackingSaved;
        } catch (final Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    @PostMapping("/{orderId}")
    @RequiredRole({Constants.ADMIN_ROL, Constants.OPERATOR_ROL})
    public ResponseEntity<Response> getTrackingDetail(@PathVariable String orderId){
        return this.trackingService.getTrackingDetail(orderId);
    }
}
