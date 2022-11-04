package org.example;

import java.util.*;
public class People {
    public static int ADULT_AGE = 18;
    /**
     *Main function
     */
    public static void main(String[] args) // Assumption - input is taken from console,
                                        // this automatically removes quotes from the input
    {
        if (args == null || args.length == 0)
        {
            throw new IllegalArgumentException("Invalid input");
        }

        List<Person> personList = convertToPerson(args); //gets the person list

        Map<String,Integer>  addressToHeadCountMap = getAddressToOccupantsMap(personList); //gets map address with count of occupants
        printAddressOccupants(addressToHeadCountMap);

        List<Person> sortedPersonList = sortNames(personList); // gets list of people where age is greater than 18, sorted by last name then first name
        printSortedList(sortedPersonList);
    }

    /**
     * Converts given string array to a person object.
     * @param input - This is the string array of given input.
     * @return List - This returns the list of person object.
     */
    public static  List<Person> convertToPerson(String[] input)
    {
        List<Person> personList = new ArrayList<>();

        for(int i = 0; i < input.length; i++)
        {
            String[] personRow = input[i].split(",");
            try
            {
                String firstName = getName(personRow[0]);
                String lastName = getName(personRow[1]);

                int age = getAge(personRow[personRow.length - 1]);
                String [] personAddress  = Arrays.copyOfRange(personRow,2,personRow.length -1);
                String address = getAddress(personAddress);

                Person p = new Person(firstName, lastName, address, age);
                personList.add(p);
            }
            catch (Exception e)
            {
                System.out.println("Incorrect entry for entry number" + i+1 + e);
            }
        }
        return personList;
    }

    /**
     * Prints the count of occupants per household.
     * @param personList - This is the list of person object.
     * @return HashMap - This is the map of address and count of occupants in that address.
     */
    public static Map<String,Integer> getAddressToOccupantsMap(List<Person> personList)
    {
        HashMap<String,Integer> addressToHeadCountMap = new HashMap<>();
        for (Person p: personList) // generate map of address and count of occupants in that address
        {
            if (addressToHeadCountMap.containsKey(p.getAddress()))
            {
                addressToHeadCountMap.put(p.getAddress(),addressToHeadCountMap.get(p.getAddress())+1);
            }
            else
            {
                addressToHeadCountMap.put(p.getAddress(),1);
            }
        }
        return addressToHeadCountMap;
    }
    public static void printAddressOccupants(Map<String,Integer> addressToHeadCountMap)
    {
        for(Map.Entry<String,Integer> entry: addressToHeadCountMap.entrySet())
        {
            System.out.println(entry.getKey() + " : " + entry.getValue()); // print map
        }
    }

    /**
     * Sorts by last name, then first name.
     * address and age where age is greater than 18.
     * @param personList - This is the list of person object.
     * @return - List of person
     */
    public static List<Person> sortNames(List<Person> personList)
    {
        List<Person> adultList = new ArrayList<>();
        for (int i = 0; i < personList.size(); i++)
        {
            if(personList.get(i).getAge() > ADULT_AGE) {
                adultList.add(personList.get(i)); // new list where age is greater than 18
            }
        }
        Collections.sort(adultList, new Comparator<Person>(){ //sorting by last name then first name using Comparator
            public int compare(Person a, Person b) {
                int res = a.getLastName().compareTo(b.getLastName());
                if(res != 0)
                    return res;
                else
                    return a.getFirstName().compareTo(b.getFirstName());
            }
        });

        return adultList;
    }
    public static void printSortedList(List<Person> adultList)
    {
        for (Person p: adultList)
        {
            System.out.println(p.getFirstName() + " " + p.getLastName() + ", " + p.getAddress() + ", " + p.getAge());
        }
    }

    /**
     * Parses name from the given name string
     * @param name - String.
     * @return - Parsed String.
     */
    public static String getName(String name) //First name is in the 0th index, last name is in the first index. Name will not be blank, null. Name will only be having character values.
    {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Illegal first or last name");
        }

        String trimmedName = name.trim();
        if (!trimmedName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Illegal first or last name");
        }
        return trimmedName;
    }

    /**
     * Coverts age string to age integer
     * @param age - String.
     * @return - Parsed age.
     */
    public static int getAge(String age)  // Age will not be null, blank, will not be lesser than 0, will not be a decimal number, will only be having integer value
    {
        if (age == null || age.isEmpty() || age.contains(".") || age.matches("\"[0-9]+\""))
        {
            throw new IllegalArgumentException("Illegal age parameter");
        }

        int age_numeral = Integer.parseInt(age);
        if(age_numeral <= 0 )
        {
            throw new IllegalArgumentException("Illegal age parameter");
        }
        return age_numeral;
    }

    /**
     * Concatenates the address string
     * @param input - This is the string array of given input.
     * @return String - concatenated string of address.
     */
    public static String getAddress(String[] input) //Everything from second to last index but one is address.Address is not case sensitive, cannot be blank
    {
        if(input == null || input.length == 0)
        {
            throw new IllegalArgumentException("Illegal address parameter");
        }
        StringBuilder address = new StringBuilder();
        for(int i = 0; i < input.length ; i++)
        {
            address.append(input[i].trim().replaceAll("\\." ,"").trim().toUpperCase());
            address.append(" ");
        }
        return address.toString().trim();
    }
}