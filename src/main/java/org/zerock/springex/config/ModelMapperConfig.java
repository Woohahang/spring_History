package org.zerock.springex.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zerock.springex.mapper.TodoMapper;

// DTO 를 VO로 또는 반대로 변환할 때 모델 매퍼를 많이 쓰니까 스프링의 빈으로 등록해서 처리
@Configuration // 클래스가 하나 이상의 @Bean 메소드를 선언하고 런타임에 해당 빈에 대한 빈 정의 및 서비스 요청을 생성하기 위해 Spring 컨테이너에 의해 처리될 수 있음을 나타냅니다.
// 한마디로 여기 클래스 안의 메서드를 빈으로 설정할 수 있다.
public class ModelMapperConfig {


    @Bean
    ModelMapper getMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) // 필드와 매칭
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE) // 필드의 접근 수준을 프라이빗
                .setMatchingStrategy(MatchingStrategies.LOOSE); // 매핑 전략을 LOOSE로 설정, 대소문자 무시 설정

        // ModleMapper 설정

        return modelMapper;
    }


}
