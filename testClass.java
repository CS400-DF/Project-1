import org.junit.jupiter.api.Test;

public class testClass
{
    @Test
    public boolean testCase1()
    {
        CovidApp ca = new CovidApp();
        ca.addStudent(444444444,"woshitababa","negative","off-campus");
        if (ca.containsStudent(444444444))
        {
            System.out.println("the first case(addStudent) passed");
            return true;
        }
        System.out.println("the first case(addStudent) failed");
        return false;
    }
    @Test
    public boolean testCase2()
    {
        CovidApp ca = new CovidApp();
        ca.load("a.txt");
        if (ca.getAmountOfStudents()==200)
        {
            System.out.println("the first case(load and getAmountOfStudents) passed");
            return true;
        }
        System.out.println("the first case(load and getAmountOfStudents) failed");
        return false;
    }
    @Test
    public boolean testCase3()
    {
        CovidApp ca = new CovidApp();
        ca.addStudent(555555555,"woshitadie","negative","off-campus");
        int size = ca.getAmountOfStudents();
        ca.removeStudent(555555555);
        int size1 = ca.getAmountOfStudents();
        if (size != size1)
        {
            System.out.println("the first case(remove) passed");
            return true;
        }
        System.out.println("the first case(remove) failed");
        return false;
    }
    @Test
    public boolean testCase4()
    {
        CovidApp ca = new CovidApp();
        ca.addStudent(666666666,"manfen","negative","on-campus");
        int size = ca.getAmountOfStudents();
        ca.clearStudents();
        int size1 = ca.getAmountOfStudents();
        if (size != size1)
        {
            System.out.println("the first case(removeAll) passed");
            return true;
        }
        System.out.println("the first case(removeAll) failed");
        return false;
    }
}
