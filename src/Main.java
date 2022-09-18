import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        Files.createDirectories(Paths.get("src/test"));
        sb.append("В директории \"src\" создана директория \"test\". \"");
        Files.createDirectories(Paths.get("res/drawables"));
        sb.append("В директории \"res\" создана директория \"drawables\". \"");
        Files.createDirectories(Paths.get("res/vectors"));
        sb.append("В директории \"res\" создана директория \"vectors\". \"");
        Files.createDirectories(Paths.get("res/icons"));
        sb.append("В директории \"res\" создана директория \"icons\". \"");

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

        GameProgress gameProgress1 = new GameProgress(10, 30, 2304, 3495);
        GameProgress gameProgress2 = new GameProgress(11, 31, 2354, 5);
        GameProgress gameProgress3 = new GameProgress(130, 673, 23, 695);
        saveGame("savegames/savegames1.txt", gameProgress1);
        saveGame("savegames/savegames2.txt", gameProgress2);
        saveGame("savegames/savegames3.txt", gameProgress3);
        List<String> list = new ArrayList<>();
        list.add("savegames/savegames1.txt");
        list.add("savegames/savegames2.txt");
        list.add("savegames/savegames3.txt");
        zipFiles("savegames/savegames.zip", list);
        deletingSaves (list);


    }


    public static boolean saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public static boolean zipFiles(String path, List<String> list) {
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));
            for (String file : list) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file);
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            zout.close();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public static boolean deletingSaves (List<String> list) {
        try {
            for (String file : list) {
                Path path = Paths.get(file);
                Files.delete(path);
            }
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

}
