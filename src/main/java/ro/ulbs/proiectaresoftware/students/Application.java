package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Student s1 = new Student(112, "Ioan", "Popa", "TI21/1");
        Student s2 = new Student(112, "Maria", "Oprea", "TI21/1");
        Student s3 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s4 = new Student(122, "Mihai", "Vecerdea", "TI22/1");
        Student s5 = new Student(122, "Eugen", "Uritescu", "TI22/2");


        Set<Student> listaStudenti = new HashSet<>();
        listaStudenti.add(s1);
        listaStudenti.add(s2);
        listaStudenti.add(s3);
        listaStudenti.add(s4);
        listaStudenti.add(s5);

        System.out.println(String.format("%-15s %-15s %-15s %-15s",
                "numar matricol", "prenume", "nume", "formatieDeStudiu"));
        System.out.println("------------------------------------------------------------");

        for (Student s : listaStudenti) {
            System.out.println(s);
        }

        System.out.println("------------------------------------------------------------");


        Student cautatB = new Student(120, "Alis", "Popa", "TI21/2");
        System.out.println("b) Este Alis Popa in lista? " + listaStudenti.contains (cautatB));


        Student cautatC = new Student(112, "Maria", "Popa", "TI21/1");
        System.out.println("c) Este Maria Popa in lista? " + listaStudenti.contains(cautatC));

    }


    public static boolean existaStudent(Set<Student> lista, Student studentCautat) {
        for (Student s : lista) {
            if (s.getPrenume().equals(studentCautat.getPrenume()) &&
                    s.getNume().equals(studentCautat.getNume()) &&
                    s.getFormatieDeStudiu().equals(studentCautat.getFormatieDeStudiu())) {
                return true;
            }
        }
        return false;
    }
}