import java.util.ArrayList;

public class Group {
    private String title;
    private ArrayList<Student> persons;
    private Student head;

    Group(String title){
        this.title = title;
        this.persons = new ArrayList<Student>();
    }

    public String getTitle(){

        return this.title;
    }

    public Student getHead(){

        return this.head;
    }

    public ArrayList<Student> getPersons(){

        return this.persons;
    }

    static void addGroup(String title, Deanery deanery){
        deanery.getGroups().add(new Group(title));
    }

    public void addStudent(Student student){
        persons.add(student);
        student.addToGroup(this);
    }
    public void addHead(Student student){

        this.head = student;
    }

    public Student SearchForStudent(int id){
        for(Student st: persons) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    public float MarkGroup(){
        float sum = 0;
        for(Student st: persons){
            sum+=st.Calculation();
        }
        return sum/ persons.size();
    }
    public void RemoveStudent(Student st){
        for(int i = 0; i< persons.size(); i++){
            if(persons.get(i).getId() == st.getId())
                persons.remove(i);
        }
    }
}