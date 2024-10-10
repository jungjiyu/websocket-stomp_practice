package com.example.stomp.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
public class WebSockConfig implements WebSocketMessageBrokerConfigurer{


    /**
     *소켓 연결과 관련한 설정
     *  addEndpoint -> 소켓 연결에 대한 요청받을 uri 설정
     *  setAllowedOriginPatterns -> 소켓또한 CORS 설정 필요
     *  withSockJS()-> 소켓을 지원하지 않는 구린 브라우저의 경우 sockJS 를 사용하도록 대비
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws-stomp").setAllowedOriginPatterns("*").withSockJS();
    }
    

    /**
     * 메세지 브로커에 대한 설정
     *  enableSimpleBroker ->  스프링 내장 브로커를 활성화 && 브로커가 관리할 path 설정
     *  setApplicationDestinationPrefixes -> (브로커 차원이 아님) 애플리케이션이 관리할 path 설정
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/queue", "/topic"); // 브로커가 관리하는 경로. 클라이언트가 해당 경로를 구독 가능하고, 구독 시 이 경로를 prefix로하여 발행이 됬을 때 해당 클라이언트에게 메시지가 발송된다
        config.setApplicationDestinationPrefixes("/app"); // 애플리케이션이 관리할 경로. 애플리케이션의 해당 경로로 들어온 request 대해선 브로커의 간섭 없이 애플리케이션이 처리한다.
    }




}
