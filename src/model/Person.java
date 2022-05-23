package model;

public class Person
{
  private String name;
  private String age;
  private String country;

  public Person(String name, String age, String country)
  {
    this.name = name;
    this.age = age;
    this.country = country;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getAge()
  {
    return age;
  }

  public void setAge(String age)
  {
    this.age = age;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }
}
