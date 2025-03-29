package br.dev.paulowolfgang.gestao_apolices.dto.mapper;

import br.dev.paulowolfgang.gestao_apolices.dto.request.PagamentoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.PagamentoResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class PagamentoMapper
{

    private static final ModelMapper mapper = new ModelMapper();

    static
    {
        mapper.addMappings(new PropertyMap<PagamentoRequestDto, Pagamento>()
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

    public static Pagamento converter(PagamentoRequestDto request)
    {
        return mapper.map(request, Pagamento.class);
    }

    public static PagamentoResponseDto converter(Pagamento pagamento)
    {
        PagamentoResponseDto dto = mapper.map(pagamento, PagamentoResponseDto.class);
        dto.setApoliceId(pagamento.getApolice().getId());
        return dto;
    }

    public static void copiarParaPropriedades(PagamentoRequestDto request, Pagamento pagamento)
    {
        mapper.map(request, pagamento);
    }
}
