package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuItenSeller;
	@FXML
	private MenuItem menuItenDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItenSellerAction() {
		System.out.println("onMenuItenSellerAction");
	}
	
	@FXML
	public void onMenuItenDepartmentAction() {
		//System.out.println("onMenuItenDepartmentAction");
		loadView("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		//System.out.println("onMenuItemAboutAction");
		loadView("/gui/About.fxml");
	}
	

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	

	// synchronized garante que executa todo o código durante o multitred  
	private synchronized void loadView (String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // carrega a tela
			VBox newVbox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			// Aceder ao VBox
			VBox mainVBox =(VBox)((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "error loading ", e.getMessage(), AlertType.ERROR);
		}
	}


}
