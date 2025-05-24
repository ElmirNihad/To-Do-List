import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TDL {
    static final String FAYL_ADI = "tapshiriqlar.txt";
    static List<Task> tapsiriqlar = new ArrayList<Task>();
    public static void main(String[] args) {
        fayldanYukle();
        Scanner input = new Scanner(System.in);
        int secim;
        do{
            menyunuGoster();
            secim = input.nextInt();
            input.nextLine();

            switch (secim){
                case 1:
                    System.out.print("Tapşırığın təsvirini yazın: ");
                    String metn = input.nextLine();
                    tapsiriqlar.add(new Task(metn));
                    System.out.println("Tapşırıq əlavə olundu.");
                    break;
                case 2:
                    tapshiriqlariGoster();
                    break;
                case 3:
                    tapshiriqlariGoster();
                    System.out.print("Hansını tamamlandı kimi qeyd etmək istəyirsən (nömrəsini yaz): ");
                    int nomre = input.nextInt() - 1;
                    if (nomre >= 0 && nomre < tapsiriqlar.size()) {
                        tapsiriqlar.get(nomre).tamamla();
                        System.out.println("Tapşırıq tamamlandı kimi qeyd edildi.");
                    } else {
                        System.out.println("Yanlış nömrə.");
                    }
                    break;
                case 4:
                    tapshiriqlariGoster();
                    System.out.print("Hansını silmək istəyirsən (nömrəsini yaz): ");
                    int silNomre = input.nextInt() - 1;
                    if (silNomre >= 0 && silNomre < tapsiriqlar.size()) {
                        tapsiriqlar.remove(silNomre);
                        System.out.println("Tapşırıq silindi.");
                    } else {
                        System.out.println("Yanlış nömrə.");
                    }
                    break;
                case 5:
                    faylaYaddaSaxla();
                    System.out.println("Tapşırıqlar yadda saxlanıldı. Çıxılır...");
                    break;

                default:
                    System.out.println("Yanlış seçim.");
            }
        }while (secim != 5);

    }
    private static void menyunuGoster() {
        System.out.println("\n--- To-Do List ---");
        System.out.println("1. Yeni tapşırıq əlavə et");
        System.out.println("2. Tapşırıqları göstər");
        System.out.println("3. Tapşırığı tamamlandı kimi qeyd et");
        System.out.println("4. Tapşırığı sil");
        System.out.println("5. Çıxış");
    }
    private static void tapshiriqlariGoster() {
        if (tapsiriqlar.isEmpty()) {
            System.out.println("Heç bir tapşırıq yoxdur.");
        } else {
            System.out.println("--- Tapşırıqlar ---");
            for (int i = 0; i < tapsiriqlar.size(); i++) {
                System.out.println((i + 1) + ". " + tapsiriqlar.get(i));
            }
        }
    }
    private static void faylaYaddaSaxla() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FAYL_ADI))) {
            for (Task task : tapsiriqlar) {
                writer.println(task.isTamamlandi() + ";" + task.getDescription());
            }
        } catch (IOException e) {
            System.out.println("Yadda saxlanarkən xəta baş verdi.");
        }
    }

    private static void fayldanYukle() {
        File file = new File(FAYL_ADI);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] hisseler = scanner.nextLine().split(";", 2);
                Task task = new Task(hisseler[1]);
                if (Boolean.parseBoolean(hisseler[0])) {
                    task.tamamla();
                }
                tapsiriqlar.add(task);
            }
        } catch (IOException e) {
            System.out.println("Fayldan yüklənərkən xəta baş verdi.");
        }
    }
}
