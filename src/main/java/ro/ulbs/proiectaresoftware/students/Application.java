package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Application {
    public static void main(String[] args) {

        Map<Integer, Student> mapStudenti = new HashMap<>();

        try {

            List<String> liniiS = Files.readAllLines(Paths.get("studenti_in.txt"));
            for (String linie : liniiS) {
                if (linie.trim().isEmpty()) continue;
                String[] d = linie.split(",");
                if (d.length == 4) {
                    int id = Integer.parseInt(d[0].trim());
                    Student s = new Student(id, d[1].trim(), d[2].trim(), d[3].trim());
                    mapStudenti.put(id, s);
                }
            }


            List<String> liniiN = Files.readAllLines(Paths.get("note_anon.txt"));
            for (String linie : liniiN) {
                if (linie.trim().isEmpty()) continue;
                String[] d = linie.split(",");
                if (d.length == 2) {
                    int id = Integer.parseInt(d[0].trim());
                    float notaVal = Float.parseFloat(d[1].trim());


                    if (mapStudenti.containsKey(id)) {
                        mapStudenti.get(id).setNota(notaVal);
                    }
                }
            }


            System.out.println(String.format("%-15s %-15s %-15s %-15s %-10s",
                    "Matricol", "Prenume", "Nume", "Grup", "Nota"));
            System.out.println("-------------");
            for (Student s : mapStudenti.values()) {
                System.out.println(s);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Eroare la procesare: " + e.getMessage());
        }
    }
}