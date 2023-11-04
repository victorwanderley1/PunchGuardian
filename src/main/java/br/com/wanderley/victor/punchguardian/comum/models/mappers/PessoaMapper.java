package br.com.wanderley.victor.punchguardian.comum.models.mappers;

import br.com.wanderley.victor.punchguardian.comum.models.dtos.PessoaDTO;
import br.com.wanderley.victor.punchguardian.comum.models.entities.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static PessoaDTO toDTO(Pessoa pessoa) {
        return MODEL_MAPPER.map(pessoa, PessoaDTO.class);
    }

    public static Pessoa toEntity(PessoaDTO dto){
        return MODEL_MAPPER.map(dto, Pessoa.class);
    }

    public static List<PessoaDTO> toPessoaDTOList(List<Pessoa> pessoas){
        return pessoas.stream().map(PessoaMapper::toDTO).toList();
    }

    public static List<Pessoa> toPessoaList(List<PessoaDTO> pessoasDto){
        return pessoasDto.stream().map(PessoaMapper::toEntity).toList();
    }
}
