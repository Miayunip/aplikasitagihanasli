-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 19, 2019 at 03:14 AM
-- Server version: 10.3.15-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penagihan`
--

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` int(100) NOT NULL,
  `no_kwitansi` int(100) NOT NULL,
  `tanggal_pembayaran` date NOT NULL,
  `jumlah_pembayaran` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `no_kwitansi`, `tanggal_pembayaran`, `jumlah_pembayaran`) VALUES
(5, 2345678, '3903-12-31', 234567),
(6, 777665, '3903-12-31', 234567);

-- --------------------------------------------------------

--
-- Table structure for table `tagihan`
--

CREATE TABLE `tagihan` (
  `id_tagihan` int(20) NOT NULL,
  `no_surat` int(50) NOT NULL,
  `tanggal_surat` date NOT NULL,
  `jenis_tagihan` enum('harian','mingguan','bulanan','ulang','lainnya') NOT NULL,
  `kategori_tagihan` enum('penggunaan ruang tunggu','penggunaan_checkin_counter','penggunaan_ruang','penggunaan_listrik','penggunaan_air','penggunaan_apron','reklame') NOT NULL,
  `status` enum('dibayar','tunggakan','','') NOT NULL,
  `nama_tagihan` varchar(30) NOT NULL,
  `jumlah_tagihan` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tertagih`
--

CREATE TABLE `tertagih` (
  `id_tertagih` int(10) NOT NULL,
  `nama_tertagih` varchar(20) NOT NULL,
  `kategori_tertagih` enum('airline','puskopau','lain-lain','') NOT NULL,
  `keterangan` varchar(20) NOT NULL,
  `status_tertagih` enum('aktif','tidak_aktif','','') NOT NULL,
  `no_tlp` bigint(15) NOT NULL,
  `penanggung_jawab` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tertagih`
--

INSERT INTO `tertagih` (`id_tertagih`, `nama_tertagih`, `kategori_tertagih`, `keterangan`, `status_tertagih`, `no_tlp`, `penanggung_jawab`) VALUES
(18, 'mia', 'airline', 'garuda', 'aktif', 456789, 'okta'),
(20, 'mias', 'puskopau', '', 'tidak_aktif', 456789, 'mia');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(8) NOT NULL,
  `level` enum('admin','staff_tu','staff_jasa','kasubag_tu') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nama`, `username`, `password`, `level`) VALUES
(101, 'mia yuni pratiwi', 'admin', '123', 'admin'),
(201, 'stafftu', 'stafftu', '123', 'staff_tu'),
(301, 'staffjasa', 'staffjasa', '123', 'staff_jasa'),
(401, 'kasubag', 'kasubag', '123', 'kasubag_tu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`);

--
-- Indexes for table `tagihan`
--
ALTER TABLE `tagihan`
  ADD KEY `id_tagihan` (`id_tagihan`);

--
-- Indexes for table `tertagih`
--
ALTER TABLE `tertagih`
  ADD PRIMARY KEY (`id_tertagih`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `id_pembayaran` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tagihan`
--
ALTER TABLE `tagihan`
  MODIFY `id_tagihan` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tertagih`
--
ALTER TABLE `tertagih`
  MODIFY `id_tertagih` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=402;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
