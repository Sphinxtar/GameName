package com.gamename;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class Racket {
	private static SoundPool SndPool;
	private static final int Soundnum = 7;
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

		Sound[0] = SndPool.load(context, R.raw.click, 1);
		Sound[1] = SndPool.load(context, R.raw.bbirth, 1);
		Sound[2] = SndPool.load(context, R.raw.bdeath, 1);
		Sound[3] = SndPool.load(context, R.raw.blife, 1);
		Sound[4] = SndPool.load(context, R.raw.bounce, 1);
		Sound[5] = SndPool.load(context, R.raw.bye, 1);
		Sound[6] = SndPool.load(context, R.raw.hit, 1);
	}

	// play( sound number )
	public void play( int noise ) {
		SndPool.play(Sound[noise], (float) 0.5, (float) 0.5, 0, 0, 1);
	}

	protected void onDestroy() {
		SndPool.release();
		SndPool = null;
	}
}