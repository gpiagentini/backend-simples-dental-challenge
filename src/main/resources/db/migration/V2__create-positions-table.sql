CREATE TABLE public.cargos (
	id serial NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT cargos_pk PRIMARY KEY (id)
);

ALTER TABLE public.profissionais ALTER COLUMN cargo TYPE int USING cargo::int;
ALTER TABLE public.profissionais ADD CONSTRAINT profissionais_cargos_fk FOREIGN KEY (cargo) REFERENCES public.cargos(id);

INSERT INTO public.cargos (nome) VALUES ('Desenvolvedor');
INSERT INTO public.cargos (nome) VALUES ('Designer');
INSERT INTO public.cargos (nome) VALUES ('Suporte');
INSERT INTO public.cargos (nome) VALUES ('Tester');