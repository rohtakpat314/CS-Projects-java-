/*
 * Author: Rohtak Patwardhan
 * Citations: no help given or received. 
 */

/**
 * Shopping cart utilities class that allows users to find the position 
 * of an item in the shopping cart.
 * It also allows users to add items to thei
    t and accounts for whether
 * they are already present or new. Thus, it allows users to add a quantity 
 * of 1 to an item already there or add an entirely new item.
 * Conversely, the user has the ability to delete an entire item from the cart
 * which discards the associated quantity with that item and decreases the
 * cart size.
 * Users can also be aware of how much money they will be spending by having 
 * the ability to track the cost of an item based on whether it's in inventory
 * and finally obtain the total cost of their cart depending on whether the 
 * item is in stock (in inventory) and the associated costs of said items.
 *
 * @author Rohtak Patwardhan'
 */

public class CartUtilities {

  /**
   * Determines whether an item is present in the cart given its name.
   * If the item is present, it gives the index at which its present 
   * in the cart. 
   * @param cart a 2D array that has a description and quantity wanted
   * @param cartSize tracks the number of items in the cart 
   * @param description the item's name 
   * @return index where item is, otherwise returns -1 (not present)
   */  

  public static int indexOfItem(String[][] cart, int cartSize, String description) { 
    for (int i = 0; i < cartSize; ++i) { 
      if (cart[i][0].equals(description)) { 
        return i; // i represents the index of the item if present in cart
      }
    }
    return -1; // return -1 otherwise, meaning that the item isn't in the cart 
  }

  /**
   * Adds an item to cart if not present. Otherwise, it adds the value wanted 
   * for a pre-existing item by 1. 
   * @param cart a 2D array that has a description and quantity wanted
   * @param cartSize tracks the number of items in the cart 
   * @param description the item's name 
   * @return newCartSize, the new number of different items in the cart 
   */  

  public static int addItemToCart(String[][] cart, int cartSize, String description) {
    int newCartSize = cartSize; 
    if ((indexOfItem(cart, cartSize, description) == -1) && (cartSize < cart.length)) { 
      newCartSize += 1;
      cart[cartSize] = new String[2];
      cart[cartSize][0] = description;
      cart[cartSize][1] = "1";
    } else { 
      int numItems = Integer.parseInt(cart[indexOfItem(cart, cartSize, description)][1]);
      numItems += 1; 
      String numItemsAsString = Integer.toString(numItems);
      cart[indexOfItem(cart, cartSize, description)][1] = numItemsAsString; 
    }
    return newCartSize; 
  }

  /**
   * Removes an item from cart given a specified position in the cart
   * that corresponds to an item. Removes all quantity and description 
   * of that cart, thus shifting all other items back by 1. 
   * @param cart a 2D array that has a description and quantity wanted
   * @param cartSize tracks the number of items in the cart 
   * @param index the specified location in the cart for the item to remove 
   * @return newCartSize, the new number of different items in the cart 
   */ 

  public static int removeItemFromCart(String[][] cart, int cartSize, int index) {
    int newCartSize = cartSize; 
    if ((index >= 0) && (index <= cartSize - 1)) { 
      newCartSize -= 1;
      for (int i = index; i < cartSize - 1; ++i) { 
        for (int j = 0; j < 2; ++j) {
          cart[i][j] = cart[i + 1][j]; // 
        }
      }
      cart[newCartSize] = null;
    } else { 
      newCartSize = newCartSize; // no change if the index isn't in the specified range 
    } 
    return newCartSize; 
  }

  /**
   * Checks the inventory to see if an item is present and returns the cost 
   * for that item if it is present. 
   * @param inventory an array that contains descriptions or the names of the items 
   * @param costs array is parallel to inventory and has associated costs for items 
   * @param description the item's name 
   * @return newCartSize, the new number of different items in the cart 
   */ 

  public static int getCostOfItem(String[] inventory, int[] costs, String description) {
    for (int i = 0; i < inventory.length; ++i) { 
      if (inventory[i].equals(description)) { 
        return costs[i]; // return the cost of the item if it's in the cart. Parallel arrays.
      }
    }
    return -1;
  }

  /**
   * Checks to see if the items in the cart match the items in inventory.
   * Then, it multiplies the amount of items the customer wishes for that item
   * by the associated cost with that item as it corresponds to the inventory.
   * @param cart a 2D array that has a description and quantity wanted
   * @param cartSize tracks the number of items in the cart 
   * @param inventory an array that contains descriptions or the names of the items
   * @param costs array is parallel to inventory and has associated costs for items 
   * @return total cost of the items in the cart that are available (quantity*cost)
   */   

  public static int getTotalCost(String[][] cart, int cartSize, String[] inventory, int[] costs) {
    int totalCost = 0; // initialized to 0
    int qty;
    for (int i = 0; i < inventory.length; ++i) { 
      for (int j = 0; j < cartSize; ++j) { 
        if (inventory[i].equals(cart[j][0])) { 
          qty = Integer.parseInt(cart[j][1]);
          totalCost += costs[i] * qty; // increment cost by quantity * cost
          continue; // check for next matching item since a description may be present at max once
        }
      }
    }
    return totalCost; // returns total cost of items in the cart
  }
}
