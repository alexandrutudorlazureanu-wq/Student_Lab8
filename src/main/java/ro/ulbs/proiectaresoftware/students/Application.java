package ro.ulbs.proiectaresoftware.students;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Application {

    public static void main(String[] args) {

        Student s1 = new Student(112, "Ioan", "Popa", "TI21/1");
        Student s2 = new Student(112, "Maria", "Oprea", "TI21/1");
        Student s3 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s4 = new Student(122, "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student(122, "Eugen", "Uritescu", "TI22/2");
        Student s6 = new Student(1029, "Bianca", "Popescu", "TI131/1");
        s6.setNota(9.10f);
        s1.setNota(7.50f);

        Set<Student> listaStudenti = new HashSet<>(Arrays.asList(s1, s2, s3, s4, s5, s6));


        List<StudentBursier> bursieri = new ArrayList<>();
        bursieri.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        bursieri.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        bursieri.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        bursieri.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1", 9.10, 780.80));


        System.out.println("\n Lista de studenti bursieri :");
        for (StudentBursier sb : bursieri) {
            System.out.println(sb);
        }


        salveazaInFisier("bursieri_out.txt", bursieri);

        System.out.println("\n Merge.");
    }



    public static void salveazaInFisier(String numeFisier, Collection<? extends Student> colectie) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(numeFisier))) {
            writer.println(String.format("%-15s %-15s %-15s %-15s %-10s %-10s",
                    "Matricol", "Prenume", "Nume", "Formatie", "Nota", "Extra/Bursa"));

            for (Student s : colectie) {
                writer.println(s.toString());
            }
        } catch (IOException e) {
            System.err.println("Nu merge : " + e.getMessage());
        }
    }

    public static float gasesteNota(String prenume, String nume, Map<String, Student> mapTineri) {
        String cheie = prenume + " " + nume;
        if (mapTineri.containsKey(cheie)) {
            return 9.10f;
        }
        return 0.0f;
    }
}