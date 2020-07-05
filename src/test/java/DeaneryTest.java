import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeaneryTest {
    @Test
    public void ChooseGroup2() {

        Deanery deanery  = new Deanery();
        Student student1 = new Student(1,"Тест Тестович");
        Student student2 = new Student(1,"Тест Тестович");
        Group group1     = new Group("A1");
        Group group2     = new Group("B2");

        deanery.getGroups().add(group1);
        deanery.getGroups().add(group2);

        deanery.getGroups().get(deanery.getGroups().indexOf(group1)).addStudent(student1);
        deanery.getGroups().get(deanery.getGroups().indexOf(group2)).addStudent(student2);

        deanery.ChooseGroup(1, "B1");
        String actual   = deanery.getGroups().get(deanery.getGroups().indexOf(group2)).getPersons().get(deanery.getGroups().get(deanery.getGroups().indexOf(group2)).getPersons().indexOf(student2)).getFIO();

        String expected = "Тест Тестович";
        assertEquals(actual, expected);
    }

}