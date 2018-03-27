package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerMain implements Initializable {

   @FXML
   private Button btnSingleChooseTmp;//单个文件导入模板选择按钮

   @FXML
   private TextField tfdSingleTmp;//单个文件导入模板路径框
   
   @FXML
   private Button btnMultiChooseTmp;//文件目录导入模板选择按钮

   @FXML
   private TextField tfdMultiTmp;//文件目录导入模板路径框
   
   @FXML
   private MenuBar menuBar;//菜单栏
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
	   Label menuLabel = new Label("构建导入模板");
	   menuLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
	       @Override
	       public void handle(MouseEvent event) {
	    	   toBuildTmp();
	       }
	   });
	   Menu btnBuildTmp = new Menu();
	   btnBuildTmp.setGraphic(menuLabel);
	   menuBar.getMenus().add(btnBuildTmp);

	   //文件名称批量修改
	   Label menuLabel2 = new Label("批量改文件名称");
	   menuLabel2.setOnMouseClicked(new EventHandler<MouseEvent>() {
	       @Override
	       public void handle(MouseEvent event) {
	    	   toChgFileName();
	       }
	   });
	   Menu btnChgFileNameTmp = new Menu();
	   btnChgFileNameTmp.setGraphic(menuLabel2);
	   menuBar.getMenus().add(btnChgFileNameTmp);
	   
	   //关于
	   Label menuLabel3 = new Label("关于");
	   menuLabel3.setOnMouseClicked(new EventHandler<MouseEvent>() {
	       @Override
	       public void handle(MouseEvent event) {
	    	   toAbout();
	       }
	   });
	   Menu btnAboutTmp = new Menu();
	   btnAboutTmp.setGraphic(menuLabel3);
	   menuBar.getMenus().add(btnAboutTmp);
    }
 
   /**
    * 弹出单个文件导入模板选择框
    */
   public void showSingleTemplateChooseDialog(){
       FileChooser fileChooser = new FileChooser();
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
       fileChooser.getExtensionFilters().add(extFilter);
       File file = fileChooser.showOpenDialog(btnSingleChooseTmp.getContextMenu());
       if (null != file) {
    	   tfdSingleTmp.setText(file.getAbsolutePath());
       }
   }

   
   /**
    * 弹出多个文件导入模板选择框
    */
   public void showMultiTemplateChooseDialog(){
       FileChooser fileChooser = new FileChooser();
       FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
       fileChooser.getExtensionFilters().add(extFilter);
       File file = fileChooser.showOpenDialog(btnMultiChooseTmp.getContextMenu());
       if (null != file) {
    	   tfdMultiTmp.setText(file.getAbsolutePath());
       }
   }
   
   /**
    * 弹出构建导入模板页面
    */
   private void toBuildTmp(){
		try {
 			Stage window = new Stage();
			window.setTitle("构建导入模板");
			AnchorPane root = FXMLLoader.load(getClass()
			        .getResource("/ui/buildTmp.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
			//使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
			window.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
   
   /**
    * 弹出修改文件名称页面
    */
   private void toChgFileName(){
		try {
			Stage window = new Stage();
			window.setTitle("批量修改文件名称");
			AnchorPane root = FXMLLoader.load(getClass()
			        .getResource("/ui/chgFileName.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
			//使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
			window.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
   }
   
   /**
    * 关于页面
    */
   public void toAbout(){
	   System.out.println("关于页面");
   }
	 
}