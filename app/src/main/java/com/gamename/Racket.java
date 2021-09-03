package com.gamename;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import java.util.HashMap;

public class SoundManager {
	private static SoundPool SndPool;
	private static HashMap<Integer, Integer> SndPoolMap;
	private static AudioManager  AudioMgr;

	private SoundManager() {
	}

	// Initialise storage for sounds
	public static  void initSounds(Context context) {
		// mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		AudioAttributes audioAttrib = new AudioAttributes.Builder()
			.setUsage(AudioAttributes.USAGE_GAME)
			.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
			.build();
        	SndPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(6).build();
		SndPoolMap = new HashMap<>();
		AudioMgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
	} 

	// Load sound assets
	public static void loadSounds(Context context) {
		SndPoolMap.put(1, SndPool.load(context, R.raw.honk, 1));
		// mSoundPoolMap.put(2, mSoundPool.load(mContext, R.raw.terminator, 1));
	}
 
	/**
	* Plays a Sound
	* @param index - The Index of the Sound to be played
	* @param speed - The Speed to play not, not currently used but included for compatibility
	*/
	public static void playSound(int index, float speed) {
		float streamVolume = AudioMgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		streamVolume = streamVolume / AudioMgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		SndPool.play(SndPoolMap.get(index), streamVolume, streamVolume, 1, 0, speed);
	}
 
	/**
	* Stop a Sound
	* @param index - index of the sound to be stopped
	*/
	public static void stopSound(int index) {

		SndPool.stop(SndPoolMap.get(index));
	}
 
	/**
	* Deallocates the resources
	*/
	public static void cleanup() {
		SndPool.release();
		SndPool = null;
		SndPoolMap.clear();
		AudioMgr.unloadSoundEffects();
	}
}