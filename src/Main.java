import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {

        openZip("D://Games/savegames/saveGames.zip", "D://Games/savegames/");

        openProgress("D://Games/savegames/save1.dat");
        openProgress("D://Games/savegames/save2.dat");
        openProgress("D://Games/savegames/save3.dat");

    }

    public static void openZip(String addressZip, String puth) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(addressZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
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

    public static void openProgress(String addressFile) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(addressFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
             gameProgress = (GameProgress) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }
}

