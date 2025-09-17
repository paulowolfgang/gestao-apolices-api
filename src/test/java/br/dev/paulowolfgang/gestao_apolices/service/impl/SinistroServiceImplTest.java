package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.SinistroMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.SinistroRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.SinistroResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;
import br.dev.paulowolfgang.gestao_apolices.exception.ApoliceNaoEncontradaException;
import br.dev.paulowolfgang.gestao_apolices.exception.SinistroNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.infra.i18n.Messages;
import br.dev.paulowolfgang.gestao_apolices.repository.IApoliceRepository;
import br.dev.paulowolfgang.gestao_apolices.repository.ISinistroRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SinistroServiceImplTest
{
    @Mock ISinistroRepository sinistroRepository;
    @Mock IApoliceRepository apoliceRepository;

    SinistroServiceImpl service;

    private MockedStatic<Messages> mockedMessages;

    @BeforeEach
    void setUp()
    {
        service = new SinistroServiceImpl(sinistroRepository, apoliceRepository);

        // Evita dependência de bundles reais no Messages.get(...)
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
    void salvar_devePersistir_quandoApoliceExiste()
    {
        // arrange
        SinistroRequestDto req = new SinistroRequestDto();
        req.setApoliceNumero("APL-123");

        Apolice ap = new Apolice();
        ap.setId(11L);
        ap.setNumero("APL-123");

        Sinistro sParaSalvar = new Sinistro();
        Sinistro sSalvo = new Sinistro(); sSalvo.setId(7L);

        SinistroResponseDto respEsperada = new SinistroResponseDto();

        when(apoliceRepository.findByNumero("APL-123")).thenReturn(Optional.of(ap));
        when(sinistroRepository.save(any(Sinistro.class))).thenReturn(sSalvo);

        try(MockedStatic<SinistroMapper> mocked = mockStatic(SinistroMapper.class))
        {
            mocked.when(() -> SinistroMapper.converter(req)).thenReturn(sParaSalvar);
            mocked.when(() -> SinistroMapper.converter(sSalvo)).thenReturn(respEsperada);

            ArgumentCaptor<Sinistro> captor = ArgumentCaptor.forClass(Sinistro.class);

            // act
            SinistroResponseDto resp = service.salvar(req);

            // assert
            assertThat(resp).isSameAs(respEsperada);
            verify(sinistroRepository).save(captor.capture());
            Sinistro capturado = captor.getValue();
            assertThat(capturado.getApolice())
                    .as("apólice deve ser associada ao sinistro antes do save")
                    .isEqualTo(ap);
        }
    }

    @Test
    void salvar_deveLancarExcecao_quandoApoliceNaoExiste()
    {
        SinistroRequestDto req = new SinistroRequestDto();
        req.setApoliceNumero("APL-404");

        when(apoliceRepository.findByNumero("APL-404")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.salvar(req))
                .isInstanceOf(ApoliceNaoEncontradaException.class);
        verify(sinistroRepository, never()).save(any());
    }

    @Test
    void atualizar_deveAtualizar_quandoNumeroExiste()
    {
        String numero = "SNT-001";
        Sinistro existente = new Sinistro(); existente.setId(5L); existente.setNumero(numero);
        SinistroRequestDto dto = new SinistroRequestDto();

        Sinistro salvo = new Sinistro(); salvo.setId(5L); salvo.setNumero(numero);
        SinistroResponseDto respEsperada = new SinistroResponseDto();

        when(sinistroRepository.findByNumero(numero)).thenReturn(Optional.of(existente));
        when(sinistroRepository.save(existente)).thenReturn(salvo);

        try(MockedStatic<SinistroMapper> mocked = mockStatic(SinistroMapper.class))
        {
            mocked.when(() -> SinistroMapper.copiarParaPropriedades(dto, existente)).thenAnswer(inv -> null);
            mocked.when(() -> SinistroMapper.converter(salvo)).thenReturn(respEsperada);

            SinistroResponseDto resp = service.atualizar(numero, dto);

            assertThat(resp).isSameAs(respEsperada);
            verify(sinistroRepository).save(existente);
        }
    }

    @Test
    void atualizar_deveLancarExcecao_quandoNumeroNaoExiste()
    {
        when(sinistroRepository.findByNumero("SNT-404")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.atualizar("SNT-404", new SinistroRequestDto()))
                .isInstanceOf(SinistroNaoEncontradoException.class);
    }

    @Test
    void remover_deveExcluir_quandoNumeroExiste()
    {
        String numero = "SNT-DEL";
        Sinistro s = new Sinistro(); s.setId(99L); s.setNumero(numero);
        when(sinistroRepository.findByNumero(numero)).thenReturn(Optional.of(s));

        service.remover(numero);

        verify(sinistroRepository).deleteById(99L);
    }

    @Test
    void remover_deveLancarExcecao_quandoNumeroNaoExiste()
    {
        when(sinistroRepository.findByNumero("SNT-NAO")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.remover("SNT-NAO"))
                .isInstanceOf(SinistroNaoEncontradoException.class);
        verify(sinistroRepository, never()).deleteById(anyLong());
    }

    @Test
    void listarTodos_deveMapearLista()
    {
        Sinistro s1 = new Sinistro(); s1.setId(1L);
        Sinistro s2 = new Sinistro(); s2.setId(2L);

        when(sinistroRepository.findAll()).thenReturn(List.of(s1, s2));

        SinistroResponseDto r1 = new SinistroResponseDto();
        SinistroResponseDto r2 = new SinistroResponseDto();

        try(MockedStatic<SinistroMapper> mocked = mockStatic(SinistroMapper.class))
        {
            mocked.when(() -> SinistroMapper.converter(s1)).thenReturn(r1);
            mocked.when(() -> SinistroMapper.converter(s2)).thenReturn(r2);

            List<SinistroResponseDto> lista = service.listarTodos();

            assertThat(lista).containsExactly(r1, r2);
        }
    }

    @Test
    void buscarPorNumero_deveRetornar_quandoExiste()
    {
        String numero = "SNT-OK";
        Sinistro s = new Sinistro(); s.setId(7L); s.setNumero(numero);
        SinistroResponseDto r = new SinistroResponseDto();

        when(sinistroRepository.findByNumero(numero)).thenReturn(Optional.of(s));

        try(MockedStatic<SinistroMapper> mocked = mockStatic(SinistroMapper.class))
        {
            mocked.when(() -> SinistroMapper.converter(s)).thenReturn(r);

            SinistroResponseDto resp = service.buscarPorNumero(numero);
            assertThat(resp).isSameAs(r);
        }
    }

    @Test
    void buscarPorNumero_deveLancarExcecao_quandoNaoExiste()
    {
        when(sinistroRepository.findByNumero("SNT-404")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorNumero("SNT-404"))
                .isInstanceOf(SinistroNaoEncontradoException.class);
    }
}
