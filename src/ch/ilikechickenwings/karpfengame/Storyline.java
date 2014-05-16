package ch.ilikechickenwings.karpfengame;

import java.awt.Graphics2D;
import java.util.ArrayList;

import ch.ilikechickenwings.karpfengame.Handler.InputHandler;
import ch.ilikechickenwings.karpfengame.Reader.SceneReader;

public class Storyline implements Story{

	private Scene scene;
	private KarpfenGame karpfenGame;
	private InputHandler inHandler;
	private int scenesCounter=0;
	public static ArrayList<Scene> scenePics = new ArrayList<Scene>();
	public static String nextLevel;
	public static String thisLevel;
	
	public Storyline(String netLevel, KarpfenGame kgame){
		karpfenGame= kgame;
		thisLevel= netLevel;
		new SceneReader(netLevel);
		scene=scenePics.get(scenesCounter);
		
	}
	
	@Override
	public void update(KarpfenGame karpfenGame, InputHandler inHandler) {
		
		scene.update(this, inHandler);
		
	}
	
	public void alertNext() {
		scenesCounter++;
		if(scenesCounter<scenePics.size()){
			scene=scenePics.get(scenesCounter);
		}else{
			if(nextLevel.startsWith("b")){
				getKarpfenGame().setLvl(new BossLevel(nextLevel, getKarpfenGame()));
				}else if(nextLevel.startsWith("s")){
				getKarpfenGame().setStory(new Storyline(nextLevel, getKarpfenGame()));	
				}else{
				getKarpfenGame().setLvl(new Level(nextLevel, karpfenGame));
				}
			scenePics.clear();
			karpfenGame.setStory(null);
			
		}
	}

	@Override
	public void draw(Graphics2D g) {
		scene.draw(g);
		
	}

	/**
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * @param scene the scene to set
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	/**
	 * @return the karpfenGame
	 */
	public KarpfenGame getKarpfenGame() {
		return karpfenGame;
	}

	/**
	 * @param karpfenGame the karpfenGame to set
	 */
	public void setKarpfenGame(KarpfenGame karpfenGame) {
		this.karpfenGame = karpfenGame;
	}

	/**
	 * @return the inHandler
	 */
	public InputHandler getInHandler() {
		return inHandler;
	}

	/**
	 * @param inHandler the inHandler to set
	 */
	public void setInHandler(InputHandler inHandler) {
		this.inHandler = inHandler;
	}



}
