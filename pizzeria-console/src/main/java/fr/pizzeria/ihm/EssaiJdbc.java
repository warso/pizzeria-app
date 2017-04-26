package fr.pizzeria.ihm;

import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EssaiJdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false",
				"root", "");
		Statement statement = myConnection.createStatement();
		ResultSet listePizza = statement.executeQuery("SELECT COUNT(id) FROM pizza");
		
		int nbPizza = 0;
		if (listePizza.next()) {
			nbPizza = listePizza.getInt(1);
		}

		PreparedStatement selectPizza = (PreparedStatement) myConnection
				.prepareStatement("SELECT * FROM pizza WHERE id=?");
		for (int i = 1; i <= nbPizza; i++) {
			selectPizza.setInt(1, i);
			ResultSet resultats = selectPizza.executeQuery();
			while (resultats.next()) {
				Integer id = resultats.getInt("id");
				String title = resultats.getString("libelle");
				BigDecimal price = resultats.getBigDecimal("prix");
				System.out.println("[id = " + id + ", libelle = " + title + ", prix = " + price + "]");
			}
			resultats.close();
		}
	}
}
