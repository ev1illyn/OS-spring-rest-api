CREATE TABLE ordem_servico
(
  id serial NOT NULL,
  cliente_id bigint NOT NULL,
  descricao text NOT NULL,
  preco decimal(10,2) NOT NULL,
  status varchar(20) NOT NULL,
  data_abertura timestamp not null,
  data_finalizacao timestamp,
  
  CONSTRAINT fk_ordem_servico_cliente FOREIGN KEY (cliente_id)
      REFERENCES cliente (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
