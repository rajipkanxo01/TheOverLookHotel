package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Guest implements Serializable
{
  /**
   * A class that is used to store the first name,last name, address, phone number, nationality and date of birth of a guest.
   * @author Rajib Paudyal
   * @version 1.0.0
   */
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  private String nationality;
  private LocalDate dateOfBirth;
  private LocalDate checkedInDate;
  private String roomNumber;

  /**
   * initiates the Guest class
   *
   * @param firstName   first name of guest
   * @param lastName    last name of guest
   * @param address     address of guest
   * @param phone       phone number of guest
   * @param nationality nationality of guest
   */
  public Guest(String firstName, String lastName, String address, String phone,
      String nationality, LocalDate dateOfBirth)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.nationality = nationality;
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * initiates the Guest class
   *
   * @param firstName   first name of guest
   * @param lastName    last name of guest
   * @param address     address of guest
   * @param phone       phone number of guest
   * @param nationality nationality of guest
   * @param checkedInDate checked in date
   * @param roomNumber   room number of guest
   */

  public Guest(String firstName, String lastName, String address, String phone,
      String nationality, LocalDate dateOfBirth, LocalDate checkedInDate, String roomNumber)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.nationality = nationality;
    this.dateOfBirth = dateOfBirth;
    this.checkedInDate = checkedInDate;
    this.roomNumber = roomNumber;
  }

  /**
   * This function returns the first name of the person.
   *
   * @return The first name of the person.
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * This function sets the firstName variable to the value of the firstName
   * parameter.
   *
   * @param firstName The first name of the user.
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * This function returns the last name of the person.
   *
   * @return The last name of the person.
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * This function sets the last name of the person.
   *
   * @param lastName The last name of the user.
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * This function returns the address of the person.
   *
   * @return The address of the person.
   */
  public String getAddress()
  {
    return address;
  }

  /**
   * This function sets the address of the object to the value of the parameter.
   *
   * @param address The address of the server.
   */
  public void setAddress(String address)
  {
    this.address = address;
  }

  /**
   * This function returns the phone number of the person.
   *
   * @return The phone number of the person.
   */
  public String getPhone()
  {
    return phone;
  }

  /**
   * This function sets the phone number of the person.
   *
   * @param phone The phone number to send the message to.
   */
  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  /**
   * This function returns the nationality of the person.
   *
   * @return The nationality of the person.
   */
  public String getNationality()
  {
    return nationality;
  }

  /**
   * This function sets the nationality of the person.
   *
   * @param nationality The nationality of the person.
   */
  public void setNationality(String nationality)
  {
    this.nationality = nationality;
  }

  /**
   * This function sets the date of birth of the person.
   *
   * @param dateOfBirth The date of birth of the person.
   */
  public void setDateOfBirth(LocalDate dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * This function returns the room Number for that guest
   *
   * @return the room number
   */
  public String getRoomNumber()
  {
    return roomNumber;
  }

  /**
   * This function returns the date of birth of the person.
   *
   * @return The date of birth of the person.
   */
  public LocalDate getDateOfBirth()
  {
    return dateOfBirth;
  }

  /**
   * The toString() method returns a string representation of the Guests object
   *
   * @return The method returns a string that contains the first name, last name,
   * address, phone number, nationality and date of birth of the person.
   */
  public String toString()
  {
    return "Name: " + firstName + " " + lastName + " ,Address: " + address
        + " ,Phone Number: " + phone + " ,Nationality: " + nationality
        + " Date of birth: " + dateOfBirth + "\n";
  }



  /**
   * If the object is not a Guest, it returns false. If it is a Guest, compare the
   * firstName, lastName, address, nationality, phone, and dateOfBirth fields. If
   * they are all equal, returns true
   *
   * @param obj The object to compare with Guest.
   * @return boolean.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Guest))
    {
      return false;
    }
    Guest others = (Guest) obj;
    return firstName.equals(others.firstName) && lastName.equals(
        others.lastName) && address.equals(others.address)
        && nationality.equals(others.nationality) && phone.equals(others.phone)
        && dateOfBirth.equals(others.dateOfBirth);
  }
}
