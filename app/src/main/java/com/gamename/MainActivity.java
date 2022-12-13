package com.gamename;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class MainActivity extends Activity {

	private void hideSystemBars( ) {
		WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(getWindow().getDecorView());
		if (windowInsetsController != null) {
			// Configure the behavior of the hidden system bars
			windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
			// Hide both the status bar and the navigation bar
			windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hideSystemBars();
		setContentView(new GameNameView(this));
    }
}
