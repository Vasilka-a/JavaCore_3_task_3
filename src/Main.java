import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        openZip("D://Games/savegames/saveGames.zip", "D://Games/savegames/");

    }
    public static void openZip (String addressZip, String puth) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(addressZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry())  != null) {
                name = entry.getName(); // получим название файла распаковка
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(puth + name))) {
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        bos.write(c);
                    }
                    zin.closeEntry();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String openProgress (String addressFile){
        try (FileInputStream fis = new FileInputStream("save.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
// десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);

    }

}