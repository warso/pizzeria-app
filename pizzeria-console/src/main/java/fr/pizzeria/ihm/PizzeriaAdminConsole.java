package fr.pizzeria.ihm;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
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

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String value = bundle.getString("dao.impl");
		
		String uneClasse = value;
		Class<?> maClasse = Class.forName(uneClasse);
		
		DaoFactory unObjet = (DaoFactory) maClasse.newInstance();

		int choixMenu = 0;

		while (choixMenu != 99) { // Valable tant qu'on ne sort pas

			String asciiArt = FigletFont.convertOneLine("PIZZERIA APP");
			System.out.println(asciiArt);
			System.out.println("*****Pizzeria Administration*****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("5. Import de données");
			System.out.println("99. Sortir");
			Scanner questionUser = new Scanner(System.in);
			questionUser.useLocale(Locale.US);
			choixMenu = questionUser.nextInt(); // Le choix de l'utilisateur

			/** Lister les pizzas */

			if (choixMenu == 1) {
				ListerPizzaOptionMenu liste = new ListerPizzaOptionMenu(unObjet.getPizzaDao());
				liste.execute();
			}

			/** Ajout de pizza */

			if (choixMenu == 2) {

				NouvellePizzaOptionMenu ajout = new NouvellePizzaOptionMenu(unObjet.getPizzaDao());
				try {
					ajout.execute();
				} catch (SavePizzaException e) {
					System.out.println(e.getMessage());
				}
			}

			/** Modification de pizza */

			if (choixMenu == 3) {

				ModifierPizzaOptionMenu modif = new ModifierPizzaOptionMenu(unObjet.getPizzaDao());
				try {
					modif.execute();
				} catch (UpdatePizzaException e) {
					System.out.println(e.getMessage());
				}
			}

			/** Suppression de pizza */

			if (choixMenu == 4) {

				SupprimerPizzaOptionMenu supp = new SupprimerPizzaOptionMenu(unObjet.getPizzaDao());
				try {
					supp.execute();
				} catch (DeletePizzaException e) {
					System.out.println(e.getMessage());
				}
			}
			
			if ((choixMenu == 5) && (value.equals("fr.pizzeria.jdbc.DaoJdbcFactory"))){
				
			}
			else{
				
			}
		}
		if (choixMenu == 99) {
			System.out.println("Au revoir \u1F614");
		}

	}

}
