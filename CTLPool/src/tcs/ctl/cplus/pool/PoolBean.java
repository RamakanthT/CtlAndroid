package tcs.ctl.cplus.pool;

import android.os.Parcel;
import android.os.Parcelable;

public class PoolBean implements Parcelable{
	
	String from, to, via, date, time, vehicle, seats, createId, createDate, createTime;
	int poolid;

	public int getPoolid() {
		return poolid;
	}

	public void setPoolid(int poolid) {
		this.poolid = poolid;
	}

	public static Parcelable.Creator<PoolBean> getCreator() {
		return CREATOR;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}	
	
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(this.from);
		dest.writeString(this.to);
		dest.writeString(this.via);
		dest.writeString(this.date);
		dest.writeString(this.time);
		dest.writeString(this.vehicle);
		dest.writeString(this.seats);
		dest.writeString(this.createId);
		dest.writeString(this.createDate);
		dest.writeString(this.createTime);
	}
	
	
	public static final Parcelable.Creator<PoolBean> CREATOR = new Parcelable.Creator<PoolBean>() {
	    public PoolBean createFromParcel(Parcel in) {
	        return new PoolBean(in);
	    }

	    public PoolBean[] newArray(int size) {
	        return new PoolBean[size];
	    }
	};
	

	public PoolBean(Parcel in) {
		
	    this.from = in.readString();
	    this.to = in.readString();
	    this.via=in.readString();
	    this.date=in.readString();
	    this.time=in.readString();
	    this.vehicle=in.readString();
	    this.seats=in.readString();
	    this.createId=in.readString();
	    this.createDate=in.readString();
	    this.createTime=in.readString();
	}
	
	public PoolBean()
	{
		
	}

}
