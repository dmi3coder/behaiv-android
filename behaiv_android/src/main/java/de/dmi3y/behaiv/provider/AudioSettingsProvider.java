package de.dmi3y.behaiv.provider;

import android.content.Context;
import android.media.AudioManager;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.util.ArrayList;
import java.util.List;

public class AudioSettingsProvider implements Provider {

    private Context context;
    private boolean headsetEnabled = false;
    private boolean playingMusic = false;
    private boolean speakerEnabled = false;


    private AudioSettingsProvider(Context context) {
        this.context = context;
    }

    @Override
    public List<String> availableFeatures() {
        ArrayList<String> featureNames = new ArrayList<>();
        if (headsetEnabled) {
            featureNames.add("headset_enabled");
        }
        if (playingMusic) {
            featureNames.add("music_enabled");
        }
        if (speakerEnabled) {
            featureNames.add("speaker_enabled");
        }
        return featureNames;
    }

    @Override
    public Observable<List<Double>> subscribeFeatures() {
        return null;
    }

    @Override
    public Single<List<Double>> getFeature() {
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);


        List<Double> features = new ArrayList<>();
        if (headsetEnabled) {
            features.add(manager.isWiredHeadsetOn() ? 1.0 : 0.0);
        }
        if (playingMusic) {
            features.add(manager.isMusicActive() ? 1.0 : 0.0);
        }
        if (speakerEnabled) {
            features.add(manager.isSpeakerphoneOn() ? 1.0 : 0.0);
        }

        return Single.just(features);
    }

    public static class Builder {

        private final AudioSettingsProvider provider;

        public Builder(Context context) {
            provider = new AudioSettingsProvider(context);
        }

        public Builder checkHeadsetConnected() {
            provider.headsetEnabled = true;
            return this;
        }

        public Builder checkPlayingMusic() {
            provider.playingMusic = true;
            return this;
        }

        public Builder checkSpeakerEnabled() {
            provider.speakerEnabled = true;
            return this;
        }

        public AudioSettingsProvider build() {

            return provider;
        }


    }
}
