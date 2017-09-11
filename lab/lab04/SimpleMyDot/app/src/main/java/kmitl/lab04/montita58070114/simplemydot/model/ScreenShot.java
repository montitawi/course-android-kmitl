package kmitl.lab04.montita58070114.simplemydot.model;


import android.graphics.Bitmap;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ScreenShot {

    public Bitmap createBitmap(View view) {

        view.buildDrawingCache(false);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.destroyDrawingCache();
        return bitmap;

    }

    public void saveBitmap(ScreenShot bitmap, String fileName) {
        try {
            FileOutputStream stream = new FileOutputStream(getCacheDir() +
                    File.separator + fileName);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

