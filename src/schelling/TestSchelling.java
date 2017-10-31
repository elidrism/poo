package schelling;

import java.awt.Color;

import gui.GUISimulator;
import immigration.MultiCell;
import immigration.MultiCellGenerator;

public class TestSchelling {

	public static void main(String[] args) {
		

		MultiCellGenerator mcg = new MultiCellGenerator(175, 85, 3);
		
		MultiCell[][] cells = mcg.getCellsGenerated();
		
//		MultiCell red = new MultiCell(1, 1, 1, 2);
//		MultiCell green = new MultiCell(1,1,1,1);
//		MultiCell white = new MultiCell(1,1,1,3);
//		
//		MultiCell[][] cells = new MultiCell[][] {
//			{red, red, green, white},
//			{white, green, red, green},
//			{red, green, white, red},
//			{red, white, green, white}
//		};
		
		
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		gui.setSimulable(new SchellingSimulator(gui, cells, 3, 2));

		
		
		
	}

}
