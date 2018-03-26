package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	 @Override
	    public void start(Stage primaryStage) {
	        try {
	            // Read file fxml and draw interface.
	            AnchorPane root = FXMLLoader.load(getClass()
	                    .getResource("/ui/main.fxml"));
	            
	            Scene scene = new Scene(root);
	          
	            primaryStage.setTitle("顺顺郭数据导入工具");
	            primaryStage.setScene(scene);
	            primaryStage.setResizable(false);
	            primaryStage.show();

	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }  

	    public static void main(String[] args) {
	        launch(args);
	    }
}
