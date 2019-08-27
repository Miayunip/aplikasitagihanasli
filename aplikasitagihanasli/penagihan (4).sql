-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 12, 2019 at 05:21 AM
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
  `id_pembayaran` int(10) NOT NULL,
  `id_surat` int(20) NOT NULL,
  `no_kwitansi` int(20) NOT NULL,
  `tanggal_pembayaran` date NOT NULL,
  `jumlah_pembayaran` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tagihan`
--

CREATE TABLE `tagihan` (
  `id_surat` int(10) NOT NULL,
  `id_tagihan` int(20) NOT NULL,
  `no_surat` int(50) NOT NULL,
  `tanggal_surat` date NOT NULL,
  `jenis_tagihan` enum('harian','mingguan','bulanan','ulang','lainnya') NOT NULL,
  `kategori_tagihan` enum('penggunaan_ruang_tunggu','penggunaan_checkin_counter','penggunaan_ruang','penggunaan_listrik','penggunaan_air','penggunaan_apron','reklame') NOT NULL,
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
  `status_tertagih` enum('aktif','tidak_aktif','','') NOT NULL,
  `no_tlp` int(15) NOT NULL,
  `penanggung_jawab` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(201, 'oktafira', 'okta', '123', 'staff_tu'),
(301, 'carol khasanah', 'carol', '123', 'staff_jasa'),
(401, 'memed ', 'memet', '123', 'kasubag_tu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `id_surat` (`id_surat`);

--
-- Indexes for table `tagihan`
--
ALTER TABLE `tagihan`
  ADD PRIMARY KEY (`id_surat`),
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
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=402;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `pembayaran_ibfk_1` FOREIGN KEY (`id_surat`) REFERENCES `tagihan` (`id_surat`);

--
-- Constraints for table `tagihan`
--
ALTER TABLE `tagihan`
  ADD CONSTRAINT `tagihan_ibfk_1` FOREIGN KEY (`id_tagihan`) REFERENCES `tertagih` (`id_tertagih`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
