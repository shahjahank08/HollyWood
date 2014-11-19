package com.hollywood.activity;

import java.util.Arrays;
import java.util.List;

import com.example.hollywood.R;
import com.facebook.Session;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FaceBookLogin extends Activity {

	private ImageButton loginButton;
	Button skipButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		initialize();

	}

	private void initialize() {
		// TODO Auto-generated method stub
		loginButton = (ImageButton) findViewById(R.id.fb_login_button);
		skipButton = (Button) findViewById(R.id.skipLogin);
		skipButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(FaceBookLogin.this,
						RegistrationEducation.class));
				overridePendingTransition(R.anim.slide_in_up,
						R.anim.slide_out_up);
			}
		});
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Give any permissions
				openActiveSession(FaceBookLogin.this, true, Arrays.asList(
						"email", "user_birthday", "user_hometown",
						"user_location"), new Session.StatusCallback() {

					@Override
					public void call(Session session, SessionState state,
							Exception exception) {
						// TODO Auto-generated method stub
						//Request me 
						startActivity(new Intent(FaceBookLogin.this,
								RegistrationEducation.class));
					}
				});
			}
		});
	}

	private static Session openActiveSession(Activity activity,
			boolean allowLoginUI, List permissions, StatusCallback callback) {
		OpenRequest openRequest = new OpenRequest(activity).setPermissions(
				permissions).setCallback(callback);
		Session session = new Session.Builder(activity).build();
		if (SessionState.CREATED_TOKEN_LOADED.equals(session.getState())
				|| allowLoginUI) {
			Session.setActiveSession(session);
			session.openForRead(openRequest);
			return session;
		}
		return null;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}

}
