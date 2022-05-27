package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class stores guest list
 * @author  Rajib Paudyal
 */
public class GuestList implements Serializable
{

  private ArrayList<Guest> guestList;

  /**
   * instantiates guest list object
   */

  public GuestList()
  {
    guestList = new ArrayList<>();
  }

  /**
   * add the guest to the guest list
   *
   * @param guest The guest to be added to the guest list.
   */
  public void addGuest(Guest guest)
  {
    guestList.add(guest);
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
   * This function returns the guest at the given index.
   *
   * @param index The index of the guest you want to get.
   * @return The guest at the index.
   */
  public Guest getGuestByIndex(int index)
  {
    return guestList.get(index);
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
