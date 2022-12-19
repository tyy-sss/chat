import chat.service.ClientConnectServiceThread;
import chat.service.ClientService;
import chat.service.Init;
import chat.view.ready.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Init init = new Init();
        new Thread(init).start();
        LoginController.start(primaryStage);
    }
}