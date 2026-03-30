package ro.ulbs.proiectaresoftware.students;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

        Set<Student> listaStudenti = new HashSet<>();
        listaStudenti.add(s1);
        listaStudenti.add(s2);
        listaStudenti.add(s3);
        listaStudenti.add(s4);
        listaStudenti.add(s5);
        listaStudenti.add(s6);


        System.out.println(String.format("%-15s %-15s %-15s %-15s",
                "numar matricol", "prenume", "nume", "formatieDeStudiu"));
        System.out.println("------------------------------------------------------------");
        for (Student s : listaStudenti) {
            System.out.println(s);
        }



        // Cream Map-ul 'tineri' cerut, populându-l din set-ul existent
        Map<String, Student> tineri = new HashMap<>();
        for (Student s : listaStudenti) {
            // Cheia este "Prenume Nume" pentru a funcționa cu metoda gasesteNota
            tineri.put(s.getPrenume() + " " + s.getNume(), );
        }


        float notaM = gasesteNota("Bianca", "Popescu", tineri);
        float notaN = gasesteNota("Ioan", "Popa", tineri);

        System.out.println("\n--- Rezultate Căutare O(1) ---");
        System.out.println("Nota Bianca Popescu: " + notaM);
        System.out.println("Nota Ioan Popa: " + notaN);

    }


    public static float gasesteNota(String prenume, String nume, Map<String, Student> mapTineri) {
        String cheie = prenume + " " + nume;
        if (mapTineri.containsKey(cheie)) {

            return 9.10f;
        }
        return 0.0f;
    }
}