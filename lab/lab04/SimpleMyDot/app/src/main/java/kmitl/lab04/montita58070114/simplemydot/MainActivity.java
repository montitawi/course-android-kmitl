package kmitl.lab04.montita58070114.simplemydot;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DotSerializable dotSerializable = new DotSerializable();
        dotSerializable.setCenterX(150);
        dotSerializable.setCenterY(150);
        dotSerializable.setRadius(30);


        final DotParcelable dotParcelable = new DotParcelable(150, 150, 50,20);

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


        dotView = (DotView) findViewById(R.id.dotView);
        dotView.setOnDotViewPressListener(this);

        dots = new Dots();
        dots.setListener(this);
    }


    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        Dot newDot = new Dot(centerX, centerY, 30, new Colors().createColor());
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
            Dot newDot = new Dot(x, y, 30, new Colors().createColor());
            dots.addDot(newDot);
        } else {
            alertDialog(dotPosition);
        }
    }


    public void onShareScreenshot(View view) {
        Bitmap bitmap = ScreenShot.getScreenShot(dotView);
        File saveFilePath = ScreenShot.getMainDirectoryName(this);
        File file = ScreenShot.store(bitmap, "screenshot.jpg", saveFilePath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/jpg");
        startActivity(Intent.createChooser(intent, "send image"));
    }
    public void alertDialog(final int dotPosition) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("What you want to do?");
        alertDialogBuilder
                .setMessage("")
                .setCancelable(true)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dots.removeBy(dotPosition);
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DotParcelable dotParcelable = new DotParcelable(dotPosition, dots.getAllDot().get(dotPosition).getColor(), dots.getAllDot().get(dotPosition).getRadius());
                        Intent editActIntent = new Intent(MainActivity.this, EditActivity.class);
                        editActIntent.putExtra("dotParcelable", dotParcelable);
                        startActivityForResult(editActIntent, 1);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            DotParcelable dotParcelable = data.getParcelableExtra("reDotParcelable");
            if (resultCode == Activity.RESULT_OK) {
                dots.getAllDot().get(dotParcelable.getDotPosition()).setColor(dotParcelable.getColor());
            } else {
                dots.getAllDot().get(dotParcelable.getDotPosition()).setRadius(dotParcelable.getRadius());
            }
        }
    }




}


