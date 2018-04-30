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
}