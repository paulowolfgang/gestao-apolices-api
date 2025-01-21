package br.dev.paulowolfgang.gestao_apolices.dto.mapper;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import org.modelmapper.ModelMapper;

public class ApoliceMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Apolice converter(ApoliceRequestDto request){
        return mapper.map(request, Apolice.class);
    }

    public static ApoliceResponseDto converter(Apolice apolice){
        return mapper.map(apolice, ApoliceResponseDto.class);
    }

    public static void copiarParaPropriedades(ApoliceRequestDto request, Apolice apolice){
        mapper.map(request, apolice);
    }
}
