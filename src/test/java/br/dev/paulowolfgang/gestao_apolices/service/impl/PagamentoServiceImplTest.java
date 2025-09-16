package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.PagamentoMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.PagamentoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.PagamentoResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;
import br.dev.paulowolfgang.gestao_apolices.exception.ApoliceNaoEncontradaException;
import br.dev.paulowolfgang.gestao_apolices.exception.PagamentoNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.infra.i18n.Messages;
import br.dev.paulowolfgang.gestao_apolices.repository.IApoliceRepository;
import br.dev.paulowolfgang.gestao_apolices.repository.IPagamentoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceImplTest
{
    @Mock IPagamentoRepository pagamentoRepository;
    @Mock IApoliceRepository apoliceRepository;

    PagamentoServiceImpl service;

    private MockedStatic<Messages> mockedMessages;

    @BeforeEach
    void setUp()
    {
        service = new PagamentoServiceImpl(pagamentoRepository, apoliceRepository);

        // Evita dependência de bundles reais
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

    /** Apólice fake apenas para testes */
    private static class ApoliceFake extends Apolice
    {
        public ApoliceFake(Long id, String numero)
        {
            setId(id);
            setNumero(numero);
        }
    }

    @Test
    void salvar_devePersistir_quandoApoliceExiste()
    {
        // arrange
        PagamentoRequestDto req = new PagamentoRequestDto();
        req.setApoliceNumero("APL-123");

        ApoliceFake apolice = new ApoliceFake(77L, "APL-123");

        Pagamento pagar = new Pagamento();
        Pagamento salvo = new Pagamento(); salvo.setId(10L);

        PagamentoResponseDto respEsperada = new PagamentoResponseDto();

        when(apoliceRepository.findByNumero("APL-123"))
                .thenReturn(Optional.of(apolice));
        when(pagamentoRepository.save(any(Pagamento.class)))
                .thenReturn(salvo);

        try(MockedStatic<PagamentoMapper> mocked = mockStatic(PagamentoMapper.class))
        {
            mocked.when(() -> PagamentoMapper.converter(req)).thenReturn(pagar);
            mocked.when(() -> PagamentoMapper.converter(salvo)).thenReturn(respEsperada);

            ArgumentCaptor<Pagamento> captor = ArgumentCaptor.forClass(Pagamento.class);

            // act
            PagamentoResponseDto resp = service.salvar(req);

            // assert
            assertThat(resp).isSameAs(respEsperada);
            verify(pagamentoRepository).save(captor.capture());
            Pagamento capturado = captor.getValue();
            assertThat(capturado.getApolice())
                    .as("apólice deve ser setada no pagamento antes do save")
                    .isEqualTo(apolice);
        }
    }

    @Test
    void salvar_deveLancarExcecao_quandoApoliceNaoExiste()
    {
        PagamentoRequestDto req = new PagamentoRequestDto();
        req.setApoliceNumero("APL-404");
        when(apoliceRepository.findByNumero("APL-404")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.salvar(req))
                .isInstanceOf(ApoliceNaoEncontradaException.class);
        verify(pagamentoRepository, never()).save(any());
    }

    @Test
    void atualizar_deveAtualizar_quandoNumeroExiste()
    {
        String numero = "PGT-001";
        Pagamento existente = new Pagamento(); existente.setId(5L); existente.setNumero(numero);
        PagamentoRequestDto dto = new PagamentoRequestDto();
        Pagamento salvo = new Pagamento(); salvo.setId(5L); salvo.setNumero(numero);
        PagamentoResponseDto respEsperada = new PagamentoResponseDto();

        when(pagamentoRepository.findByNumero(numero))
                .thenReturn(Optional.of(existente));
        when(pagamentoRepository.save(existente))
                .thenReturn(salvo);

        try(MockedStatic<PagamentoMapper> mocked = mockStatic(PagamentoMapper.class))
        {
            mocked.when(() -> PagamentoMapper.copiarParaPropriedades(dto, existente)).thenAnswer(inv -> null);
            mocked.when(() -> PagamentoMapper.converter(salvo)).thenReturn(respEsperada);

            PagamentoResponseDto resp = service.atualizar(numero, dto);

            assertThat(resp).isSameAs(respEsperada);
            verify(pagamentoRepository).save(existente);
        }
    }

    @Test
    void atualizar_deveLancarExcecao_quandoNumeroNaoExiste()
    {
        when(pagamentoRepository.findByNumero("PGT-404")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.atualizar("PGT-404", new PagamentoRequestDto()))
                .isInstanceOf(PagamentoNaoEncontradoException.class);
    }

    @Test
    void remover_deveExcluir_quandoNumeroExiste()
    {
        String numero = "PGT-DEL";
        Pagamento p = new Pagamento(); p.setId(99L); p.setNumero(numero);
        when(pagamentoRepository.findByNumero(numero)).thenReturn(Optional.of(p));

        service.remover(numero);

        verify(pagamentoRepository).deleteById(99L);
    }

    @Test
    void remover_deveLancarExcecao_quandoNumeroNaoExiste()
    {
        when(pagamentoRepository.findByNumero("PGT-NAO")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.remover("PGT-NAO"))
                .isInstanceOf(PagamentoNaoEncontradoException.class);
        verify(pagamentoRepository, never()).deleteById(anyLong());
    }

    @Test
    void listarTodos_deveMapearLista()
    {
        Pagamento p1 = new Pagamento(); p1.setId(1L);
        Pagamento p2 = new Pagamento(); p2.setId(2L);
        when(pagamentoRepository.findAll()).thenReturn(List.of(p1, p2));

        PagamentoResponseDto r1 = new PagamentoResponseDto();
        PagamentoResponseDto r2 = new PagamentoResponseDto();

        try(MockedStatic<PagamentoMapper> mocked = mockStatic(PagamentoMapper.class))
        {
            mocked.when(() -> PagamentoMapper.converter(p1)).thenReturn(r1);
            mocked.when(() -> PagamentoMapper.converter(p2)).thenReturn(r2);

            List<PagamentoResponseDto> lista = service.listarTodos();

            assertThat(lista).containsExactly(r1, r2);
        }
    }

    @Test
    void buscarPorNumero_deveRetornar_quandoExiste()
    {
        String numero = "PGT-OK";
        Pagamento p = new Pagamento(); p.setId(7L); p.setNumero(numero);
        PagamentoResponseDto r = new PagamentoResponseDto();

        when(pagamentoRepository.findByNumero(numero)).thenReturn(Optional.of(p));

        try(MockedStatic<PagamentoMapper> mocked = mockStatic(PagamentoMapper.class))
        {
            mocked.when(() -> PagamentoMapper.converter(p)).thenReturn(r);

            PagamentoResponseDto resp = service.buscarPorNumero(numero);

            assertThat(resp).isSameAs(r);
        }
    }

    @Test
    void buscarPorNumero_deveLancarExcecao_quandoNaoExiste()
    {
        when(pagamentoRepository.findByNumero("PGT-404")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorNumero("PGT-404"))
                .isInstanceOf(PagamentoNaoEncontradoException.class);
    }
}
