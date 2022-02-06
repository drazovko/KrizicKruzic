package hr.foi.air.webservice;

import hr.foi.air.core.entities.Igrac;
import hr.foi.air.webservice.responses.MyWebserviceResponse;
import hr.foi.air.webservice.responses.MyWebserviceResponse2;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;

public interface MyWebservice {

    @GET("igraci")
    Call<MyWebserviceResponse> getSviIgraci();

    @POST("igra")
    Call<MyWebserviceResponse> slanjeZahtjevaZaIgrom();

    @POST("igrac")
    Call<MyWebserviceResponse2> kreiranjeIgraca(@Body Igrac igrac);
}

    //@Field("username") String username, @Field("zauzet") boolean zauzet