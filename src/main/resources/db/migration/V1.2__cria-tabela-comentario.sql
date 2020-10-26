CREATE TABLE comentario
(
  id serial NOT NULL primary key,
  ordem_servico_id bigint NOT NULL,
  descricao text NOT NULL,
  data_envio timestamp not null,

  CONSTRAINT fk_comentario_ordem_servico FOREIGN KEY (ordem_servico_id)
      REFERENCES ordem_servico (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
