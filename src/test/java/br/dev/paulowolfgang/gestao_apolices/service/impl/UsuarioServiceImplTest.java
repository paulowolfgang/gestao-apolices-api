package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.UsuarioMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.UsuarioRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.UsuarioResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import br.dev.paulowolfgang.gestao_apolices.exception.UsuarioNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.infra.i18n.Messages;
import br.dev.paulowolfgang.gestao_apolices.repository.IUsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest
{
    @Mock IUsuarioRepository usuarioRepository;

    UsuarioServiceImpl service;

    private MockedStatic<Messages> mockedMessages;

    @BeforeEach
    void setUp()
    {
        service = new UsuarioServiceImpl(usuarioRepository);

        // Mock de i18n para não depender de bundles reais
        mockedMessages = mockStatic(Messages.class);
        mockedMessages.when(() -> Messages.get(anyString(), any()))
                .thenAnswer(inv -> inv.getArgument(0));
        mockedMessages.when(() -> Messages.get(anyString()))
                .thenAnswer(inv -> inv.getArgument(0));
    }

    @AfterEach
    void tearDown()
    {
        if (mockedMessages != null) mockedMessages.close();
    }

    @Test
    void atualizar_deveAtualizar_quandoExiste()
    {
        Long id = 10L;
        Usuario existente = new Usuario(); existente.setId(id);
        Usuario salvo = new Usuario(); salvo.setId(id);
        UsuarioRequestDto dto = new UsuarioRequestDto();
        UsuarioResponseDto respEsperada = new UsuarioResponseDto();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(usuarioRepository.save(existente)).thenReturn(salvo);

        try(MockedStatic<UsuarioMapper> mocked = mockStatic(UsuarioMapper.class))
        {
            mocked.when(() -> UsuarioMapper.copiarParaPropriedades(dto, existente)).thenAnswer(inv -> null);
            mocked.when(() -> UsuarioMapper.converter(salvo)).thenReturn(respEsperada);

            UsuarioResponseDto resp = service.atualizar(id, dto);

            assertThat(resp).isSameAs(respEsperada);
            verify(usuarioRepository).save(existente);
        }
    }

    @Test
    void atualizar_deveLancarExcecao_quandoNaoExiste()
    {
        Long id = 999L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.atualizar(id, new UsuarioRequestDto()))
                .isInstanceOf(UsuarioNaoEncontradoException.class);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void remover_deveExcluir_quandoExiste()
    {
        Long id = 5L;
        when(usuarioRepository.existsById(id)).thenReturn(true);

        service.remover(id);

        verify(usuarioRepository).deleteById(id);
    }

    @Test
    void remover_deveLancarExcecao_quandoNaoExiste()
    {
        Long id = 5L;
        when(usuarioRepository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> service.remover(id))
                .isInstanceOf(UsuarioNaoEncontradoException.class);
        verify(usuarioRepository, never()).deleteById(anyLong());
    }

    @Test
    void listarTodos_deveMapearLista()
    {
        Usuario u1 = new Usuario(); u1.setId(1L);
        Usuario u2 = new Usuario(); u2.setId(2L);
        when(usuarioRepository.findAll()).thenReturn(List.of(u1, u2));

        UsuarioResponseDto r1 = new UsuarioResponseDto();
        UsuarioResponseDto r2 = new UsuarioResponseDto();

        try(MockedStatic<UsuarioMapper> mocked = mockStatic(UsuarioMapper.class))
        {
            mocked.when(() -> UsuarioMapper.converter(u1)).thenReturn(r1);
            mocked.when(() -> UsuarioMapper.converter(u2)).thenReturn(r2);

            List<UsuarioResponseDto> lista = service.listarTodos();

            assertThat(lista).containsExactly(r1, r2);
        }
    }

    @Test
    void buscarPorId_deveRetornar_quandoExiste()
    {
        Long id = 7L;
        Usuario u = new Usuario(); u.setId(id);
        UsuarioResponseDto r = new UsuarioResponseDto();

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(u));

        try(MockedStatic<UsuarioMapper> mocked = mockStatic(UsuarioMapper.class))
        {
            mocked.when(() -> UsuarioMapper.converter(u)).thenReturn(r);

            UsuarioResponseDto resp = service.buscarPorId(id);

            assertThat(resp).isSameAs(r);
        }
    }

    @Test
    void buscarPorId_deveLancarExcecao_quandoNaoExiste()
    {
        Long id = 123L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(id))
                .isInstanceOf(UsuarioNaoEncontradoException.class);
    }
}
