package br.dev.paulowolfgang.gestao_apolices.dto.mapper;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ApoliceMapper {

    private static final ModelMapper mapper = new ModelMapper();

    static {
        // Configura o mapeamento de ApoliceRequestDto para Apolice
        mapper.addMappings(new PropertyMap<ApoliceRequestDto, Apolice>() {
            @Override
            protected void configure() {
                // Ignora o mapeamento do ID e do cliente
                skip(destination.getId());
                skip(destination.getCliente());
            }
        });
    }

    public static Apolice converter(ApoliceRequestDto request) {
        return mapper.map(request, Apolice.class);
    }

    public static ApoliceResponseDto converter(Apolice apolice) {
        ApoliceResponseDto dto = mapper.map(apolice, ApoliceResponseDto.class);
        dto.setClienteId(apolice.getCliente().getId());
        return dto;
    }

    public static void copiarParaPropriedades(ApoliceRequestDto request, Apolice apolice) {
        mapper.map(request, apolice);
    }
}

//public class ApoliceMapper {
//
//    private static final ModelMapper mapper = new ModelMapper();
//
//    public static Apolice converter(ApoliceRequestDto request){
//        return mapper.map(request, Apolice.class);
//    }
//
//    public static ApoliceResponseDto converter(Apolice apolice){
//        ApoliceResponseDto dto = mapper.map(apolice, ApoliceResponseDto.class);
//        dto.setClienteId(apolice.getCliente().getId());
//        return dto;
//    }
//
//    public static void copiarParaPropriedades(ApoliceRequestDto request, Apolice apolice){
//        mapper.map(request, apolice);
//    }
//}
