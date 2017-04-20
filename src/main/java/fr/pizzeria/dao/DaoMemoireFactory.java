package fr.pizzeria.dao;

public class DaoMemoireFactory implements DaoFactory {

	
	private IPizzaDao pizzaDao = null;
	
	@Override
	public IPizzaDao getPizzaDao() {
		
		return pizzaDao;
	}
	
	

}
