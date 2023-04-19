package com.kuparty.webflux;

import com.kuparty.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@Slf4j
public class GlobalResponseWrapper extends ResponseBodyResultHandler {

    private static final CommonResult<?> COMMON_RESULT_SUCCESS = CommonResult.success(null);
    private static final MethodParameter METHOD_PARAMETER_MONO_COMMON_RESULT;

    static {
        try {
            // 获得 METHOD_PARAMETER_MONO_COMMON_RESULT 。其中 -1 表示 `#methodForParams()` 方法的返回值
            METHOD_PARAMETER_MONO_COMMON_RESULT = new MethodParameter(
                    GlobalResponseWrapper.class.getDeclaredMethod("methodForParams"), -1);
        } catch (NoSuchMethodException e) {
            log.error("[Kuparty] [获取 METHOD_PARAMETER_MONO_COMMON_RESULT 时, 找不到方法");
            throw new RuntimeException(e);
        }
    }

    public GlobalResponseWrapper(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {
        super(writers, resolver);
    }

    public GlobalResponseWrapper(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver, ReactiveAdapterRegistry registry) {
        super(writers, resolver, registry);
    }

    private static <T> CommonResult<?> wrapCommonResult(T body) {
        // 如果已经是 CommonResult 类型，则直接返回
        if (body instanceof CommonResult) {
            return (CommonResult<?>) body;
        }
        // 如果不是，则包装成 CommonResult 类型
        return CommonResult.success(body);
    }


    private static <T> Mono<CommonResult<T>> methodForParams() {
        return null;
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {

        // 全局设置应答头 Content-Type
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Object returnValue = result.getReturnValue();
        Object body;
            // <1.1>  处理返回结果为 Mono 的情况
        if (returnValue instanceof Mono) {
            body = ((Mono<?>) result.getReturnValue())
                    .map((Function<Object, Object>) GlobalResponseWrapper::wrapCommonResult)
                    .defaultIfEmpty(COMMON_RESULT_SUCCESS);
            //  <1.2> 处理返回结果为 Flux 的情况
        } else if (returnValue instanceof Flux) {
            body = ((Flux<?>) result.getReturnValue())
                    .collectList()
                    .map((Function<Object, Object>) GlobalResponseWrapper::wrapCommonResult)
                    .defaultIfEmpty(COMMON_RESULT_SUCCESS);
            //  <1.3> 处理结果为其它类型
        } else {
            body = wrapCommonResult(returnValue);
        }
        log.debug(String.format("[Kuparty] handleResult %s", body));
        return writeBody(body, METHOD_PARAMETER_MONO_COMMON_RESULT, exchange);
    }

}
