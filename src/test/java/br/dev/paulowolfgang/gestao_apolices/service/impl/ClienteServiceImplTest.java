package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.ClienteMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteFisicoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;
import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import br.dev.paulowolfgang.gestao_apolices.exception.ClienteNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.infra.i18n.Messages;
import br.dev.paulowolfgang.gestao_apolices.repository.IClienteRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários apenas para a camada de serviço (ClienteServiceImpl).
 */
@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest
{
    @Mock
    IClienteRepository clienteRepository;

    ClienteServiceImpl service;

    // Mock estático de Messages.get(...)
    private MockedStatic<Messages> mockedMessages;

    @BeforeEach
    void setUp()
    {
        service = new ClienteServiceImpl(clienteRepository);

        // Evitar que Messages busque bundles reais: retorna só a chave formatada
        mockedMessages = mockStatic(Messages.class);
        mockedMessages.when(() -> Messages.get(anyString(), any()))
                .thenAnswer(inv -> inv.getArgument(0));
        mockedMessages.when(() -> Messages.get(anyString()))
                .thenAnswer(inv -> inv.getArgument(0));
    }

    @AfterEach
    void tearDown()
    {
        SecurityContextHolder.clearContext();
        if(mockedMessages != null) mockedMessages.close();
    }

    /** Classe fake para não depender de ClienteFisico/ClienteJuridico reais */
    private static class ClienteFake extends Cliente {}

    @Test
    void salvar_devePersistirCliente_quandoUsuarioAutenticado()
    {
        // arrange
        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        Usuario usuario = new Usuario();
        when(auth.getPrincipal()).thenReturn(usuario);

        SecurityContext sc = mock(SecurityContext.class);
        when(sc.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(sc);

        ClienteRequestDto request = new ClienteFisicoRequestDto();

        ClienteFake clienteParaSalvar = new ClienteFake();
        ClienteFake clienteSalvo = new ClienteFake();
        clienteSalvo.setId(1L);

        ClienteResponseDto responseEsperado = new ClienteResponseDto();

        // Mock estático de ClienteMapper
        try (MockedStatic<ClienteMapper> mocked = mockStatic(ClienteMapper.class))
        {
            mocked.when(() -> ClienteMapper.converter(request))
                    .thenReturn(clienteParaSalvar);
            mocked.when(() -> ClienteMapper.converter(clienteSalvo))
                    .thenReturn(responseEsperado);

            when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteSalvo);

            ArgumentCaptor<Cliente> captor = ArgumentCaptor.forClass(Cliente.class);

            // act
            ClienteResponseDto resp = service.salvar(request);

            // assert
            assertThat(resp).isSameAs(responseEsperado);
            verify(clienteRepository).save(captor.capture());
            Cliente salvoArg = captor.getValue();
            assertThat(salvoArg.getUsuario())
                    .as("usuario deve ser setado a partir do principal autenticado")
                    .isEqualTo(usuario);
        }
    }

    @Test
    void salvar_deveLancarExcecao_quandoNaoAutenticado()
    {
        // arrange: sem auth no contexto
        SecurityContextHolder.clearContext();

        ClienteRequestDto request = new ClienteFisicoRequestDto();

        // act + assert
        assertThatThrownBy(() -> service.salvar(request))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void atualizar_deveAtualizarClienteExistente()
    {
        Long id = 10L;
        ClienteFake existente = new ClienteFake();
        existente.setId(id);

        ClienteFisicoRequestDto dto = new ClienteFisicoRequestDto();

        ClienteFake salvo = new ClienteFake();
        salvo.setId(id);
        ClienteResponseDto respEsperada = new ClienteResponseDto();

        when(clienteRepository.findById(id)).thenReturn(Optional.of(existente));
        when(clienteRepository.save(existente)).thenReturn(salvo);

        try (MockedStatic<ClienteMapper> mocked = mockStatic(ClienteMapper.class))
        {
            mocked.when(() -> ClienteMapper.copiarParaPropriedades(dto, existente))
                    .thenAnswer(inv -> null);

            mocked.when(() -> ClienteMapper.converter(salvo))
                    .thenReturn(respEsperada);

            ClienteResponseDto resp = service.atualizar(id, dto);

            assertThat(resp).isSameAs(respEsperada);
            verify(clienteRepository).save(existente);
        }
    }

    @Test
    void atualizar_deveLancarExcecao_quandoNaoEncontrado()
    {
        Long id = 999L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.atualizar(id, new ClienteFisicoRequestDto()))
                .isInstanceOf(ClienteNaoEncontradoException.class);
    }

    @Test
    void remover_deveExcluir_quandoExiste()
    {
        Long id = 5L;
        when(clienteRepository.existsById(id)).thenReturn(true);

        service.remover(id);

        verify(clienteRepository).deleteById(id);
    }

    @Test
    void remover_deveLancarExcecao_quandoNaoExiste()
    {
        Long id = 5L;
        when(clienteRepository.existsById(id)).thenReturn(false);

        assertThatThrownBy(() -> service.remover(id))
                .isInstanceOf(ClienteNaoEncontradoException.class);
        verify(clienteRepository, never()).deleteById(anyLong());
    }

    @Test
    void listarTodos_deveMapearLista()
    {
        ClienteFake c1 = new ClienteFake(); c1.setId(1L);
        ClienteFake c2 = new ClienteFake(); c2.setId(2L);

        when(clienteRepository.findAll()).thenReturn(List.of(c1, c2));

        ClienteResponseDto r1 = new ClienteResponseDto();
        ClienteResponseDto r2 = new ClienteResponseDto();

        try(MockedStatic<ClienteMapper> mocked = mockStatic(ClienteMapper.class))
        {
            mocked.when(() -> ClienteMapper.converter(c1)).thenReturn(r1);
            mocked.when(() -> ClienteMapper.converter(c2)).thenReturn(r2);

            List<ClienteResponseDto> lista = service.listarTodos();

            assertThat(lista).containsExactly(r1, r2);
        }
    }

    @Test
    void buscarPorId_deveRetornar_quandoExiste()
    {
        Long id = 7L;
        ClienteFake c = new ClienteFake(); c.setId(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(c));

        ClienteResponseDto r = new ClienteResponseDto();

        try(MockedStatic<ClienteMapper> mocked = mockStatic(ClienteMapper.class))
        {
            mocked.when(() -> ClienteMapper.converter(c)).thenReturn(r);

            ClienteResponseDto resp = service.buscarPorId(id);

            assertThat(resp).isSameAs(r);
        }
    }

    @Test
    void buscarPorId_deveLancarExcecao_quandoNaoEncontrado()
    {
        Long id = 123L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(id))
                .isInstanceOf(ClienteNaoEncontradoException.class);
    }
}
