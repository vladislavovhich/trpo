package lab1;

public class Allowance
{
    private double standardSize;

    public Allowance(double standardSize)
    {
        this.standardSize = standardSize;
    }

    public boolean CanBeGiven(Student student)
    {
        if (student.getClass() == StudentBudget.class && student.AverageGrade() >= 6)
        {
            return true;
        }

        return false;
    }

    public double GiveAllowance(Student student)
    {
        if (!this.CanBeGiven(student))
        {
            return 0;
        }

        double avrgGrade = student.AverageGrade();

        if (avrgGrade >= 7 && avrgGrade <= 8)
        {
            return standardSize * 1.25;
        }

        if (avrgGrade >= 8 && avrgGrade <= 10)
        {
            return standardSize * 1.5;
        }
        else
        {
            return standardSize;
        }
    }
}