package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

// classe para controlar a parte gr�fica do departamento 
public class DepartmentFormController implements Initializable {
	
	private DepartmentService service;
	
	// entidade relacionada com este formul�rio
	private Department entity;
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML 
	private Label labelErroName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancele;
	
	// m�todo set da entidade Departamento 
	public void setDepartment(Department entity) {
		this.entity=entity;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service=service;
	}
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity==null) {
			throw new IllegalStateException("entity was null");	
			}
		if (service==null) {
			throw new IllegalStateException("service was null");	
			}
		try {
		//System.out.println("onBtSaveAction");
		entity = getFormData();
		service.saveOrUpdate(entity);
		// pega a referencia da janela actual e em seguida fecha a janela
		Utils.currentStage(event).close();
		}
		catch (DbException e) {
			Alerts.showAlert("Error saving obj", null, e.getMessage(), AlertType.ERROR);
		}
		
		
	}
	
	private Department getFormData() {
		Department obj = new Department();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());
		
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
		System.out.println("onBtCancelAction");
	}
	
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
				
	}
	
	private void initializeNodes() {
		// o txtid apenas pode ter numeros
		Constraints.setTextFieldInteger(txtId);
		//O textName apenas pode ter no m�ximo 30 caracteres
		Constraints.setTextFieldMaxLength(txtName, 30);
		
	}
	
	//metodo para actualizar os dados do departamento
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());		
	}

}



