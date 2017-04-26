package fr.pizzeria.jdbc;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import fr.pizzeria.dao.DeletePizzaException;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.SavePizzaException;
import fr.pizzeria.dao.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJdbc implements IPizzaDao {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pizza> findAllPizzas() {

		try {

			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false",
					"root", "");
	
			PreparedStatement selectPizza = (PreparedStatement) myConnection
					.prepareStatement("SELECT * FROM pizza");
			List<Pizza> pizzaTab = new ArrayList<Pizza>();
				ResultSet resultats = selectPizza.executeQuery();
				while (resultats.next()) {
					Integer id = resultats.getInt(1);
					String title = resultats.getString(2);
					Double price = resultats.getDouble(3);
					String code = resultats.getString(4);

					Pizza pizza = new Pizza(id, code, title, price, CategoriePizza.valueOf(resultats.getString(5)));
					pizzaTab.add(pizza);
				}
				resultats.close();
				selectPizza.close();
			return pizzaTab;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;

	}

	public Pizza findPizzaById(int id) {
		return null;
	}

	public Pizza findPizzaByCode(String code) {
		return null;
	}

	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {

		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false",
					"root", "");
			myConnection.setAutoCommit(false);
			PreparedStatement createPizza = (PreparedStatement) myConnection
					.prepareStatement("INSERT INTO pizza(id,libelle,prix,code,categorie) VALUES(?,?,?,?,?)");
			createPizza.setInt(1, pizza.getId());
			createPizza.setString(2, pizza.getNom());
			createPizza.setDouble(3, pizza.getPrix());
			createPizza.setString(4, pizza.getCode());
			createPizza.setString(5, pizza.cat.toString());
			createPizza.executeUpdate();
			
			myConnection.commit();
			
			createPizza.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updatePizza(String code, Pizza pizza) throws UpdatePizzaException {

		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false",
					"root", "");
			PreparedStatement updatePizza = (PreparedStatement) myConnection
					.prepareStatement("UPDATE pizza SET libelle=?, prix=?,code=?,categorie=? WHERE code=?");

			updatePizza.setString(1, pizza.getNom());
			updatePizza.setDouble(2, pizza.getPrix());
			updatePizza.setString(3, pizza.getCode());
			updatePizza.setString(4, pizza.cat.toString());
			updatePizza.setString(5, code);
			updatePizza.executeUpdate();
			
			updatePizza.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean deletePizza(String code) throws DeletePizzaException {

		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false",
					"root", "");
			PreparedStatement deletePizza = (PreparedStatement) myConnection
					.prepareStatement("DELETE FROM pizza WHERE code=?");

			deletePizza.setString(1, code);
			deletePizza.executeUpdate();
			
			deletePizza.close();

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
