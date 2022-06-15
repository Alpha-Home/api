package br.senac.go.api.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "email", nullable = false, length = 50)
    private String email;


    @ManyToOne()
    @JoinColumn(name = "web_user_id", nullable = false)
    @ToString.Exclude
    private WebUser webUser;

    @ManyToOne()
    @JoinColumn(name = "endereco_id", nullable = false)
    @ToString.Exclude
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Pedido> pedidos = new LinkedHashSet<>();

}