package tcs.ctl.cplus.pool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class FlashScreen extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flashscreen);
		
		initialize();
	}
	
	public void initialize()
	{
		
Thread timer=new Thread(){
			
			public void run()
			{
				try
				{
					
					sleep(5000);
					
				} catch (InterruptedException ie)
				{
					
					ie.printStackTrace();
					
				}finally{
					
					Intent openStartingPoint = new Intent("tcs.ctl.cplus.pool.SigninRegPage");
					startActivity(openStartingPoint);
				}
			}
		};
		
		timer.start();
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	

}
