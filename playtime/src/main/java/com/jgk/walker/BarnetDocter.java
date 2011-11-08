package com.jgk.walker;

public class BarnetDocter {
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
    String[] words = new String[] {
            "burden",
            "carton",
            "hasten",
            "cable",
            "civil",
            "dwindle",
            "gallon",
            "fumble",
            "normal",
            "novel",
            "basin",
            "whistle",
            "villain",
            "urban",
            "organ",
            "satin",
            "curtain",
            "peril",
            "gravel",
            "dangle",
    };

    public static void main(String[] args) {
        BarnetDocter bd = new BarnetDocter();
        bd.go();
    }

    private void go() {
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = letters.indexOf(c)+1;
                if(i>0&&i<(word.length())) {
                    sb.append(" + ");
                }
                sb.append(index+"");
                sum += index;
            }
            System.out.println(word+" = " + sb.toString() + " = " + sum);
            System.out.println("--------------------------------------------------");
        }
    }
}
