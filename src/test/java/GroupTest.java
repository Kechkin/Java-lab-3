import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    public void addStudent() {
        Student student = new Student(1,"Тест Тестович");
        Group group     = new Group("A1");

        group.addStudent(student);
        String actual = group.getTitle();
        String expected = student.getGroup().getTitle();
        assertEquals(actual, expected);
    }

    @Test
    public void addHead() {
        Student student = new Student(1,"Тест Тестович");
        Group group     = new Group("A1");

        group.addHead(student);
        String actual = group.getHead().getFIO();
        String expected = student.getFIO();
        assertEquals(actual, expected);
    }

    @Test
    public void SearchForStudent() {
        Student student    = new Student(1,"Тест Тестович");
        Group group        = new Group("A1");

        group.addStudent(student);
        String actual   = group.SearchForStudent(1).getFIO();
        String expected = student.getFIO();
        assertEquals(actual, expected);
    }

    @Test
    public void addMark() {
        Student st1    = new Student(1,"Тест Тестович");
        Student st2    = new Student(2,"Тест Тестович");
        Group group    = new Group("A1");
        st1.addMark(2);
        st1.addMark(4);
        st1.addMark(5);
        st1.addMark(2);
        st1.addMark(3);
        st1.addMark(4);
        st1.addMark(2);

        st2.addMark(2);
        st2.addMark(5);
        st2.addMark(3);
        st2.addMark(5);
        st2.addMark(3);
        st2.addMark(2);


        group.addStudent(st1);
        group.addStudent(st2);

        double actual = (double) group.MarkGroup();
        double expected = 3.3;
        assertEquals(actual,expected,0.1);
    }
}