package fr.pizzeria.dao.fichier;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.IPizzaDao;

public class DaoFichierFactory implements DaoFactory {

	private static final String DATA_DIR = "C:/Users/ETY/Documents/workspace-sts-3.8.3.RELEASE/pizzeria-console- objet/data";

	public static String getDataDir() {
		return DATA_DIR;
	}

	private IPizzaDao pizzaDao = new PizzaDaoImpFichier(DATA_DIR);

	@Override
	public IPizzaDao getPizzaDao() {
		
		return pizzaDao;
	}

}
