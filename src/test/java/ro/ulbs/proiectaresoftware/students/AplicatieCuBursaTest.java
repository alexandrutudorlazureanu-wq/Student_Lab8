package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;


public class AplicatieCuBursaTest {



    @Test
    public void testSorteaza() {

        AplicatieCuBursa app = new AplicatieCuBursa();
        List<StudentBursier> lista = app.genereaza();


        List<StudentBursier> sortata = app.sorteaza(lista);



        Assertions.assertEquals("ISM141/1", sortata.get(0).getFormatieDeStudiu());


        int index100 = -1;
        int index780 = -1;
        for(int i=0; i<sortata.size(); i++) {
            if(sortata.get(i).getCuantumBursa() == 100.00) index100 = i;
            if(sortata.get(i).getCuantumBursa() == 780.80) index780 = i;
        }
        Assertions.assertTrue(index100 < index780, "Studentul cu bursa mai mica ar trebui sa fie inainte");
    }

}
