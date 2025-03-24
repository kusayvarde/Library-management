import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Kutuphane {
    static List<Kitap> kitaplar = new ArrayList<>();
    static List<Uye> uyeler = new ArrayList<>();
    
    static Scanner kullanici = new Scanner(System.in);

    // Kütüphaneye yeni kitap ekler.
    static void kitapEkle(Kitap kitap) {
        kitaplar.add(kitap);
        Main.maineGonder(); // Ana menüye geri döner.
    }
    

    // ISBN kullanarak kitap silme iSlemi yapar.
    static void kitapSil() {
        System.out.print("Silmek istediginiz kitabin ISBN'i giriniz: ");
        String isbn = kullanici.nextLine();
        Kitap kitap = Kitap.mevcutDurumu(isbn);// silmeden önce kitabın bulunmasından sorgulama
        if (kitap != null) {
            kitaplar.remove(kitap);
            System.out.println("Kitap basariyla silinmistir");
        } else {
            System.out.println("Kitap bulunamadi");
        }
        Main.maineGonder();
    }
    

    // ISBN kullanarak kitap arama iSlemi yapar.
    static void kitapAra(String isbn) {
        Kitap kitap = Kitap.mevcutDurumu(isbn);
        if (kitap == null) {
            System.out.println("Kitap bulunmamaktadir");
            Main.maineGonder();
        } else {
            Kitap.kitapOzellikleriYaz(kitap);
        }
    }

    // Tüm kitapları listeler.
    static void kitapListele() {
        if (kitaplar.isEmpty()) // Kitap yoksa uyarı verir.
            System.out.println("Kutuphanede kitap bulunmamaktadir");

        for (Kitap kitap : kitaplar) {
            Kitap.kitapOzellikleriYaz(kitap); // Kitap bilgilerini yazdırır.
            if (!kitap.mevcut)
                System.out.println("Kitap " + kitap.almaTarihi + " tarihinde alinmistir");
        }

        Main.maineGonder();
    }

    // Yeni üye ekler.
    static void uyeEkle(Uye uye) {
        uyeler.add(uye);
        System.out.println("\"" + uye.isim + "\" adli \'" + uye.uyeNO + "\' uye Nolu uye basariyla olusturuldu");
        Main.maineGonder();
    }

    // Üye numarasına göre üye siler.
    static void uyeSil() {
        System.out.print("Iptal etmek istediginiz uyelik numarasini giriniz: ");
        int uyeno = kullanici.nextInt();
        Uye uye = uyeKontrol(uyeno);
        if (uye == null) {
            System.out.println("Uye bulunamadi");
            Main.maineGonder();
        } else {
            uyeler.remove(uye);
            System.out.println("Uyelik basariyla iptal edilmistir");
            Main.maineGonder();
        }
    }

    // Üye adına göre arama yapar.
    static void uyeAra() {
        System.out.println("Aramak istediginiz uye adini giriniz: ");
        String isim = kullanici.nextLine();
        for (Uye uye : uyeler) {
            if (uye.isim.equals(isim)) {
                System.out.println("Ad-Soyad        : " + uye.isim);
                System.out.println("Uyelik numarasi : " + uye.uyeNO);
                System.out.println("Alinan kitaplar =>");
                uye.kitapGecmisiListele(); // Üyenin ödünç aldığı kitapları listeler.
                return;
            }
        }
        System.out.println("Uye bulunmamaktadir");
        Main.maineGonder();
    }

    // Tüm üyeleri listeler.
    static void uyeListele() {
        if (uyeler.isEmpty()) { // Üye yoksa uyarı verir.
            System.out.println("Kutuphanede uye bulunmamaktadir");
            Main.maineGonder();
            return;
        }
        for (Uye uye : uyeler) {
            System.out.println("Uye ismi        : " + uye.isim);
            System.out.println("Uyelik numarasi : " + uye.uyeNO);
        }
        Main.maineGonder();
    }

    // Belirtilen üye numarasına göre üye kontrol eder.
    static Uye uyeKontrol(int uyeno) {
        for (Uye uye : uyeler) {
            if (uye.uyeNO == uyeno) 
                return uye;
        }
        return null;
    }
}
