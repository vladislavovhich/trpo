package lab1;
import java.util.ArrayList;

public class StudentRepo
{
    private ArrayList<Student> students;

    public Student Get(int index)
    {
        return students.get(index);
    }

    public ArrayList<Student> Get()
    {
        return students;
    }

    public void Create(Student student)
    {
        students.add(student);
    }

    public void Update(Student student, int index)
    {
        students.set(index, student);
    }

    public void Delete(int index)
    {
        students.remove(index);
    }
}