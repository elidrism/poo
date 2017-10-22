package conway;



import java.awt.Color;

import gui.GUISimulator;

public class TestConway {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CellsGenerator cG = new CellsGenerator(4, 4);
		Cell[][] cells = cG.getCellsGenerated();
		
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		
		
		
		
		gui.setSimulable(new ConwaySimulator(gui, cells));
		
		
		
	}
	
}
