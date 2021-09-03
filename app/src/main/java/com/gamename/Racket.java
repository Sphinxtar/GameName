package com.gamename;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class Racket {
	private static SoundPool SndPool;
	private static final int Soundnum = 5;
	private static final int[] Sound = new int[Soundnum];

	public Racket(Context context) {
		AudioAttributes audioAttributes = new AudioAttributes.Builder()
				.setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
				.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
				.build();
		SndPool = new SoundPool.Builder()
                    .setMaxStreams(Soundnum)
                    .setAudioAttributes(audioAttributes)
                    .build();
		Sound[0] = SndPool.load(context, R.raw.honk, 1);
	}

	public void play( int noise ) {
		SndPool.play(Sound[noise], 1, 1, 0, 0, 1);
	}

	protected void onDestroy() {
		SndPool.release();
		SndPool = null;
	}

}