package br.dev.paulowolfgang.gestao_apolices.dto.mapper;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteFisicoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteJuridicoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteFisicoResponseDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteJuridicoResponseDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;
import br.dev.paulowolfgang.gestao_apolices.entity.ClienteFisico;
import br.dev.paulowolfgang.gestao_apolices.entity.ClienteJuridico;
import org.modelmapper.ModelMapper;

public class ClienteMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Cliente converter(ClienteRequestDto request) {
        if (request instanceof ClienteFisicoRequestDto) {
            return mapper.map(request, ClienteFisico.class);
        } else if (request instanceof ClienteJuridicoRequestDto) {
            return mapper.map(request, ClienteJuridico.class);
        }
        throw new IllegalArgumentException("Tipo de ClienteRequestDto desconhecido.");
    }

    public static ClienteResponseDto converter(Cliente cliente) {
        if (cliente instanceof ClienteFisico) {
            return mapper.map(cliente, ClienteFisicoResponseDto.class);
        } else if (cliente instanceof ClienteJuridico) {
            return mapper.map(cliente, ClienteJuridicoResponseDto.class);
        }
        throw new IllegalArgumentException("Tipo de Cliente desconhecido.");
    }

    public static void copiarParaPropriedades(ClienteRequestDto request, Cliente cliente) {
        mapper.map(request, cliente);
    }
}
