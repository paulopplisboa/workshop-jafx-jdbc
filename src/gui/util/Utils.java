package gui.util;

import java.awt.event.ActionEvent;

import javafx.stage.Stage;
import javafx.scene.Node;

public class Utils {
	
	// fun��o que devolve o palco actual
	public static Stage currentStage(javafx.event.ActionEvent event) {
		
		return (Stage)((Node)event.getSource()).getScene().getWindow();
		
	}

}