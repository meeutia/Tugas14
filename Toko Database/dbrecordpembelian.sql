-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Des 2023 pada 14.06
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbrecordpembelian`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `purchase_records`
--

CREATE TABLE `purchase_records` (
  `id` int(11) NOT NULL,
  `nama_pelanggan` varchar(255) NOT NULL,
  `no_hp_pelanggan` varchar(20) NOT NULL,
  `alamat_pelanggan` varchar(255) NOT NULL,
  `total_bayar` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `purchase_records`
--

INSERT INTO `purchase_records` (`id`, `nama_pelanggan`, `no_hp_pelanggan`, `alamat_pelanggan`, `total_bayar`) VALUES
(1, 'wiwin', '0857781563108', 'jalan penuh', 288003000),
(2, 'jujun', '085781563108', 'jalan kesana kemari', 240003000),
(3, 'jihan', '085781563108', 'jalan kehatimu', 12000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `purchase_records`
--
ALTER TABLE `purchase_records`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `purchase_records`
--
ALTER TABLE `purchase_records`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
