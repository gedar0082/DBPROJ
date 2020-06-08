package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.postgresql.util.PSQLException;

public class ControllerGood {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane hsimage;

    @FXML
    private Text good_name;

    @FXML
    private Button button_add_cart;

    @FXML
    private Button button_to_cart;

    @FXML
    private ScrollPane char_pane;

    @FXML
    private Button button_back;

    @FXML
    private Text good_price;



    @FXML
    void initialize() throws SQLException {
        DBStarter db = new DBStarter();
        db.dbStarter();
        Statement statement = db.getStatement();
        int goodId = ControllerStore.goodId;
        final FlowPane pane = new FlowPane();
        good_name.setText(ControllerStore.goodName);
        good_price.setText(ControllerStore.goodPrice);
        ResultSet rs = statement.executeQuery("select c.name, char_has_good.valuee from char_has_good inner join char c on char_has_good.char_id = c.id where good_id = " + goodId + ";");
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
        for (String s: arr){
            Text text = new Text(s + "                ");
            pane.getChildren().add(text);
        }
        char_pane.setContent(pane);
        char_pane.setPannable(true);

        button_to_cart.setOnAction(actionEvent -> {
            button_to_cart.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sceen_shopping_cart.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        button_add_cart.setOnAction(actionEvent -> {
            ControllerStore.order.add(goodId);
        });

        button_back.setOnAction(actionEvent -> {
            button_back.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("screen_store.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }

    private String[] rsToStringArr(ResultSet rs) throws SQLException {
        Vector<String> ores = new Vector<>();
        while(rs.next()){
            int i = 1;
            StringBuilder st = new StringBuilder();
            try{
                while(true){
                    if(rs.getString(i).length() > 7){
                        st.append(rs.getString(i)).append("\t\t");
                        i++;
                    }else{
                        st.append(rs.getString(i)).append("\t\t");
                        i++;
                    }

                }
            }catch (ArrayIndexOutOfBoundsException | PSQLException | NullPointerException e ){
                ores.add(st.toString());
            }
        }
        return ores.toArray(new String[0]);
    }
}

