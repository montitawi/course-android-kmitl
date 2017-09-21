package kmitl.lab04.montita58070114.simplemydot.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.util.Random;

import kmitl.lab04.montita58070114.simplemydot.R;
import kmitl.lab04.montita58070114.simplemydot.model.Colors;
import kmitl.lab04.montita58070114.simplemydot.model.Dot;
import kmitl.lab04.montita58070114.simplemydot.model.Dots;
import kmitl.lab04.montita58070114.simplemydot.model.ScreenShot;
import kmitl.lab04.montita58070114.simplemydot.view.DotView;

public class DotFragment extends Fragment implements DotView.OnDotViewPressListener, Dots.OnDotsChangeListener, View.OnClickListener {
    public interface OnDotSelectListener {
        void onDotSelect(Dot dot, int position);
    }

    private OnDotSelectListener listener;
    private Dots dots;
    private DotView dotView;
    private static final String ALLDOT = "allDot";

    public DotFragment() {
        // Required empty public constructor
    }


    public static DotFragment newInstance() {

        Bundle args = new Bundle();
        DotFragment fragment = new DotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            dots = savedInstanceState.getParcelable(ALLDOT);
        } else {
            dots = new Dots();
        }
        dots.setListener(this);
        listener = (OnDotSelectListener) getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dotView.setDots(dots);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ALLDOT, dots);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dot, container, false);
        initSetup(rootView);
        //dots = new Dots();
        dots.setListener(this);
        return rootView;
    }

    private void initSetup(View rootView) {
        dotView = (DotView) rootView.findViewById(R.id.fragmentDot);
        dotView.setOnDotViewPressListener(this);
        Button btnRandom = (Button) rootView.findViewById(R.id.btnRandom);
        Button btnClear = (Button) rootView.findViewById(R.id.btnClear);
        Button btnUndo = (Button) rootView.findViewById(R.id.btnUndo);
        Button btnShare = (Button) rootView.findViewById(R.id.btnShare);
        btnRandom.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    public void onRandomDot() {
        Random random = new Random();
        int centerX = random.nextInt(dotView.getWidth());
        int centerY = random.nextInt(dotView.getHeight());
        int radius = random.nextInt(200);
        Dot newDot = new Dot(centerX, centerY, radius, new Colors().createColor());
        dots.addDot(newDot);
    }

    public void onRemoveAll() {
        dots.clearAll();
    }

    private void onUndo() {
        dots.undo();
    }

    public void onShareScreenshot() {
        Bitmap bitmap = ScreenShot.getScreenShot(dotView);
        File saveFilePath = ScreenShot.getMainDirectoryName(getActivity());
        File file = ScreenShot.store(bitmap, "screenshot.jpg", saveFilePath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/jpg");
        startActivity(Intent.createChooser(intent, "send image"));
    }

    @Override
    public void onDotViewPressed(int x, int y, String status) {
        Random random = new Random();
        int radius = random.nextInt(200);
        int dotPosition = dots.findDot(x, y);
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, radius, new Colors().createColor());
            dots.addDot(newDot);
        } else {
            if (status.equals("short")) {
                dots.removeBy(dotPosition);
            } else {
                listener.onDotSelect(dots.getAllDot().get(dotPosition), dotPosition);

            }
        }
    }


    @Override
    public void onDotsChanged(Dots dots) {
        dotView.setDots(dots);
        dotView.invalidate();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnRandom:
                onRandomDot();
                break;
            case R.id.btnClear:
                onRemoveAll();
                break;
            case R.id.btnUndo:
                onUndo();
                break;
            case R.id.btnShare:
                onShareScreenshot();
                break;

        }

    }

}