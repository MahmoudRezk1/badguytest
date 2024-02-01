package com.alexbank.springsecbasic.config;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\Projects\\Java project\\alex bank\\testcopy\\INDBGY.01-02-2024 04-35-32 AM.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line =br.readLine();
        System.out.println(line);
    }
}
