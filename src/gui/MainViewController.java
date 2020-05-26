package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("onMenuItenDepartmentAction");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		System.out.println("onMenuItemAboutAction");
	}
	

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}


}
