package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {
 
	public ModifierPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(pizzaDao);
	}

	public boolean execute() throws UpdatePizzaException {
		
		Scanner questionUser = new Scanner(System.in);
		questionUser.useLocale(Locale.US);
		
		ListerPizzaOptionMenu listePizza = new ListerPizzaOptionMenu(pizzaDao);
		listePizza.execute();

		System.out.println("Veuillez choisir la pizza a modifier");
		System.out.println("99 pour abandonner");

		String code = questionUser.next(); // Choix de la pizza a
											// modifier

		if (!code.equals("99")) {

			System.out.println("Veuillez saisir le nouveau code");
			String nouveaucode = questionUser.next(); // Choix du
														// nouveau code
														// de la pizza
			if (verifCode(nouveaucode)){
				throw new UpdatePizzaException("Le code existe deja");
			}
			System.out.println("Veuillez saisir le nouveau nom (sans espace)");
			String nouveaunom = questionUser.next(); // Choix du nouveau
														// nom de la
														// pizza
			if (nouveaunom.isEmpty()){
				throw new UpdatePizzaException("Le nom est vide");
			}
			System.out.println("Veuillez saisir le nouveau prix");
			double nouveauprix = questionUser.nextDouble(); // Donner un
														// nouveau prix
														// a la pizza
			
			System.out.println("Veuillez choisir VIANDE, POISSON ou SANS_VIANDE");
			String cat = questionUser.next(); // Donner un
														// nouveau prix
														// a la pizza

			Pizza pizzaAModifier = new Pizza ( 0, nouveaucode, nouveaunom, nouveauprix, CategoriePizza.valueOf(cat));
			pizzaDao.updatePizza(code, pizzaAModifier);
			

		}
		return true;
	}

}
