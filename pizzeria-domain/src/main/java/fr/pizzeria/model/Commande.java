package fr.pizzeria.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Commande {

	public Commande(int id, int numeroCommande, String statut, Date dateCommande, Livreur livreur, Client client) {
		super();
		this.id = id;
		this.numeroCommande = numeroCommande;
		this.statut = statut;
		this.dateCommande = dateCommande;
	}

	public Commande() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "numero_commande")
	private int numeroCommande;

	@Column(name = "statut")
	private String statut;

	@Column(name = "date_commande")
	private Date dateCommande;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "livreur_id")
	private Livreur livreur;

	@ManyToMany
	@JoinTable(name = "commande_pizza", joinColumns =

	@JoinColumn(name = "commande_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"))
	private Set<Pizza> pizzas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
