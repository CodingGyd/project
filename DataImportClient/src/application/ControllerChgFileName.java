package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import filetools.BatchChgFNameTool;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * 
 * 类名:  ControllerChgFileName.java
 * 包名:  application
 * 描述:  批量修改文件名称
 * 
 * 作者:  guoyd
 * 日期:  2018年3月27日
 *
 * Copyright @ 2017 Corpration Name
 */
public class ControllerChgFileName implements Initializable{

	@FXML
	private TextField txtResourceDir;//资源文件夹
	@FXML
	private TextField txtTargetDir;//目标文件夹
	@FXML
	private TextField txtOriginContent;//文件名称中待替换的内容
	@FXML
	private TextField txtTargetContent;//替换的目标内容
	@FXML
	private Button btnResourceDir;//资源文件夹选择按钮
	@FXML
	private Button btnTargetDir;//目标文件夹选择按钮
	@FXML
	private Button btnChgFileName;//修改名称
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnResourceDir.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				    // 批量修改文件名称-弹出资源文件夹选择框
					   Stage fileStage = null;
 				       DirectoryChooser folderChooser = new DirectoryChooser();
 				       File file = folderChooser.showDialog(fileStage);
				       if (null != file) {
				    	   txtResourceDir.setText(file.getAbsolutePath());
				       }
			}
		});

		btnTargetDir.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
			    // 批量修改文件名称-弹出资源文件夹选择框
				   Stage fileStage = null;
				   DirectoryChooser folderChooser = new DirectoryChooser();
				   File file = folderChooser.showDialog(fileStage);
			       if (null != file) {
			    	   txtTargetDir.setText(file.getAbsolutePath());
			       }
			}
		});
		
		btnChgFileName.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
					
					String oldFileDir = txtResourceDir.getText();
					String newFileDir = txtTargetDir.getText();
					
					String replaceContent = txtOriginContent.getText();
					String targetContent = txtTargetContent.getText();
					
					try {
						BatchChgFNameTool.chgFileName(replaceContent, targetContent, oldFileDir, newFileDir);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
	}
   
	 
}