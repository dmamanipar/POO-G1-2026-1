package pe.edu.upeu;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApp extends Application {
    private ApplicationContext context;
    private Parent parent;

    @Override
    public void init() throws Exception {
        context= Micronaut.build(getParameters().getRaw().toArray(new String[0]))
                .mainClass(JavaFxApp.class).start();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/maingui.fxml"));
        loader.setControllerFactory(context::getBean);
        parent=loader.load();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene=new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Crud Cliente");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        if (context!=null) context.close();
        super.stop();
    }
}
