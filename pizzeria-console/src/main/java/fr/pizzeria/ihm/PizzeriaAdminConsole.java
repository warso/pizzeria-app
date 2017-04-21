package fr.pizzeria.ihm;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.github.lalyos.jfiglet.FigletFont;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.fichier.DaoFichierFactory;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.DeletePizzaException;
import fr.pizzeria.dao.SavePizzaException;
import fr.pizzeria.dao.UpdatePizzaException;
import fr.pizzeria.ihm.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.ModifierPizzaOptionMenu;
import fr.pizzeria.ihm.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.SupprimerPizzaOptionMenu;
import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsole {

	public static void main(String[] args) {

		DaoFactory daoFact = new DaoFichierFactory();
		IPizzaDao pizzaDao = daoFact.getPizzaDao();
		
		List<Pizza> tableauPizza = daoFact.getPizzaDao().findAllPizzas();

		// Pizza[] pizza= new Pizza[50];
		//
		// pizza[0] = new Pizza(0, "PEP", "Pépéroni", 12.50);
		// pizza[1] = new Pizza(1, "MAR", "Margherita", 14.00);
		// pizza[2] = new Pizza(2, "REIN", "La Reine", 11.50);
		// pizza[3] = new Pizza(3, "FRO", "La 4 Fromage", 12.00);
		// pizza[4] = new Pizza(4, "CAN", "La Cannibale", 12.50);
		// pizza[5] = new Pizza(5, "SAV", "La Savoyarde", 13.00);
		// pizza[6] = new Pizza(6, "ORI", "L'Orientale", 13.50);
		// pizza[7] = new Pizza(7, "IND", "L'Indienne", 14.00);

		

		int choixMenu = 0;
		//PizzaDaoImp.nbrePizza = 8;

		while (choixMenu != 99) { // Valable tant qu'on ne sort pas

			String asciiArt = FigletFont.convertOneLine("PIZZERIA APP");
			System.out.println(asciiArt);
			System.out.println("*****Pizzeria Administration*****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			Scanner questionUser = new Scanner(System.in);
			questionUser.useLocale(Locale.US);
			choixMenu = questionUser.nextInt(); // Le choix de l'utilisateur

			/** Lister les pizzas */

			if (choixMenu == 1) {
				ListerPizzaOptionMenu liste = new ListerPizzaOptionMenu(pizzaDao);
				liste.execute();
			}

			/** Ajout de pizza */

			if (choixMenu == 2) {

				NouvellePizzaOptionMenu ajout = new NouvellePizzaOptionMenu(pizzaDao);
				try {
					ajout.execute();
				} catch (SavePizzaException e) {
					System.out.println(e.getMessage());
				}
			}

			/** Modification de pizza */

			if (choixMenu == 3) {

				ModifierPizzaOptionMenu modif = new ModifierPizzaOptionMenu(pizzaDao);
				try {
					modif.execute();
				} catch (UpdatePizzaException e) {
					System.out.println(e.getMessage());
				}

			}

			/** Suppression de pizza */

			if (choixMenu == 4) {

				SupprimerPizzaOptionMenu supp = new SupprimerPizzaOptionMenu(pizzaDao);
				try {
					supp.execute();
				} catch (DeletePizzaException e) {
					System.out.println(e.getMessage());
				}
			}

		}
		if (choixMenu == 99) {
			System.out.println("Au revoir \u1F614");
		}

	}

}
