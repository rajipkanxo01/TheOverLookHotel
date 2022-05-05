package model;

import java.util.ArrayList;

public class GuestList
{
  /**
   * This class deals with adding and removing of guests.
   *
   * @author Rajib Paudyal
   * @version 1.0.0
   */

  private ArrayList<Guest> guestList;
  private int guestNumber;

  /**
   * Creating a new GuestList object with a guestNumber.
   *
   * @param guestNumber number of guest
   */

  public GuestList(int guestNumber)
  {
    guestList = new ArrayList<>();
    this.guestNumber = guestNumber;
  }

  /**
   * If the size of guest list is less than guest number, add the guest to the guest list
   *
   * @param guest The guest to be added to the guest list.
   */
  public void addGuest(Guest guest)
  {
    if (guestList.size() > guestNumber)
    {
      guestList.add(guest);
    }
  }

  /**
   * This function returns the number of guests in the guest list.
   *
   * @return The number of guests in the guest list.
   */
  public int getNumberOfGuest()
  {
    return guestList.size();
  }

  /**
   * "Remove the guest from the guest list."
   *
   * @param guest The guest to be removed from the guest list.
   */
  public void removeGuest(Guest guest)
  {
    for (int i = 0; i < guestList.size(); i++)
    {
      if (guestList.get(i).equals(guest))
      {
        guestList.remove(i);
        break;
      }
    }
  }

  /**
   * The function returns a string that contains information of all guests in the
   * guest list
   *
   * @return The toString method is returning a string of all the guests in the
   * guestList array.
   */
  public String toString()
  {
    String returnStr = " ";
    for (int i = 0; i < guestList.size(); i++)
    {
      returnStr += guestList.get(i);
    }
    return returnStr;
  }

  /**
   * If the object is not a GuestList, return false. Otherwise, return whether the
   * guestList is equal to the other GuestList's guestList.
   *
   * @param obj The object to compare to.
   * @return The return value is boolean.
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof GuestList))
    {
      return false;
    }
    GuestList others = (GuestList) obj;
    return guestList.equals(others.guestList);
  }
}