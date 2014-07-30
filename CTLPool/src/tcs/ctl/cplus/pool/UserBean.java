package tcs.ctl.cplus.pool;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable{
	
	int id=0;
	String name,gender,empid,mobile,password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.name);
		dest.writeString(this.mobile);
		dest.writeString(this.password);
		dest.writeString(this.empid);
		dest.writeString(this.gender);
		dest.writeInt(this.id);
	}
	
	
	public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
	    public UserBean createFromParcel(Parcel in) {
	        return new UserBean(in);
	    }

	    public UserBean[] newArray(int size) {
	        return new UserBean[size];
	    }
	};
	

	public UserBean(Parcel in) {
	    this.name = in.readString();
	    this.mobile = in.readString();
	    this.password=in.readString();
	    this.empid=in.readString();
	    this.gender=in.readString();
	    this.id=in.readInt();
	}
	
	public UserBean()
	{
		
	}
}
