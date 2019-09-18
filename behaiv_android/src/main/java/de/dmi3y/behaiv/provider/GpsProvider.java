package de.dmi3y.behaiv.provider;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

import java.util.Arrays;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class GpsProvider implements Provider {

    private Context context;
    private final LocationManager locationManager;

    public GpsProvider(Context context) {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        this.context = context;
    }

    @Override
    public List<String> availableFeatures() {
        return Arrays.asList("latitude", "longitude");
    }

    @Override
    public Observable<List<Double>> subscribeFeatures() {
        return null;
    }

    @Override
    public Single<List<Double>> getFeature() {
        return Single.create(new SingleOnSubscribe<List<Double>>() {
            @Override
            public void subscribe(final SingleEmitter<List<Double>> emitter) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //We use 0,0 coordinates if we don't have position as of now
                    emitter.onSuccess(Arrays.asList(0.0, 0.0));
                    return;
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                emitter.onSuccess(Arrays.asList(location.getLatitude(), location.getLongitude()));
            }
        });
    }
}
