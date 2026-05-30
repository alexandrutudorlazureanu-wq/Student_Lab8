package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.*;


class StudentiInConsola implements ExportStrategy {

    public void exporta(String s, List<Student> l) {
        System.out.println("\n=== Afișare în " + s + " ===");
        l.forEach(System.out::println);
    }

}


class StudentiInFisierText implements ExportStrategy {

    public void exporta(String s, List<Student> l) {
        try (PrintWriter w = new PrintWriter(new FileWriter(s))) {
            for (Student st : l) {
                w.println(st.getNumarMatricol() + "," + st.getPrenume() + "," + st.getNume() + "," + st.getFormatieDeStudiu() + "," + st.getNota());
            }
            System.out.println("Export finalizat: " + s);
        } catch (IOException e) { System.err.println("Eroare: " + e.getMessage()); }
    }

}


class StudentiInFisierXlsx implements ExportStrategy {

    public void exporta(String s, List<Student> l) {
        try (Workbook wb = new HSSFWorkbook(); FileOutputStream out = new FileOutputStream(s)) {
            Sheet sh = wb.createSheet("Studenti");
            int r = 0;
            for (Student st : l) {
                Row rw = sh.createRow(r++);
                rw.createCell(0).setCellValue(st.getNumarMatricol());
                rw.createCell(1).setCellValue(st.getPrenume());
                rw.createCell(2).setCellValue(st.getNume());
                rw.createCell(3).setCellValue(st.getFormatieDeStudiu());
                rw.createCell(4).setCellValue(st.getNota());
            }
            wb.write(out);
            System.out.println("Export Excel finalizat cu succes în: " + s);
        } catch (IOException e) { System.err.println("Eroare Excel: " + e.getMessage()); }
    }

}


class StudentiDinFisierText implements ImportStrategy {

    public List<Student> importa(String s) {
        List<Student> list = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(s))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] d = line.split(",");
                list.add(new Student(Integer.parseInt(d[0]), d[1], d[2], d[3], Float.parseFloat(d[4])));
            }
        } catch (Exception e) { System.err.println("Eroare citire TXT: " + e.getMessage()); }
        return list;
    }

}


class StudentiDinFisierXlsx implements ImportStrategy {

    public List<Student> importa(String s) {
        List<Student> list = new ArrayList<>();
        try (Workbook wb = new HSSFWorkbook(new FileInputStream(s))) {
            Sheet sh = wb.getSheetAt(0);
            for (int i = 0; i <= sh.getLastRowNum(); i++) {
                Row rw = sh.getRow(i);
                if (rw == null) continue;
                list.add(new Student(
                        (int) rw.getCell(0).getNumericCellValue(),
                        rw.getCell(1).getStringCellValue(),
                        rw.getCell(2).getStringCellValue(),
                        rw.getCell(3).getStringCellValue(),
                        (float) rw.getCell(4).getNumericCellValue()
                ));
            }
        } catch (Exception e) { System.err.println("Eroare: " + e.getMessage()); }
        return list;
    }

}


class ManagerStudenti {

    private ExportStrategy ex;
    private ImportStrategy im;

    public void setExportStrategy(ExportStrategy e) { this.ex = e; }

    public void setImportStrategy(ImportStrategy i) { this.im = i; }

    public void executaExport(String s, List<Student> l) { if (ex != null) ex.exporta(s, l); }

    public List<Student> executaImport(String s) { return im != null ? im.importa(s) : new ArrayList<>(); }

}


public class AplicatieCuStrategy {

    public static void main(String[] args) {
        List<Student> studenti = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70f),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10f),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90f),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10f),
                new Student(1029, "Maria", "Pana", "TI131/2", 4.10f),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33f),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20f),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12f),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22f)
        );

        ManagerStudenti manager = new ManagerStudenti();

        manager.setExportStrategy(new StudentiInConsola());
        manager.executaExport("Consola Standard", studenti);

        System.out.println("\n--- Se execută exportul în text... ---");
        manager.setExportStrategy(new StudentiInFisierText());
        manager.executaExport("studenti_strategy.txt", studenti);

        System.out.println("\n--- Se execută exportul în Excel... ---");
        manager.setExportStrategy(new StudentiInFisierXlsx());
        manager.executaExport("studenti_strategy.xls", studenti);

        System.out.println("\n--- Se execută importul din text... ---");
        manager.setImportStrategy(new StudentiDinFisierText());
        List<Student> dinTxt = manager.executaImport("studenti_strategy.txt");
        dinTxt.forEach(System.out::println);

        System.out.println("\n--- Se execută importul din Excel... ---");
        manager.setImportStrategy(new StudentiDinFisierXlsx());
        List<Student> dinExcel = manager.executaImport("studenti_strategy.xls");
        dinExcel.forEach(System.out::println);
    }

}