package br.dev.paulowolfgang.gestao_apolices.dto.mapper;

import br.dev.paulowolfgang.gestao_apolices.dto.request.UsuarioRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.UsuarioResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import org.modelmapper.ModelMapper;

public class UsuarioMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Usuario converter(UsuarioRequestDto request){
        return mapper.map(request, Usuario.class);
    }

    public static UsuarioResponseDto converter(Usuario usuario){
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static void copiarParaPropriedades(UsuarioRequestDto request, Usuario usuario){
        mapper.map(request, usuario);
    }
}
