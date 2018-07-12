package com.maytwitt;

import java.util.Observable;

public class MessageMainWindow extends Observable {

	public MessageMainWindow() {
		 super();
	}
	
	void changeData(Object data) {
        setChanged(); // the two methods of Observable class
        notifyObservers(data);
    }
}
