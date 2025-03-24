import java.util.Scanner;
import java.time.LocalDate;

public class Kitap {
    // Kitabın özellikleri: başlık, yazar, ISBN, mevcut durumu, alma ve iade tarihleri
    String baslik;
    String yazar;
    String ISBN;
    boolean mevcut;
    LocalDate almaTarihi;
    LocalDate iadeTarihi;

    // Kullanıcıdan girdi almak için bir Scanner nesnesi
    Scanner kullanici = new Scanner(System.in);

    // Kitap oluşturucu: Kitap bilgilerini kaydeder ve kütüphaneye ekler.
    public Kitap() {
        kitapEkle(); // Kitap bilgilerini kaydet
        Kutuphane.kitapEkle(this); // Kitabı kütüphaneye ekle
    }

    // Kitap bilgilerini alır ve mevcut olarak işaretler
    public void kitapEkle() {
        System.out.print("Kitabin basligi : ");
        this.baslik = kullanici.nextLine();
        
        System.out.print("Kitabin yazari  : ");
        this.yazar = kullanici.nextLine();
        
        System.out.print("Kitabin ISBN    : ");
        this.ISBN = kullanici.nextLine();
        
        this.mevcut = true;
        System.out.println("Kitap basariyla eklenmistir");
    }

    // Kitabın özelliklerini yazdırır
    public static void kitapOzellikleriYaz(Kitap kitap) {
        System.out.println("Baslik : " + kitap.baslik);
        System.out.println("Yazar  : " + kitap.yazar);
        System.out.println("ISBN   : " + kitap.ISBN);
        System.out.print("Durum  : ");
        if (kitap.mevcut)
            System.out.println("Mevcuttur");
        else
            System.out.println("Alinmistir");
    }

    // Verilen ISBN ile kitap mevcut mu kontrol eder ve kitabı döndürür
    public static Kitap mevcutDurumu(String isbn) {
        for (Kitap kitap : Kutuphane.kitaplar) {
            if (kitap.ISBN.equals(isbn))
                return kitap; // Kitap bulunursa döndürülür
        }
        return null; // Kitap bulunamazsa null döner
    }
}



