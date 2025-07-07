package br.dev.paulowolfgang.gestao_apolices.dto.mapper;

import br.dev.paulowolfgang.gestao_apolices.dto.request.SinistroRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.SinistroResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class SinistroMapper
{
    private static final ModelMapper mapper = new ModelMapper();

    static
    {
        mapper.addMappings(new PropertyMap<SinistroRequestDto, Sinistro>()
        {
            @Override
            protected void configure()
            {
                skip(destination.getId());
                skip(destination.getApolice());
                skip(destination.getNumero());
            }
        });
    }

    public static Sinistro converter(SinistroRequestDto request)
    {
        return mapper.map(request, Sinistro.class);
    }

    public static SinistroResponseDto converter(Sinistro sinistro)
    {
        SinistroResponseDto dto = mapper.map(sinistro, SinistroResponseDto.class);
        dto.setApoliceId(sinistro.getApolice().getId());
        return dto;
    }

    public static void copiarParaPropriedades(SinistroRequestDto request, Sinistro sinistro)
    {
        mapper.map(request, sinistro);
    }
}
