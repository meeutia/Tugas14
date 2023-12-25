import java.sql.*;
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
}
