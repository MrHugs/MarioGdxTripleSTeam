package interfaces;

import java.util.ArrayList;

public interface Observable {
	
		public void addObserver(Observador observador);
		public void removeObserver(Observador observador);
		public void notifyObservers();

}
