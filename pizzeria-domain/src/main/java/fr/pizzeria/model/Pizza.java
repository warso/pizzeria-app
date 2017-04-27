package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {

	public Pizza(int id, String code, String nom, double prix, CategoriePizza cat) {
		super();
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.cat = cat;
	}

	public Pizza() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	public CategoriePizza getCat() {
		return cat;
	}

	public void setCat(CategoriePizza cat) {
		this.cat = cat;
	}

	@Column(name = "code")
	private String code;

	@Column(name = "libelle")
	private String nom;

	@Column(name = "prix")
	private double prix;

	@Enumerated(EnumType.STRING)
	@Column(name="categorie")
	private CategoriePizza cat;

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
