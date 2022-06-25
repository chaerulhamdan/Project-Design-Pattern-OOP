-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Jun 2022 pada 13.36
-- Versi server: 10.4.19-MariaDB
-- Versi PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restoran_oop`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `burger`
--

CREATE TABLE `burger` (
  `foodid` int(11) NOT NULL,
  `foodname` varchar(255) NOT NULL,
  `foodtype` varchar(255) NOT NULL,
  `foodprice` int(11) NOT NULL,
  `burgerbun` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `burger`
--

INSERT INTO `burger` (`foodid`, `foodname`, `foodtype`, `foodprice`, `burgerbun`) VALUES
(1, 'Whooper Burger', 'Burger', 55000, 'Original'),
(2, 'Cheese Burger', 'Burger', 60000, 'Original'),
(3, 'Mushroom Swiss Burger', 'Burger', 65000, 'Original'),
(4, 'Spicy Mozarela Burger', 'Burger', 70000, 'Black Bun'),
(5, 'Fish Fillet Burger', 'Burger', 45000, 'Black Bun');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sandwich`
--

CREATE TABLE `sandwich` (
  `foodid` int(11) NOT NULL,
  `foodname` varchar(255) NOT NULL,
  `foodtype` varchar(255) NOT NULL,
  `foodprice` int(11) NOT NULL,
  `breadtype` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `sandwich`
--

INSERT INTO `sandwich` (`foodid`, `foodname`, `foodtype`, `foodprice`, `breadtype`) VALUES
(6, 'Italian Sandwich', 'Sandwich', 45000, 'Soft'),
(7, 'Tuna Sandwich', 'Sandwich', 37500, 'Soft'),
(8, 'Egg Mayo Sandwich', 'Sandwich', 38000, 'Soft'),
(9, 'Mac & Cheese Sandwich', 'Sandwich', 50000, 'Crunch'),
(10, 'BBQ Chicken Sandwich', 'Sandwich', 65000, 'Crunch');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaction`
--

CREATE TABLE `transaction` (
  `transactionid` varchar(255) NOT NULL,
  `userid` int(11) NOT NULL,
  `foodid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaction`
--

INSERT INTO `transaction` (`transactionid`, `userid`, `foodid`, `quantity`) VALUES
('TR001', 2, 5, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `age` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`userid`, `username`, `email`, `age`) VALUES
(2, 'Chaerul', 'Chaerul@gmail.com', 20),
(3, 'Brian', 'Brian@gmail.com', 20);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `burger`
--
ALTER TABLE `burger`
  ADD PRIMARY KEY (`foodid`);

--
-- Indeks untuk tabel `sandwich`
--
ALTER TABLE `sandwich`
  ADD PRIMARY KEY (`foodid`);

--
-- Indeks untuk tabel `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionid`),
  ADD UNIQUE KEY `userid` (`userid`),
  ADD UNIQUE KEY `foodid` (`foodid`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `burger`
--
ALTER TABLE `burger`
  MODIFY `foodid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `sandwich`
--
ALTER TABLE `sandwich`
  MODIFY `foodid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
