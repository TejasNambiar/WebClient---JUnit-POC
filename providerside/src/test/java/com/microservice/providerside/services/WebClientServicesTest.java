package com.microservice.providerside.services;

import com.microservice.providerside.model.dto.CustomersDTO;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WebClientServices.class})
@ExtendWith(SpringExtension.class)
class WebClientServicesTest {
    @Autowired
    private WebClientServices webClientServices;

    /**
     * Method under test: {@link WebClientServices#getCustomerListViaProvider(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCustomerListViaProvider() {
        // TODO: Complete this test.
        //   Reason: R006 Static initializer failed.
        //   The static initializer of
        //   io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess
        //   threw java.lang.RuntimeException while trying to load it.
        //   Make sure the static initializer of UnsafeAccess
        //   can be executed without throwing exceptions.
        //   Exception: java.lang.RuntimeException: com.diffblue.cover.sandbox.execution.ForbiddenByPolicyException: Sandboxing policy violation. Reason: to access 'sun.misc'
        //       at io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess.getUnsafe(UnsafeAccess.java:71)
        //       at io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess.<clinit>(UnsafeAccess.java:44)
        //       at io.netty.util.internal.PlatformDependent$Mpsc$1.run(PlatformDependent.java:1001)
        //       at java.security.AccessController.doPrivileged(Native Method)
        //       at io.netty.util.internal.PlatformDependent$Mpsc.<clinit>(PlatformDependent.java:997)
        //       at io.netty.util.internal.PlatformDependent.newMpscQueue(PlatformDependent.java:1040)
        //       at io.netty.channel.nio.NioEventLoop.newTaskQueue0(NioEventLoop.java:282)
        //       at io.netty.channel.nio.NioEventLoop.newTaskQueue(NioEventLoop.java:153)
        //       at io.netty.channel.nio.NioEventLoop.<init>(NioEventLoop.java:141)
        //       at io.netty.channel.nio.NioEventLoopGroup.newChild(NioEventLoopGroup.java:183)
        //       at io.netty.channel.nio.NioEventLoopGroup.newChild(NioEventLoopGroup.java:38)
        //       at io.netty.util.concurrent.MultithreadEventExecutorGroup.<init>(MultithreadEventExecutorGroup.java:84)
        //       at io.netty.util.concurrent.MultithreadEventExecutorGroup.<init>(MultithreadEventExecutorGroup.java:60)
        //       at io.netty.util.concurrent.MultithreadEventExecutorGroup.<init>(MultithreadEventExecutorGroup.java:49)
        //       at io.netty.channel.MultithreadEventLoopGroup.<init>(MultithreadEventLoopGroup.java:59)
        //       at io.netty.channel.nio.NioEventLoopGroup.<init>(NioEventLoopGroup.java:87)
        //       at io.netty.channel.nio.NioEventLoopGroup.<init>(NioEventLoopGroup.java:82)
        //       at io.netty.channel.nio.NioEventLoopGroup.<init>(NioEventLoopGroup.java:69)
        //       at reactor.netty.resources.DefaultLoopResources.cacheNioServerLoops(DefaultLoopResources.java:206)
        //       at reactor.netty.resources.DefaultLoopResources.cacheNioClientLoops(DefaultLoopResources.java:173)
        //       at reactor.netty.resources.DefaultLoopResources.onClient(DefaultLoopResources.java:141)
        //       at reactor.netty.transport.NameResolverProvider.newNameResolverGroup(NameResolverProvider.java:473)
        //       at reactor.netty.tcp.TcpResources.getOrCreateDefaultResolver(TcpResources.java:315)
        //       at reactor.netty.http.HttpResources.getOrCreateDefaultResolver(HttpResources.java:162)
        //       at reactor.netty.http.client.HttpClientConfig.defaultAddressResolverGroup(HttpClientConfig.java:381)
        //       at reactor.netty.transport.ClientTransportConfig.resolverInternal(ClientTransportConfig.java:224)
        //       at reactor.netty.http.client.HttpClientConfig.resolverInternal(HttpClientConfig.java:435)
        //       at reactor.netty.http.client.HttpClientConnect$MonoHttpConnect.lambda$subscribe$0(HttpClientConnect.java:266)
        //       at reactor.core.publisher.MonoCreate.subscribe(MonoCreate.java:58)
        //       at reactor.core.publisher.FluxRetryWhen.subscribe(FluxRetryWhen.java:77)
        //       at reactor.core.publisher.MonoRetryWhen.subscribeOrReturn(MonoRetryWhen.java:46)
        //       at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:57)
        //       at reactor.netty.http.client.HttpClientConnect$MonoHttpConnect.subscribe(HttpClientConnect.java:273)
        //       at reactor.core.publisher.InternalMonoOperator.subscribe(InternalMonoOperator.java:64)
        //       at reactor.core.publisher.MonoDefer.subscribe(MonoDefer.java:52)
        //       at reactor.core.publisher.Mono.subscribe(Mono.java:4397)
        //       at reactor.core.publisher.Mono.block(Mono.java:1706)
        //       at com.microservice.providerside.services.WebClientServices.getCustomerListViaProvider(WebClientServices.java:43)

        // Arrange
        // TODO: Populate arranged inputs
        String provider = "";

        // Act
        List<CustomersDTO> actualCustomerListViaProvider = this.webClientServices.getCustomerListViaProvider(provider);

        // Assert
        // TODO: Add assertions on result
    }
}

