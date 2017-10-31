package immigration;

import java.awt.Color;

import gui.Rectangle;

public class MultiCell extends Rectangle {

	public MultiCell(int x, int y, int height, int state) {
		
		super(x, y, Color.GRAY, MultiCell.getColorFromState(state), height);
		this.state = state;
		
	}
//	
//	public MultiCell(int x, int y, int height, int state, Color fill) {
//		
//		super(x, y, Color.GRAY, fill, height);
//		this.state = state;
//		
//	}
	
	private int state;

	public int getState() {
		return state;
	}


	public static Color getColorFromState(int nb) {
		
		switch (nb) {
		case 3:
			return Color.WHITE;
		case 6:
			return Color.LIGHT_GRAY;
		case 5: 
			return Color.DARK_GRAY;
		case 4:
			return Color.BLACK;
		case 1:
			return Color.green;
		case 2:
			return Color.red;
		case 7: 
			return Color.ORANGE;
		default:
			return Color.YELLOW;
		}
		
	}
	
}
