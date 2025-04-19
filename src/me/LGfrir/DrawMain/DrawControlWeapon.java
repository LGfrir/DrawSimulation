package me.LGfrir.DrawMain;

import java.util.Random;

import static me.LGfrir.BasicPr.DrawWeaponPr.WeaponCalcFI;
import static me.LGfrir.BasicPr.DrawWeaponPr.WeaponCalcFO;
import static me.LGfrir.DrawMain.Main.gameSelection;

public class DrawControlWeapon {
    private static int currentTargetWeapons;
    private static int stars;
    private static int drawTimes;
    private static int totalDrawTimes;

    private static int timeTilFIWeapon = 1;
    private static int timeTilFOWeapon = 1;
    private static boolean lastMissFIWeapon = false;

    private static final Random rand = new Random();

    private static int rand1k() {
        return rand.nextInt(1000) + 1;
    }

    private static void init() {
        currentTargetWeapons = DrawData.getCurrentTargetWeapons();
        stars = DrawData.getStars();
        drawTimes = DrawData.getDrawTimes();
        totalDrawTimes = DrawData.getTotalDrawTimes();
        timeTilFIWeapon = DrawData.getTimeTilFIWeapon();
        timeTilFOWeapon = DrawData.getTimeTilFOWeapon();
        lastMissFIWeapon = DrawData.isLastMissFIWeapon();
    }

    public static void DrawWeapon() {
        init();
        while (currentTargetWeapons < DrawData.getTotalTargetWeapons() && drawTimes > 0) {
            drawTimes--;
            goDraw();
            drawTimes += stars / 5;
            stars %= 5;
            totalDrawTimes++;
        }
        DrawData.SaveWeapon(currentTargetWeapons, stars, drawTimes, totalDrawTimes,
                timeTilFIWeapon, timeTilFOWeapon, lastMissFIWeapon);

    }

    private static void goDraw() {

        if (rand1k() <= WeaponCalcFI(timeTilFIWeapon)) {
            switch (gameSelection) {
                case 1:
                    goFI_GenshenImpact();break;
                case 2:
                    goFI_StarRail();break;
            }

        } else if (rand1k() <= WeaponCalcFO(timeTilFOWeapon)) goFO();
        else goSimple();

    }

    private static void goFI_GenshenImpact() {
        int miss = rand1k();
        if (lastMissFIWeapon) miss = 1;

        if (miss > 375) {
            lastMissFIWeapon = true;
            stars += 10;
        } else {
            lastMissFIWeapon = false;
            stars += 10;
            currentTargetWeapons++;
        }

        timeTilFIWeapon = 1;
        timeTilFOWeapon++;
    }

    private static void goFI_StarRail() {
        int miss = rand1k();
        if (lastMissFIWeapon) miss = 1;

        if (miss > 750) {
            lastMissFIWeapon = true;
            stars += 10;
        } else {
            lastMissFIWeapon = false;
            stars += 10;
            currentTargetWeapons++;
        }

        timeTilFIWeapon = 1;
        timeTilFOWeapon++;
    }


    private static void goFO() {
        stars += 2;
        timeTilFOWeapon = 1;
        timeTilFIWeapon++;
    }

    private static void goSimple() {
        timeTilFOWeapon++;
        timeTilFIWeapon++;
    }

}
