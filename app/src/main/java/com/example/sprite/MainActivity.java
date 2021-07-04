package com.example.sprite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.SpringAnimation;

import android.content.res.XmlResourceParser;
import android.graphics.Canvas;

import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sprite.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AnimatedSprite animatedSprite;
    private ArrayList<MySprite> sprites;
    private MySprite sprite;
    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnimatedSprite animatedSprite = new AnimatedSprite(this);
        setContentView(animatedSprite);
    }
}