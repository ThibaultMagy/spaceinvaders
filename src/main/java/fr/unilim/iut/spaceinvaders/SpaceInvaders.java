package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders implements Jeu{
	//ATTRIBUTS
	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;
	Collision collision;

	//CONSTRUCTEUR
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.collision = new Collision();
	}
	
	
	//METHODES
	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		   if ((vaisseau.dimension.hauteur()+ dimensionMissile.hauteur()) > this.hauteur ) {
			   throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		   }
		   this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
 }
	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();
			
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		Dimension dimensionVaisseau = dimension;
			
		if (!estDansEspaceJeu(x + dimension.longueur - 1, y))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - dimension.hauteur + 1))
			throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension,position,vitesse);
	}
	public void positionnerUnEnvahisseur(Dimension dimension, Position position, int vitesse) {
		int x = position.abscisse();
		int y = position.ordonnee();
			
		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

		Dimension dimensionEnvahisseur = dimension;
			
		if (!estDansEspaceJeu(x + dimension.longueur - 1, y))
			throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - dimension.hauteur + 1))
			throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

		envahisseur = new Envahisseur(dimension,position,vitesse);
	}
	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}
	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);;
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.dimension.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}
	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}
	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}
	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
				marque = Constante.MARQUE_MISSILE;
		else marque = Constante.MARQUE_VIDE;
		return marque;
	}
	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return aUnMissile() && aUnMissileQuiOccupe(x,y);
	}
	public boolean aUnMissileQuiOccupe(int x, int y) {
		return missile.occupeLaPosition(x, y);
	}
	public boolean aUnMissile() {
		return missile!=null;
	}

	boolean droite=true;
	@Override
    public void evoluer(Commande commandeUser) {
		if (commandeUser.gauche) {
           deplacerVaisseauVersLaGauche();
		}
		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}
		if (commandeUser.tir && !this.aUnMissile()) {
			tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR), Constante.MISSILE_VITESSE);
		}
		if(aUnMissile()) {
			missile.deplacerVerticalementVer(Direction.HAUT_ECRAN);
			if(aUnEnvahisseur() && collision.detecterCollision(missile, envahisseur)) {
				detruireEnvahisseur();
				detruireMissile();
			}
			else if(!estDansEspaceJeu(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse())) {
				detruireMissile();
			}
		}
		if(aUnEnvahisseur()) {
			
			if(envahisseur.abscisseLaPlusAGauche() <= 1) {
				droite=true;
			}
			else if(envahisseur.abscisseLaPlusAGauche() + envahisseur.dimension.longueur() >= this.longueur-1) {
				droite=false;
			}
			if(droite) {
				envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			}
			else {
				envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
			}
		}
   }
   @Override
   public boolean etreFini() {
      return false; 
   }
	
	
   //----------------------------------------------------------INITIALISATION DU JEU-----------------------------------------------------------------------
   public void initialiserJeu() {
	    Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
	    Position positionVaisseau = new Position(this.longueur/2 - Constante.VAISSEAU_LONGUEUR/2, this.hauteur-1);
	    positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
	    Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
	    Position positionEnvahisseur = new Position(this.longueur/2 - Constante.ENVAHISSEUR_LONGUEUR/2, Constante.ENVAHISSEUR_HAUTEUR - 1);
	    positionnerUnEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
   }
	
	
	//REFACTOR DEGRE 0
	public boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && aUnVaisseauQuiOccupe(x, y);
	}
	public boolean aUnVaisseauQuiOccupe(int x, int y) {
		return vaisseau.occupeLaPosition(x, y);
	}
	public boolean aUnVaisseau() {
		return vaisseau!=null;
	}
	public boolean aUnEnvahisseur() {
		return envahisseur!=null;
	}
	public Vaisseau getVaisseau() {
		return this.vaisseau;
	}
	public Missile getMissile() {
		return this.missile;
	}
	public void detruireMissile() {
		missile=null;
	}
	public void deplacerMissile() {
		missile.deplacerVerticalementVer(Direction.HAUT_ECRAN);;
	}
	public void detruireEnvahisseur() {
		envahisseur = null;
	}
}	
