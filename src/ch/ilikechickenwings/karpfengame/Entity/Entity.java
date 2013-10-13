package ch.ilikechickenwings.karpfengame.Entity;

public class Entity {
	
	private int x_Point;
	private int y_Point;
	private int lifes;
	private int width;
	private int height;
	private int velocity;
	private int damage;
	
	/*
	public Entity(int x, int y, int lifes, int width, int height){
		this.setX_Point(x);
		this.setY_Point(y);
		this.setLifes(lifes);
		this.setWidth(width);
		this.setHeight(height);
		
	}*/

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getX_Point() {
		return x_Point;
	}

	public void setX_Point(int x_Point) {
		this.x_Point = x_Point;
	}

	public int getY_Point() {
		return y_Point;
	}

	public void setY_Point(int y_Point) {
		this.y_Point = y_Point;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
