package com.example.se23;

public class Exercise01 {
	/**
	 * Returns the {@code User} with the given user ID.
	 * 
	 * <p>Example:
	 * <pre>{@code 
	 *      var user = findUser(3615);
	 *  }
	 *  </pre>
	 *  </p>
	 *  
	 *  @param userId the user ID
	 *  @return the user
	 */
	
	public User findUser(int userId) {
		return new User("jack bauer");
	}
}


record User(String fullname) {}