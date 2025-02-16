CREATE TABLE public.contatos (
    id serial NOT NULL,
	nome varchar NOT NULL,
	contato varchar NOT NULL,
	created_date timestamp NOT NULL,
	profissional int NOT NULL,
	CONSTRAINT contatos_pk PRIMARY KEY (id)
);

CREATE TABLE public.profissionais (
	nome varchar NOT NULL,
	id serial NOT NULL,
	cargo varchar NOT NULL,
	nascimento date NOT NULL,
	created_date timestamp NOT NULL,
	ativo boolean DEFAULT true NOT NULL,
	CONSTRAINT profissionais_pk PRIMARY KEY (id)
);

ALTER TABLE public.contatos ADD CONSTRAINT contatos_profissionais_fk FOREIGN KEY (profissional) REFERENCES public.profissionais(id) ON DELETE CASCADE;
