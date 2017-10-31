package immigration;

import java.awt.Color;

import gui.GUISimulator;

public class TestImmigration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MultiCellGenerator mcg = new MultiCellGenerator(10, 10, 4);
		MultiCell[][] cells = mcg.getCellsGenerated();
		
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		gui.setSimulable(new ImmigrationSimulator(gui, cells));
		
	}

}
