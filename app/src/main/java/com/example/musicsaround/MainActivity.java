package com.example.musicsaround;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.musicsaround.dj.DJActivity;
import com.example.musicsaround.speaker.SpeakerActivity;

public class MainActivity extends Activity
{
	// public key for other activities to access to figure out the mode
	public final static String MODE = "MODE";
	private Button btnLogout;
	private Session session;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		session = new Session(this);
		if(!session.loggedin()){
			logout();
		}
		btnLogout = (Button)findViewById(R.id.btnLogout);
		btnLogout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				logout();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onBtnDJ(View view)
	{
		Intent intent = new Intent(this, DJActivity.class);
		intent.putExtra(MODE, DJActivity.DJ_MODE);
		startActivity(intent);
	}

	public void onBtnSp(View view)
	{
		Intent intent = new Intent(this, SpeakerActivity.class);
		intent.putExtra(MODE, SpeakerActivity.SPEAKER_MODE);
		startActivity(intent);
	}

	private void logout(){
		session.setLoggedin(false);
		finish();
		startActivity(new Intent(MainActivity.this,LoginActivity.class));
	}
}
