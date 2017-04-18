package fr.pizzeria.ihm;

public class Menu {
	
	//public String Titre;
	public OptionMenu[] option;
	

//	public Menu(String titre) {
//		super();
//		//Titre = titre;
//	}

	public void affiche(){
		
		System.out.println("*****Pizzeria Administration*****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");

	}


}
