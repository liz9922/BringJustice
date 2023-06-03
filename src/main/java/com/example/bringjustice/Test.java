package com.example.bringjustice;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        SQL sql = new SQL();
        sql.connect("blandainteinaraberna");
        sql.createTable();
        sql.disconnect();

        FBIapi fbIapi = new FBIapi();

       /* ArrayList<WantedPeople> w = fbIapi.getAllWantedPeople();
        for (int i = 0; i<w.size(); i++) {
            System.out.println(w.get(i).toString());
        }*/
        ArrayList<WantedPeople> ww = fbIapi.getAllWantedPeopleGPT();
        for (int i = 0; i<ww.size(); i++) {
            System.out.println(ww.get(i).toString());
        }


    }
}
