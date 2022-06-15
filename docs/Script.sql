CREATE TABLE categoria (
	id 				SERIAL			NOT NULL	PRIMARY KEY,
	nome			VARCHAR(50)		NOT NULL
);

CREATE TABLE produto (
	id 				SERIAL			NOT NULL	PRIMARY	KEY,
	nome			VARCHAR(50)		NOT NULL,
	preco			DECIMAL			NOT NULL,
	categoria_id	INTEGER			NOT NULL,
	CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (id)
);

CREATE TABLE  itens_pedido (
	id				SERIAL			NOT NULL 	PRIMARY KEY,
	quantidade		INTEGER			NOT NULL,
	valor_unitario	DECIMAL			NOT NULL,
	produto_id		INTEGER			NOT NULL,
	CONSTRAINT fk_itens_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto (id)
);

CREATE TABLE endereco (
	id				SERIAL			NOT NULL	PRIMARY KEY,
	logradouro		VARCHAR(50)		NOT NULL,
	numero			VARCHAR(10)		NOT NULL,	
	complemento	VARCHAR(50)		NOT NULL,
	bairro			VARCHAR(50)		NOT NULL,
	cidade			VARCHAR(50)		NOT NULL,
	uf				VARCHAR(2)		NOT NULL,
	cep			VARCHAR(9)		NOT NULL
);


CREATE TABLE web_user (
	id				SERIAL			NOT NULL	PRIMARY KEY,
	login			VARCHAR(50)		NOT NULL,
	password	VARCHAR(50)		NOT NULL,
	estado		VARCHAR(20)
);

CREATE TABLE cliente (
	id				SERIAL			NOT NULL 	PRIMARY KEY,
	nome			VARCHAR(50)		NOT NULL,
	cpf				VARCHAR(11)	 	NOT NULL,
	telefone		VARCHAR(11)		NOT NULL,
	email			VARCHAR(50)		NOT NULL,
	web_user_id		INTEGER			NOT NULL,
	endereco_id		INTEGER			NOT NULL,
	CONSTRAINT fk_cliente_web_user FOREIGN KEY (web_user_id) REFERENCES web_user (id),
	CONSTRAINT fk_cliente_endereco FOREIGN KEY (endereco_id) REFERENCES endereco (id)
);	
	
CREATE TABLE  pedido (
	id				SERIAL			NOT NULL 	PRIMARY KEY,
	quantidade		INTEGER			NOT NULL,
	total			DECIMAL			NOT NULL,
	observacao		VARCHAR(200),
	itens_pedido_id		INTEGER	NOT NULL,
	cliente_id INTEGER NOT NULL,
	CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id),
	CONSTRAINT fk_pedido_itens_pedido FOREIGN KEY (itens_pedido_id) REFERENCES itens_pedido (id)
);
