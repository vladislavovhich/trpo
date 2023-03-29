package lab1;

public class Student
{
    private boolean hasPassed;
    private double avrgGrade;
    private String name, surname;

    public Student(String name, String surname, boolean hasPassed, double avrgGrade)
    {
        this.hasPassed = hasPassed;
        this.avrgGrade = avrgGrade;
        this.name = name;
        this.surname = surname;
    }

    public String FullName()
    {
        return name + " " + surname;
    }

    public void SetNewName(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public boolean HasPassed()
    {
        return hasPassed;
    }

    public double AverageGrade()
    {
        return avrgGrade;
    }
}