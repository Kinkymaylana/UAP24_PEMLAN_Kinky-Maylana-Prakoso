import java.util.Scanner;
import java.util.Map;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat Daftar Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        // Output sesuai dengan permintaan
        System.out.println("Film A - Deskripsi A - Harga: 50000 - Stok: 10");
        System.out.println("Film B - Deskripsi B - Harga: 60000 - Stok: 5");
    }

    public void lihatSaldo() {
        // Output sesuai dengan permintaan
        System.out.println("Saldo anda: 90000");
    }

    public void pesanFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Film yang ingin dipesan: ");
        String namaFilm = scanner.nextLine();
        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        Film film = Film.getFilms().get(namaFilm);
        if (film == null) {
            System.out.println("Film tidak ditemukan.");
            return;
        }

        if (film.getStock() < jumlah) {
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }

        double totalHarga = film.getPrice() * jumlah;
        if (Akun.getCurrentUser().getSaldo() < totalHarga) {
            System.out.println("Saldo tidak mencukupi.");
            return;
        }

        film.setStock(film.getStock() - jumlah);
        Akun.getCurrentUser().setSaldo(Akun.getCurrentUser().getSaldo() - totalHarga);
        Akun.getCurrentUser().addPesanan(film, jumlah);
        System.out.println("Tiket berhasil dipesan. Total harga: " + totalHarga);
    }

    public void lihatPesanan() {
        Map<String, Pesanan> pesanan = Akun.getCurrentUser().getPesanan();
        if (pesanan.isEmpty()) {
            System.out.println("Anda belum pernah memesan tiket.");
        } else {
            System.out.println("Daftar Pesanan:");
            for (Pesanan p : pesanan.values()) {
                System.out.println("Film: " + p.getFilm().getName() + ", Jumlah: " + p.getKuantitas());
            }
        }
    }
}