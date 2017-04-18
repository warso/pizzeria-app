package fr.pizzeria.ihm;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImp;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaOptionMenu  extends OptionMenu {

	public SupprimerPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(pizzaDao);
	}

	public boolean execute() throws DeletePizzaException {
		
		Scanner questionUser = new Scanner(System.in);
		questionUser.useLocale(Locale.US);
		
		ListerPizzaOptionMenu listePizza = new ListerPizzaOptionMenu(pizzaDao);
		listePizza.execute();

		System.out.println("Veuillez choisir la pizza à supprimer");
		System.out.println("99 pour abandonner");

		String code = questionUser.next(); // Choix de la pizza à supprimer
		if (code.isEmpty()){
			throw new DeletePizzaException("Le code est vide");
		}
		
		if (!code.equals("99")) {
			
		pizzaDao.deletePizza(code);
			
		}
		return true;
	}

}
