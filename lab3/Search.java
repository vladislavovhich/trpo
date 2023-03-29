public class Search
{
    public static void main(String[] args)
    {
        System.out.println("Max value for function f(x) = {x^2 + 2x + 1} is  " + search(-5, 5, 0.0001));
    }

    public static double search(double left, double right, double e)
    {
        if (right - left < e)
            return (left + right) / 2;
        

        double a = (left * 2 + right) / 3;
        double b = (left + right * 2) / 3;

        if (func(a) > func(b))
            return search(left, b, e);
        else
            return search(a, right, e);
    }

    public static double func(double x)
    {
        return -(x * x) + (2*x) + 1;
    }
}