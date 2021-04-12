package View;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        File theDir = new File("Resource\\Users");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    }
}
