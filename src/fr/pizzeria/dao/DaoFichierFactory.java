package fr.pizzeria.dao;

public class DaoFichierFactory implements DaoFactory {

	private static final String DATA_DIR = "data";

	private IPizzaDao pizzaDao = new PizzaDaoImpFichier(DATA_DIR);

	@Override
	public IPizzaDao getPizzaDao() {
		
		return pizzaDao;
	}

}
