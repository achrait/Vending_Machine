package com.techelevator;

import com.techelevator.vend.VendingMachine;
import com.techelevator.vend.Menu;


import java.time.LocalDateTime;
import java.util.Scanner;

import static com.techelevator.vend.VendLog.*;

public class Application {
	public static void main(String[] args) {
		VendingMachine vendingMachine = new VendingMachine();
		Menu menu = new Menu(vendingMachine);
		menu.mainMenu();
	}
}