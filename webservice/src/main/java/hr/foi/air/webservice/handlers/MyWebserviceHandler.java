package hr.foi.air.webservice.handlers;

public interface MyWebserviceHandler {
    void onDataArrived(
            Object result,
            int responseID,
            boolean ok,
            long timeStamp);
}
