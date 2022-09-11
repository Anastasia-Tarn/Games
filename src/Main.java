import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        Files.createDirectories(Paths.get("res"));
        sb.append("Создана директория \"res\". ");
        Files.createDirectories(Paths.get("savegames"));
        sb.append("Создана директория \"savegames\". \"");
        Files.createDirectories(Paths.get("temp"));
        sb.append("Создана директория \"temp\". \"");
        Files.createDirectories(Paths.get("src"));
        sb.append("Создана директория \"src\". \"");
        Files.createDirectories(Paths.get("src/main"));
        sb.append("В директории \"src\" создана директория \"main\". \"");
        Files.createDirectories(Paths.get("src/test"));
        sb.append("В директории \"src\" создана директория \"test\". \"");
        Files.createDirectories(Paths.get("res/drawables"));
        sb.append("В директории \"res\" создана директория \"drawables\". \"");
        Files.createDirectories(Paths.get("res/vectors"));
        sb.append("В директории \"res\" создана директория \"vectors\". \"");
        Files.createDirectories(Paths.get("res/icons"));
        sb.append("В директории \"res\" создана директория \"icons\". \"");

        try {
            File main = new File("src/main/Main.java");
            if (main.createNewFile())
                sb.append("File \"Main.java\" created. ");
            else
                System.out.println("File already exists. ");
        } catch (Exception e) {
            System.err.println(e);
        }

        try {
            File utils = new File("src/main/Utils.java");
            if (utils.createNewFile())
                sb.append("File \"Utils.java\" created. ");
            else
                System.out.println("File already exists");
        } catch (Exception e) {
            System.err.println(e);
        }

        try {
            File temp = new File("temp/temp.txt");
            if (temp.createNewFile())
               sb.append("File \"temp.txt\" created. ");
            else
                System.out.println("File already exists");
        } catch (Exception e) {
            System.err.println(e);
        }

        FileWriter fw = new FileWriter("temp/temp.txt");
        fw.write(sb.toString());
        fw.close();


    }


}
