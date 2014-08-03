package tcs.ctl.cplus.pool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
 
public class PoolView extends Activity {
 
	TextView tvSrcDest,tvVia,tvDate,tvTime,tvSeats,tvVehicle,tvUname,tvUmobile,tvUempid,tvUgender,tvAvailable,tvBooked,tvMySeats;
	EditText etCounter;
	Button bDecrement,bIncrement,bJoin;
	ImageButton ibExit;
	
	UserBean UBOBJ;
	PoolBean PBOBJ,pbObj2;
	SubscribeBean sbObj;
	
	PoolDb pdbObj=new PoolDb(this);
	
    /** Called when the activity is first created. */
	  
      @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
  	      getActionBar().hide();
          setContentView(R.layout.pool_view);
          
          Intent tmpIntent=getIntent();
          UBOBJ=tmpIntent.getParcelableExtra("globalUbObject");
          PBOBJ=tmpIntent.getParcelableExtra("globalPbObject");
          
          
          initialize();          
          setValues();
          
        }
      
      public void initialize()
      {
    	  tvSrcDest=(TextView)findViewById(R.id.tvSrcDest);
    	  tvVia=(TextView)findViewById(R.id.tvVia);
    	  tvDate=(TextView)findViewById(R.id.tvDate);
    	  tvTime=(TextView)findViewById(R.id.tvTime);
    	  tvSeats=(TextView)findViewById(R.id.tvSeats);
    	  tvVehicle=(TextView)findViewById(R.id.tvVehicle);
    	  tvUname=(TextView)findViewById(R.id.tvUname);
    	  tvUmobile=(TextView)findViewById(R.id.tvUmobile);
    	  tvUempid=(TextView)findViewById(R.id.tvUempid);
    	  tvUgender=(TextView)findViewById(R.id.tvUgender);
    	  
    	  tvAvailable=(TextView)findViewById(R.id.tvAvailable);
    	  tvBooked=(TextView)findViewById(R.id.tvBooked);
    	  tvMySeats=(TextView)findViewById(R.id.tvMySeats);
    	  
    	  etCounter=(EditText)findViewById(R.id.etCounter);
    	  
    	  bDecrement=(Button)findViewById(R.id.bIncrement);
    	  bIncrement=(Button)findViewById(R.id.bDecrement);
    	  bJoin=(Button)findViewById(R.id.bJoin);
    	  
    	  ibExit=(ImageButton)findViewById(R.id.imageButton2);
    	  
    	  bDecrement.setOnClickListener(poolViewOnClickListener);
    	  bIncrement.setOnClickListener(poolViewOnClickListener);
    	  bJoin.setOnClickListener(poolViewOnClickListener);
    	  ibExit.setOnClickListener(poolViewOnClickListener);
    	  
    	  
      }
      
      
      private OnClickListener poolViewOnClickListener =new OnClickListener() {
  		
  		@Override
  		public void onClick(View v) {
  			// TODO Auto-generated method stub
  			switch(v.getId())
  			{
  			case R.id.bIncrement: 
  				String counter=etCounter.getText().toString();
  				if(isDigit(counter))
  				{
  					int i=Integer.parseInt(counter);
  					i++;
  					etCounter.setText(String.valueOf(i));
  				}
  							
  				break;
  				
  			case R.id.bDecrement: 								
  								
  				String counter1=etCounter.getText().toString();
  				if(isDigit(counter1))
  				{
  					int i=Integer.parseInt(counter1);
  					i--;
  					if(i>=0)
  					{  					
  					  					
  					etCounter.setText(String.valueOf(i));
  					}
  					else
  					{
  						Toast.makeText(PoolView.this, "Counter should be greater or equal to 0", Toast.LENGTH_SHORT).show();
  					}
  				}
  							
  				break;
  				
  			case R.id.bJoin:
  				String strCounter=etCounter.getText().toString();
  				Toast.makeText(PoolView.this, strCounter, Toast.LENGTH_SHORT).show();
  				if(isDigit(strCounter)&& validateCounter(strCounter))
  				{
  					System.out.println("pv1");
  					if(bJoin.getText().equals("Join"))
  					{
  						System.out.println("pv"+bJoin.getText());
  						
  						if(strCounter.equals("0"))
  						{
  							Toast.makeText(PoolView.this, "Seats selection empty", Toast.LENGTH_SHORT).show();
  						}
  						else{
  							
  							int selCounter=Integer.parseInt(etCounter.getText().toString());
  							int mySeats=Integer.parseInt(tvMySeats.getText().toString());
  							
  							
  							PoolBean tmpPbObj=PBOBJ;
  							
  							
  							if(selCounter>mySeats)
  							{
  								String tmpstr1=String.valueOf(Integer.parseInt(tmpPbObj.getAvailableSeats())-(selCounter-mySeats));
  								tmpPbObj.setAvailableSeats(tmpstr1);
  								
  								tmpstr1=String.valueOf(Integer.parseInt(tmpPbObj.getBookedSeats())+(selCounter-mySeats));
  								tmpPbObj.setBookedSeats(tmpstr1);
  							}
  							else if(mySeats>selCounter)
  							{
  								String tmpstr1=String.valueOf(Integer.parseInt(tmpPbObj.getAvailableSeats())+(mySeats-selCounter));
  								tmpPbObj.setAvailableSeats(tmpstr1);
  								
  								tmpstr1=String.valueOf(Integer.parseInt(tmpPbObj.getBookedSeats())-(mySeats-selCounter));
  								tmpPbObj.setBookedSeats(tmpstr1);
  							}
  							
  						Boolean pdbObjRet=pdbObj.addSubscription(String.valueOf(UBOBJ.getId()),tmpPbObj,String.valueOf(selCounter));
  	  					
  	  					if(pdbObjRet)
  	  					{
  	  						Toast.makeText(PoolView.this, "added seat count", Toast.LENGTH_SHORT).show();
  	  						PBOBJ=pdbObj.fetchUpdatedPool(String.valueOf(tmpPbObj.getPoolid()));
  	  						if(PBOBJ!=null)
  	  						{
  	  						sbObj=pdbObj.fetchSubscription(UBOBJ,PBOBJ);
  	  						tvAvailable.setText(PBOBJ.getAvailableSeats());
  	  						tvBooked.setText(PBOBJ.getBookedSeats());
  	  						tvMySeats.setText(sbObj.getQty());
  	  						bJoin.setText("Update");
  	  						
  	  						}
  	  						else
  	  						{
  	  						Toast.makeText(PoolView.this, "Seat update failed", Toast.LENGTH_SHORT).show();
  	  						}
  	  						
  	  					}
  	  					else
  	  					{
  	  						Toast.makeText(PoolView.this, "Failed join", Toast.LENGTH_SHORT).show();
  	  					}
  	  					
//  	  				Intent intent = getIntent();
 // 	  				finish();
//  	  				startActivity(intent);
  					}
  					}
  					else if(bJoin.getText().equals("Update"))
  					{
  						System.out.println("pv"+bJoin.getText());
  						
  						if(strCounter.equals(sbObj.getQty()))
  						{
  							Toast.makeText(PoolView.this, "Same as your previous selection", Toast.LENGTH_SHORT).show();
  						}  						
  						else
  						{
  							
  							int selCounter=Integer.parseInt(etCounter.getText().toString());
  							int mySeats=Integer.parseInt(tvMySeats.getText().toString());
  							
  							
  							PoolBean tmpPbObj=PBOBJ;
  							
  							
  							if(selCounter>mySeats)
  							{
  								String tmpstr1=String.valueOf(Integer.parseInt(tmpPbObj.getAvailableSeats())-(selCounter-mySeats));
  								tmpPbObj.setAvailableSeats(tmpstr1);
  								
  								tmpstr1=String.valueOf(Integer.parseInt(tmpPbObj.getBookedSeats())+(selCounter-mySeats));
  								tmpPbObj.setBookedSeats(tmpstr1);
  							}
  							else if(mySeats>selCounter)
  							{
  								String tmpstr1=String.valueOf(Integer.parseInt(tmpPbObj.getAvailableSeats())+(mySeats-selCounter));
  								tmpPbObj.setAvailableSeats(tmpstr1);
  								
  								tmpstr1=String.valueOf(Integer.parseInt(tmpPbObj.getBookedSeats())-(mySeats-selCounter));
  								tmpPbObj.setBookedSeats(tmpstr1);
  							}				
  							
  						Boolean pdbObjRet=pdbObj.updateSubscription(String.valueOf(UBOBJ.getId()),tmpPbObj,String.valueOf(selCounter));
  	  					
  	  					if(pdbObjRet)
  	  					{
  	  						Toast.makeText(PoolView.this, "Updated seat count", Toast.LENGTH_SHORT).show();
  	  						PBOBJ=pdbObj.fetchUpdatedPool(String.valueOf(tmpPbObj.getPoolid()));
  	  						if(PBOBJ!=null)
  	  						{
  	  						tvAvailable.setText(PBOBJ.getAvailableSeats());
  	  						tvBooked.setText(PBOBJ.getBookedSeats());
  	  						tvMySeats.setText(sbObj.getQty());
  	  						
  	  						}
  	  						else
  	  						{
  	  						Toast.makeText(PoolView.this, "Seat update failed", Toast.LENGTH_SHORT).show();
  	  						}
	  						
  	  					}
  	  					else
  	  					{
  	  						Toast.makeText(PoolView.this, "Failed update", Toast.LENGTH_SHORT).show();
  	  					}
  	  					
 // 	  				Intent intent = getIntent();
 // 	  				finish();
 // 	  				startActivity(intent);
  					}
  					}
  					
  				}
  				break;
  				
  			case R.id.imageButton2:
//  				 Intent myIntent = getIntent();
//			    myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack  			   
//  			    finish();
  			    
  			  SharedPreferences sharedpreferences = getSharedPreferences(LoginPage.MyPREFERENCES, Context.MODE_PRIVATE);
  				      Editor editor = sharedpreferences.edit();
  				      editor.clear();
  				      editor.commit();
  				      moveTaskToBack(true); 
  				      PoolView.this.finish();
  				      
  				break;
  			}
  		}
  	};
  	
  	
  	protected boolean isDigit(String strCounter)
  	{
  		Pattern pattern;
		Matcher matcher; 
		
		String USERNAME_PATTERN = "^[0-9]$"; 
		
		pattern = Pattern.compile(USERNAME_PATTERN);
		
		matcher = pattern.matcher(strCounter);
		
		if(!matcher.matches())
		{
			Toast.makeText(PoolView.this, "counter should be digits", Toast.LENGTH_SHORT).show();
			return matcher.matches();
		}
		
		return matcher.matches();
  	}
  	
  	protected boolean validateCounter(String strCounter)
  	{
  		if(Integer.parseInt(strCounter)>Integer.parseInt(PBOBJ.getSeats()))
  		{
  			Toast.makeText(PoolView.this, "Counter should be less or equal to total seats", Toast.LENGTH_SHORT).show();
  			return false;
  		}
  		else if(Integer.parseInt(strCounter)<0)
  		{
  			Toast.makeText(PoolView.this, "Counter should be greater or equal to '0'", Toast.LENGTH_SHORT).show();
  			return false;
  		}
  		else
  		{
  			return true;
  		}
  	}
  	
  	
      protected void setValues()
      {
    	  tvSrcDest.setText(PBOBJ.getFrom()+"--"+PBOBJ.getTo());
    	  tvVia.setText(PBOBJ.getVia());
    	  tvDate.setText(PBOBJ.getDate());
    	  tvTime.setText(PBOBJ.getTime());
    	  tvSeats.setText(PBOBJ.getSeats());
    	  tvVehicle.setText(PBOBJ.getVehicle());
    	  tvUname.setText(UBOBJ.getName());
    	  tvUmobile.setText(UBOBJ.getMobile());
    	  tvUempid.setText(UBOBJ.getEmpid());
    	  tvUgender.setText(UBOBJ.getGender());
    	  tvAvailable.setText(PBOBJ.getAvailableSeats());
    	  tvBooked.setText(PBOBJ.getBookedSeats());
    	  
    	  
    	  sbObj=pdbObj.fetchSubscription(UBOBJ,PBOBJ);
    	  
    	  
    	  if(sbObj!=null)
    	  {
    		  etCounter.setText(sbObj.getQty());
    		  tvMySeats.setText(sbObj.getQty());
    		  bJoin.setText("Update");
    	  }
    	  else{
    		  etCounter.setText("0");
    		  tvMySeats.setText("0");
    		  bJoin.setText("Join");
    	  }
    	  
    	  if(String.valueOf(UBOBJ.getId()).equals(PBOBJ.getCreateId()))
    	  {
    		  bJoin.setVisibility(View.INVISIBLE);
    		  bIncrement.setVisibility(View.INVISIBLE);
    		  bDecrement.setVisibility(View.INVISIBLE);
    		  etCounter.setVisibility(View.INVISIBLE);
    	  }
    	  
      }
}