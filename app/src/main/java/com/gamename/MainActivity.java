package com.gamename;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {
	public static Racket racket;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		racket = new Racket(this);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new GameNameView(this));
    }
}
