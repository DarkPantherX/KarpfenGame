package ch.ilikechickenwings.karpfengame.Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.ilikechickenwings.karpfengame.KarpfenGame;

public class ButtonHandler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(KarpfenGame.textArea2.getText());
		//KarpfenGame.textArea1.setText(KarpfenGame.textArea1.getText()+"<"+dateFormat.format(date)+">"+Karbaos.toString());
		KarpfenGame.textArea2.setText("");
		
	}

}
