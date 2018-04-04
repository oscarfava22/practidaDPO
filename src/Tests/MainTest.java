package Tests;

import json.io.JsonIO;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        Plato[] pl = (Plato[])JsonIO.readJson(Plato[].class,"data/json/platos.json");
        System.out.println("Test");
    }
}
