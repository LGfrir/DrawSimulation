package me.LGfrir.SafeInput;

import java.util.ArrayList;

import static me.LGfrir.DrawMain.Main.sc;

public class SafeInput {

    public static int nextInt(ArrayList<String> prompt) {
        while (true) {
            for (String s : prompt)
                System.out.print(s);

            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("输入无效，请输入一个整数！");
            }
        }
    }

    public static int nextInt(ArrayList<String> prompt, int min, int max) {
        while (true) {
            int value = nextInt(prompt);
            if (value < min || value > max) {
                System.out.printf("请输入一个在 [%d, %d] 范围内的整数！\n", min, max);
            } else {
                return value;
            }
        }
    }

    public static int nextInt(String prompt, int min, int max) {
        ArrayList<String> p = new ArrayList<>();
        p.add(prompt);
        return nextInt(p, min, max);
    }

    public static int nextInt(String prompt) {
        ArrayList<String> p = new ArrayList<>();
        p.add(prompt);
        return nextInt(p);
    }

    public static boolean nextBoolean(ArrayList<String> prompt) {
        while (true) {
            for (String s : prompt)
                System.out.print(s + "(y/n)");

            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("是") || input.equals("y") || input.equals("yes") || input.equals("1")) {
                return true;
            } else if (input.equals("否") || input.equals("n") || input.equals("no") || input.equals("0")) {
                return false;
            } else {
                System.out.println("输入无效，请输入 y/n！");
            }
        }
    }

    public static boolean nextBoolean(String prompt) {
        ArrayList<String> p = new ArrayList<>();
        p.add(prompt);
        return nextBoolean(p);
    }


}
