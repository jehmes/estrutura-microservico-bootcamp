CREATE TABLE `product` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `nome` longtext,
  `preco` decimal(65,2) NOT NULL,
  `preco_total` decimal(65,2) NOT NULL,
  `descricao` longtext,
  `url` longtext,
  `formato_imagem` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
