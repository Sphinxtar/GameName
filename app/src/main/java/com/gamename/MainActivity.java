package com.gamename;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	SoundManager.getInstance();
	SoundManager.initSounds(this);
	SoundManager.loadSounds();
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(new GameNameView(this));
    }
}
