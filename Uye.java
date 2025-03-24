import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Uye {
    // Üyenin bilgileri: isim, üye numarası, aldıkları kitaplar ve geçmiş kitap listesi
    String isim;
    int uyeNO;
    List<Kitap> kitapListesi = new ArrayList<>();
    List<Kitap> kitapGecmisi = new ArrayList<>();

    // Kullanıcıdan giriş almak için bir Scanner nesnesi
    static Scanner kullanici = new Scanner(System.in);

    // Üye oluşturucu: Üye bilgilerini kaydeder ve kütüphaneye ekler.
    public Uye() {
        uyeEkle(); // Üye bilgilerini kaydet
        Kutuphane.uyeEkle(this); // Üyeyi kütüphaneye ekle
    }

    // Üyenin bilgilerini günceller.
    public static void bilgiGuncelle() {
        System.out.print("Guncellemek istediginiz uye numarasini giriniz: ");
        int uyeno = kullanici.nextInt();
        Uye uye = Kutuphane.uyeKontrol(uyeno);
        if (uye == null) {
            System.out.println("Uye bulunamadi!");
            Main.maineGonder();
            return;
        }

        System.out.println("Eski uye adi: " + uye.isim);
        System.out.print("Yeni uye adi: ");
        kullanici.nextLine();
        String isim = kullanici.nextLine();
        
        uye.isim = isim; 
        System.out.println("Bilgiler basariyla guncellenmistir");
        Main.maineGonder();
    }

    // Üye bilgilerini alır ve üye numarasını belirler.
    public void uyeEkle() {
        System.out.print("Ad-Soyad: ");
        this.isim = kullanici.nextLine();
        if (Kutuphane.uyeler.isEmpty())
            this.uyeNO = 1; // İlk üye için numara 1 olur
        else {
            int sonKullaniciNo = Kutuphane.uyeler.size() - 1;
            int yeniNo = Kutuphane.uyeler.get(sonKullaniciNo).uyeNO + 1;
            this.uyeNO = yeniNo; // Yeni üyeye bir sonraki numara atanır
        }
    }

    // Üyenin kitap ödünç almasını sağlar.
    public void kitapAlma() {
        if (Kutuphane.uyeKontrol(uyeNO) == null) {
            System.out.println("Kitap almak icin uye olmaniz gerekiyor!!");
            Main.maineGonder();
            return;
        }
        System.out.print("Almak istediginiz kitabin ISBN'i giriniz: ");
        String isbn = kullanici.nextLine();
        Kutuphane.kitapAra(isbn);
        
        Kitap kitap = Kitap.mevcutDurumu(isbn);
        if (kitap != null && kitap.mevcut) {
            kitapListesi.add(kitap);
            kitapGecmisi.add(kitap);
            kitap.iadeTarihi = null; // Yeni ödünç alınan kitabın iade tarihi yok
            kitap.almaTarihi = LocalDate.now(); // Ödünç alma tarihi bugünün tarihi olarak atanır
            kitap.mevcut = false;
            System.out.println("Islem basarili");
        } else {
            System.out.println("Maalesef kitap alinmistir");
        }
        Main.maineGonder();
    }

    // Üyenin kitabı iade etmesini sağlar.
    public void kitapIade() {
        System.out.print("Iade etmek istediginiz kitabin ISBN'i giriniz: ");
        String isbn = kullanici.nextLine();
        Kutuphane.kitapAra(isbn);

        Kitap kutuphaneKitabi = Kitap.mevcutDurumu(isbn);
        for (Kitap kitap : kitapListesi) {
            if (kitap.ISBN.equals(isbn)) {
                kitapListesi.remove(kitap);
                kitap.iadeTarihi = LocalDate.now(); // İade tarihi bugünün tarihi olarak atanır
                kutuphaneKitabi.mevcut = true; // Kitap tekrar mevcut hale gelir
                System.out.println("Kitap basariyla iade edilmistir");
                Main.maineGonder();
                return;
            }
        }
        
        System.out.println("Kitap bulunamadi");
        Main.maineGonder();
    }

    // Üyenin şu anda ödünç aldığı kitapları listeler.
    public void AlinankitapListele() {
        if (kitapListesi.isEmpty())
            System.out.println("Alinan kitap bulunmamaktadir");

        for (Kitap kitap : kitapListesi)
            System.out.println(kitap.baslik + "(" + kitap.almaTarihi + ")");

        Main.maineGonder();
    }

    // Üyenin ödünç aldığı kitapların geçmişini listeler.
    public void kitapGecmisiListele() {
        if (kitapGecmisi.isEmpty())
            System.out.println("Alinan kitap bulunmamaktadir");

        for (Kitap kitap : kitapGecmisi) {
            if (kitap.iadeTarihi == null)
                System.out.println(kitap.baslik + "(" + kitap.almaTarihi + " / ~ )");
            else
                System.out.println(kitap.baslik + "(" + kitap.almaTarihi + " / " + kitap.iadeTarihi + ")");
        }
        Main.maineGonder();
    }
}
