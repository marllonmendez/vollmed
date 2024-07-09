package com.marllonmendez.voll.domain.Paciente.entity;

import com.marllonmendez.voll.domain.Endereco.entity.Endereco;
import com.marllonmendez.voll.domain.Paciente.dto.DadosAtualizacaoPacienteDTO;
import com.marllonmendez.voll.domain.Paciente.dto.DadosCadastroPacienteDTO;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "pacientes")
@Entity(name = "Pacientes")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean disponivel;

    public Paciente(DadosCadastroPacienteDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.cpf = dto.cpf();
        this.endereco = new Endereco(dto.endereco());
        this.disponivel = true;
    }

    public void atualizarPaciente(DadosAtualizacaoPacienteDTO dto) {
        this.nome = dto.nome() != null ? dto.nome() : this.nome;
        this.telefone = dto.telefone() != null ? dto.telefone() : this.telefone;

        if (dto.endereco() != null) {
            this.endereco.atualizarEndereco(dto.endereco());
        }

    }

    public void remover() {
        this.disponivel = false;
    }

}
