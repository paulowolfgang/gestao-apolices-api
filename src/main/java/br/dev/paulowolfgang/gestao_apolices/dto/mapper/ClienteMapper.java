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

public class ClienteMapper
{

    private static final ModelMapper mapper = new ModelMapper();

    public static Cliente converter(ClienteRequestDto request)
    {
        if (request instanceof ClienteFisicoRequestDto)
        {
            return mapper.map(request, ClienteFisico.class);
        }
        else if (request instanceof ClienteJuridicoRequestDto)
        {
            return mapper.map(request, ClienteJuridico.class);
        }

        throw new IllegalArgumentException("Tipo de ClienteRequestDto desconhecido.");
    }

    public static ClienteResponseDto converter(Cliente cliente)
    {
        if (cliente instanceof ClienteFisico)
        {
            return mapper.map(cliente, ClienteFisicoResponseDto.class);
        }
        else if (cliente instanceof ClienteJuridico)
        {
            return mapper.map(cliente, ClienteJuridicoResponseDto.class);
        }

        throw new IllegalArgumentException("Tipo de Cliente desconhecido.");
    }

    public static void copiarParaPropriedades(ClienteRequestDto dto, Cliente cliente)
    {
        cliente.setEmail(dto.getEmail());
        cliente.setEndereco(dto.getEndereco());
        cliente.setTelefone(dto.getTelefone());

        if (cliente instanceof ClienteFisico && dto instanceof ClienteFisicoRequestDto)
        {
            ClienteFisico clienteFisico = (ClienteFisico) cliente;
            ClienteFisicoRequestDto clienteFisicoDto = (ClienteFisicoRequestDto) dto;
            clienteFisico.setNome(clienteFisicoDto.getNome());
            clienteFisico.setCpf(clienteFisicoDto.getCpf());
            clienteFisico.setDataNascimento(clienteFisicoDto.getDataNascimento());
        }
        else if (cliente instanceof ClienteJuridico && dto instanceof ClienteJuridicoRequestDto)
        {
            ClienteJuridico clienteJuridico = (ClienteJuridico) cliente;
            ClienteJuridicoRequestDto clienteJuridicoDto = (ClienteJuridicoRequestDto) dto;
            clienteJuridico.setNomeFantasia(clienteJuridicoDto.getNomeFantasia());
            clienteJuridico.setRazaoSocial(clienteJuridicoDto.getRazaoSocial());
            clienteJuridico.setCnpj(clienteJuridicoDto.getCnpj());
            clienteJuridico.setDataAbertura(clienteJuridicoDto.getDataAbertura());
        }
        else
        {
            throw new IllegalArgumentException("Tipo de cliente incompatível para cópia de propriedades.");
        }
    }
}
