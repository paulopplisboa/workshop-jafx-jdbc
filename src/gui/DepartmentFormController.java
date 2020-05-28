package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

// classe para controlar a parte gráfica do departamento 
public class DepartmentFormController implements Initializable {
	
	// entidade relacionada com este formulário
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
	
	// método set da entidade Departamento 
	public void setDepartment(Department entity) {
		this.entity=entity;
	}
	
	@FXML
	public void onBtSaveAction() {
		System.out.println("onBtSaveAction");
	}
	
	@FXML
	public void onBtCancelAction() {
		System.out.println("onBtCancelAction");
	}
	
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
				
	}
	
	private void initializeNodes() {
		// o txtid apenas pode ter numeros
		Constraints.setTextFieldInteger(txtId);
		//O textName apenas pode ter no máximo 30 caracteres
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



