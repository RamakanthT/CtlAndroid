package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Chk2 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chk2);
		
		Button bLogin=(Button)findViewById(R.id.button1);
		bLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Chk2.this, "Button Clicked", Toast.LENGTH_SHORT).show();
//				Intent openPoolScreen = new Intent("com.example.test.Chk3");
//				startActivity(openPoolScreen);
				
				Context c=arg0.getContext();
				Intent intent = new Intent(Chk2.class, Chk3.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				c.startActivity(intent);
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}

}
