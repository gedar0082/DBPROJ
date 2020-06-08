package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.postgresql.util.PSQLException;

public class ControllerStore {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button id_filter;

    @FXML
    private URL location;

    @FXML
    private CheckBox check_sasu;

    @FXML
    private CheckBox check_phanthom;

    @FXML
    private CheckBox check_grenje;

    @FXML
    private CheckBox check_snsoog;

    @FXML
    private CheckBox check_mapple;

    @FXML
    private CheckBox check_bruder;

    @FXML
    private CheckBox check_ph;

    @FXML
    private CheckBox check_hoywey;

    @FXML
    private CheckBox check_ksiaomi;

    @FXML
    private CheckBox check_elji;

    @FXML
    private TextField price_from;

    @FXML
    private TextField price_to;

    @FXML
    private CheckBox id_from_storage;

    @FXML
    private CheckBox id_from_provider;

    @FXML
    private TextField weight_from;

    @FXML
    private TextField weight_to;

    @FXML
    private TextField size_from;

    @FXML
    private TextField size_to;

    @FXML
    private TextField mem_from;

    @FXML
    private TextField mem_to;

    @FXML
    private TextField bat_from;

    @FXML
    private TextField bat_to;

    @FXML
    private TextField pix_from;

    @FXML
    private TextField pix_to;

    @FXML
    private TextField frame_from;

    @FXML
    private TextField frame_to;

    @FXML
    private TextField sys_from;

    @FXML
    private TextField sys_to;

    @FXML
    private TextField volt_from;

    @FXML
    private TextField volt_to;

    @FXML
    private TextField mhz_from;

    @FXML
    private TextField mhz_to;

    @FXML
    private TextField tf_from;

    @FXML
    private TextField tf_to;

    @FXML
    private TextField slots_from;

    @FXML
    private TextField slots_to;

    @FXML
    private TextField ram_from;

    @FXML
    private TextField ram_to;

    @FXML
    private MenuBar id_all_cat;

    @FXML
    private Menu id_categ;

    @FXML
    private Menu id_1;

    @FXML
    private Menu id_11;

    @FXML
    private MenuItem id_111;

    @FXML
    private MenuItem id_112;

    @FXML
    private MenuItem id_113;

    @FXML
    private MenuItem id_114;

    @FXML
    private Menu id_12;

    @FXML
    private MenuItem id_121;

    @FXML
    private MenuItem id_122;

    @FXML
    private Menu id_13;

    @FXML
    private MenuItem id_131;

    @FXML
    private MenuItem id_132;

    @FXML
    private MenuItem id_133;

    @FXML
    private Menu id_2;

    @FXML
    private Menu id_21;

    @FXML
    private MenuItem id_211;

    @FXML
    private MenuItem id_212;

    @FXML
    private MenuItem id_213;

    @FXML
    private MenuItem id_214;

    @FXML
    private Menu id_22;

    @FXML
    private MenuItem id_221;

    @FXML
    private MenuItem id_222;

    @FXML
    private MenuItem id_223;

    @FXML
    private MenuItem id_224;

    @FXML
    private Menu id_23;

    @FXML
    private MenuItem id_231;

    @FXML
    private MenuItem id_232;

    @FXML
    private MenuItem id_233;

    @FXML
    private MenuItem id_234;

    @FXML
    private Button id_cart;

    @FXML
    private TextField id_search;

    @FXML
    private ScrollPane id_right_scroll;
    String cat = "";
    static String goodName = "";
    static String goodPrice = "";
    static int goodId = 0;
    int size = 0;
    public static ArrayList<Integer> order = new ArrayList<>();

