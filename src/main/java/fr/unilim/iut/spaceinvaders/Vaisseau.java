package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	//ATTRIBUTS
	int x;
	int y;

	//CONSTRUCTEURS
	public Vaisseau(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//METHODES
	public boolean occupeLaPosition(int x, int y) {
		return (this.x==x) && (this.y==y);
	}
	public void seDeplacerVersLaDroite() {
		this.x = this.x + 1 ;
	}
	public void seDeplacerVersLaGauche() {
		this.x = this.x - 1;
	}
	//GETTERS SETTERS
	public int abscisse() {
		return x;
	}
}