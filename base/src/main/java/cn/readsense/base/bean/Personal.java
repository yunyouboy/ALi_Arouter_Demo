package cn.readsense.base.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:qyg
 * DATE:2021/1/11 10:38
 * Description：
 **/
public class Personal implements Parcelable {
    int id;
    String name;
    long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Personal() {
    }

    public Personal(int id, String name, long time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    protected Personal(Parcel in) {
        id = in.readInt();
        name = in.readString();
        time = in.readLong();
    }

    public static final Creator<Personal> CREATOR = new Creator<Personal>() {
        @Override
        public Personal createFromParcel(Parcel in) {
            return new Personal(in);
        }

        @Override
        public Personal[] newArray(int size) {
            return new Personal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeLong(time);
    }

    @Override
    public String toString() {
        return "Personal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
