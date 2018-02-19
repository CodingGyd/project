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
	                    .getResource("/application/MyScene.fxml"));
	            
//	            Button buttonLoad = new Button("Load");
//	            buttonLoad.setOnAction(new EventHandler<ActionEvent>(){
//	                 @Override
//	                public void handle(ActionEvent arg0) {
//	                    FileChooser fileChooser = new FileChooser();
//	                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//	                    fileChooser.getExtensionFilters().add(extFilter);
//	                    File file = fileChooser.showOpenDialog(primaryStage);
//	                    System.out.println(file);
//	                }
//	            });
//	            root.getChildren().add(buttonLoad);
	          
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
