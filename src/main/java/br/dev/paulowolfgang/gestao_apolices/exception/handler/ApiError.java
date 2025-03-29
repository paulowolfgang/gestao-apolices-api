package br.dev.paulowolfgang.gestao_apolices.exception.handler;

public class ApiError
{
    private Long timestamp;
    private Integer statusCode;
    private String error;

    public ApiError(Long timestamp, Integer statusCode, String error)
    {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.error = error;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    public Integer getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
}
