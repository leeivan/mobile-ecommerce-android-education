package com.google.mcommerce.sample.android.chapter11.mediaplay;

import com.google.mcommerce.sample.android.R;

import android.app.Activity;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;

public class AudioEffectsControlActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c11_audio_effects_control_layout);
        
        Intent intent = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
        startActivityForResult(intent, 0);
    }
}