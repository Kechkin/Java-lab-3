import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    public void addToGroup() {
        Student student = new Student(1,"Тест Тестович");
        Group group     = new Group("B2");
        student.addToGroup(group);
        String actual   = student.getGroup().getTitle();
        String expected = group.getTitle();
        assertEquals(actual, expected);
    }

    @Test
    public void addMark() {
        Student student    = new Student(1,"Тест Тестович");
        student.addMark(2);
        student.addMark(5);
        student.addMark(3);
        Integer[] actual   = student.getMarks().toArray(new Integer[student.getMarks().size()]);
        Integer[] expected = {2,5,3};
        assertArrayEquals(actual, expected );
    }

    @Test
    public void MarkGroup() {
        Student student1 = new Student(1,"Тест Тестович");
        student1.addMark(2);
        student1.addMark(5);
        student1.addMark(3);
        double actual    = (double) student1.Calculation();
        double expected  = 3.3;
        assertEquals(actual,expected,0.1);
    }
}