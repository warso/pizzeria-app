package fr.pizzeria.model;

public class Pizza {
	
	public Pizza(int id, String code, String nom, double prix, CategoriePizza cat) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.cat=cat;
	}
	public Pizza() {
		// TODO Auto-generated constructor stub
	}
	private int id;
	private String code;
	private String nom;
	private double prix;
	public CategoriePizza cat;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	

}
