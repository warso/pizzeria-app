package fr.pizzeria.dao.fichier;

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

import fr.pizzeria.dao.StockageException;
import fr.pizzeria.dao.DeletePizzaException;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.SavePizzaException;
import fr.pizzeria.dao.UpdatePizzaException;
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

		try (Stream<Path> list = Files.list(Paths.get(DaoFichierFactory.getDataDir()))) {

			return list.map(path -> { // map le path en pizzas
				String code = path.toFile().getName().replaceAll(".txt", "");

				try (Stream<String> lines = Files.lines(path);) {

					Optional<String> premiereLigneDuFichier = lines.findFirst();

					String premiereLigne = premiereLigneDuFichier
							.orElseThrow(() -> new StockageException("fichier vide"));
					String[] valueTab = premiereLigne.split(";");
					return new Pizza(0, code, valueTab[0], Double.valueOf(valueTab[1]),
							CategoriePizza.valueOf(valueTab[2]));

				} catch (IOException e) {
					throw new StockageException(e);
				}

			}).collect(Collectors.toList()); // Collecte toutes les pizzas

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

		String pathNewFile = DaoFichierFactory.getDataDir() + "/" + pizza.getCode() + ".txt";
		try (BufferedWriter writer = Files.newBufferedWriter(FileSystems.getDefault().getPath(pathNewFile))) {

			String chaine = pizza.getNom() + ";" + pizza.getPrix() + ";" + pizza.getCat();
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

			Files.delete(Paths.get(DaoFichierFactory.getDataDir() + "/" + code + ".txt"));

		} catch (IOException e) {
			throw new StockageException(e);
		}

		return false;
	}

}
