package br.dev.paulowolfgang.gestao_apolices.infra.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Messages
{
    private static MessageSource messageSource;

    public Messages(MessageSource messageSource)
    {
        Messages.messageSource = messageSource;
    }

    public static String get(String code, Object... args)
    {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
