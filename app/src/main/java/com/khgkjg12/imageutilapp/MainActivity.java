package com.khgkjg12.imageutilapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.khgkjg12.imageutil.ImageUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView testImage = findViewById(R.id.test_image);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Bitmap result = ImageUtil.getCircledImage(getResources(), bitmap );
        bitmap.recycle();
        testImage.setImageBitmap(result);

    }
}
