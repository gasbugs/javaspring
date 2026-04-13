// src/test/java/com/example/AppTest.java
package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(App.class) // App 컨트롤러만 테스트하기 위한 애노테이션
class AppTest {

    @Autowired
    private MockMvc mockMvc; // 웹 API를 테스트하기 위한 Mock 객체

    @Test
    void home() throws Exception {
        // given: 테스트 준비는 별도로 필요 없음

        // when & then: "/" 경로로 GET 요청을 보내고 응답을 검증
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // HTTP 상태 코드가 200 (OK)인지 확인
                .andExpect(content().string("Hello, Web Docker Multi-Stage!")); // 응답 본문이 예상 문자열과 일치하는지 확인
    }
}
