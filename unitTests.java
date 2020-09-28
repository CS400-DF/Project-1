import org.junit.jupiter.api.Test;

public class unitTests
{
    @Test
    public void testCase1()
    {
        CovidApp ca = new CovidApp();
        ca.addStudent(444444444,"woshitababa","negative","off-campus");
        if (ca.containsStudent(444444444))
        {
            System.out.println("the first case(addStudent) passed");
            return;
        }
        System.out.println("the first case(addStudent) failed");
    }
    @Test
    public void testCase2()
    {
        CovidApp ca = new CovidApp();
        ca.load("a.txt");
        if (ca.getAmountOfStudents()==200)
        {
            System.out.println("the first case(load and getAmountOfStudents) passed");
            return;
        }
        System.out.println("the first case(load and getAmountOfStudents) failed");
    }
    @Test
    public void testCase3()
    {
        CovidApp ca = new CovidApp();
        ca.addStudent(555555555,"woshitadie","negative","off-campus");
        int size = ca.getAmountOfStudents();
        ca.removeStudent(555555555);
        int size1 = ca.getAmountOfStudents();
        if (size != size1)
        {
            System.out.println("the first case(remove) passed");
            return;
        }
        System.out.println("the first case(remove) failed");
    }
    @Test
    public void testCase4()
    {
        CovidApp ca = new CovidApp();
        ca.addStudent(666666666,"manfen","negative","on-campus");
        int size = ca.getAmountOfStudents();
        ca.clearStudents();
        int size1 = ca.getAmountOfStudents();
        if (size != size1)
        {
            System.out.println("the first case(removeAll) passed");
            return;
        }
        System.out.println("the first case(removeAll) failed");
    }
}
