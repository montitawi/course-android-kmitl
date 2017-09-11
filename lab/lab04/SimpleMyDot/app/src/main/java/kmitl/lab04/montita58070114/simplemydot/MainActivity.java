package kmitl.lab04.montita58070114.simplemydot;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.Random;

import kmitl.lab04.montita58070114.simplemydot.model.Colors;
import kmitl.lab04.montita58070114.simplemydot.model.Dot;
import kmitl.lab04.montita58070114.simplemydot.model.DotParcelable;
import kmitl.lab04.montita58070114.simplemydot.model.DotSerializable;
import kmitl.lab04.montita58070114.simplemydot.model.Dots;
import kmitl.lab04.montita58070114.simplemydot.model.ScreenShot;
import kmitl.lab04.montita58070114.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
        implements Dots.OnDotsChangeListener, DotView.OnDotViewPressListener {

    private DotView dotView;
    private Dots dots;
    private ScreenShot screen;
    private ScreenShot bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DotSerializable dotSerializable = new DotSerializable();
        dotSerializable.setCenterX(150);
        dotSerializable.setCenterY(150);
        dotSerializable.setRadius(30);

        final DotParcelable dotParcelable = new DotParcelable(150, 150, 50);

        Button btnOpenActivity = (Button) findViewById(R.id.btnOpenActivity);
        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("xValue", 30);
                intent.putExtra("dotSerializable", dotSerializable);
                intent.putExtra("dotParcelable", dotParcelable);
                startActivity(intent);
            }
        });



        Button btnShare = (Button) findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen = new ScreenShot();
                bitmap = new ScreenShot();
                bitmap.createBitmap(dotView);
                String fileName = "dotViewImg.png";
                screen.saveBitmap(bitmap, fileName);
                File file = new File(getCacheDir(), fileName);
                Uri contentUri = FileProvider.getUriForFile(getApplicationContext(),
                        "kmitl.lab04.montita58070114.simplemydot.fileprovider", file);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, "Share to"));

            }

        });

        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, new Colors().getColor());
        dots.addDot(newDot);
    }

    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    public void onRemoveAll(View view) {
        dots.clearAll();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        int dotPosition = dots.findDot(x, y);
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, 30, new Colors().getColor());
            dots.addDot(newDot);
        } else {
            dots.removeBy(dotPosition);
        }
    }
}