package fr.pizzeria.dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
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

		try (Stream<Path> list = Files.list(Paths.get(DaoFichierFactory.getDataDir()))){     //Recupère la liste des fichier present 
            																				// dans le repertoire data/pizzas

		return list.map(path->{ //map le path en pizzas 
		String code=path.toFile().getName().replaceAll(".txt", ""); //Recupère le code de pizza avec le nom du 
																	//fichier en enlevant le .txt
		
		try(Stream<String> lines = Files.lines(path);){ //Cette notation permet de fermer le stream à la fin du bloc try et de liberer la ressource
		
			Optional<String> premiereLigneDuFichier=lines.findFirst();    //Recupère la première ligne du fichier, 
																			//on utilise un Optional pour eviter l'exception
																			//si le fichier est vide
																			
		String premiereLigne=premiereLigneDuFichier.orElseThrow(()->new StockageException("fichier vide"));    //Permet de gèrer l'exception si le fichier est vide
		
		String[] valueTab=premiereLigne.split(";");   //Recupère les éléments de la ligne avec pour séparateur un ";"
		
		return new Pizza(0,code,valueTab[0],Double.valueOf(valueTab[1]),CategoriePizza.valueOf(valueTab[2])); //Retourne la pizza créer
		
		}catch(IOException e){
			throw new StockageException(e);
		}
		
		}).collect(Collectors.toList()); //Collecte toutes les pizzas
		
		}catch(IOException e){
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

		String pathNewFile = DaoFichierFactory.getDataDir() + "/" + pizza.getCode() + ".txt";
		try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(pathNewFile))) {

			String chaine = pizza.getNom() + ";" + pizza.getPrix() + ";" + pizza.cat;
			writer.write(chaine, 0, chaine.length());
		} catch (IOException x) {
			throw new StockageException(x);
		}
		return true;
	}

	@Override
	public boolean updatePizza(String code, Pizza pizza) throws UpdatePizzaException {

		this.deletePizza(code);
		this.saveNewPizza(pizza);

		return false;
	}

	@Override
	public boolean deletePizza(String code) throws DeletePizzaException {
		try {

			Files.delete(Paths.get(DaoFichierFactory.getDataDir() +"/"+ code + ".txt"));

		} catch (IOException e) {
			throw new StockageException(e);
		}

		return false;
	}

}
