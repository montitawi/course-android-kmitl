package kmitl.lab04.montita58070114.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DotParcelable implements Parcelable {
    private int centerX;
    private int centerY;
    private int radius;

    @Override
    public String toString() {
        return "DotParcelable{" +
                "centerX=" + centerX +
                ", centerY=" + centerY +
                ", radius=" + radius +
                '}';
    }

    public DotParcelable(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public DotParcelable(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(centerX);
        dest.writeInt(centerY);
        dest.writeInt(radius);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DotParcelable> CREATOR = new Creator<DotParcelable>() {
        @Override
        public DotParcelable createFromParcel(Parcel in) {
            return new DotParcelable(in);
        }

        @Override
        public DotParcelable[] newArray(int size) {
            return new DotParcelable[size];
        }
    };
}
