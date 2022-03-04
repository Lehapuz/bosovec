import controller.DefaultController;

import java.io.IOException;

public class BlogRun {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DefaultController defaultController = new DefaultController();
        defaultController.read();
        defaultController.run();
    }
}



