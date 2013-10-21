package ch.ilikechickenwings.karpfengame.Skill;

public class Skill {

	private static int nr=2; // number of Skills
	private int coffee;
	private int id; // 0=CarpSkill , 1=EelSkill
	
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

	public static int getNr() {
		return nr;
	}
	
	
	
}
