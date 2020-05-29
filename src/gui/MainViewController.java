package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItenSeller;
	@FXML
	private MenuItem menuItenDepartment;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItenSellerAction() {
		// System.out.println("onMenuItenSellerAction");
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItenDepartmentAction() {
		// System.out.println("onMenuItenDepartmentAction");
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmenteService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAboutAction() {
		// System.out.println("onMenuItemAboutAction");
		loadView("/gui/About.fxml", x -> {
		});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	// synchronized garante que executa todo o código durante o multitred
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // carrega a tela
			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();
			// Aceder ao VBox
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());

			// executam a função passada por argumento
			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "error loading ", e.getMessage(), AlertType.ERROR);
		}
	}

	/*
	 * private synchronized void loadView2 (String absoluteName) { try { FXMLLoader
	 * loader = new FXMLLoader(getClass().getResource(absoluteName)); // carrega a
	 * tela VBox newVbox = loader.load();
	 * 
	 * Scene mainScene = Main.getMainScene(); // Aceder ao VBox VBox mainVBox
	 * =(VBox)((ScrollPane) mainScene.getRoot()).getContent();
	 * 
	 * Node mainMenu = mainVBox.getChildren().get(0);
	 * mainVBox.getChildren().clear(); mainVBox.getChildren().add(mainMenu);
	 * mainVBox.getChildren().addAll(newVbox.getChildren());
	 * 
	 * DepartmentListController controller = loader.getController();
	 * controller.setDepartmenteService(new DepartmentService());
	 * controller.updateTableView();
	 * 
	 * } catch (IOException e) { Alerts.showAlert("IO Exception", "error loading ",
	 * e.getMessage(), AlertType.ERROR); } }
	 */

}
