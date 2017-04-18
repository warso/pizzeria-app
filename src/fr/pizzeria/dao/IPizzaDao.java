package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {
	
	List<Pizza> findAllPizzas();
	boolean saveNewPizza(Pizza pizza);
	boolean updatePizza(String code, Pizza pizza);
	boolean deletePizza(String code);

}
