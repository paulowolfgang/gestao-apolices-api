package br.dev.paulowolfgang.gestao_apolices.dto.mapper;

import br.dev.paulowolfgang.gestao_apolices.dto.request.PagamentoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.PagamentoResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;
import org.modelmapper.ModelMapper;

public class PagamentoMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Pagamento converter(PagamentoRequestDto request) {
        return mapper.map(request, Pagamento.class);
    }

    public static PagamentoResponseDto converter(Pagamento pagamento){
        return mapper.map(pagamento, PagamentoResponseDto.class);
    }

    public static void copiarParaPropriedades(PagamentoRequestDto request, Pagamento pagamento){
        mapper.map(request, pagamento);
    }
}
