package com.interpackage.tracking.controller;

import com.interpackage.tracking.aspect.RequiredRole;
import com.interpackage.tracking.model.Response;
import com.interpackage.tracking.model.Tracking;
import com.interpackage.tracking.service.EventService;
import com.interpackage.tracking.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(Constants.API_TRACKING_V1 + "/tracking")
public class TrackingController {

    @Autowired
    private EventService eventService;

    @PostMapping("/tracking/")
    @RequiredRole({Constants.OPERATOR_ROL})
    public ResponseEntity<Response> tracking(final @RequestBody Tracking tracking){
        try {
            // Lógica para el tracking
            tracking.setDate(LocalDate.now());
            eventService.sendNotification(tracking);
            return ResponseEntity.ok(new Response("Todo bien!", tracking));
        } catch (final Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}
