package github.freeseawind.tutorial;

import java.time.LocalDate;

public class Person
{

    public enum Sex
    {
        MALE, FEMALE
    }

    String name;
    int age;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    public Sex getGender()
    {
        return gender;
    }

    public void setGender(Sex gender)
    {
        this.gender = gender;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public boolean isEmpty()
    {
        return false;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

}