package com.example.bringjustice;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private SQL sql;

    public Menu() {
        scanner = new Scanner(System.in);
        sql = new SQL();
    }

    public void start() {
        System.out.println("Välkommen till applikationen!");

        // Anslut till databasen
        sql.connect("path_to_your_database");


        // Låt användaren skriva in sitt namn
        System.out.print("Skriv in ditt namn: ");
        String playerName = scanner.nextLine();

        // Skapa en ny spelare och spara den i tabellen
        Player player = new Player(playerName);
        sql.insertPlayer(player);

        System.out.println("Ditt namn har sparats i databasen.");

        // Stäng anslutningen till databasen
        sql.disconnect();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }
}