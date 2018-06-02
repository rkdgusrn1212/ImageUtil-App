package com.khgkjg12.imageutil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.NonNull;

/**
 * Created by Hyun on 2018-06-01.
 */

public class ImageUtil {

    public static Bitmap getMaskedImage(@NonNull Bitmap oriBitmap, @NonNull Bitmap maskBitmap){

        Bitmap result = Bitmap.createBitmap(maskBitmap.getWidth(),maskBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int scaledHeight;
        int scaledWidth;
        int x =0, y=0;
        if(maskBitmap.getWidth()*oriBitmap.getHeight()>maskBitmap.getHeight()*oriBitmap.getWidth()){
            scaledWidth=maskBitmap.getWidth();
            scaledHeight=scaledWidth*oriBitmap.getHeight()/oriBitmap.getWidth();
            y=(int)((scaledHeight-maskBitmap.getHeight())*0.5);
        }else{
            scaledHeight=maskBitmap.getHeight();
            scaledWidth=scaledHeight*oriBitmap.getWidth()/oriBitmap.getHeight();
            x=(int)((scaledWidth-maskBitmap.getWidth())*0.5);
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(oriBitmap, scaledWidth, scaledHeight, false);

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(bitmap, -x, -y, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(maskBitmap, 0, 0, paint);
        bitmap.recycle();
        return result;
    }

    public static Bitmap getCircledImage(@NonNull Resources res, @NonNull Bitmap oriBitmap){
        Bitmap mask = BitmapFactory.decodeResource(res, R.drawable.circle_mask);
        Bitmap result = getMaskedImage(oriBitmap,mask);
        mask.recycle();
        return result;
    }
}
