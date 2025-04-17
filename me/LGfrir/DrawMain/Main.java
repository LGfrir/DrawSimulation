package me.LGfrir.DrawMain;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static me.LGfrir.DrawMain.DrawControlCharacter.DrawCharacter;
import static me.LGfrir.DrawMain.DrawControlWeapon.DrawWeapon;

import me.LGfrir.PrintChart.ChartApp;

public class Main {
    public static int op;
    private static int command;
    private static int character1, character2, character3;
    private static int currentTargetCharacters, currentTargetWeapons, timeTilFIChar, timeTilFIWeapon;
    private static boolean lastMissFIChar, lastMissFIWeapon;
    private static long simulationTimes;

    private static final ArrayList<String> text = new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);

    //mode1
    private static int targetCharacters, targetWeapons, prepareDrawTimes;


    //mode2
    private static final int[][] strategies = new int[15][2];
    private static int strIndex = 1;


    public static void main(String[] args) {

        System.out.println("欢迎使用抽卡模拟程序");

        inputAll(0);

        //求抽数期望，用10000抽模拟


        if (command == 1) {
            inputMode1(0);
            inputMode1Confirm();
            doMode1Draw();
        }

        //模拟当全部梭哈之后的平均期望命座

        else if (command == 2) {
            inputMode2(0);
            inputMode2Confirm();
            doMode2Draw();
        }

    }


    private static void printProgress(long index, long total) {
        long z = total / 50;

        if (index % z == 0) System.out.print("█");
    }

    private static void inputAll(int k) {
        if (k == 0) {
            do {
                System.out.println("模式1:求抽数期望");
                System.out.println("用10000抽模拟，求从开始抽到目标时最后期望消耗的抽数");
                System.out.println("模式2:求命座期望");
                System.out.println("输入抽卡策略，用目前所拥有的全部抽数无脑All In，直到抽到抽数耗尽或达到目标");
                command = sc.nextInt();
                sc.nextLine();
                if (command == 1 || command == 2) {
                    break;
                }
                System.out.println("如果想要别的模式在b站可以跟我说，不要乱选！");
            } while (true);
        }

        if (k == 0 || k == 1) {
            do {
                System.out.println("请输入当前游戏：");
                System.out.println("1.原神");
                System.out.println("2.崩坏：星穹铁道");
                op = sc.nextInt();
                sc.nextLine();
                if (op == 1 || op == 2) {
                    break;
                }
                System.out.println("如果想要别的游戏在b站可以跟我说，不要乱选！");
            } while (true);
        }


        //四星角色输入

        if (k == 0) System.out.println("请输入当期卡池四星的命座(无为-1)");

        if (k == 0 || k == 2) {
            do {
                System.out.print("角色1:");
                character1 = sc.nextInt() + 1;
                sc.nextLine();
                if (0 <= character1 && character1 <= 7) break;
                System.out.println("输入错误，请重新输入！");
            } while (true);
        }
        if (k == 0 || k == 3) {
            do {
                System.out.print("角色2:");
                character2 = sc.nextInt() + 1;
                sc.nextLine();
                if (0 <= character2 && character2 <= 7) break;
                System.out.println("输入错误，请重新输入！");
            } while (true);
        }
        if (k == 0 || k == 4) {
            do {
                System.out.print("角色3:");
                character3 = sc.nextInt() + 1;
                sc.nextLine();
                if (0 <= character3 && character3 <= 7) break;
                System.out.println("输入错误，请重新输入！");
            } while (true);
        }

        //当期限定拥有情况计算
        if (k == 0 || k == 5) {
            do {
                System.out.print("请输入你目前的限定角色命座(无为-1)：");
                currentTargetCharacters = sc.nextInt() + 1;
                sc.nextLine();
                if (0 <= currentTargetCharacters && currentTargetCharacters < 7) break;
                System.out.println("输入错误，请重新输入！");
            } while (true);
        }

        if (k == 0 || k == 6) {
            do {
                System.out.print("请输入你目前的限定武器个数：");
                currentTargetWeapons = sc.nextInt();
                sc.nextLine();
                if (0 <= currentTargetWeapons) break;
                System.out.println("输入错误，请重新输入！");
            } while (true);
        }

        //当前抽卡情况

        if (k == 0 || k == 7) {
            do {
                System.out.print("请输入角色池目前垫了多少抽：");
                timeTilFIChar = sc.nextInt() + 1;
                sc.nextLine();
                if (timeTilFIChar > 0 && timeTilFIChar <= 90) break;
                System.out.println("输入错误，请重新输入！");
            } while (true);
        }

        if (k == 0 || k == 8) {
            do {
                System.out.print("请输入武器池目前垫了多少抽：");
                timeTilFIWeapon = sc.nextInt() + 1;
                sc.nextLine();
                if (timeTilFIWeapon > 0 && timeTilFIWeapon <= 90) break;
                System.out.println("输入错误，请重新输入！");
            } while (true);
        }

        if (k == 0 || k == 9) {
            System.out.println("这一个角色金是否为大保底(Y/N)");
            lastMissFIChar = sc.next().equals("Y");
        }

        if (k == 0 || k == 10) {
            System.out.println("武器池命定值是否为1(Y/N)");
            lastMissFIWeapon = sc.next().equals("Y");
        }

        //模拟次数

        if (k == 0 || k == 11) {
            System.out.println("请输入模拟次数(万)");
            simulationTimes = sc.nextLong() * 10000L;
            sc.nextLine();
        }
    }

    private static void setDefault() {
        character1 = 7;
        character2 = 7;
        character3 = 3;
        currentTargetCharacters = 1;
        currentTargetWeapons = 0;
        timeTilFIChar = 5;
        timeTilFIWeapon = 10;
        lastMissFIChar = false;
        lastMissFIWeapon = false;
        simulationTimes = 100 * 10000;
    }


    private static void inputMode1(int k) {
        if (k == 0 || k == 1) {
            do {
                System.out.print("目标限五角色命座：");
                targetCharacters = sc.nextInt() + 1;
                sc.nextLine();
                if (targetCharacters >= 0) break;
                System.out.println("输入错误，请重新输入");
            } while (true);
        }

        if (k == 0 || k == 2) {
            do {
                System.out.print("目标限五武器个数：");
                targetWeapons = sc.nextInt();
                sc.nextLine();
                if (targetWeapons >= 0) break;
                System.out.println("输入错误，请重新输入");
            } while (true);
        }
        if (k == 0 || k == 3) {
            do {
                System.out.print("准备抽数：");
                prepareDrawTimes = sc.nextInt();
                sc.nextLine();
                if (prepareDrawTimes >= 0 && prepareDrawTimes <= 2500) break;
                System.out.println("输入错误，请重新输入");
            } while (true);
        }
    }

    private static void inputMode2(int k) {
        if (k == 0 || k == 1) {
            do {
                System.out.println("请输入你当前所有的抽数数量：");
                prepareDrawTimes = sc.nextInt();
                sc.nextLine();
                if (prepareDrawTimes >= 0 && 10000 >= prepareDrawTimes) break;
                System.out.println("输入错误，请重新输入");
            } while (true);
        }

        if (k == 0 || k == 2) {
            strIndex = 1;
            strategies[0][0] = currentTargetCharacters;
            strategies[0][1] = currentTargetWeapons;
            for (int i = 1; i < strategies.length; i++) {
                strategies[i] = new int[]{0, 0};
            }
            System.out.println("请输入每次的抽卡策略");
            System.out.println("输入end结束，输入C代表下一次抽角色，输入W代表下一次抽武器");
            System.out.println("例如：");
            System.out.println("从-+0 -> 0+0 -> 0+1 -> 6+1 -> 6+5");
            System.out.println("C 1");
            System.out.println("W 1");
            System.out.println("C 6");
            System.out.println("W 4");
            System.out.println("end");

            while (true) {
                String input = sc.next();
                if (Objects.equals(input, "end")) break;
                int t = sc.nextInt();
                if (t <= 0) {
                    System.out.println("输入错误");
                    continue;
                }
                if (Objects.equals(input, "C")) {
                    strategies[strIndex][0] = t;

                    strategies[strIndex][0] += strategies[strIndex - 1][0];
                    strategies[strIndex][1] += strategies[strIndex - 1][1];

                    if (strategies[strIndex][0] > 7) {
                        strategies[strIndex][0] = 0;
                        strategies[strIndex][1] = 0;
                        System.out.println("输入错误");
                        continue;
                    }
                } else if (Objects.equals(input, "W")) {
                    strategies[strIndex][1] = t;
                    strategies[strIndex][1] += strategies[strIndex - 1][1];
                    strategies[strIndex][0] += strategies[strIndex - 1][0];

                    if (strategies[strIndex][1] > 5) {
                        strategies[strIndex][0] = 0;
                        strategies[strIndex][1] = 0;
                        System.out.println("输入错误");
                        continue;
                    }
                } else
                    System.out.println("错误输入");

                //打印阶段性方法
                for (int i = 0; i <= strIndex; i++)
                    System.out.print((strategies[i][0] == 0 ? "-" : (strategies[i][0] - 1)) + "+" + strategies[i][1] + (i == strIndex ? "" : " -> "));
                System.out.println();

                strIndex++;
            }
            strIndex--;
        }

    }

    private static void inputConfirm() {
        do {
            System.out.println("请核对当前所有输入");
            System.out.println("游戏名称：《" + switch (op) {
                case 1 -> "原神";
                case 2 -> "崩坏：星穹铁道";
                default -> "未知";
            } + "》");
            System.out.println("2.四星角色1命座：" + (character1 - 1));
            System.out.println("3.四星角色2命座：" + (character2 - 1));
            System.out.println("4.四星角色3命座：" + (character3 - 1));
            if (currentTargetCharacters - 1 == 0) System.out.println("5.目前无目标限定五星角色");
            else System.out.println("5.目前限定角色命座为" + (currentTargetCharacters - 1) + "命");
            System.out.println("6.目前总限定武器个数" + currentTargetWeapons);
            System.out.println("7.角色池垫了" + (timeTilFIChar - 1) + "抽");
            System.out.println("8.武器池垫了" + (timeTilFIWeapon - 1) + "抽");
            System.out.println("9.角色池是" + (lastMissFIChar ? "大保底" : "小保底"));
            System.out.println("10.武器池命定值为" + (lastMissFIWeapon ? 1 : 0));
            System.out.println("11.模拟次数：" + simulationTimes / 10000L + "万次");

            System.out.println("是否重新输入？是请输入对应名称");
            int cc = sc.nextInt();
            sc.nextLine();
            if (cc == 0 || cc > 11) break;
            inputAll(cc);
            if (command == 2 && (cc == 5 || cc == 6)) {
                bl = true;
            }
        } while (true);
    }

    private static void inputMode1Confirm() {
        inputConfirm();
        do {
            System.out.println("请核对当前所有输入");
            System.out.println("1.目标限定五星角色命座：" + (targetCharacters - 1));
            System.out.println("2.目标限定五星武器个数：" + (targetWeapons));
            System.out.println("3.准备抽数" + prepareDrawTimes);

            System.out.println("是否重新输入？是请输入对应名称");
            int cc = sc.nextInt();
            sc.nextLine();
            if (cc == 0 || cc > 3) break;
            inputMode1(cc);
        } while (true);
    }

    private static boolean bl = false;

    private static void inputMode2Confirm() {
        inputConfirm();
        if (bl) {
            System.out.println("由于操作过总角色/武器，所以抽卡策略重新输入：");
            inputMode2(2);
        }
        do {
            System.out.println("请核对当前所有输入");
            System.out.println("1.准备抽数：" + (prepareDrawTimes));
            System.out.print("2.抽卡策略：");
            for (int i = 0; i <= strIndex; i++)
                System.out.print((strategies[i][0] == 0 ? "-" : (strategies[i][0] - 1)) + "+" + strategies[i][1] + (i == strIndex ? "" : " -> "));
            System.out.println();

            System.out.println("是否重新输入？是请输入对应名称");
            int cc = sc.nextInt();
            sc.nextLine();
            if (cc == 0 || cc > 2) break;
            inputMode2(cc);
        } while (true);
    }

    private static void doMode1Draw() {
        int MAX_DRAW = 10000;
        long index = 1;
        long sum = 0;
        int max = 1, min = MAX_DRAW;
        long sumStars = 0;
        int[] hashMap1 = new int[MAX_DRAW + 10];

        text.add("在以下情况：");
        text.add("游戏名称：《" + switch (op) {
            case 1 -> "原神";
            case 2 -> "崩坏：星穹铁道";
            default -> "未知";
        } + "》");


        text.add("四星角色命座分别为：" + (character1 == 0 ? "无" : (character1 - 1)) + "，" + (character2 == 0 ? "无" : (character2 - 1)) + "，" + (character3 == 0 ? "无" : (character3 - 1)));
        text.add("角色池垫了" + (timeTilFIChar - 1) + "抽，" + "武器池垫了" + (timeTilFIWeapon - 1) + "抽");
        text.add("角色池的下一金" + (lastMissFIChar ? "" : "不") + "为大保底，" + "武器池命定值为" + (lastMissFIWeapon ? "1" : "0"));


        System.out.println("██████████████████████████████████████████████████");

        while (index++ <= simulationTimes) {
            printProgress(index, simulationTimes);
            DrawData.Init(currentTargetCharacters, currentTargetWeapons,
                    character1, character2, character3,
                    0, MAX_DRAW, timeTilFIChar, lastMissFIChar, timeTilFIWeapon, lastMissFIWeapon);

            DrawData.setTotalTargetCharacters(targetCharacters);
            DrawData.setTotalTargetWeapons(targetWeapons);

            DrawCharacter();
            DrawWeapon();
            int totalUseDraw = MAX_DRAW - DrawData.getDrawTimes();
            int totalDraw = DrawData.getTotalDrawTimes();


            sum += totalUseDraw;
            sumStars += totalDraw;
            max = Math.max(totalUseDraw, max);
            min = Math.min(totalUseDraw, min);

            if (totalUseDraw <= prepareDrawTimes)
                hashMap1[0]++;

            if (totalUseDraw >= 1) hashMap1[totalUseDraw]++;
            else hashMap1[1]++;

        }
        sumStars -= sum;
        for (int i = 2; i <= MAX_DRAW; i++) {
            hashMap1[i] += hashMap1[i - 1];
        }

        System.out.println();


        text.add("从角色" + (currentTargetCharacters == 0 ? "未持有" : (currentTargetCharacters - 1) + "命") + "，专武共" + currentTargetWeapons + "把");
        text.add("抽到角色" + (targetCharacters - 1) + "命，专武共" + targetWeapons + "把");

        text.add("经过" + simulationTimes / 10000 + "万次模拟，得出以下结论");
        text.add("");

        String s1 = String.format("期望抽数：%.2f", (double) sum / simulationTimes);
        System.out.println(s1);
        text.add(s1);

        String s2 = String.format("%d万次模拟最大抽数：%d，最小抽数：%d", simulationTimes / 10000, max, min);
        System.out.println("可能会出现最小抽数 <= 目标角色数 + 目标武器数，原因是五星频率过高导致星辉数过多使抽数不减反增..");
        System.out.println(s2);
        text.add(s2);

        String s3 = String.format("期望星辉兑换抽数：%.2f", (double) sumStars / simulationTimes);
        System.out.println(s3);
        text.add(s3);

        text.add("");

        String s4 = String.format("在持有%d抽下的成功率：%.2f%%", prepareDrawTimes, 100.0 * hashMap1[0] / simulationTimes);
        System.out.println(s4);
        text.add(s4);


        System.out.println("成功率计算：");
        ArrayList<Double> list = new ArrayList<>();
        int startI = MAX_DRAW, endI = 1;
        for (int i = 1; i <= MAX_DRAW; i++) {
            if (0 < hashMap1[i] && hashMap1[i] < simulationTimes) {
                startI = Math.min(startI, i);
                endI = Math.max(endI, i);
            }
        }
        System.out.println(endI + " " + startI);
        int divider = (endI - startI) / 15;
        for (int i = startI; i <= endI; i += divider) {
            list.add(100.0 * hashMap1[i] / simulationTimes);
        }
        ChartApp.initializeSuccessPercentageChart(list, null, "抽数", "成功率", startI, divider, text);

    }

    private static void doMode2Draw() {

        text.add("在以下情况：");

        text.add("游戏名称：《" + switch (op) {
            case 1 -> "原神";
            case 2 -> "崩坏：星穹铁道";
            default -> "未知";
        } + "》");

        text.add("四星角色命座分别为：" + (character1 == 0 ? "无" : (character1 - 1)) + "，" + (character2 == 0 ? "无" : (character2 - 1)) + "，" + (character3 == 0 ? "无" : (character3 - 1)));
        text.add("角色池垫了" + (timeTilFIChar - 1) + "抽，" + "武器池垫了" + (timeTilFIWeapon - 1) + "抽");
        text.add("角色池的下一金" + (lastMissFIChar ? "" : "不") + "为大保底，" + "武器池命定值为" + (lastMissFIWeapon ? "1" : "0"));

        //拿到一个strategies
        long simIndex = 1;
        long[][] hashMap = new long[8][6];
        System.out.println("██████████████████████████████████████████████████");

        while (simIndex++ <= simulationTimes) {
            printProgress(simIndex, simulationTimes);

            DrawData.Init(currentTargetCharacters, currentTargetWeapons,
                    character1, character2, character3,
                    0, prepareDrawTimes, timeTilFIChar, lastMissFIChar, timeTilFIWeapon, lastMissFIWeapon);
            for (int i = 1; i <= strIndex; i++) {
                int targetCharacters = strategies[i][0];
                int targetWeapons = strategies[i][1];
                DrawData.setTotalTargetCharacters(targetCharacters);
                DrawData.setTotalTargetWeapons(targetWeapons);
                DrawCharacter();
                DrawWeapon();
            }
            int redundantDraw = DrawData.getDrawTimes();
            int totalCharacters = DrawData.getCurrentTargetCharacters();
            int totalWeapons = DrawData.getCurrentTargetWeapons();


            hashMap[totalCharacters][totalWeapons]++;

        }
        System.out.println();

        class Temp {
            final int i;
            final int j;
            long hashMap;

            Temp(int i, int j, long hashMap) {
                this.i = i;
                this.j = j;
                this.hashMap = hashMap;
            }

        }


        ArrayList<Temp> temps = new ArrayList<>();

        int i = currentTargetCharacters, j = currentTargetWeapons, k = 1;
        while (i < strategies[strIndex][0] || j < strategies[strIndex][1]) {
            // 添加边界检查
            if (k >= strategies.length) {
                break;
            }

            // 处理角色维度
            if (strategies[k][0] > i) {
                while (i < strategies[k][0] && i < strategies[strIndex][0]) {
                    Temp temp = new Temp(i, j, hashMap[i][j]);
                    temps.add(temp);
                    i++;
                }
            }

            // 处理武器维度
            if (strategies[k][1] > j) {
                while (j < strategies[k][1] && j < strategies[strIndex][1]) {
                    Temp temp = new Temp(i, j, hashMap[i][j]);
                    temps.add(temp);
                    j++;
                }
            }

            k++;
        }

        // 添加最终状态
        if (i <= strategies[strIndex][0] && j <= strategies[strIndex][1]) {
            temps.add(new Temp(i, j, hashMap[i][j]));
        }

        ArrayList<Double> list = new ArrayList<>();
        list.add(100.0 * temps.getLast().hashMap / simulationTimes);

        for (int ii = temps.size() - 2; ii >= 0; ii--) {
            temps.get(ii).hashMap += temps.get(ii + 1).hashMap;
            list.add(100.0 * temps.get(ii).hashMap / simulationTimes);
        }

        ArrayList<String> textLines = new ArrayList<>();
        for (int ii = temps.size() - 1; ii >= 0; ii--) {
            textLines.add(temps.get(ii).i + "+" + temps.get(ii).j);
        }

        boolean hasKeptOneHundred = false;
        for (int ii = 0; ii < textLines.size(); ii++) {
            double value = list.get(ii);
            if (value == 0.0) {
                list.remove(ii);
                textLines.remove(ii);
                ii--;
            } else if (value >= 100.0) {
                if (hasKeptOneHundred) {
                    list.remove(ii);
                    textLines.remove(ii);
                    ii--;
                } else {
                    hasKeptOneHundred = true; // 保留第一个 100%
                }
            }
        }

        text.add("从持有抽数：" + prepareDrawTimes);
        text.add("到全部抽取完毕");

        text.add("经过" + simulationTimes / 10000 + "万次模拟，得出以下结论");
        text.add("");

        text.add("最优抽取可能为：" + (textLines.getFirst().charAt(0) == '0' ? "-" : (char) (textLines.getFirst().charAt(0) - 1)) + (textLines.getFirst().substring(1, 3)));
        text.add("最差抽取可能为：" + (textLines.getLast().charAt(0) == '0' ? "-" : (char) (textLines.getLast().charAt(0) - 1)) + (textLines.getLast().substring(1, 3)));

        ChartApp.initializeSuccessPercentageChart(list, textLines, "抽取情况", "成功率", 0, 0, text);


    }
}
