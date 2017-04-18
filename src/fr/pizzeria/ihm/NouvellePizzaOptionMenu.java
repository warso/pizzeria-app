package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImp;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class NouvellePizzaOptionMenu extends OptionMenu {
	
	public NouvellePizzaOptionMenu(IPizzaDao pizzaDao) {
		super(pizzaDao);
	}

	public boolean execute() throws SavePizzaException {
		
		Scanner questionUser = new Scanner(System.in);
		questionUser.useLocale(Locale.US);
		System.out.println("Veuillez saisir le code");
		String code = questionUser.next(); // Choix du code de la pizza
		
		
		System.out.println("Veuillez saisir le nom (sans espace)");
		String nom = questionUser.next(); // Choix du nom de la pizza
		
		
		System.out.println("Veuillez saisir le prix");
		double prix = questionUser.nextDouble(); // Donner un prix à la pizza
		if(prix<0){
			throw new SavePizzaException("Veuillez saisir un prix positif");
		}
		
		System.out.println("Veuillez choisir VIANDE, POISSON ou SANS_VIANDE");
		String cat = questionUser.next(); // Donner un
													// nouveau prix
													// à la pizza

		Pizza pizza = new Pizza(PizzaDaoImp.nbrePizza, code, nom, prix,  CategoriePizza.valueOf(cat));
		pizzaDao.saveNewPizza(pizza);
		
//		return nbrePizza;
		return true;
	}
	

}
