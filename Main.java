import java.util.Scanner;

class MayinTarlasi {
    public static void main(String[] args) {
        final int SATIR = 5; // Oyun tahtasının satır sayısı
        final int SUTUN = 5; // Oyun tahtasının sütun sayısı
        final int MAYIN_SAYISI = 3; // Oyunda bulunması gereken mayın sayısı

        int[][] tarla = new int[SATIR][SUTUN]; // Oyun tahtası matrisi
        int[][] gosterilenTarla = new int[SATIR][SUTUN]; // Gösterilen tahta matrisi

        mayinlariYerlestir(tarla, MAYIN_SAYISI); // Mayınları yerleştir

        boolean devam = true; // Oyun devam ediyor mu?

        while (devam) {
            oyunTablosunuGoster(gosterilenTarla); // Tahtayı göster

            int satir = satirSec(); // Kullanıcıdan satır seçmesini iste
            int sutun = sutunSec(); // Kullanıcıdan sütun seçmesini iste

            if (tarla[satir][sutun] == 1) {
                System.out.println("Mayına bastınız! Oyun bitti.");
                devam = false; // Mayına basıldığı için oyunu sonlandır
            } else {
                gosterilenTarla[satir][sutun] = 1; // Seçilen hücreyi aç
                if (oyunBittiMi(gosterilenTarla, MAYIN_SAYISI)) {
                    System.out.println("Tebrikler! Mayınları buldunuz. Oyunu kazandınız!");
                    devam = false; // Tüm mayınlar bulunduğu için oyunu sonlandır
                }
            }
        }
    }

    public static void mayinlariYerlestir(int[][] tarla, int mayinSayisi) {
        int satir, sutun;

        for (int i = 0; i < mayinSayisi; i++) {
            do {
                satir = (int) (Math.random() * tarla.length); // Rastgele bir satır seç
                sutun = (int) (Math.random() * tarla[0].length); // Rastgele bir sütun seç
            } while (tarla[satir][sutun] == 1); // Eğer hücrede mayın varsa tekrar seç

            tarla[satir][sutun] = 1; // Mayını belirtilen hücreye yerleştir
        }
    }

    public static void oyunTablosunuGoster(int[][] tarla) {
        System.out.println("Mayın Tarlası Oyunu");

        for (int i = 0; i < tarla.length; i++) {
            for (int j = 0; j < tarla[0].length; j++) {
                if (tarla[i][j] == 1) {
                    System.out.print("* "); // Mayını göster
                } else {
                    System.out.print("- "); // Boş hücreyi göster
                }
            }
            System.out.println();
        }
    }

    public static int satirSec() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Satır seçin (0-4): ");
        int satir = scan.nextInt();
        return satir; // Kullanıcının seçtiği satırı döndür
    }

    public static int sutunSec() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Sütun seçin (0-4): ");
        int sutun = scan.nextInt();
        return sutun; // Kullanıcının seçtiği sütunu döndür
    }

    public static boolean oyunBittiMi(int[][] gosterilenTarla, int mayinSayisi) {
        int acilanHucreler = 0; // Açılan hücre sayısı

        for (int i = 0; i < gosterilenTarla.length; i++) {
            for (int j = 0; j < gosterilenTarla[0].length; j++) {
                if (gosterilenTarla[i][j] == 1) {
                    acilanHucreler++; // Her açılan hücrede sayacı artır
                }
            }
        }

        int toplamHucreSayisi = gosterilenTarla.length * gosterilenTarla[0].length; // Toplam hücre sayısı
        int gizliHucreler = toplamHucreSayisi - acilanHucreler; // Gizli hücre sayısı

        return gizliHucreler == mayinSayisi; // Gizli hücre sayısı mayın sayısına eşitse oyun bitti
    }
}
