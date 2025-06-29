# 🎓 LLM을 이용한 SW융합대학 웹사이트 제작 해커톤 (백엔드) 🏫

[![Uptime Robot status](https://img.shields.io/uptimerobot/status/m800668201-c738159d6dea0ef0205cad01?up_message=Online&down_message=Offline&style=for-the-badge&logo=uptimerobot&logoColor=white&label=status&labelColor=065f46&color=10b981)](https://uswai.2jang.me)  
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Gradle](https://img.shields.io/badge/Gradle-8.13-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)
[![WebSocket](https://img.shields.io/badge/WebSocket-STOMP-blue?style=for-the-badge&logo=socketdotio&logoColor=white)](https://stomp.github.io/)
[![LLM](https://img.shields.io/badge/LLM-OpenRouter-9cf?style=for-the-badge&logo=openai&logoColor=white)](https://openrouter.ai/)

LLM을 이용한 수원대학교 지능형SW융합대학의 정보 제공 및 편리한 기능을 제공하는 웹사이트입니다. 학과 정보, LLM을 통한 질의응답(예: 졸업 요건), 캠퍼스 네비게이션 등 다양한 기능을 통해 학생들의 적극적인 참여를 유도합니다!🚀

1주일 동안의 해커톤 대회 기간동안 제작되었습니다!  
본 프로젝트의 frontend 레포지스토리는 [여기에서](https://github.com/2jang/sw-hackathon-react) 확인할 수 있습니다

## ✨ 주요 기능

### 📊 학부 대항 클릭 배틀
* 학과 항목에 대한 실시간 클릭 수 증가 처리
* WebSocket (STOMP)을 통한 클릭 수 브로드캐스팅 (모두가 같은 값으로 동기화)
* 클릭 요청에 대한 비율 제한(Rate Limiting) 기능 적용 (초당 20회/항목)
* synchronized를 사용하여 값 업데이트 도중 경쟁 상태가 발생해도 순차적으로 처리

### 💬 챗봇 (LLM 연동)
* OpenRouter를 통해 Google의 Gemma LLM 모델 연동
* 사용자 질문(예: 학교 생활, 졸업 요건 등)에 대한 답변을 stream 형식으로 제공 (딜레이 감소 및 시각적 효과)
* 경로: `/route/stream`

### 🗺️ 수원대학교 캠퍼스 내비게이션 ("수벅수벅")
* 캠퍼스 내 주요 건물 및 장소 목록 조회
* 지정된 두 건물/장소 간의 예상 이동 시간 정보 제공
* 경로: `/suwon-navi/colleges`, `/suwon-navi?buildings=건물1,건물2`

## 🏗️ 시스템 구조

```
peggi/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/swhackathon/peggi/
│       │       ├── config/               # Spring 및 애플리케이션 설정 (CORS, WebSocket, WebClient, RateLimiter)
│       │       ├── controller/           # API 엔드포인트 및 요청 처리 (Click, Route, SuwonNavi)
│       │       ├── data/                 # 초기 데이터 로드 (SeedData)
│       │       ├── domain/
│       │       ├── dto/                  # 데이터 전송 객체 (Request/Response 모델)
│       │       ├── exception/
│       │       ├── repository/
│       │       └── service/              # 비즈니스 로직 (Click, Route, SuwonNavi 서비스)
│       │
│       └── resources/
│           └── application.yml           # 기본 설정 파일
│
└── build.gradle
```

## 🔄 API 엔드포인트

### WebSocket (STOMP)

* **Handshake Endpoint**: `/ws-click`
* **Client → Server**:
    * 메시지 형식: `com.swhackathon.peggi.dto.ClickMessage` (`clickId` 포함)
    * 설명: 각 학부별 클릭 수를 증가시킵니다.
* **Server → Client**:
    * 메시지 형식: `com.swhackathon.peggi.dto.ClickMessage` (`clickId`, `clickNum` 포함)
    * 설명: 업데이트된 클릭 현황을 모든 구독자에게 브로드캐스팅합니다.

### RESTful API
* `GET /click-num`:
    * 설명: 각 학부별 클릭수의 초기값 목록을 반환합니다.
    * 응답 형식: `List<com.swhackathon.peggi.dto.ClickResponse>`
* `POST /route/stream`:
    * 설명: LLM을 통해 사용자 질문에 대한 답변을 stream 형식으로 제공합니다.
    * 요청 본문: `com.swhackathon.peggi.dto.ChatToLlmRequest` (`question` 필드)
    * 응답 형식: 순수 텍스트 스트림 (LLM 응답 조각들)
* `GET /suwon-navi/colleges`:
    * 설명: 수원캠퍼스 내 건물/장소 식별자 목록을 반환합니다.
    * 응답 형식: `com.swhackathon.peggi.dto.CollegeResponse` (`college` 필드: `List<String>`)
* `GET /suwon-navi`:
    * 설명: 두 건물 간의 예상 이동 시간을 반환합니다.
    * 요청 파라미터: `buildings` (예: `buildings=buildingA,buildingB`)
    * 응답 형식 (성공 시): `com.swhackathon.peggi.dto.SuwonNaviResponse` (`distance` 필드: 이동 시간)
    * 응답 형식 (실패 시): `com.swhackathon.peggi.dto.ErrorResponse`

## 🚀 설치 및 실행 방법

### 준비물

* Java (JDK) 17 이상
* Gradle (프로젝트 내 Gradle Wrapper 사용 권장)
* OpenRouter API Key (LLM 기능 사용 시 필수)

### 설치 단계

1.  **저장소 클론하기**
    ```bash
    git clone https://github.com/2jang/sw-hackathon-backend.git
    cd sw-hackathon-backend
    ```

2. **애플리케이션 설정**

    `application.yml` 파일을 수정합니다.

    * **OpenRouter API 키** (필수):
        ```yaml
        openrouter:
          api:
            key: [OpenRouter API 키]
        ```

3. **프로젝트 빌드** (Gradle Wrapper 사용)
    ```bash
    ./gradlew build
    ```

### 실행 방법 🏃‍♀️

1.  **Spring Boot 애플리케이션 실행**
    * Gradle Wrapper 사용:
        ```bash
        ./gradlew bootRun
        ```

2.  애플리케이션은 기본적으로 `http://localhost:5041` 에서 실행됩니다.

## 🧩 사용된 기술

* **Backend Framework**: Spring Boot 3.4.5 (Spring MVC)
* **Programming Language**: Java 17
* **Real-time Communication**: Spring WebSocket (with STOMP messaging protocol)
* **Build Tool**: Gradle
* **Embedded Server**: Tomcat
* **LLM Integration**: OpenRouter AI
* **Utilities**: Lombok
* **Configuration**: YAML

## 🔧 문제해결 팁

* **Port 충돌**: `server.port` (기본값 `5041`)가 다른 애플리케이션에서 사용 중인지 확인하고, 필요시 다른 포트로 변경합니다.
* **OpenRouter API 키 오류**: `openrouter.api.key`가 설정 파일에 정확히 입력되었는지, 유효한 키인지 확인합니다.
* **의존성 문제**: `./gradlew clean build`를 통해 프로젝트를 클린 빌드하여 의존성이 올바르게 다운로드되었는지 확인합니다.
* **WebSocket 연결 실패**: 방화벽 설정이나 프록시 환경을 확인하고, `/ws-click` 엔드포인트가 정상적으로 노출되었는지 확인합니다.

## 📝 라이센스

이 프로젝트는 [MIT 라이선스](LICENSE) 하에 배포됩니다.

## 🙏 감사의 말

* 해커톤을 주최해주신 안홍렬 교수님과 IT관련 동아리 분들께 감사드립니다!
* 그리고 같이 해커톤 참가해서 열심히 개발에 임해준 우리 1조 팀원들 너무 수고했어요!!

---

⭐ 이 프로젝트가 유용하다고 생각되시면 Star를 눌러주세요! ⭐
