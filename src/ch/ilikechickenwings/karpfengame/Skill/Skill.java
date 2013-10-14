package ch.ilikechickenwings.karpfengame.Skill;

public class Skill {

	public static int nr=5; // number of Skills
	private int coffee;
	private int id; // 0=CarpSkill
	
	public Skill(){
		setCoffee(10);
	}

	public int getCoffee() {
		return coffee;
	}

	public void setCoffee(int coffee) {
		this.coffee = coffee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
