package me.LGfrir.BasicPr;

public class DrawCharacterPr {
    /**
     * 代表的是这一抽下去之后一共有多少抽，也就是说从来不会出现currentDraw = 0的情况
     *
     * */
    public static int CharacterCalcFI(int currentDraw){
        if(0 <= currentDraw && currentDraw <= 73) return 6;
        if(73 < currentDraw && currentDraw < 90) return 6 + (currentDraw - 73) * 60;
        return 1000;
    }

    public static int CharacterCalcFO(int currentDraw){
        if(0 < currentDraw && currentDraw <= 8) return 51;
        if(currentDraw == 9) return 561;
        else return 1000;
    }

}

