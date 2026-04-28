package pe.edu.upeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import pe.edu.upeu.conn.SQLiteConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class App extends Application {
    public static void main( String[] args ) {
        /*Connection con=
                SQLiteConnection.getInstance()
                        .getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cliente");
            while (rs.next()){
                System.out.println(rs.getString("nombre"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/view/maingui.fxml"));
        Screen screen=Screen.getPrimary();
        Rectangle2D bouds=screen.getVisualBounds();

        Scene scene=new Scene(loader.load(), bouds.getWidth(), bouds.getHeight()-100);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.show();
    }
}
