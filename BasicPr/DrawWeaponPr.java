package me.LGfrir.BasicPr;

public class DrawWeaponPr {
    public static int WeaponCalcFI(int currentDraw){
        if(0 < currentDraw && currentDraw <= 62) return 7;
        if(62 < currentDraw && currentDraw <= 74) return 7 + (currentDraw - 62) * 70;
        if(75 < currentDraw && currentDraw <= 79) return 777 + (currentDraw - 73) * 35;
        return 1000;
    }

    public static int WeaponCalcFO(int currentDraw){
        if(0 < currentDraw && currentDraw <= 7) return 60;
        if(currentDraw == 8) return 660;
        if(currentDraw == 9) return 960;
        return 1000;
    }
}
