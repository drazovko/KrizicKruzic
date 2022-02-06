package hr.foi.air.webservice;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;

import hr.foi.air.core.entities.Igrac;
import hr.foi.air.webservice.handlers.MyWebserviceHandler;
import hr.foi.air.webservice.responses.MyWebserviceResponse;
import hr.foi.air.webservice.responses.MyWebserviceResponse2;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MyWebserviceCaller {
    // retrofit object
    Retrofit retrofit;
    //base URL of the web service
    private final String baseUrl = "http://192.168.5.17:5000/";
    private MyWebserviceHandler myWebserviceHandler;

    //constructor
    public MyWebserviceCaller(MyWebserviceHandler myWebserviceHandler){
        //caller received reference to response handler to enable callback when data is ready
        this.myWebserviceHandler = myWebserviceHandler;

        OkHttpClient client = new OkHttpClient();

        // for debuggint use HttpInterceptor
        //client.interceptors().add(new HttpInterceptor());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void logiranjeNaServer(String username, String password){
        MyWebservice serviceAPI = retrofit.create(MyWebservice.class);
        Call<MyWebserviceResponse2> call;
        Igrac igrac = new Igrac(username, false);


        call = serviceAPI.kreiranjeIgraca(igrac);

//        cekanjeOdgovora(call);

        if (call != null){
            call.enqueue(new Callback<MyWebserviceResponse2>() {
                @Override
                public void onResponse(Response<MyWebserviceResponse2> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()){
          //                  if (response.body().getResponseID() == 103){
                                //Object igracItems = response.body().getItems();

                                myWebserviceHandler.onDataArrived(
                                        response.body().getItems(),
                                        response.body().getResponseID(),
                                        true,
                                        response.body().getTimeStamp());
             //               } else if (response.body().getResponseID() == 101){
               //                 System.out.println("Ovde dolazi String");
                  //          }


                        } else {
                            System.out.println("Ni dobro");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        processFailureResponse(ex.getMessage());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    processFailureResponse(t.getMessage());
                }
            });
        }
    }

    // get all records from a web service
    public void slanjeZahtjevaZaIgrom(){

        MyWebservice serviceAPI = retrofit.create(MyWebservice.class);
        Call<MyWebserviceResponse> call;

        call = serviceAPI.slanjeZahtjevaZaIgrom();

        if(call != null) {
            call.enqueue((new Callback<MyWebserviceResponse>() {
                @Override
                public void onResponse(Response<MyWebserviceResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()){
                            System.out.println("Prošao po...");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        processFailureResponse(ex.getMessage());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    processFailureResponse(t.getMessage());
                }
            }));
        }
    }

    //get all players
    public void getAllPlayers(String method, final Type entitiyType){

        MyWebservice serviceAPI = retrofit.create(MyWebservice.class);
        Call<MyWebserviceResponse> callGetSviIgraci;

        callGetSviIgraci = serviceAPI.getSviIgraci();

        cekanjeOdgovora(callGetSviIgraci);


    }

    private void cekanjeOdgovora(Call<MyWebserviceResponse> call) {
        if (call != null){
            call.enqueue(new Callback<MyWebserviceResponse>() {
                @Override
                public void onResponse(Response<MyWebserviceResponse> response, Retrofit retrofit) {
                    try {
                        if (response.isSuccess()){
                            System.out.println("Imamo igrače . . . ");
                            Gson gson = new Gson();
                            Igrac[] igracItems = response.body().getItems();

                            myWebserviceHandler.onDataArrived(
                                    Arrays.asList(igracItems),
                                    response.body().getResponseID(),
                                    true,
                                    response.body().getTimeStamp());
                        } else {
                            System.out.println("Ni dobro");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        processFailureResponse(ex.getMessage());
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    processFailureResponse(t.getMessage());
                }
            });
        }
    }

    private void processFailureResponse(String message) {
        //TODO - Refactor to send message to handler

        myWebserviceHandler.onDataArrived(
                null,
                0,
                false,
                new Date().getTime());
    }
}
