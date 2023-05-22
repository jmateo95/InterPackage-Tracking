package com.interpackage.tracking.service;

import com.interpackage.basedomains.dto.TrackingEvent;
import com.interpackage.tracking.interfaces.EventInterface;
import com.interpackage.tracking.model.Tracking;
import com.interpackage.tracking.producers.TrackingProducer;
import com.interpackage.tracking.util.Constants;
import org.springframework.stereotype.Service;

@Service
public class EventService implements EventInterface {

    private final TrackingProducer trackingProducer;

    public EventService(TrackingProducer trackingProducer) {
        this.trackingProducer = trackingProducer;
    }

    @Override
    public void sendNotification(Tracking tracking) {
        TrackingEvent event = new TrackingEvent();
        event.setMessage(Constants.MESSAGE_NOTIFICATION);
        event.setStatus(Constants.PENDING_STATE);
        event.setTracking(
                new com.interpackage.basedomains.dto.Tracking(
                        tracking.getIdOrder(),
                        tracking.getCity(),
                        Constants.CHECK_IN_STATE,
                        tracking.getDate().toString()
                )
        );
        trackingProducer.sendMessage(event);
    }
}
