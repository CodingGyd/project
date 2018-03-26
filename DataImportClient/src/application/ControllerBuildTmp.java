package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerBuildTmp implements Initializable{

	   String[] downloadableExtensions = {".doc", ".xls", ".zip", ".exe", ".rar", ".pdf", ".jar", ".png", ".jpg", ".gif"};

	@FXML
	private WebView buildTmpWv;//目标文件夹选择按钮
	@Override
	public void initialize(URL location, ResourceBundle resources) {
 		
		final WebEngine webEngine = buildTmpWv.getEngine();
		webEngine.load("http://rj.baidu.com/index.html?fxq");
		webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            	
	                for(String downloadAble : downloadableExtensions) {
                    if ( newValue.endsWith(downloadAble)) {
                    	 try {
//	                             if(!file.exists()) {
//                                 file.mkdir();
//                             }
                            
	                             File fileTmp = new File(newValue);
	                     	     FileChooser fileChooser = new FileChooser();
    	            	     fileChooser.setInitialFileName(fileTmp.getName());
                             File download = fileChooser.showSaveDialog(null);
//                             File download = new File(file + "/" + fileTmp.getName());
                             if (null == download) {
                            	 break;
                             }
                             if(download.exists()) {
	                                 showDialog("提示","下载的文件已存在");
                                 break;
                             }
	                             FileUtils.copyURLToFile(new URL(webEngine.getLocation()), download);
	                             showDialog("提示","下载完成,保存路径: " + download.getAbsolutePath());
	                             break;
                    	 } catch(Exception e) {
                             e.printStackTrace();
                         }
                    }
                }
            }
        });
		
	}
	 
	   public Stage showDialog(String title,String message) throws IOException{
		   Stage window = new Stage();
			window.setTitle(title);
			AnchorPane root = new AnchorPane();
			Label label = new Label();
			label.setText(message);
			root.getChildren().add(label);
			Scene scene = new Scene(root);
			window.setScene(scene);
			//使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
			window.showAndWait();
			return window;
	   }
   
	 
}