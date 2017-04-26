package fr.pizzeria.dao.fichier;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.IPizzaDao;

public class DaoFichierFactory implements DaoFactory {

	private static final String DATA_DIR = "data";

	public static String getDataDir() {
		return DATA_DIR;
	}

	private IPizzaDao pizzaDao = new PizzaDaoImpFichier(DATA_DIR);

	@Override
	public IPizzaDao getPizzaDao() {
		
		return pizzaDao;
	}
	
	

}
