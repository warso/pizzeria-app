package fr.pizzeria.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoImpFichier implements IPizzaDao {

	public static void main(String[] args) throws IOException{
	
	// lister le contenu d'un dossier
	
	 
		 
		 
		
			
				 					
			 
			 
		
	 

	 
	
	}

	@Override
	public List<Pizza> findAllPizzas() {
		try {
			return Files.list(Paths.get("data")).map(path-> {
				String code = path.toFile().getName().replaceAll(".txt","");
				 try{
					 String[] valueTab = Files.lines(path)
								.findFirst();
								.orElseThrow(()-> new StockageException("fichier vide"));
								.split(";");
								
					return new Pizza(0,code, valueTab[0],Double.valueOf(valueTab[1]), CategoriePizza.valueOf(valueTab[2]));
					
				 } catch(IOException e) {
					 throw new StockageException(e);
				 }
				}).collect(Collectors.toList());

				 }
			
		}
		
		
		return null;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePizza(String code, Pizza pizza) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePizza(String code) {
		// TODO Auto-generated method stub
		return false;
	}
}
