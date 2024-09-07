package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadCalc  extends Thread{
	private static int id = 1;
	private Semaphore semaforo;
	
	public ThreadCalc(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	public void run() {
		calc(id++);
	}
	
	private void calc(int idt) {
		Random aleat = new Random();
		
		int min = 0,  max = 0,  rep = 0, repbd = 0;
		
		switch(idt % 3) {
		case 1:
			min = 200;
			max = 1000;
			rep = 2;
			break;
		case 2:
			min = 500;
			max = 1500;
			rep = 3;
			break;
		case 0:
			min = 200;
			max = 1000;
			rep = 3;
			break;
		}
		
		repbd = rep;
		
		while(rep > 0) {
			int calc = aleat.nextInt(min, max);
			try {
				Thread.sleep(calc);
				System.out.println(idt+ "# calculando por " + ((double) calc / 1000) + "s.");
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			rep--;
		}
		
		try {
			semaforo.acquire();
			dataBase(max, repbd, idt);	
		}catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}finally {
			semaforo.release();
		}
	}
	
	private void dataBase(int bd, int rep, int idt) {
		while(rep > 0) {
			try {
				Thread.sleep(bd);
				System.out.println(idt+ "# efetuando transação com banco de dados por " + ((double) bd / 1000) + "s.");
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			rep--;
		}
	}
}
