import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Iterator;
import java.util.Random;


public class Deanery {
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Group> groups     = new ArrayList<Group>();

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public ArrayList<Group> getGroups() {
        return this.groups;
    }
    public void GetStuFromFile(String filename){
        int id = 1;
        JSONParser parser = new JSONParser();
        try{
            Object obj=parser.parse(new FileReader(filename));
            JSONObject j         = (JSONObject) obj;
            JSONArray person     = (JSONArray)j.get("students");
            Iterator studentsItr = person.iterator();
            while(studentsItr.hasNext()){
                JSONObject str  = (JSONObject)studentsItr.next();
                String fio      = str.get("fio").toString();
                Student.addStudent(id++, fio, this);
            }
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void GetMyGroup(String filename){

        int count = 0;
        int i     = 0;
        int k     = 0;

        JSONParser parser = new JSONParser();
        try{
            Object obj    =  parser.parse(new FileReader(filename));
            JSONObject j  = (JSONObject) obj;
            JSONArray grp = (JSONArray)j.get("groups");
            Iterator groupIterator = grp.iterator();
            while(groupIterator.hasNext()){
                count+=10;
                JSONObject group  =(JSONObject)groupIterator.next();
                String title      = group.get("title").toString();
                Group.addGroup(title, this);
                for (;i<count;i++){
                    groups.get(k).addStudent(students.get(i));
                }
                k++;

            }
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void AddMarks(){
        Random random = new Random();
        for(Student st: students){
            for(int i = 0; i<10; i++){
                int mark = random.nextInt(4)+2;
                st.addMark(mark);
            }
        }
    }

    public void AddHead(){
        for (Group g: groups){
            float maxAverage = 0;
            for(Student st: g.getPersons()){
                if (st.Calculation()>maxAverage){
                    maxAverage = st.Calculation();
                }
            }
            for(Student st: g.getPersons()){
                if(st.Calculation() == maxAverage){
                    g.addHead(st);
                }
            }
        }
    }

    public void ChooseGroup(int id, String title){
        for (Student s: students){
            if (s.getId() == id){
                for(Group gr: groups){
                    if(gr.getTitle().equals(title))
                        s.addToGroup(gr);
                }
            }
        }
        for(Group g: groups){
            if(g.SearchForStudent(id) !=null)
                for(Group gr: groups){
                    if(gr.getTitle().equals(title)){
                        gr.addStudent(g.SearchForStudent(id));
                        g.RemoveStudent(g.SearchForStudent(id));
                    }
                }
        }
    }

    public void MarksGroup(){
        int id;
        for (int i = 0; i<students.size();i++){
            if (students.get(i).Calculation()<3.0){
                id=students.get(i).getId();
                for (Group g: groups){
                    for (int j = 0; j<g.getPersons().size(); j++){
                        if(g.getPersons().get(j).getId() == id){
                            g.getPersons().remove(j);
                        }
                    }
                }
                students.remove(i);
            }
        }
    }

    public void ResultToFile(String filename){
        try {
            FileWriter writer   = new FileWriter(filename);
            JSONArray js        = new JSONArray();
            for (Group group1: groups){
                JSONObject std1 = new JSONObject();
                std1.put("Title:", group1.getTitle());
                std1.put("Head:", group1.getHead().getFIO());
                std1.put("Marks of Group:", group1.MarkGroup());
                JSONArray js2    = new JSONArray();
                for (Student group2: group1.getPersons()){
                    JSONObject std2 = new JSONObject();
                    std2.put("Id:", group2.getId());
                    std2.put("FIO:", group2.getFIO());
                    std2.put("Group:", group2.getGroup().getTitle());
                    JSONArray StudM = new JSONArray();
                    for(Integer i: group2.getMarks()){
                        StudM.add(i);
                    }
                    std2.put("Marks:", StudM);
                    js2.add(std2);
                }
                std1.put("Person:", js2);
                js.add(std1);
            }
            writer.write(js.toJSONString());
            writer.flush();
            writer.close();
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void ShowInfo(){
        System.out.println("Students:");
        for (Student s: students){
            System.out.println("id:"+s.getId());
            System.out.println("FIO: "+s.getFIO());
            System.out.println("Group title: "+s.getGroup().getTitle());
            System.out.print("Marks: ");
            for (int i = 0; i<s.getMarks().size(); i++){
                System.out.print(s.getMarks().get(i)+" ");
            }
            System.out.println();
        }
        for (Group g: groups){
            System.out.println("Group title:"+g.getTitle());
            System.out.println("Group "+g.getTitle() + " head: "+ g.getHead().getFIO());
            System.out.printf("Group %s mark is: %.2f\n", g.getTitle(),g.MarkGroup());
            for (Student s: g.getPersons()){
                System.out.println("id:"+s.getId());
                System.out.println("FIO: "+s.getFIO());
                System.out.print("Marks: ");
                for (int i = 0; i<s.getMarks().size(); i++){
                    System.out.print(s.getMarks().get(i)+" ");
                }
                System.out.println();
            }
        }
    }
}


