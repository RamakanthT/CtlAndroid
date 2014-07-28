package tcs.ctl.cplus.pool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PoolSearch extends Activity {
	 
    /** Called when the activity is first created. */
       
      @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.pool_search);
          
          Button bLogin=(Button)findViewById(R.id.bView);
  		bLogin.setOnClickListener(new View.OnClickListener() {
  			
  			@Override
  			public void onClick(View arg0) {
  				// TODO Auto-generated method stub
  				Toast.makeText(PoolSearch.this, "View Button Clicked", Toast.LENGTH_SHORT).show();
  				Intent openPoolScreen = new Intent("tcs.ctl.cplus.pool.PoolView");
  				startActivity(openPoolScreen);
  			}
  		});
  		
        }
}