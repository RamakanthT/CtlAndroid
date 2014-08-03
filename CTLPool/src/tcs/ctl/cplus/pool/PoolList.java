package tcs.ctl.cplus.pool;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class PoolList extends Activity {

    ListView listview;
    
    ArrayList<PoolBean> poolAl=null;
    
    UserBean UBOBJ;
    PoolBean tmpPBOBJ, PBOBJ;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
        setContentView(R.layout.pool_list);
        
        Intent inte= getIntent();
        UBOBJ=(UserBean)inte.getParcelableExtra("globalUbObject");
        tmpPBOBJ=(PoolBean)inte.getParcelableExtra("searchPbObject");
        
        initialize();
        businessLogic();
        
    }
    
    protected void initialize()
    {
    	listview = (ListView) findViewById(R.id.listview);
    }
    
   
    protected void businessLogic()
    {
    	poolAl=new ArrayList<PoolBean>();
        
        PoolDb pdbObj = new PoolDb(this);
        
       
        poolAl=pdbObj.fetchPools(tmpPBOBJ);
        
        PoolAdapter poolObj = new PoolAdapter(this, poolAl);        
        listview.setAdapter(poolObj);        
        
        int i=0;
        PoolBean tmp;
        while(i<poolAl.size())
        {
        	tmp=poolAl.get(i);
        	System.out.println("id--"+tmp.getPoolid());
        	System.out.println("seats--"+tmp.getSeats());
        	i++;
        }
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Toast.makeText(PoolList.this, "myPos "+i, Toast.LENGTH_LONG).show();
                PBOBJ=poolAl.get(i); 
                
                Intent openPoolViewPage = new Intent(getApplicationContext(), PoolView.class);
                openPoolViewPage.putExtra("globalUbObject",UBOBJ);
                openPoolViewPage.putExtra("globalPbObject",PBOBJ);
				startActivity(openPoolViewPage); 
            }
        });
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		businessLogic();
	}
    
    
}
