package com.interpackage.tracking.interfaces;

import com.interpackage.tracking.model.Response;
import com.interpackage.tracking.model.Tracking;
import org.springframework.http.ResponseEntity;

public interface TrackingInterface {
    
    ResponseEntity<Response> getTrackingDetail(String orderId);

    ResponseEntity<Response> saveTracking(Tracking tracking);
}
