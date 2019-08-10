package info.androidhive.materialdesign.Constant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import info.androidhive.materialdesign.R;


/**
 * Created by Dilip on 15/3/16.
 */
public class CustomProgressBar extends Dialog {

    Context context;

    public CustomProgressBar(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.custom_progressbar);

        setCancelable(true);
        setCanceledOnTouchOutside(true);

//        set backgroung transparent
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

//        WindowManager manager = (WindowManager) activity.getSystemService(Activity.WINDOW_SERVICE);
//        int width, height;
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
//            width = manager.getDefaultDisplay().getWidth();
//            height = manager.getDefaultDisplay().getHeight();
//        } else {
//            Point point = new Point();
//            manager.getDefaultDisplay().getSize(point);
//            width = point.x;
//            height = point.y;
//        }
//        getWindow().setLayout(width, height);

    }

}
