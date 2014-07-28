package com.example.test;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class StackActivity extends Activity {

    ListView listview;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listview = (ListView) findViewById(R.id.listview);
        ArrayList<PoolBean> poolAl=new ArrayList<PoolBean>();
        for(int i=0;i<10;i++)
        {
        	PoolBean poolObj=new PoolBean();
        	
        	poolObj.setPseats("seats"+i);
        	poolObj.setPfrom("from"+i);
        	poolObj.setPto("to"+i);
        	poolObj.setPvia("via"+i);
        	poolObj.setPdate("date"+i);
        	poolObj.setPtime("time"+i);
        	
        	poolAl.add(poolObj);
        	
        	
        }
        
        YourAdapter yaObj = new YourAdapter(this, poolAl);
        listview.setAdapter(yaObj);        
        
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Toast.makeText(StackActivity.this, "myPos "+i, Toast.LENGTH_LONG).show();
            }
        });
    }
    
   
}