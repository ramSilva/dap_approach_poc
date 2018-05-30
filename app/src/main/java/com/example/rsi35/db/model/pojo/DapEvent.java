package com.example.rsi35.db.model.pojo;

public class DapEvent {
    private String eventJson;

    public static DapEvent from(Event event) {
        DapEvent dapEvent = new DapEvent();
        dapEvent.setEventJson("{" + event.getKey() + ":" + event.getValue() + "}");
        return dapEvent;
    }

    public String getEventJson() {
        return eventJson;
    }

    public void setEventJson(final String eventJson) {
        this.eventJson = eventJson;
    }
}
