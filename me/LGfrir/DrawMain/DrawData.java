package me.LGfrir.DrawMain;

public class DrawData {
    private static int totalTargetCharacters;
    private static int totalTargetWeapons;

    private static int currentTargetCharacters;
    private static int currentTargetWeapons;

    private static int character1;
    private static int character2;
    private static int character3;

    private static int stars;
    private static int drawTimes;
    private static int totalDrawTimes;

    private static int timeTilFIChar = 1;
    private static int timeTilFOChar = 1;
    private static boolean lastMissFIChar = false;
    private static boolean lastMissFOChar = false;


    private static int timeTilFIWeapon = 1;
    private static int timeTilFOWeapon = 1;
    private static boolean lastMissFIWeapon = false;

    private static int counter = 1;

    public static void SaveCharacter(
                    int currentTargetCharacters,
                    int character1, int character2, int character3,
                    int stars, int drawTimes, int totalDrawTimes,
                    int timeTilFIChar, int timeTilFOChar, boolean lastMissFIChar, boolean lastMissFOChar,
                    int counter) {
        DrawData.currentTargetCharacters = currentTargetCharacters;
        DrawData.character1 = character1;
        DrawData.character2 = character2;
        DrawData.character3 = character3;
        DrawData.stars = stars;
        DrawData.drawTimes = drawTimes;
        DrawData.totalDrawTimes = totalDrawTimes;
        DrawData.timeTilFIChar = timeTilFIChar;
        DrawData.timeTilFOChar = timeTilFOChar;
        DrawData.lastMissFIChar = lastMissFIChar;
        DrawData.lastMissFOChar = lastMissFOChar;
        DrawData.counter = counter;
    }


    public static void SaveWeapon(int currentTargetWeapons,
                    int stars, int drawTimes, int totalDrawTimes,
                    int timeTilFIWeapon, int timeTilFOWeapon, boolean lastMissFIWeapon) {
        DrawData.currentTargetWeapons = currentTargetWeapons;
        DrawData.stars = stars;
        DrawData.drawTimes = drawTimes;
        DrawData.totalDrawTimes = totalDrawTimes;
        DrawData.timeTilFIWeapon = timeTilFIWeapon;
        DrawData.timeTilFOWeapon = timeTilFOWeapon;
        DrawData.lastMissFIWeapon = lastMissFIWeapon;
    }

    public static void Init(int currentTargetCharacters, int currentTargetWeapons,
                                int character1, int character2, int character3,
                                int stars, int drawTimes,
                                int timeTilFIChar,  boolean lastMissFIChar,
                                int timeTilFIWeapon, boolean lastMissFIWeapon) {
        DrawData.currentTargetCharacters = currentTargetCharacters;
        DrawData.currentTargetWeapons = currentTargetWeapons;
        DrawData.character1 = character1;
        DrawData.character2 = character2;
        DrawData.character3 = character3;
        DrawData.stars = stars;
        DrawData.drawTimes = drawTimes;
        DrawData.totalDrawTimes = 0;
        DrawData.timeTilFIChar = timeTilFIChar;
        DrawData.timeTilFOChar = 1;
        DrawData.lastMissFIChar = lastMissFIChar;
        DrawData.lastMissFOChar = false;
        DrawData.timeTilFIWeapon = timeTilFIWeapon;
        DrawData.timeTilFOWeapon = 1;
        DrawData.lastMissFIWeapon = lastMissFIWeapon;
        counter = 1;
    }


    public static int getTotalTargetCharacters() {
        return totalTargetCharacters;
    }

    public static int getTotalTargetWeapons() {
        return totalTargetWeapons;
    }

    public static int getCurrentTargetCharacters() {
        return currentTargetCharacters;
    }

    public static int getCurrentTargetWeapons() {
        return currentTargetWeapons;
    }

    public static int getCharacter1() {
        return character1;
    }

    public static int getCharacter2() {
        return character2;
    }

    public static int getCharacter3() {
        return character3;
    }

    public static int getStars() {
        return stars;
    }

    public static int getDrawTimes() {
        return drawTimes;
    }

    public static int getTotalDrawTimes() {
        return totalDrawTimes;
    }

    public static int getTimeTilFIChar() {
        return timeTilFIChar;
    }

    public static int getTimeTilFOChar() {
        return timeTilFOChar;
    }

    public static boolean isLastMissFIChar() {
        return lastMissFIChar;
    }

    public static boolean isLastMissFOChar() {
        return lastMissFOChar;
    }

    public static int getTimeTilFIWeapon() {
        return timeTilFIWeapon;
    }

    public static int getTimeTilFOWeapon() {
        return timeTilFOWeapon;
    }

    public static boolean isLastMissFIWeapon() {
        return lastMissFIWeapon;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setTotalTargetCharacters(int totalTargetCharacters) {
        DrawData.totalTargetCharacters = totalTargetCharacters;
    }

    public static void setTotalTargetWeapons(int totalTargetWeapons) {
        DrawData.totalTargetWeapons = totalTargetWeapons;
    }
}
