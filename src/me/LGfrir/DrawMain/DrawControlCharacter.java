package me.LGfrir.DrawMain;

import java.util.Random;

import static me.LGfrir.BasicPr.DrawCharacterPr.CharacterCalcFI;
import static me.LGfrir.BasicPr.DrawCharacterPr.CharacterCalcFO;
import static me.LGfrir.DrawMain.Main.gameSelection;

public class DrawControlCharacter {
    private static int currentTargetCharacters;
    private static int character1;
    private static int character2;
    private static int character3;
    private static int stars;
    private static int drawTimes;
    private static int totalDrawTimes;

    private static int timeTilFIChar;
    private static int timeTilFOChar;
    private static boolean lastMissFIChar;
    private static boolean lastMissFOChar;

    private static int counter;


    private static final Random rand = new Random();

    private static void init() {
        currentTargetCharacters = DrawData.getCurrentTargetCharacters();
        character1 = DrawData.getCharacter1();
        character2 = DrawData.getCharacter2();
        character3 = DrawData.getCharacter3();
        stars = DrawData.getStars();
        drawTimes = DrawData.getDrawTimes();
        timeTilFOChar = DrawData.getTimeTilFOChar();
        timeTilFIChar = DrawData.getTimeTilFIChar();
        lastMissFIChar = DrawData.isLastMissFIChar();
        lastMissFOChar = DrawData.isLastMissFOChar();
        totalDrawTimes = DrawData.getTotalDrawTimes();
        counter = DrawData.getCounter();
    }

    private static int rand1k() {
        return rand.nextInt(1000) + 1;
    }

    public static void DrawCharacter() {

        init();
        while (currentTargetCharacters < DrawData.getTotalTargetCharacters() && drawTimes > 0) {
            drawTimes--;
            goDraw();
            drawTimes += stars / 5;
            stars %= 5;
            totalDrawTimes++;
        }
        DrawData.SaveCharacter(currentTargetCharacters, character1, character2, character3,
                stars, drawTimes, totalDrawTimes, timeTilFIChar, timeTilFOChar, lastMissFIChar, lastMissFOChar, counter);
    }

    private static void goDraw() {

        if (rand1k() <= CharacterCalcFI(timeTilFIChar)) {
            switch (gameSelection){
                case 1:
                    goFI_GenshenImpact(); break;
                case 2:
                    goFI_StarRail();break;
            }
        }
        else if (rand1k() <= CharacterCalcFO(timeTilFOChar)) goFO();
        else goSimple();

    }

    private static void goFI_GenshenImpact() {
        boolean miss = rand.nextBoolean();
        if (lastMissFIChar) miss = false;
        if (!lastMissFIChar && counter == 3) {
            miss = false;
            counter = 2;
        }

        if (miss) {
            lastMissFIChar = true;
            stars += 10;
            counter++;
        } else {
            if (currentTargetCharacters != 0) stars += 10;
            if (!lastMissFIChar && counter > 0) counter--;
            lastMissFIChar = false;
            currentTargetCharacters++;
        }

        timeTilFIChar = 1;
        timeTilFOChar++;
    }

    private static void goFI_StarRail() {
        boolean miss = rand.nextBoolean();
        if (lastMissFIChar) miss = false;

        if (miss) {
            lastMissFIChar = true;
            stars += 10;
            counter++;
        } else {
            if (currentTargetCharacters != 0) stars += 10;
            lastMissFIChar = false;
            currentTargetCharacters++;
        }

        timeTilFIChar = 1;
        timeTilFOChar++;
    }

    private static void goFO() {
        boolean miss = rand.nextBoolean();
        if (lastMissFOChar) miss = false;
        if (miss) {
            lastMissFOChar = true;
            stars += 2;

        } else {
            lastMissFOChar = false;

            //四星三选一
            int command = rand.nextInt(3) + 1;
            switch (command) {
                case 1:
                    if (character1 >= 7) stars += 5;
                    else if (character1 != 0) {
                        character1++;
                        stars += 2;
                    } else character1++;
                    break;
                case 2:
                    if (character2 >= 7) stars += 5;
                    else if (character2 != 0) {
                        character2++;
                        stars += 2;
                    } else character2++;
                    break;
                case 3:
                    if (character3 >= 7) stars += 5;
                    else if (character3 != 0) {
                        character3++;
                        stars += 2;
                    } else character3++;
                    break;
            }


        }
        timeTilFOChar = 1;
        timeTilFIChar++;

    }

    private static void goSimple() {
        timeTilFOChar++;
        timeTilFIChar++;
    }

}
