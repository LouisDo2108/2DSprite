package com.example.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

public class MySprite {
    public int textWidth = 400;
    public int nBMPs, iBMP;
    public int left, top, width, height, pokemon = -1;
    public Bitmap[] BMPs;
    public boolean orientation = true;
    public String pikachu = "National №: 025\nType: ELECTRIC\n" +
            "Species Mouse Pokémon \n" +
            "Height 0.4 m\n" +
            "Weight 6.0 kg\n" +
            "Abilities 1. Static\n" +
            "Lightning Rod (hidden ability)";

    public String blast = "National №: 025\nType: WATER/n\n" +
            "Species Shellfish Pokémon\n" +
            "Height 1.6 m/n\n" +
            "Weight 85.5 kg/n\n" +
            "Abilities 1. Torrent/n\n" +
            "Rain Dish (hidden ability)";

    public String charizard = "National №: 006\nType: FIRE FLYING\n" +
            "Species Flame Pokémon\n" +
            "Height 1.7 m\n" +
            "Weight 90.5 kg\n" +
            "Abilities 1. Blaze\n" +
            "Solar Power (hidden ability)";

    public String venusaur = "National №: 003\nType: Grass Poison\n" +
            "Species Seed Pokémon\n" +
            "Height 2.0 m\n" +
            "Weight 100.0 kg\n" +
            "Abilities 1. Overgrow\n" +
            "Chlorophyll (hidden ability)";
    TextPaint tp = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
    StaticLayout sl;

    public MySprite(Bitmap[] bmps, int left, int top, int width, int height) {
        BMPs = bmps;
        nBMPs = bmps.length;
        iBMP = 0;
        this.top = top;
        this.left = left;

        if (width == 0 && height == 0) {
            width = bmps[0].getWidth();
            height = bmps[0].getHeight();
        }
        this.width = width;
        this.height = height;
    }

    public void update() {
        iBMP = (iBMP + 1) % nBMPs;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(BMPs[iBMP], left, top, null);
        tp.setColor(Color.WHITE);
        tp.setTextSize(36);
        if (orientation) {
            switch (pokemon) {
                case 1:
                    sl = new StaticLayout(pikachu, tp, textWidth,
                            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    canvas.save();
                    canvas.translate(30, 200); //position text on the canvas
                    sl.draw(canvas);
                    canvas.restore();
                    break;
                case 2:
                    sl = new StaticLayout(blast, tp, textWidth,
                            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    canvas.save();
                    canvas.translate(200, 180); //position text on the canvas
                    sl.draw(canvas);
                    canvas.restore();
                    break;
                case 3:
                    sl = new StaticLayout(charizard, tp, textWidth,
                            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    canvas.save();
                    canvas.translate(420, 120); //position text on the canvas
                    sl.draw(canvas);
                    canvas.restore();
                    break;
                case 4:
                    sl = new StaticLayout(venusaur, tp, textWidth,
                            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    canvas.save();
                    canvas.translate(700, 200); //position text on the canvas
                    sl.draw(canvas);
                    canvas.restore();
                    break;
                default:
                    break;
            }
        } else {
            switch (pokemon) {
                case 1:
                    sl = new StaticLayout(pikachu, tp, textWidth,
                            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    canvas.save();
                    canvas.translate(310, 250); //position text on the canvas
                    sl.draw(canvas);
                    canvas.restore();
                    break;
                case 2:
                    sl = new StaticLayout(blast, tp, textWidth,
                            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    canvas.save();
                    canvas.translate(500, 200); //position text on the canvas
                    sl.draw(canvas);
                    canvas.restore();
                    break;
                case 3:
                    sl = new StaticLayout(charizard, tp, textWidth,
                            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    canvas.save();
                    canvas.translate(750, 140); //position text on the canvas
                    sl.draw(canvas);
                    canvas.restore();
                    break;
                case 4:
                    sl = new StaticLayout(venusaur, tp, textWidth,
                            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
                    canvas.save();
                    canvas.translate(1050, 200); //position text on the canvas
                    sl.draw(canvas);
                    canvas.restore();
                    break;
                default:
                    break;
            }
        }
    }

    public boolean isSelected(float x, float y) {
        return (x >= left && x <= left + width && y >= top && y <= y + height);
    }
}
