import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Sonsuz döngü ile ana menü sürekli olarak gösteriliyor.
        while (true) {
            System.out.print("\033\143"); // Konsolu temizler.
            System.out.println("*-------------------------------MAIN-------------------------------*");
            // Menü seçenekleri kullaniciya gösteriliyor.
            System.out.println("\n\n\n\n");
            System.out.println("         Uye islemleri         <****>        kitap islemleri        ");
            System.out.println("Uye ol                 ==>   1 <****>  Kitap ekle         ==>     7 ");
            System.out.println("Uyeleri goster         ==>   2 <****>  Kitaplari goster   ==>     8 ");
            System.out.println("Uye ara                ==>   3 <****>  Kitap ara          ==>     9 ");
            System.out.println("Uye bilgileri guncelle ==>   4 <****>  Kitap sil          ==>     10");
            System.out.println("Uye sil                ==>   5 <****>  Kitap al           ==>     11");
            System.out.println("Uye gecmisi goster     ==>   6 <****>  kitap iade         ==>     12");
            System.out.println("         Uyenin odunc aldigi kitaplari listele   ==>  13");
            System.out.println("\n\nEXIT ==> 0\n");
            System.out.print("Yapmak istediginiz islemi secin  >>> ");

            Scanner kullanici = new Scanner(System.in);
            try {
                int islem = kullanici.nextInt(); // Kullanicidan işlem numarasi aliniyor.                

                switch (islem) {
                    case 0:
                        System.out.print("\033\143");//konsolu temizler
                        System.out.println("Uygulama kapatiliyor...");
                        kullanici.close(); // Uygulama kapatilirken Scanner kapatiliyor.
                        try {
                            Thread.sleep(3000); // Programi kapatmadan önce kisa bir bekleme süresi.
                        } catch(Exception e) {}
                        System.exit(0); // Program sonlandiriliyor.
                        break;
                        
                    case 1:
                        System.out.print("\033\143");
                        new Uye(); // Yeni üye oluştur.
                        break;

                    case 2:
                        System.out.print("\033\143");
                        Kutuphane.uyeListele(); // Üyeleri listele.
                        break;

                    case 3:
                        System.out.print("\033\143");
                        Kutuphane.uyeAra(); // Üye ara.
                        break;
                        
                    case 4:
                        System.out.print("\033\143");
                        Uye.bilgiGuncelle(); // Üye bilgilerini güncelle.
                        break;
                        
                    case 5:
                        System.out.print("\033\143");
                        Kutuphane.uyeSil(); // Üyeyi sil.
                        break;
                        
                    case 6:
                        System.out.print("\033\143");
                        try {    
                            System.out.print("Gösterilecek üyenin geçmisi, üye numarasini giriniz: ");
                            int uynoGecmisi = kullanici.nextInt();
                            Uye uyeGecmisi = Kutuphane.uyeKontrol(uynoGecmisi);
                            if (uyeGecmisi == null) {
                                System.out.println("Üye bulunamadi");
                                maineGonder(); // Menüye geri dön.
                            }
                            uyeGecmisi.kitapGecmisiListele(); // Üyenin kitap geçmişini listele.
                        } catch(Exception e) {
                            System.out.println("Hatali Giris!");
                            maineGonder();
                        }
                        break;

                    case 7:
                        System.out.print("\033\143");
                        new Kitap(); // Yeni kitap ekle.
                        break;

                    case 8:
                        System.out.print("\033\143");
                        Kutuphane.kitapListele(); // Kitaplari listele.
                        break;

                    case 9:
                        System.out.print("\033\143");
                        System.out.print("Aramak istediginiz kitabin ISBN'i giriniz: ");
                        kullanici.nextLine();
                        String isbn = kullanici.nextLine();
                        Kutuphane.kitapAra(isbn); // ISBN ile kitap ara.
                        maineGonder();
                        break;
                        
                    case 10:
                        System.out.print("\033\143");
                        Kutuphane.kitapSil(); // Kitabi sil.
                        break;

                    case 11:
                        try {
                                System.out.print("\033\143");
                            System.out.print("Kitap almak için üye numarasini giriniz: ");
                            int uyenoKitapAl = kullanici.nextInt();
                            Uye uye2 = Kutuphane.uyeKontrol(uyenoKitapAl);//üye kontrolü
                            if (uye2 == null) {
                                System.out.println("Üye bulunamadi");
                                maineGonder();
                            }
                            uye2.kitapAlma(); // Üyeye kitap ödünç ver.
                        } catch(Exception e) {
                            System.out.println("Hatali giris");
                            maineGonder();
                        }
                        break;

                    case 12:
                        System.out.print("\033\143");
                        System.out.print("Kitap iade etmek için üye numarasini giriniz: ");
                        try {
                            int uyenoKitapIade = kullanici.nextInt();   
                            Uye uye3 = Kutuphane.uyeKontrol(uyenoKitapIade);
                            uye3.kitapIade(); // Üyenin kitabi iade etme işlemi.
                        } catch (Exception e) {
                            System.out.println("Hatali giris");
                            maineGonder();
                        }
                        break;

                    case 13:
                        System.out.print("\033\143");
                        System.out.print("Kitaplari listelemek için üye numarasini giriniz: ");
                        try {
                            int uyenoKitapIade = kullanici.nextInt();   
                            Uye uye3 = Kutuphane.uyeKontrol(uyenoKitapIade);
                            uye3.AlinankitapListele(); // Üyenin aldiği kitaplari listele.
                        } catch (Exception e) {
                            System.out.println("Hatali giris");
                            maineGonder();
                        }
                        break;
                
                    default:
                        System.out.println("HATALI GIRIS");
                        maineGonder();
                        break;
                }
            } catch (Exception e) {
                System.out.println("HATALI GIRIS!!!");
                maineGonder();
            }    
        }
    }  

    // Kullanici devam etmek için bir tuşa bastiğinda ana menüye döndürme fonksiyonu.
    static void maineGonder() {
        System.out.println("\n\nDevam etmek için Enter'a basin...");
        try {
            System.in.read();
        } catch(Exception e) {}
        main(null); // Ana menüye geri döner.
    }
}
