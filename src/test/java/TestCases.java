import org.example.People;
import org.example.Person;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestCases extends People  {

    @Test
    public static void  testNameTrim()
    {
        Assert.assertEquals(getName(" abc"), "abc");
        Assert.assertEquals(getName("     abc"), "abc");
        Assert.assertFalse(getName("   abc").isBlank(), "is blank");
        Assert.assertFalse(getName("   abc").isEmpty());
        Assert.assertEquals(getName("abc "), "abc");
    }

    @Test( expectedExceptions = IllegalArgumentException.class)
    public static void testNameNullCheck() throws IllegalArgumentException
    {
        Assert.assertEquals(getName(null),"Illegal first or last name");
    }

    @Test( expectedExceptions = IllegalArgumentException.class)
    public static void testNameHasOnlyCharacters() throws IllegalArgumentException
    {
        Assert.assertEquals(getName("Dave1"),"Illegal first or last name");
    }

    @Test
    public static void checkNameIsCorrect()
    {
        Assert.assertEquals(getName("Dave"), "Dave","name matches");
        Reporter.log("Check if the name has numeric value");
    }

    @Test
    public static void testIfAddressisCorrect()
    {
        Assert.assertEquals(getAddress(new String[]{"123 main st.", "seattle", "wa"}), "123 MAIN ST SEATTLE WA");
    }

    @Test
    public static void testIfAddressIsInCorrect()
    {
        Assert.assertNotEquals(getAddress(new String[]{"123 main st.", "seattle"}), "123 MAIN ST SEATTLE WA");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testIfAddressIsNull()
    {
        Assert.assertEquals(getAddress(null),"Illegal address parameter");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testIfAddressIsBlank()
    {
        Assert.assertEquals(getAddress(new String[] {}),"Illegal age parameter");
    }

    @Test
    public static void testCorrectAge()
    {
        Assert.assertEquals(getAge("20"),20);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testNullAge()
    {
        Assert.assertEquals(getAge("null"),"Illegal age parameter");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testBlankAge()
    {
        Assert.assertEquals(getAge(""),"Illegal age parameter");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testZaroAge()
    {
        Assert.assertEquals(getAge("0"),"Illegal age parameter");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testDecimalAge()
    {
        Assert.assertEquals(getAge("1.2"),"Illegal age parameter");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public static void testAgeHasOnlyNumbers()
    {
        Assert.assertEquals(getAge("28a"),"Illegal age parameter");
    }

    @Test
    public static void testAddressMap()
    {
        Person p1 = new Person("Carol", "Johnson", "234 2ND AVE SEATTLE WA", 67);
        Person p2 = new Person("Justin", "Johnson", "234 2ND AVE SEATTLE WA", 69);
        Person p3 = new Person("Frank", "Smith", "987 7TH SEATTLE WA", 56);

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Map<String, Integer> map = getAddressToOccupantsMap(list);
        Assert.assertEquals(2, map.size());
    }

    @Test
    public static void testSortNames()
    {
        Person p1 = new Person("Justin", "Johnson", "234 2ND AVE SEATTLE WA", 69);
        Person p2 = new Person("Carol", "Johnson", "234 2ND AVE SEATTLE WA", 67);
        Person p3 = new Person("Frank", "Smith", "987 7TH SEATTLE WA", 6);

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        List<Person> personlist = sortNames(list);
        Assert.assertEquals(2, personlist.size());
        Assert.assertEquals(personlist.get(0).getFirstName(), "Carol");
    }
}
