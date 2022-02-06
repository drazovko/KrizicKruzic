package hr.foi.air.webservice.responses;

import hr.foi.air.core.entities.Igrac;

public class MyWebserviceResponse {
    public Igrac[] items;
    public int responseID;
    public String responseText;
    public long timeStamp;

    public Igrac[] getItems() {
        return items;
    }

    public void setItems(Igrac[] items) {
        this.items = items;
    }

    public int getResponseID() {
        return responseID;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
