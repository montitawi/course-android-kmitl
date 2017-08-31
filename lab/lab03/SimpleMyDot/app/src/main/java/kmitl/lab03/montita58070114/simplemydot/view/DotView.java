package kmitl.lab03.montita58070114.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import kmitl.lab03.montita58070114.simplemydot.model.Dot;


public class DotView extends View {

    private Paint paint;
    private Dot dot;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dot != null) {
            Random random = new Random();
            for(int i = 0 ;i < dot.getlistCenterx().size();i++){
                canvas.drawCircle(dot.getlistCenterx().get(i), dot.getlistCentery().get(i), dot.getlistRadius().get(i), paint);
                int colorDot = Color.argb(255,dot.getListred().get(i),dot.getListgreen().get(i),dot.getListblue().get(i));
                paint.setColor(colorDot);
            }

        }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }
}
