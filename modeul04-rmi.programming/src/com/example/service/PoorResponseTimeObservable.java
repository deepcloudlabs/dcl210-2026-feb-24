package com.example.service;

import java.util.Observable;

public class PoorResponseTimeObservable extends Observable {
	public void changed() {
		setChanged();
	}
}