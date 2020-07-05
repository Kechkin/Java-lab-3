public class main {
    public static void main(String[] args) {
        
        Deanery deanery = new Deanery();
        deanery.GetStuFromFile("students");
        deanery.GetMyGroup("groups");
        deanery.AddMarks();
        deanery.AddHead();
        deanery.ChooseGroup(1, "A1");
        deanery.MarksGroup();
        deanery.ShowInfo();
        deanery.ResultToFile("Diary.json");
    }
}
