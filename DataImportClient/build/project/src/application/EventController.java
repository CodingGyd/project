package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class EventController implements Initializable {

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
   public void toBuildTmp(){
	   System.out.println("构建导入模板页面");
   }
   
   public void toAbout(){
	   System.out.println("关于页面");
   }
  
}