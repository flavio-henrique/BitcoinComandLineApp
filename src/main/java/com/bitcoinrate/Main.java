package com.bitcoinrate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Application app = new Application(new Scanner(System.in));
        app.run();
    }
}
