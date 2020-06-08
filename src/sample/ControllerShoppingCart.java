package sample;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.postgresql.util.PSQLException;
import javafx.scene.text.Text;

public class ControllerShoppingCart {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane hsimage;

    @FXML
    private Button button_to_cart;

    @FXML
    private ScrollPane char_pane;

    @FXML
    private Button button_back;

    @FXML
    private Text sum_price;

    @FXML
    void initialize() throws SQLException {
        DBStarter db = new DBStarter();
        db.dbStarter();
        Statement statement = db.getStatement();
        textSetter(statement);

        button_to_cart.setOnAction(actionEvent -> {
            ResultSet rs1 = null;
            try {
                rs1 = statement.executeQuery("select * from public.order where id = (select max(id) from public.order)");
                String st1 = rsToStringArr(rs1)[0];
                int a = Integer.parseInt(st1.replaceAll("\t", " ").trim().split(" ")[0]);
                double ssa = Double.parseDouble(sum_price.getText());

                statement.execute("insert into public.order (worker_id, client_id, sum_price, date_start, status_id) values (1, 1, " + ssa + ", '2020-06-04', 1);");
                for(int i : ControllerStore.order){
                    ResultSet rs2 = statement.executeQuery("select good.price from good where good.id = " + i + ";");
                    double price = Double.parseDouble(rsToStringArr(rs2)[0]);
                    statement.execute("insert into order_has_good (good_id, number, order_id, provider_id, price) values (" + i + ", 1, " + (a+1) + ", 1, " + price + ");");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });





    }

    private void textSetter(Statement statement) throws SQLException {
        final FlowPane pane = new FlowPane();
        double current_sum = 0.0;
        //ArrayList<Integer> ord = ControllerStore.order;
        for (Integer i : ControllerStore.order){
            ResultSet rs = statement.executeQuery("select good.name, good.price from good where good.id = " + i + ";");
            ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
            double sum = sumCounter(arr.get(0));
            current_sum += sum;
            Button button = new Button();
            button.setText("удалить");
            button.setOnAction(actionEvent -> {
                ControllerStore.order.remove(i);
                try {
                    textSetter(statement);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            sum_price.setText(String.valueOf(current_sum));
            Text text = new Text(arr.get(0));
            pane.getChildren().add(text);
            pane.getChildren().add(button);
            char_pane.setContent(pane);
            char_pane.setPannable(true);
        }
    }

    private double sumCounter(String  arr){
        arr = arr.replaceAll("\t", " ");
        arr = arr.trim();
        String[] st = arr.split(" ");
        return Double.parseDouble(st[3]);
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
