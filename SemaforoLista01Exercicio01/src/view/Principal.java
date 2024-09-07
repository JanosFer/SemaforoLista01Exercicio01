package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCalc;

public class Principal {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 0; i < 21; i++) {
			ThreadCalc t = new ThreadCalc(semaforo);
			t.start();
		}
	}
}
