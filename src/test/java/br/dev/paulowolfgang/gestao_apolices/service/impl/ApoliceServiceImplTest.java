package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.ApoliceMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;
import br.dev.paulowolfgang.gestao_apolices.exception.ApoliceNaoEncontradaException;
import br.dev.paulowolfgang.gestao_apolices.exception.ClienteNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.infra.i18n.Messages;
import br.dev.paulowolfgang.gestao_apolices.repository.IApoliceRepository;
import br.dev.paulowolfgang.gestao_apolices.repository.IClienteRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApoliceServiceImplTest
{
    @Mock IApoliceRepository apoliceRepository;
    @Mock IClienteRepository clienteRepository;

    ApoliceServiceImpl service;

    // Mock estático de Messages para não depender de bundles reais
    private MockedStatic<Messages> mockedMessages;

    @BeforeEach
    void setUp()
    {
        service = new ApoliceServiceImpl(apoliceRepository, clienteRepository);

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

    /** Cliente fake, pois Cliente é abstract no domínio */
    private static class ClienteFake extends Cliente
    {
        public ClienteFake(Long id) { setId(id); }
    }

    @Test
    void salvar_devePersistir_quandoClienteExiste()
    {
        // arrange
        ApoliceRequestDto req = new ApoliceRequestDto();
        req.setClienteId(42L);

        ClienteFake cliente = new ClienteFake(42L);

        Apolice apoliceParaSalvar = new Apolice();
        Apolice apoliceSalva = new Apolice();
        apoliceSalva.setId(10L);

        ApoliceResponseDto respostaEsperada = new ApoliceResponseDto();

        when(clienteRepository.findById(42L)).thenReturn(Optional.of(cliente));
        when(apoliceRepository.save(any(Apolice.class))).thenReturn(apoliceSalva);

        try(MockedStatic<ApoliceMapper> mocked = mockStatic(ApoliceMapper.class))
        {
            mocked.when(() -> ApoliceMapper.converter(req)).thenReturn(apoliceParaSalvar);
            mocked.when(() -> ApoliceMapper.converter(apoliceSalva)).thenReturn(respostaEsperada);

            ArgumentCaptor<Apolice> captor = ArgumentCaptor.forClass(Apolice.class);

            // act
            ApoliceResponseDto resp = service.salvar(req);

            // assert
            assertThat(resp).isSameAs(respostaEsperada);
            verify(apoliceRepository).save(captor.capture());
            Apolice capturada = captor.getValue();
            assertThat(capturada.getCliente()).isEqualTo(cliente);
        }
    }

    @Test
    void salvar_deveLancarExcecao_quandoClienteNaoExiste()
    {
        ApoliceRequestDto req = new ApoliceRequestDto();
        req.setClienteId(99L);
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.salvar(req))
                .isInstanceOf(ClienteNaoEncontradoException.class);
        verify(apoliceRepository, never()).save(any());
    }

    @Test
    void atualizar_deveAtualizar_quandoNumeroExiste()
    {
        String numero = "APL-123";
        Apolice existente = new Apolice(); existente.setId(7L); existente.setNumero(numero);
        ApoliceRequestDto dto = new ApoliceRequestDto();
        Apolice salvo = new Apolice(); salvo.setId(7L); salvo.setNumero(numero);
        ApoliceResponseDto respEsperada = new ApoliceResponseDto();

        when(apoliceRepository.findByNumero(numero)).thenReturn(Optional.of(existente));
        when(apoliceRepository.save(existente)).thenReturn(salvo);

        try(MockedStatic<ApoliceMapper> mocked = mockStatic(ApoliceMapper.class))
        {
            mocked.when(() -> ApoliceMapper.copiarParaPropriedades(dto, existente)).thenAnswer(inv -> null);
            mocked.when(() -> ApoliceMapper.converter(salvo)).thenReturn(respEsperada);

            ApoliceResponseDto resp = service.atualizar(numero, dto);

            assertThat(resp).isSameAs(respEsperada);
            verify(apoliceRepository).save(existente);
        }
    }

    @Test
    void atualizar_deveLancarExcecao_quandoNumeroNaoExiste()
    {
        when(apoliceRepository.findByNumero("APL-X")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.atualizar("APL-X", new ApoliceRequestDto()))
                .isInstanceOf(ApoliceNaoEncontradaException.class);
    }

    @Test
    void remover_deveExcluir_quandoNumeroExiste()
    {
        String numero = "APL-XYZ";
        Apolice ap = new Apolice(); ap.setId(55L); ap.setNumero(numero);

        when(apoliceRepository.findByNumero(numero)).thenReturn(Optional.of(ap));

        service.remover(numero);

        verify(apoliceRepository).deleteById(55L);
    }

    @Test
    void remover_deveLancarExcecao_quandoNumeroNaoExiste()
    {
        when(apoliceRepository.findByNumero("APL-404")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.remover("APL-404"))
                .isInstanceOf(ApoliceNaoEncontradaException.class);
        verify(apoliceRepository, never()).deleteById(anyLong());
    }

    @Test
    void listarTodos_deveMapearLista()
    {
        Apolice a1 = new Apolice(); a1.setId(1L);
        Apolice a2 = new Apolice(); a2.setId(2L);
        when(apoliceRepository.findAll()).thenReturn(List.of(a1, a2));

        ApoliceResponseDto r1 = new ApoliceResponseDto();
        ApoliceResponseDto r2 = new ApoliceResponseDto();

        try(MockedStatic<ApoliceMapper> mocked = mockStatic(ApoliceMapper.class))
        {
            mocked.when(() -> ApoliceMapper.converter(a1)).thenReturn(r1);
            mocked.when(() -> ApoliceMapper.converter(a2)).thenReturn(r2);

            List<ApoliceResponseDto> lista = service.listarTodos();
            assertThat(lista).containsExactly(r1, r2);
        }
    }

    @Test
    void buscarPorNumero_deveRetornar_quandoExiste()
    {
        String numero = "APL-OK";
        Apolice a = new Apolice(); a.setId(9L); a.setNumero(numero);
        ApoliceResponseDto r = new ApoliceResponseDto();

        when(apoliceRepository.findByNumero(numero)).thenReturn(Optional.of(a));

        try(MockedStatic<ApoliceMapper> mocked = mockStatic(ApoliceMapper.class))
        {
            mocked.when(() -> ApoliceMapper.converter(a)).thenReturn(r);

            ApoliceResponseDto resp = service.buscarPorNumero(numero);
            assertThat(resp).isSameAs(r);
        }
    }

    @Test
    void buscarPorNumero_deveLancarExcecao_quandoNaoExiste()
    {
        when(apoliceRepository.findByNumero("APL-NAO")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.buscarPorNumero("APL-NAO"))
                .isInstanceOf(ApoliceNaoEncontradaException.class);
    }
}
