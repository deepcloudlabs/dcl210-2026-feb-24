package com.example.service;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class PoorResponseTimeObservable extends Observable {
	public void changed() {
		setChanged();
	}
}