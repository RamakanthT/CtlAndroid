package tcs.ctl.cplus.pool;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class PoolList extends Activity {

    ListView listview;
    
    UserBean UBOBJ;
    PoolBean tmpPBOBJ;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pool_list);
        
        Intent inte= getIntent();
        UBOBJ=(UserBean)inte.getParcelableExtra("globalUbObject");
        tmpPBOBJ=(PoolBean)inte.getParcelableExtra("searchPbObject");
        
        initialize();
    }
    
    protected void initialize()
    {
    	listview = (ListView) findViewById(R.id.listview);
    }
    
   
    protected void businessLogic()
    {
    	ArrayList<PoolBean> poolAl=new ArrayList<PoolBean>();
        
        PoolDb pdbObj = new PoolDb(this);
        
        
        poolAl=pdbObj.fetchPools(tmpPBOBJ);
         
        PoolAdapter poolObj = new PoolAdapter(this, poolAl);        
        listview.setAdapter(poolObj);        
        
        
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Toast.makeText(PoolList.this, "myPos "+i, Toast.LENGTH_LONG).show();
            }
        });
    }
}
