package ro.ulbs.proiectaresoftware.students;

import java.util.List;

public interface ExportStrategy {

    void exporta(String sursa, List<Student> studenti);
}
