package fr.pizzeria.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoImpFichier implements IPizzaDao {
	
	private String dataDir;

	public PizzaDaoImpFichier(String dataDir) {
		super();
		this.dataDir = dataDir;
	}
	


	@Override
	public List<Pizza> findAllPizzas() {

		try {
			return Files.list(Paths.get(dataDir)).map(path -> {
				
				String code = path.toFile().getName().replaceAll(".txt","");
				
				try {
					String[] valueTab = Files.lines(path)
												.findFirst()
												.orElseThrow(() -> new StockageException("fichier vide"))
												.split(";");
					
					return new Pizza(0, code, valueTab[0], Double.valueOf(valueTab[1]), CategoriePizza.valueOf(valueTab[2]));
					
				} catch (IOException e) {
					throw new StockageException(e);
				}
			}).collect(Collectors.toList());
		} catch (IOException e) {
			throw new StockageException(e);
		}
	}
	
	@Override
	public Pizza findPizzaById(int id) {
		
		return null;
	}



	@Override
	public Pizza findPizzaByCode(String code) {
		
		return null;
	}
	
	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pizza.getCode()+".txt")));
			
			// normalement si le fichier n'existe pas, il est crée à la racine du projet
			writer.write(dataDir);

			writer.close();
			}
			catch (IOException e)
			{
			e.printStackTrace();
			}
		
		
		return false;
	}

	@Override
	public boolean updatePizza(String code, Pizza pizza) throws UpdatePizzaException {
		
		return false;
	}

	@Override
	public boolean deletePizza(String code) throws DeletePizzaException {
	
		
		return false;
	}

}


