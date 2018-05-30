package fr.unilim.iut.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.DessinJeu;

public class DessinSpaceInvaders implements DessinJeu{
	//Attribut
	private SpaceInvaders jeu;
	
	//Constructeur
	public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		this.jeu=spaceInvaders;
	}
	
	//Methodes
	@Override
	public void dessiner(BufferedImage image) {
		if(this.jeu.aUnVaisseau()) {
			Vaisseau vaisseau = jeu.getVaisseau();
			this.dessinerVaisseau(vaisseau, image);
		}
		if(this.jeu.aUnMissile()) {
			Missile missile = jeu.getMissile();
			this.dessinerUnMissile(missile, image);
		}
	}
	private void dessinerVaisseau(Vaisseau vaisseau, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();
		crayon.setColor(Color.green);
		crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.dimension.longueur(), vaisseau.dimension.hauteur());

	   }
	private void dessinerUnMissile(Missile missile, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.dimension.longueur(), missile.dimension.hauteur());
	}

}
