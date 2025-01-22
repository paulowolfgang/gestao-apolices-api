package br.dev.paulowolfgang.gestao_apolices.dto.mapper;

import br.dev.paulowolfgang.gestao_apolices.dto.request.SinistroRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.SinistroResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;
import org.modelmapper.ModelMapper;

public class SinistroMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Sinistro converter(SinistroRequestDto request){
        return mapper.map(request, Sinistro.class);
    }

    public static SinistroResponseDto converter(Sinistro sinistro){
        return mapper.map(sinistro, SinistroResponseDto.class);
    }

    public static void copiarParaPropriedades(SinistroRequestDto request, Sinistro sinistro){
        mapper.map(request, sinistro);
    }
}
