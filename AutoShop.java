package ;

import java.util.*;

/**
 * A class representing a <strong> auto shop </strong> that has an <strong>
 * owner</strong> . A auto shop <strong> <em> owns a collection (or possibly
 * collections) of vehicles, but does not own the vehicles themselves</em>
 * </strong>. In other words, <strong> <em>the auto shop and its collection of
 * vehicles form a composition</em> </strong>.
 *
 * <p>
 * Only the owner of the auto shop is able to sell vehicles from the auto shop.
 * <strong> <em> The auto shop does NOT own its owner</em> </strong>. In other
 * words, <strong> <em>the auto shop and its owner form an aggregation</em>
 * </strong>.
 * </p>
 */

public class AutoShop {

	/*
	 * YOU NEED A FIELD HERE TO HOLD THE Vehicle OF THIS AutoShop
	 */
	private ShopOwner owner;
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	/**
	 * Initializes this auto shop so that it has the specified owner and no
	 * vehicles.
	 *
	 * @param owner the owner of this auto shop
	 */
	public AutoShop(ShopOwner owner) {
		// COMPLETE THIS
		this.owner = owner;

	}

	/**
	 * Initializes this auto shop by copying another auto shop. This auto shop will
	 * have the same owner and the same number and type of vehicles as the other
	 * auto shop.
	 *
	 * @param other the auto shop to copy
	 */
	public AutoShop(AutoShop other) {
		// COMPLETE THIS
		this.owner = other.owner;
		this.vehicles = other.vehicles;

	}

	/**
	 * Returns the owner of this auto shop.
	 *
	 * <p>
	 * This method is present only for testing purposes. Returning the owner of this
	 * auto shop allows any user to sell vehicles from the auto shop (because any
	 * user can get the owner of this auto shop)!
	 *
	 * @return the owner of this auto shop
	 */
	public ShopOwner getOwner() {
		// ALREADY IMPLEMENTED; DO NOT MODIFY
		return this.owner;
	}

	/**
	 * Allows the current owner of this auto shop to give this auto shop to a new
	 * owner.
	 *
	 * @param currentOwner the current owner of this auto shop
	 * @param newOwner     the new owner of this auto shop
	 * @throws IllegalArgumentException if currentOwner is not the current owner of
	 *                                  this auto shop
	 */
	public void changeOwner(ShopOwner currentOwner, ShopOwner newOwner) {

		// COMPLETE THIS
		if (!(this.owner.equals(currentOwner))) {
			throw new IllegalArgumentException("You are not current owner of this auto shop ");
		}
		this.owner = newOwner;

	}

	/**
	 * Adds the specified vehicles to this auto shop.
	 *
	 * @param vehicles a list of vehicles to add to this auto shop
	 */
	public void add(List<Vehicle> vehicles) {
		// COMPLETE THIS
		for (Vehicle vc : vehicles) {
			this.vehicles.add(vc);
		}
	}

	/**
	 * Returns true if this auto shop contains the specified vehicle, and false
	 * otherwise.
	 *
	 * @param vehicle a vehicle
	 * @return true if this auto shop contains the specified vehicle, and false
	 *         otherwise
	 */
	public boolean contains(Vehicle vehicle) {

		// COMPLETE THIS
		if (this.vehicles.contains(vehicle)) {
			return true;
		} else
			return false;
	}

	/**
	 * Allows the owner of this auto shop to sell a single vehicle equal to the
	 * specified vehicle from this auto shop.
	 *
	 * <p>
	 * If the specified user is not equal to the owner of this auto shop, then the
	 * vehicle is not sold from this auto shop, and null is returned.
	 *
	 * @param user    the person trying to sell the vehicle
	 * @param vehicle a vehicle
	 * @return a vehicle equal to the specified vehicle from this auto shop, or null
	 *         if user is not the owner of this auto shop @pre. the auto shop
	 *         contains a vehicle equal to the specified vehicle
	 */
	public Vehicle sellingsingleVehicle(ShopOwner user, Vehicle vehicle) {

		// COMPLETE THIS
		Vehicle vc = new Vehicle();
		if (user.equals(owner)) {
			if (this.vehicles.contains(vehicle)) {
				// this.vehicles.remove(vehicle);
				vc = vehicle;
			}
		} else {
			vc = null;
		}
		return vc;
	}

	/**
	 * Allows the owner of this auto shop to sell the smallest number of vehicles
	 * whose total price value in dollars is equal or less than to the specified
	 * price value in dollars.
	 *
	 * <p>
	 * Returns the empty list if the specified user is not equal to the owner of
	 * this auto shop.
	 * </p>
	 *
	 * @param user       the person trying to sell vehicles from this auto shop
	 * @param pricevalue a value in dollars
	 * @return the smallest number of vehicles whose total price value in dollars is
	 *         equal to the specified value in dollars from this auto shop @pre. the
	 *         auto shop contains a group of vehicles whose total price value is
	 *         equal to specified value
	 */
	public List<Vehicle> sellingVehicles(ShopOwner user, int pricevalue) {

		// COMPLETE THIS

		List<Vehicle> result = new ArrayList<Vehicle>();
		List<Vehicle> other = deepCopy();
		Collections.reverse(other);
		while (pricevalue > 0 && other.size() != 0) {
			if (other.get(0).getPrice() <= pricevalue) {
				result.add(other.get(0));
				pricevalue -= other.get(0).getPrice();
			}
			other.remove(other.get(0));
		}
		return result;

	}

	/**
	 * of the vehicles in this auto shop. The returned list has its vehicles in
	 * sorted order (from smallest price value to largest price value).
	 * <p>
	 * Remember that <strong> <em>the auto shop and its collection of vehicles form
	 * a composition.</em> </strong>
	 * </p>
	 *
	 * @return a deep copy of the vehicles in this auto shop sorted by price value
	 */
	public List<Vehicle> deepCopy() {

		// COMPLETE THIS
		List<Vehicle> t = new ArrayList<Vehicle>();
		for (Vehicle vc : vehicles) {
			t.add(new Vehicle(vc));
		}
		Collections.sort(t);
		return t;
	}

	/**
	 * Returns a Shallow copy of the vehicles in this auto shop. The returned list
	 * has its vehicles in sorted order (from smallest year of make value to largest
	 * year of make value).
	 * <p>
	 * Remember that <strong> <em>the auto shop and its collection of vehicles form
	 * a composition.</em> </strong>
	 * </p>
	 *
	 * @return a show copy of the vehicles in this auto shop sorted by year of make
	 */

	public List<Vehicle> shallowCopySortedbyYear() {
		// COMPLETE THIS
		List<Vehicle> t = new ArrayList<Vehicle>();
		for (Vehicle vc : vehicles) {
			t.add(vc);
			Collections.sort(t, new SortVehiclebyYear());
		}
		return t;

	}

	/**
	 * Returns a deep copy of the vehicles in this auto shop. The returned list has
	 * its vehicles in sorted based on make and then ordered based on price value.
	 *
	 * (i.e., from smallest price value to largest price value).
	 * <p>
	 * Remember that <strong> <em>the auto shop and its collection of vehicles form
	 * a composition.</em> </strong>
	 * </p>
	 *
	 * @return a deep copy of the vehicles in this auto shop sorted based on make
	 *         and then ordered based on price value.
	 */
	public List<Vehicle> deepCopySortedbyMakePrice() {
		// COMPLETE THIS
		List<Vehicle> t = new ArrayList<Vehicle>();
		for (Vehicle vc : vehicles) {
			t.add(vc);
			Collections.sort(t, new SortVehiclebyMake());
		}
		return t;

	}

}

