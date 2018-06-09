package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.*;

import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.Collision;
import fr.unilim.iut.spaceinvaders.Dimension;
import fr.unilim.iut.spaceinvaders.Envahisseur;
import fr.unilim.iut.spaceinvaders.Missile;
import fr.unilim.iut.spaceinvaders.Position;

@SuppressWarnings("unused")
public class CollisionTest {
	private Collision collision = new Collision();
	
	@Test
	public void collisionParLaDroite(){
		Envahisseur envahisseur = new Envahisseur(new Dimension(3,2), new Position(3,2), 0);
		Missile missile = new Missile(new Dimension(1,2), new Position(4,2), 1);
		assertTrue(collision.detecterCollision(envahisseur,missile));
	}
	
	@Test
	public void collisionParLaGauche(){
		Envahisseur envahisseur = new Envahisseur(new Dimension(3,2), new Position(3,2), 1);
		Missile missile = new Missile(new Dimension(1,2), new Position(4,2), 1);
		assertTrue(collision.detecterCollision(missile,envahisseur));
	}
	
	@Test
	public void collisionParLeBas_DeLEnvahisseur(){
		Envahisseur envahisseur = new Envahisseur(new Dimension(2,2), new Position(1,3), 1);
		Missile missile = new Missile(new Dimension(1,2), new Position(1,4), 1);
		assertTrue(collision.detecterCollision(envahisseur, missile));
	}
	
	@Test
	public void collisionParLeHaut_DeLEnvahisseur(){
		Envahisseur envahisseur = new Envahisseur(new Dimension(2,2), new Position(1,3), 1);
		Missile missile = new Missile(new Dimension(1,2), new Position(1,2), 1);
		assertTrue(collision.detecterCollision(envahisseur, missile));
	}
}