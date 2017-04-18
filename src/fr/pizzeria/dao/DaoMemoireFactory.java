package fr.pizzeria.dao;

public class DaoMemoireFactory implements DaoFactory {

	
	private IPizzaDao pizzaDao = new PizzaDaoImp();
	
	@Override
	public IPizzaDao getPizzaDao() {
		
		return pizzaDao;
	}
	
	

}