    @FXML
    void initialize() throws SQLException {

        DBStarter db = new DBStarter();
        db.dbStarter();
        Statement statement = db.getStatement();
        final FlowPane container = new FlowPane();
        id_cart.setOnAction(event -> {
            id_cart.getScene().getWindow().hide();
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
        id_search.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)){
                if (!id_search.getText().equals("")){

                    try {
                        final FlowPane newContainer = new FlowPane();
                        ResultSet rs = statement.executeQuery("select good.id, good.name, good.price from good where good.name like '%" + id_search.getText() + "%' ;");
                        ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                        for (String s : arr) {
                            Text text = new Text(s);
                            Button button = new Button();
                            button.setText("в корзину");

                            button.setOnAction(event ->{
                                order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                                System.out.println(order);
                            });

                            text.setOnMouseClicked(event->{
                                goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                                goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                                goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                                text.getScene().getWindow().hide();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("screen_good.fxml"));
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

                            newContainer.getChildren().add(text);
                            newContainer.getChildren().add(button);
                        }
                        id_right_scroll.setContent(newContainer);
                        id_right_scroll.setPannable(true);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        id_111.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 111;");
                cat = " category_id = 111 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_112.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 112;");
                cat = " category_id = 112 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(evenntt-> {
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                    });
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_113.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 113;");
                cat = " category_id = 113 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(evenntt-> {
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                    });
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_114.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 114;");
                cat = " category_id = 114 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(evenntt-> {
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                    });
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_121.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 121;");
                cat = " category_id = 121 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(evenntt-> {
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                    });
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_122.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 122;");
                cat = " category_id = 122 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_131.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 131;");
                cat = " category_id = 131 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_132.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 132;");
                cat = " category_id = 132 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_133.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 133;");
                cat = " category_id = 133 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_211.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 211;");
                cat = " category_id = 211 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_212.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 212;");
                cat = " category_id = 212 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_213.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 213;");
                cat = " category_id = 213 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_214.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 214;");
                cat = " category_id = 214 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_221.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 221;");
                cat = " category_id = 221 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_222.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 222;");
                cat = " category_id = 222 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_223.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 223;");
                cat = " category_id = 223 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_224.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 224;");
                cat = " category_id = 224 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_231.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 231;");
                cat = " category_id = 231 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_232.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 232;");
                cat = " category_id = 232 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_233.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 233;");
                cat = " category_id = 233 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        id_234.setOnAction(event1 -> {
            try {
                final FlowPane newContainer = new FlowPane();
                ResultSet rs = statement.executeQuery("select good.id, good.name, good.price " +
                        "from good " +
                        "inner join good_has_category ghc " +
                        "on good.id = ghc.good_id where category_id = 234;");
                cat = " category_id = 234 and ";
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));
                for (String s : arr) {
                    Text text = new Text(s);
                    Button button = new Button();
                    button.setText("в корзину");
                    button.setOnAction(event ->{
                        order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });

                    text.setOnMouseClicked(event->{
                        goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        id_filter.setOnAction(actionEvent -> {
            String filteredBrands = filtersBrand();
            String filteredProvider = filterProvider();
            String filteredPrice = filterPrice();
            String filteredChar = filterChar();
            final FlowPane newContainer = new FlowPane();
            try {
                System.out.println("clicked");
                ResultSet rsc = statement.executeQuery(
                        "select good.id, good.name, good.price " +
                                "from good " +
                                "inner join char_has_good chg on good.id = chg.good_id " +
                                "inner join good_has_category ghc on good.id = ghc.good_id" +
                                filteredProvider +
                                " where " + cat + filteredChar + " and " + filteredPrice + " and " + filteredBrands +  " group by good.id, good.name, good.price having count(*) > 1 order by good.id;");
                System.out.println();
                ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rsc)));
                System.out.println(arr);
                for(int i = 0; i < arr.size(); i++){
                    Text text = new Text(arr.get(i));
                    Button button = new Button();
                    button.setText("в корзину");
                    int finalI = i;
                    button.setOnMouseClicked(mouseEvent -> {
                        order.add(Integer.parseInt(arr.get(finalI).replaceAll("\t", " ").trim().split(" ")[0]));
                        System.out.println(order);
                    });
                    text.setOnMouseClicked(event -> {

                        goodId = Integer.parseInt(arr.get(finalI).replaceAll("\t", " ").trim().split(" ")[0]);
                        goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                        goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                        text.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("screen_good.fxml"));
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
                    newContainer.getChildren().add(text);
                    newContainer.getChildren().add(button);
                }
                id_right_scroll.setContent(newContainer);
                id_right_scroll.setPannable(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
        ResultSet rs = statement.executeQuery("select * from good");
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(rsToStringArr(rs)));

        for (String s : arr) {
            Text text = new Text(s);
            Button button = new Button();
            button.setText("в корзину");
            button.setOnAction(event ->{
                order.add(Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]));
                System.out.println(order);
            });

            text.setOnMouseClicked(event->{
                goodId = Integer.parseInt(s.replaceAll("\t", " ").trim().split(" ")[0]);
                goodName = text.getText().replaceAll("\t", " ").trim().split(" ")[1];
                goodPrice = text.getText().replaceAll("\t", " ").trim().split(" ")[2];
                text.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("screen_good.fxml"));
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
            container.getChildren().add(text);
            container.getChildren().add(button);
        }
        id_right_scroll.setContent(container);
        id_right_scroll.setPannable(true);

    }

    private String filtersBrand(){
        ArrayList<String> arr = new ArrayList<>();
        if(check_sasu.isSelected()) arr.add(FilterConfigs.SASU);
        if(check_snsoog.isSelected()) arr.add(FilterConfigs.Snsoog);
        if(check_grenje.isSelected()) arr.add(FilterConfigs.Grenje);
        if(check_phanthom.isSelected()) arr.add(FilterConfigs.Phanthom);
        if(check_mapple.isSelected()) arr.add(FilterConfigs.Mapple);
        if(check_bruder.isSelected()) arr.add(FilterConfigs.Bruder);
        if(check_ph.isSelected()) arr.add(FilterConfigs.PH);
        if(check_hoywey.isSelected()) arr.add(FilterConfigs.Hoywey);
        if(check_ksiaomi.isSelected()) arr.add(FilterConfigs.Ksiaomi);
        if(check_elji.isSelected()) arr.add(FilterConfigs.ELJI);

        if (arr.size() == 0) return " (good.name like 'SASU%'" +
                " or good.name like 'Snsoog%'" +
                " or good.name like 'Grenje%'" +
                " or good.name like 'Phanthom%'" +
                " or good.name like 'Mapple%'" +
                " or good.name like 'Bruder%'" +
                " or good.name like 'PH%'" +
                " or good.name like 'Hoywey%'" +
                " or good.name like 'Ksiaomi%'" +
                " or good.name like 'ELJI%')";
        String res = "(good.name like '" + arr.get(0) + "%'";
        for(String st : arr){
            res = res + " or good.name like '" + st + "%'";
        }
        res = res + ")";
        return res;
    }

    private String filterChar(){
        String res = "";
        int weightFrom;
        int weightTo;
        int sizeFrom;
        int sizeTo ;
        int memFrom;
        int memTo;
        int batFrom;
        int batTo;
        int pixFrom;
        int pixTo;
        int frameFrom;
        int frameTo;
        int sysFrom;
        int sysTo;
        int voltFrom;
        int voltTo;
        int mhzFrom;
        int mhzTo;
        int tfFrom;
        int tfTo;
        int slotsFrom;
        int slotsTo;
        int ramFrom;
        int ramTo;
        
        if(weight_from.getText().equals("")) weightFrom = 0;
        else weightFrom = Integer.parseInt (weight_from.getText());
        if(weight_to.getText().equals("")) weightTo = 99999;
        else weightTo = Integer.parseInt (weight_to.getText());

        if(size_from.getText().equals("")) sizeFrom = 0;
        else sizeFrom = Integer.parseInt (size_from.getText());
        if(size_to.getText().equals("")) sizeTo = 99999;
        else sizeTo = Integer.parseInt (size_to.getText());

        if(mem_from.getText().equals("")) memFrom = 0;
        else memFrom = Integer.parseInt (mem_from.getText());
        if(mem_to.getText().equals("")) memTo = 99999;
        else memTo = Integer.parseInt (mem_to.getText());

        if(bat_from.getText().equals("")) batFrom = 0;
        else batFrom = Integer.parseInt (bat_from.getText());
        if(bat_to.getText().equals("")) batTo = 99999;
        else batTo = Integer.parseInt (bat_to.getText());

        if(pix_from.getText().equals("")) pixFrom = 0;
        else pixFrom = Integer.parseInt (pix_from.getText());
        if(pix_to.getText().equals("")) pixTo = 99999;
        else pixTo = Integer.parseInt (pix_to.getText());

        if(frame_from.getText().equals("")) frameFrom = 0;
        else frameFrom = Integer.parseInt (frame_from.getText());
        if(frame_to.getText().equals("")) frameTo = 99999;
        else frameTo = Integer.parseInt (frame_to.getText());

        if(sys_from.getText().equals("")) sysFrom = 0;
        else sysFrom = Integer.parseInt (sys_from.getText());
        if(sys_to.getText().equals("")) sysTo = 99999;
        else sysTo = Integer.parseInt (sys_to.getText());

        if(volt_from.getText().equals("")) voltFrom = 0;
        else voltFrom = Integer.parseInt (volt_from.getText());
        if(volt_to.getText().equals("")) voltTo = 99999;
        else voltTo = Integer.parseInt (volt_to.getText());

        if(mhz_from.getText().equals("")) mhzFrom = 0;
        else mhzFrom = Integer.parseInt (mhz_from.getText());
        if(mhz_to.getText().equals("")) mhzTo = 99999;
        else mhzTo = Integer.parseInt (mhz_to.getText());

        if(tf_from.getText().equals("")) tfFrom = 0;
        else tfFrom = Integer.parseInt (tf_from.getText());
        if(tf_to.getText().equals("")) tfTo = 99999;
        else tfTo = Integer.parseInt (tf_to.getText());

        if(slots_from.getText().equals("")) slotsFrom = 0;
        else slotsFrom = Integer.parseInt (slots_from.getText());
        if(slots_to.getText().equals("")) slotsTo = 99999;
        else slotsTo = Integer.parseInt (slots_to.getText());

        if(ram_from.getText().equals("")) ramFrom = 0;
        else ramFrom = Integer.parseInt (ram_from.getText());
        if(ram_to.getText().equals("")) ramTo = 99999;
        else ramTo = Integer.parseInt (ram_to.getText());






        res = res + " ((chg.char_id = 1  and chg.valuee between " + weightFrom + " and " + weightTo + ") or " +
                    "(chg.char_id = 2  and chg.valuee between " + sizeFrom + " and " + sizeTo + ") or " +
                    "(chg.char_id = 3  and chg.valuee between " + memFrom + " and " + memTo + ") or " +
                    "(chg.char_id = 4  and chg.valuee between " + batFrom + " and " + batTo + ") or " +
                    "(chg.char_id = 5  and chg.valuee between " + pixFrom + " and " + pixTo + ") or " +
                    "(chg.char_id = 6  and chg.valuee between " + frameFrom + " and " + frameTo + ") or " +
                    "(chg.char_id = 7  and chg.valuee between " + sysFrom + " and " + sysTo + ") or " +
                    "(chg.char_id = 8  and chg.valuee between " + voltFrom + " and " + voltTo + ") or " +
                    "(chg.char_id = 9  and chg.valuee between " + mhzFrom + " and " + mhzTo + ") or " +
                    "(chg.char_id = 10 and chg.valuee between " + tfFrom + " and " + tfTo + ") or " +
                    "(chg.char_id = 11 and chg.valuee between " + slotsFrom + " and " + slotsTo + ") or " +
                    "(chg.char_id = 12 and chg.valuee between " + ramFrom + " and " + ramTo + ")) ";


        return res;
    }

    private String filterProvider(){
        String res = "";
        if(id_from_storage.isSelected()) res = res + " inner join storage on storage.good_id = good.id ";
        if(id_from_provider.isSelected()) res = res + " inner join outer_storage on outer_storage.good_id = good.id ";
        return res;
    }

    private String filterPrice(){
        int priceFrom;
        int priceTo;
        if (price_from.getText().equals("")) priceFrom = 0;
        else priceFrom = Integer.parseInt(price_from.getText());
        if (price_to.getText().equals("")) priceTo = 99999;
        else priceTo = Integer.parseInt(price_to.getText());
        return "(price between " + priceFrom + " and " + priceTo + ")";


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

    private class RSArray{
        private String string;
        private Range range;
        RSArray(){
            string = "";
            range = new Range();
        }

        RSArray(String string, Range range){
            this.string = string;
            this.range = range;
        }

        public Range getRange() {
            return range;
        }

        public String getString() {
            return string;
        }

        public void setRange(Range range) {
            this.range = range;
        }

        public void setString(String string) {
            this.string = string;
        }
    }

    private class Range{
        private double from;
        private double to;
        Range(){
            from = 0.0;
            to = 99999.0;
        }

        Range(double from, double to){
            this.from = from;
            this.to = to;
        }

        public void setFrom(double from) {
            this.from = from;
        }

        public void setTo(double to) {
            this.to = to;
        }

        public double getFrom() {
            return from;
        }

        public double getTo() {
            return to;
        }

        public boolean isInRange(double var){
            return var >= from && var <= to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return Double.compare(range.from, from) == 0 &&
                    Double.compare(range.to, to) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}




