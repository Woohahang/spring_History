package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
// 루트 컨텍스트에서 여기 테스트를 지정 안했으니 임폴트 같은거 한거임
public class TimeMapperTests {

    // 테스트 첫 번째 // 타입들이 인터페이스여서 객체화 하지 못 하지만 펄스는!!! 빈이 없어도 무시하고 주입한다!
    @Autowired(required = false) // (required = false) 해당 객체가 주입을 받지 못 하더라도 예외가 발생하지 않는다.
    private TimMapper timeMapper;

    // 테스트 두 번째
    @Autowired(required = false)
    private TimeMapper2 timeMapper_2;


    @Test
    public void testGetTime() {
        log.info("\n");
        log.info("\n 로그 testGetTime()  :  " + timeMapper.getTime() + "\n");
        log.info("\n 로그 testGetTime()  :  " + timeMapper_2.getNow() + "\n");
    }

}
