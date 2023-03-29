package lab1;

import java.util.ArrayList;

public class Program
{
    public static void main(String[] args)
    {
        Allowance allowance = new Allowance(120);

        ArrayList<Student> students = new ArrayList<Student>();

        students.add(new Student("Joe", "Biden", true, 6));
        students.add(new StudentBudget("Andrei", "Bolkonskij", true, 8.5));

        for (Student student : students)
        {
            if (allowance.CanBeGiven(student))
            {
                System.out.println("Student " + student.FullName() + " gets " + allowance.GiveAllowance(student));
            }
        }
    }
}