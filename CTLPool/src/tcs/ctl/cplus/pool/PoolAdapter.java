package tcs.ctl.cplus.pool;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class PoolAdapter extends BaseAdapter {

    Context context;
    String[] data;
    ArrayList<PoolBean> poolAl;
    private static LayoutInflater inflater = null;
    
    TextView pfromTv,ptoTv,pviaTv,pseatsTv,pdateTv,ptimeTv;

    public PoolAdapter(Context context, ArrayList<PoolBean> poolAl) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.poolAl = poolAl;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return poolAl.size();
    	
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return poolAl.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.pool_row, null);
        
        pseatsTv = (TextView) vi.findViewById(R.id.pseats);
        pfromTv = (TextView) vi.findViewById(R.id.pfrom);
        ptoTv = (TextView) vi.findViewById(R.id.pto);
        pviaTv = (TextView) vi.findViewById(R.id.pvia);
        pdateTv = (TextView) vi.findViewById(R.id.pdate);
        ptimeTv = (TextView) vi.findViewById(R.id.ptime);
        
        PoolBean poolObj=poolAl.get(position);

        pseatsTv.setText(poolObj.getSeats());
        pfromTv.setText(poolObj.getFrom());
        ptoTv.setText(poolObj.getTo());
        pviaTv.setText(poolObj.getVia());
        pdateTv.setText(poolObj.getDate());
        ptimeTv.setText(poolObj.getTime());
        
        return vi;
    }

	
}
