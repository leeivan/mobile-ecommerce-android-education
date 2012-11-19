package com.google.mcommerce.sample.android.chapter10.mediaplay;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.mcommerce.sample.android.R;

public class AudioActivity extends Activity {
	private MediaPlayer up, down, left, right, enter;
	private MediaPlayer a, s, d, f;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c10_audio_layout);

		// Native rate is 44.1kHz 16 bit stereo, but
		// to save space we just use MPEG-3 22kHz mono
		up = MediaPlayer.create(this, R.raw.up);
		down = MediaPlayer.create(this, R.raw.down);
		left = MediaPlayer.create(this, R.raw.left);
		right = MediaPlayer.create(this, R.raw.right);
		enter = MediaPlayer.create(this, R.raw.enter);
		a = MediaPlayer.create(this, R.raw.a);
		s = MediaPlayer.create(this, R.raw.s);
		d = MediaPlayer.create(this, R.raw.d);
		f = MediaPlayer.create(this, R.raw.f);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		MediaPlayer mp;
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			mp = up;
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			mp = down;
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			mp = left;
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			mp = right;
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_ENTER:
			mp = enter;
			break;
		case KeyEvent.KEYCODE_A:
			mp = a;
			break;
		case KeyEvent.KEYCODE_S:
			mp = s;
			break;
		case KeyEvent.KEYCODE_D:
			mp = d;
			break;
		case KeyEvent.KEYCODE_F:
			mp = f;
			break;
		default:
			return super.onKeyDown(keyCode, event);
		}
		mp.seekTo(0);
		mp.start();
		return true;
	}

}
