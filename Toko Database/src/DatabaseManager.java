import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/dbrecordpembelian";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", USER);
        connectionProps.put("password", PASSWORD);
        return DriverManager.getConnection(DATABASE_URL, connectionProps);
    }

    // CREATE
    public static void createPurchaseRecord(String namaPelanggan, String noHpPelanggan, String alamatPelanggan, long totalBayar) {
        Connection connection = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);  // Non-otomatisasi transaksi

            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO purchase_records (nama_pelanggan, no_hp_pelanggan, alamat_pelanggan, total_bayar) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, namaPelanggan);
                preparedStatement.setString(2, noHpPelanggan);
                preparedStatement.setString(3, alamatPelanggan);
                preparedStatement.setLong(4, totalBayar);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating purchase record failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Purchase record created with ID: " + generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Creating purchase record failed, no ID obtained.");
                    }
                }
            }

            connection.commit();  // Commit transaksi setelah berhasil eksekusi INSERT
        } catch (SQLException e) {
            e.printStackTrace();  // Cetak detail kesalahan ke konsol

            try {
                if (connection != null) {
                    connection.rollback();  // Rollback transaksi jika terjadi kesalahan
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // READ
    public static List<PurchaseRecord> getAllPurchaseRecords() {
        List<PurchaseRecord> purchaseRecords = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM purchase_records")) {
            while (resultSet.next()) {
                PurchaseRecord purchaseRecord = new PurchaseRecord(
                        resultSet.getLong("id"),
                        resultSet.getString("nama_pelanggan"),
                        resultSet.getString("no_hp_pelanggan"),
                        resultSet.getString("alamat_pelanggan"),
                        resultSet.getLong("total_bayar")
                );
                purchaseRecords.add(purchaseRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchaseRecords;
    }

    // UPDATE
    public static void updatePurchaseRecord(long id, String namaPelanggan, String noHpPelanggan, String alamatPelanggan, long totalBayar) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE purchase_records SET nama_pelanggan=?, no_hp_pelanggan=?, alamat_pelanggan=?, total_bayar=? WHERE id=?")) {
            preparedStatement.setString(1, namaPelanggan);
            preparedStatement.setString(2, noHpPelanggan);
            preparedStatement.setString(3, alamatPelanggan);
            preparedStatement.setLong(4, totalBayar);
            preparedStatement.setLong(5, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No records updated.");
            } else {
                System.out.println("Purchase record with ID " + id + " updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deletePurchaseRecord(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM purchase_records WHERE id=?")) {
            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("No records deleted.");
            } else {
                System.out.println("Purchase record with ID " + id + " deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
