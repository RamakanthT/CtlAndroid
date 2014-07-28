package com.example.test;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Chk1 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chk1);
		
		Button bLogin=(Button)findViewById(R.id.button1);
		bLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(Chk1.this, "Button Clicked", Toast.LENGTH_SHORT).show();
				Intent openPoolScreen = new Intent("com.example.test.Chk2");
				startActivity(openPoolScreen);
			}
		});
	}

}
