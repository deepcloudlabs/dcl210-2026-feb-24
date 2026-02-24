package com.example.se07;

import java.io.Closeable;
import java.io.IOException;

public class Exercise10 {
	public static void main(String[] args) {
		PreciousResource res1 = new PreciousResource(1);
		PreciousResource res2 = new PreciousResource(2);
		PreciousResource res3 = new PreciousResource(3);
		PreciousResource res4 = new PreciousResource(4);
		try {
			// use resources
			System.err.println("Using the resources...");
			throw new IllegalArgumentException("Something is wrong!");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				res1.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					res2.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				} finally {
					try {
						res3.close();
					} catch (IOException e) {
						System.err.println(e.getMessage());
					} finally {
						try {
							res4.close();
						}catch (IOException e) {
							System.err.println(e.getMessage());
						} 
					}
				}
			}
		}

	}

}

class PreciousResource implements Closeable {
	private final int id;

	public PreciousResource(int id) {
		this.id = id;
	}

	@Override
	public void close() throws IOException {
		System.err.println("Closing the resource (%d)...".formatted(id));
		throw new IOException("An error has occured while closing the resource (%d)...".formatted(id));
	}
}