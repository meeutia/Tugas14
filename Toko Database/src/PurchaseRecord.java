public class PurchaseRecord {
    private long id;
    private String namaPelanggan;
    private String noHpPelanggan;
    private String alamatPelanggan;
    private long totalBayar;

    public PurchaseRecord(long id, String namaPelanggan, String noHpPelanggan, String alamatPelanggan, long totalBayar) {
        this.id = id;
        this.namaPelanggan = namaPelanggan;
        this.noHpPelanggan = noHpPelanggan;
        this.alamatPelanggan = alamatPelanggan;
        this.totalBayar = totalBayar;
    }

    public long getId() {
        return id;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public String getNoHpPelanggan() {
        return noHpPelanggan;
    }

    public String getAlamatPelanggan() {
        return alamatPelanggan;
    }

    public long getTotalBayar() {
        return totalBayar;
    }

    // You can add getters for other properties as needed

    @Override
    public String toString() {
        return "PurchaseRecord{" +
                "id=" + id +
                ", namaPelanggan='" + namaPelanggan + '\'' +
                ", noHpPelanggan='" + noHpPelanggan + '\'' +
                ", alamatPelanggan='" + alamatPelanggan + '\'' +
                ", totalBayar=" + totalBayar +
                '}';
    }
}

