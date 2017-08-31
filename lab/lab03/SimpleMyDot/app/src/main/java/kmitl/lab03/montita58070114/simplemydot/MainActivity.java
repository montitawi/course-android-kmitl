package kmitl.lab03.montita58070114.simplemydot;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;
import kmitl.lab03.montita58070114.simplemydot.model.Dot;
import kmitl.lab03.montita58070114.simplemydot.view.DotView;


public class MainActivity extends AppCompatActivity implements Dot.onDotChangeListener {

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot = new Dot(this, 0, 0, 20);
        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Random random = new Random();
                        dot.setCenterX((int) event.getX());
                        dot.setCenterY((int) event.getY());
                        dot.setRadius(random.nextInt(200));
                        dot.setRed(random.nextInt(256));
                        dot.setGreen(random.nextInt(256));
                        dot.setBlue(random.nextInt(256));
                        break;
                }

                return true;

            }
        });
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        dot.setCenterX(random.nextInt(this.dotView.getWidth()));
        dot.setCenterY(random.nextInt(this.dotView.getHeight()));
        dot.setRadius(random.nextInt(200));
        dot.setRed(random.nextInt(256));
        dot.setGreen(random.nextInt(256));
        dot.setBlue(random.nextInt(256));


    }

    public void onClearDot(View view) {
        dot.clearDot();
    }

    @Override
    public void onDotChangeListener(Dot dot) {

        dotView.setDot(dot);
        dotView.invalidate();
    }
}
