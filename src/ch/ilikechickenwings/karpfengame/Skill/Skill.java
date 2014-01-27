package ch.ilikechickenwings.karpfengame.Skill;

public class Skill {

	private static int nr=5; // number of Skills
	private int coffee;
	private int id; // 0=CarpSkill , 1=EelSkill, 2=GiantCarp, 3=FlyingCarp, 4=Grenade
	
	public Skill(){
		
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
