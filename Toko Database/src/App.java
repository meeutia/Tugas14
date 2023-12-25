import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        long totalBayar = 0; 

        // Membuat daftar barang (Polimorphisme)
        ArrayList<Barang> daftarBarang = new ArrayList<>();
        daftarBarang.add(new Elektronik());
        daftarBarang.add(new NonElektronik());

        // Initialize the shopping cart
        Map<Barang, Integer> keranjangBelanja = new HashMap<>();

        // Menampilkan list barang
        System.out.println(" ");
        System.out.println("=======List Barang======");
        for (Barang barang : daftarBarang) {
            barang.tampilkanInfo();
        }

        // Exception Handling
        try {
            // Additional fields for customer data
            System.out.print("\nNama Pelanggan: ");
            String namaPelanggan = scanner.nextLine();
            System.out.print("No. HP Pelanggan: ");
            String noHpPelanggan = scanner.nextLine();
            System.out.print("Alamat Pelanggan: ");
            String alamatPelanggan = scanner.nextLine();

            // Additional fields for purchase data
            System.out.print("\nMini Market Happy ");
            scanner.nextLine();
            System.out.println(DateUtil.getCurrentDateTime());
            System.out.println("\n========================");
            System.out.println("DATA PELANGGAN");
            System.out.println("---------------------");
            System.out.println("Nama Pelanggan: " + namaPelanggan);
            System.out.println("No. HP         : " + noHpPelanggan);
            System.out.println("Alamat         : " + alamatPelanggan);
            System.out.println("++++++++++++++++++++++++");

            System.out.println("\nDATA PEMBELIAN BARANG");
            System.out.println("------------------------------");

            for (Barang barang : daftarBarang) { // Corrected: Loop through daftarBarang
                System.out.print("Jumlah Beli " + barang.nama + ": ");
                int jumlahBeli = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                keranjangBelanja.put(barang, jumlahBeli);
            }

            // Call the method to store purchase record in the database
            DatabaseManager.createPurchaseRecord(namaPelanggan, noHpPelanggan, alamatPelanggan, totalBayar);

            // Iterate over the shopping cart entries
            for (Map.Entry<Barang, Integer> entry : keranjangBelanja.entrySet()) {
                Barang barang = entry.getKey();
                int jumlahBeli = entry.getValue();

                System.out.println("Kode Barang     : " + barang.kode);  // Assuming a new field "kode" in Barang class
                System.out.println("Nama Barang     : " + barang.nama);
                System.out.println("Harga Barang    : Rp. " + barang.harga);
                System.out.println("Jumlah Beli     : " + jumlahBeli);

                long totalBayarBarang = barang.hitungTotalBayar(jumlahBeli);
                System.out.println("Total Bayar     : Rp. " + totalBayarBarang);
                System.out.println("++++++++++++++++++++++++");

                totalBayar += totalBayarBarang;
            }

            System.out.println("\nTotal Bayar Keseluruhan: Rp. " + totalBayar);
            System.out.println("++++++++++++++++++++++++");

            // Additional fields for cashier data
            System.out.println("Kasir: " + getKasirInfo());  // Assuming a method to get cashier information

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Additional method to get cashier information
    private static String getKasirInfo() {
        return "Meutia";  // Replace with the actual cashier information
    }
}