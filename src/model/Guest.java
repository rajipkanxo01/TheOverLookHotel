package model;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * A class that is used to store the first name,last name, address, phone number, nationality and date of birth of a guest.
 * @author Rajib Paudyal
 * @version 1.0.0
 */
public class Guest implements Serializable
{

  private String firstName;
  private String lastName;
  private String fullName;
  private String address;
  private String phone;
  private String nationality;
  private LocalDate dateOfBirth;
  private LocalDate checkedInDate;
  private LocalDate checkOutDate;
  private String roomNumber;
  private boolean smoking;

  /**
   * instantiates the Guest class
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
    fullName = firstName + " " + lastName;
  }



  // This is a constructor that takes in all the parameters and sets the values of
  // the variables to the parameters.

  public Guest(String firstName, String lastName, String address, String phone,String nationality, LocalDate dateOfBirth,
      LocalDate checkedInDate,LocalDate checkOutDate, String roomNumber , boolean smoking)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.roomNumber = roomNumber;
    this.nationality = nationality;
    this.dateOfBirth = dateOfBirth;
    this.checkedInDate = checkedInDate;
    this.checkOutDate = checkOutDate;
    this.smoking = smoking;
  }

  /**
   * Instantiates a new Guest.
   *
   * @param roomNumber          the room number
   * @param checkedInDateString the checked in date string
   */
  public Guest(String roomNumber, LocalDate checkedInDateString)
  {

    this.checkedInDate = checkedInDateString;
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
   * This function returns the last name of the person.
   *
   * @return The last name of the person.
   */
  public String getLastName()
  {
    return lastName;
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
   * This function returns the phone number of the person.
   *
   * @return The phone number of the person.
   */
  public String getPhone()
  {
    return phone;
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
   * Gets check in date.
   *
   * @return the check in date
   */
  public LocalDate getCheckedInDate()
  {
    return checkedInDate;
  }
  /**
   * This function returns the checkOutDate of the current object
   *
   * @return The checkOutDate is being returned.
   */
  public LocalDate getCheckOutDate()
  {
    return checkOutDate;
  }

  /**
   * This function returns the full name of the person.
   *
   * @return The full name of the person.
   */
  public String getFullName()
  {
    return fullName;
  }


  /**
   * If the person is smoking, return true.
   *
   * @return true if smoking or else false
   */
  public boolean ifSmoking() {
    return smoking;
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
        + " ,Date of birth: " + dateOfBirth + "\n";
  }

}
