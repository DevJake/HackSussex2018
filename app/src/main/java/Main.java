import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAz3A5Ud1pxswkMEAgNSSP7X7SfR7aiAwk")
                .build();

        GeocodingResult[] result0 = GeocodingApi.reverseGeocode(context, new LatLng(50.8653263, -0.0861952)).await();

        DirectionsResult result1 = DirectionsApi.getDirections(context,
                result0[0].formattedAddress,
                "Sainsbury's Lewes Road").units(Unit.METRIC).mode(TravelMode.WALKING).await();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(result1));
    }
}
