package com.LearningTime;

import java.util.*;

public class Experiments {

    public static boolean canWin(int leap, int[] game) {
        ArrayList<Integer> goodZerosI = new ArrayList<Integer>();
        ArrayList<Integer> oldZerosI = new ArrayList<Integer>();

        for (int i = game.length - 1; i > Math.max(game.length - 1 - Math.max(leap, 1), -1); i--) {
            if (game[i] == 0) {
                goodZerosI.add(i);
            }
        }

        if (leap > game.length) {
            return true;
        } else if (leap == game.length) {
            if (game[game.length - 1] == 0) {
                return true;
            }
        }

        while (goodZerosI.size() > 0) {
            int size = goodZerosI.size();

            for (int i = 0; i < size; i++) {

                if (goodZerosI.get(0) + 1 < game.length && !(goodZerosI.contains(goodZerosI.get(0) + 1) && !(oldZerosI.contains(goodZerosI.get(0) + 1)))) {
                    if (game[goodZerosI.get(0) + 1] == 0) {
                        goodZerosI.add(goodZerosI.get(0) + 1);
                    }
                }

                if (goodZerosI.get(0) - 1 >= 0 && !(goodZerosI.contains(goodZerosI.get(0) - 1)) && !(oldZerosI.contains(goodZerosI.get(0) - 1))) {
                    if (game[goodZerosI.get(0) - 1] == 0) {
                        goodZerosI.add(goodZerosI.get(0) - 1);
                    }
                }

                int leapI = goodZerosI.get(0) - leap;

                if (leapI >= 0 && !(goodZerosI.contains(goodZerosI.get(0) - leap)) && !(oldZerosI.contains(goodZerosI.get(0) - leap))) {
                    if (game[leapI] == 0) {
                        goodZerosI.add(goodZerosI.get(0) - leap);
                    }
                }

                int zero = goodZerosI.remove(0);
                oldZerosI.add(zero);
            }

            if (goodZerosI.contains(0)) {
                return true;
            }
        }
        return false;
    }

    public static void actual() {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}
