package com.example.sprite;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AnimatedSprite extends View {
    public ArrayList<MySprite> getSprites() {
        return sprites;
    }

    private ArrayList<MySprite> sprites;
    private final Class<?> drawable_class = R.drawable.class;

    private final int pikachu_portrait_left = 50;
    private final int blastoise_portrait_left = 200;
    private final int charizard_portrait_left = 420;
    private final int venusaur_portrait_left = 700;

    private final int pikachu_land_left = 350;
    private final int blastoise_land_left = 500;
    private final int charizard_land_left = 720;
    private final int venusaur_land_left = 1000;

    private final int pikachu_top = 650;
    private final int blastoise_top = 500;
    private final int charizard_top = 400;
    private final int venusaur_top = 580;
    private MySprite sprite;
    private Boolean orientation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

    public AnimatedSprite(Context context) {
        super(context);
        prepareContent();
    }

    private void prepareContent() {
        sprites = new ArrayList<>();
        CreateBackground(0, 0, R.drawable.forest);
        if (orientation) {
            CreatePokemon(pikachu_portrait_left, pikachu_top, 33, "pikachu", drawable_class);
            CreatePokemon(blastoise_portrait_left, blastoise_top, 74, "blastoise", drawable_class);
            CreatePokemon(charizard_portrait_left, charizard_top, 47, "charizard", drawable_class);
            CreatePokemon(venusaur_portrait_left, venusaur_top, 71, "venusaur", drawable_class);
        } else {
            CreatePokemon(pikachu_land_left, pikachu_top, 33, "pikachu", drawable_class);
            CreatePokemon(blastoise_land_left, blastoise_top, 74, "blastoise", drawable_class);
            CreatePokemon(charizard_land_left, charizard_top, 47, "charizard", drawable_class);
            CreatePokemon(venusaur_land_left, venusaur_top, 71, "venusaur", drawable_class);
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < sprites.size(); i++) {
                    sprites.get(i).update();
                }
                postInvalidate();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 17);
    }

    private void CreatePokemon(int left, int top, final int num_bitmap, String resName, Class<?> c) {
        Bitmap[] bitmaps = new Bitmap[num_bitmap];
        String index;
        for (int i = 0; i < num_bitmap; ++i) {
            index = resName + (i >= 10 ? String.valueOf(i) : "0" + String.valueOf(i));
            bitmaps[i] = BitmapFactory.decodeResource(getResources(), getResId(index, c));
        }
        MySprite mySprite = new MySprite(bitmaps, left, top, 0, 0);
        if (!this.orientation) mySprite.orientation = false;
        sprites.add(mySprite);
    }

    private void CreateBackground(int left, int top, int resID) {
        createSingleSpriteImage(left, top, resID);
    }

    private void createSingleSpriteImage(int left, int top, int resID) {
        Bitmap[] bitmaps = new Bitmap[1];
        bitmaps[0] = BitmapFactory.decodeResource(getResources(), resID);
        MySprite mySprite = new MySprite(bitmaps, left, top, 0, 0);
        if (!this.orientation) mySprite.orientation = false;
        sprites.add(mySprite);
    }

    public AnimatedSprite(Context context, AttributeSet attrs) {
        super(context, attrs);
        prepareContent();
    }

    public AnimatedSprite(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepareContent();
    }

    public AnimatedSprite(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        prepareContent();
    }

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < sprites.size(); ++i) {
            sprites.get(i).draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {

        int maskedAction = event.getActionMasked();
        float x = event.getX();
        float y = event.getY();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                getSelectedSpriteIndex(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_POINTER_UP:
                sprite.pokemon = -1;
                invalidate();
                break;
        }
        return true;
    }

    private void selectPokemon(float x, float y, int i, int w, int h) {
        sprite = sprites.get(i);
        if (orientation) {
            if (check(pikachu_portrait_left, pikachu_top, x, y, w, h)) {
                sprite.pokemon = 1;
                return;
            }
            if (check(blastoise_portrait_left, blastoise_top, x, y, w, h)) {
                sprite.pokemon = 2;
                return;
            }
            if (check(charizard_portrait_left, charizard_top, x, y, w, h)) {
                sprite.pokemon = 3;
                return;
            }
            if (check(venusaur_portrait_left, venusaur_top, x, y, w, h)) {
                sprite.pokemon = 4;
                return;
            }
        } else {
            if (check(pikachu_land_left, pikachu_top, x, y, w, h)) {
                sprite.pokemon = 1;
                return;
            }
            if (check(blastoise_land_left, blastoise_top, x, y, w, h)) {
                sprite.pokemon = 2;
                return;
            }
            if (check(charizard_land_left, charizard_top, x, y, w, h)) {
                sprite.pokemon = 3;
                return;
            }
            if (check(venusaur_land_left, venusaur_top, x, y, w, h)) {
                sprite.pokemon = 4;
                return;
            }
        }
        sprite.pokemon = -1;
    }

    private boolean check(int left, int top, float x, float y, int w, int h) {
        return (x >= (left + 0.25 * w) && x <= (left + 0.75 * w)
                && (y >= top + 0.25 * h) && (y <= top + 0.75 * h));
    }


    private int getSelectedSpriteIndex(float x, float y) {
        for (int i = sprites.size() - 1; i >= 0; i--) {
            if (sprites.get(i).isSelected(x, y)) {
                selectPokemon(x, y, i, sprites.get(i).width, sprites.get(i).height);
                return i;
            }
        }
        return -1;
    }

}
