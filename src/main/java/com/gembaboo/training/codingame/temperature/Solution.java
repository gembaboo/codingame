package com.gembaboo.training.codingame.temperature;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Solution {


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String tempsInput = in.nextLine(); // the n temperatures expressed as integers ranging from -273 to 5526


        List<Integer> temps = Arrays.asList(tempsInput.split("[\\s,]", n)).stream().map(a -> "".equals(a.trim()) ? "0" : a.trim()).map(
                Integer::valueOf).collect(Collectors.toList());


        int min = Integer.MAX_VALUE;
        for (int temp : temps) {
            if (Math.abs(temp) <= Math.abs(min)) {
                if (Math.abs(temp) < Math.abs(min))
                    min = temp;
                else
                    min = 0 < temp ? temp : min;

            }
        }

        System.out.println(min);
    }
}
