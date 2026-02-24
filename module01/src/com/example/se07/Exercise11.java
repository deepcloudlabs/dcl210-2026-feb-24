package com.example.se07;

public class Exercise11 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try( // try-with-resources
				PreciousResource res1 = new PreciousResource(1);
				PreciousResource res2 = new PreciousResource(2);
				PreciousResource res3 = new PreciousResource(3);		
				PreciousResource res4 = new PreciousResource(4);		
				) 
		{
			// use resources
			System.err.println("Using the resources...");
			throw new IllegalArgumentException("Something is wrong!");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			for (Throwable t : e.getSuppressed()) {
				System.err.println(t.getMessage());
			}
		} 
	}

}