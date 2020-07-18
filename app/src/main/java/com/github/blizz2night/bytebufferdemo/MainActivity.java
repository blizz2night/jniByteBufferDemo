package com.github.blizz2night.bytebufferdemo;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);
        byteBuffer.order(ByteOrder.nativeOrder());
        Log.i(TAG, "native-lib RED big-endian="+Integer.toHexString(Color.RED).toUpperCase());
        byteBuffer.putInt(Color.RED);
        byteFromJNI(byteBuffer);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void byteFromJNI(ByteBuffer byteBuffer);
}
