# 🧸 TeDDie

> **우테코 프리코스 스타일 TDD 연습 문제 생성기**

---

## 🤔 왜 이 프로젝트를 만들었나요?

---

우아한테크코스 프리코스를 진행하면서 TDD의 가치를 체감했습니다.  
하지만 우테코 스타일의 연습 문제가 더 필요했고, "그럼 AI로 만들어보자!"라고 생각했습니다.

**핵심 아이디어:**
- 실제 우테코 프리코스 과제를 RAG로 학습
- 비슷한 스타일의 새로운 문제를 무한 생성
- 바로 풀 수 있도록 프로젝트 템플릿까지 자동 생성

---

## 📋 프로젝트 개요

---

### 주요 역할
- [X] CLI를 통한 사용자 입력 처리 (`--topic`, `--difficulty`)
- [X] 로컬 LLM API 호출 (LM Studio)
- [X] RAG API 연동 (우테코 과제 검색)
- [X] 프로젝트 템플릿 자동 생성
- [X] **ApplicationTest.java 자동 생성** (기능 테스트 + 예외 테스트)

---

## 📁 프로젝트 구조

---

```
src/main/java/teddie/
 ├── Application.java
 ├── api/
 │   ├── RagClient.java
 │   └── dto/
 │       ├── ApiMessage.java
 │       ├── ApiRequest.java
 │       └── RagResult.java
 ├── common/
 │   ├── config/
 │   │   └── AppConfig.java
 │   └── util/
 │       └── HttpRequestSender.java
 ├── controller/
 │   ├── ProjectGeneratorController.java
 │   └── TeDDieController.java
 ├── domain/
 │   ├── CommandLineArgs.java
 │   ├── Difficulty.java
 │   └── Topic.java
 ├── exception/
 │   └── HttpRequestException.java
 ├── generator/
 │   ├── PackageStatementReplacer.java
 │   ├── PackageStructureBuilder.java
 │   ├── ProjectReplacer.java
 │   ├── ProjectWriter.java
 │   ├── ReadmeWriter.java
 │   ├── SettingsGradleReplacer.java
 │   ├── TemplateCopier.java
 │   └── TestGenerator.java
 ├── prompt/
 │   ├── SystemPrompt.java
 │   └── UserPrompt.java
 ├── service/
 │   ├── ApiResponse.java
 │   ├── Choice.java
 │   ├── Message.java
 │   ├── MissionResponse.java
 │   ├── MissionService.java
 │   ├── RequestBodyBuilder.java
 │   └── TestCase.java
 └── view/
     ├── ConsoleView.java
     └── OutputView.java
```

---

## 🎯 상세 기능 구현 목록

---

### api/ - API 통신 계층
- [X] **RagClient**: RAG API 호출 및 결과 수신
- [X] **dto/ApiRequest**: LLM API 요청 데이터 구조 (Record)
- [X] **dto/ApiMessage**: API 메시지 데이터 구조 (Record)
- [X] **dto/RagResult**: RAG 응답 데이터 구조 (Record)

### common/ - 공통 유틸리티 계층
- [X] **config/AppConfig**: 애플리케이션 설정 및 의존성 주입
- [X] **util/HttpRequestSender**: HTTP/1.1 POST 요청 전송

### controller/ - 흐름 제어 계층
- [X] **TeDDieController**: CLI 인자 파싱 및 전체 흐름 제어
- [X] **ProjectGeneratorController**: 프로젝트 생성 흐름 관리

### domain/ - 도메인 객체 계층
- [X] **CommandLineArgs**: CLI 인자 파싱 및 검증
- [X] **Topic**: 문제 주제 원시값 포장
- [X] **Difficulty**: 난이도 원시값 포장

### exception/ - 예외 처리 계층
- [X] **HttpRequestException**: HTTP 요청 실패 예외

### generator/ - 프로젝트 생성 계층
- [X] **TemplateCopier**: 템플릿 디렉토리 복사
- [X] **PackageStructureBuilder**: 패키지 구조 생성
- [X] **ProjectWriter**: 프로젝트 파일 작성 조정자 (README + Test + Replacer 통합)
- [X] **ProjectReplacer**: 프로젝트 파일 치환 조정자 (패키지 선언문 + settings.gradle 통합)
- [X] **ReadmeWriter**: README.md 파일 생성
- [X] **TestGenerator**: ** ApplicationTest.java 자동 생성** (기능/예외 테스트 JUnit 코드 변환)
- [X] **PackageStatementReplacer**: 패키지 선언문 치환
- [X] **SettingsGradleReplacer**: settings.gradle 파일 치환

### prompt/ - 프롬프트 관리 계층
- [X] **SystemPrompt**: LLM 시스템 프롬프트 관리
- [X] **UserPrompt**: RAG 결과 기반 사용자 프롬프트 생성

### service/ - 비즈니스 로직 계층
- [X] **MissionService**: 미션 생성 및 테스트 케이스 추출 핵심 로직
- [X] **MissionResponse**: ** 미션 본문 + 테스트 케이스 구조화된 응답** (record)
- [X] **TestCase**: ** 테스트 케이스 데이터** (name, input, expectedOutput, expectError)
- [X] **RequestBodyBuilder**: LLM API 요청 JSON 생성
- [X] **ApiResponse**: LLM 응답 파싱 (record)
- [X] **Choice**: API 응답 choice 객체 (record)
- [X] **Message**: API 응답 message 객체 (record)

### view/ - 출력 계층
- [X] **ConsoleView**: 콘솔 출력 담당
- [X] **OutputView**: 출력 포맷팅

---

## 🏗 시스템 아키텍처

---

### 하이브리드 구조를 선택한 이유

**초기 고민:**
- Java만으로 RAG를 구현? → FAISS, sentence-transformers가 Python 생태계에 더 성숙
- Python만 사용? → 우테코가 Java 중심이고, TDD 연습도 Java로 하고 싶음

**결정:**
- **Java**: 메인 애플리케이션, TDD 연습, 도메인 로직
- **Python**: RAG 시스템 (벡터 검색, 임베딩)
- **FastAPI**: Java ↔ Python 브릿지

```
┌─────────────────────────────────────────────────────────────┐
│              teddie Application (Java 21 + TDD)             │
│                                                             │
│  ┌─────────────────┐        ┌───────────────────────────┐   │
│  │   controller/   │        │        service/           │   │
│  │ TeDDieController│──────> │     MissionService        │   │
│  │ ProjectGenerator│        │   MissionResponse         │   │
│  │   Controller    │        │      TestCase             │   │
│  └─────────────────┘        │  RequestBodyBuilder       │   │
│          │                  │  ApiResponse/Choice/Msg   │   │
│          │                  └───────────────────────────┘   │
│          │                              │                   │
│          │                    ┌─────────┴──────────┐        │
│          │                    ▼                    ▼        │
│          │           ┌──────────────┐    ┌──────────────┐   │
│          │           │     api/     │    │   prompt/    │   │
│          │           │  RagClient   │    │ SystemPrompt │   │
│          │           │  dto/        │    │  UserPrompt  │   │
│          │           └──────────────┘    └──────────────┘   │
│          │                    │                             │
│          │                    ▼                             │
│          │           ┌──────────────┐                       │
│          │           │   common/    │                       │
│          │           │  AppConfig   │                       │
│          │           │HttpRequest   │                       │
│          │           │   Sender     │                       │
│          │           └──────────────┘                       │
│          │                                                  │
│          ├───────────> generator/                           │
│          │             TemplateCopier                       │
│          │             PackageStructureBuilder              │
│          │             ProjectWriter                        │
│          │             ├─ ReadmeWriter                      │
│          │             ├─ TestGenerator                     │
│          │             └─ ProjectReplacer                   │
│          │                ├─ PackageStatementReplacer       │
│          │                └─ SettingsGradleReplacer         │
│          │                                                  │
│          └───> view/                                        │
│                ConsoleView, OutputView                      │
│                                                             │
│  ┌─────────────┐  ┌──────────────┐                          │
│  │   domain/   │  │  exception/  │                          │
│  │ CommandLine │  │ HttpRequest  │                          │
│  │ Args, Topic │  │  Exception   │                          │
│  │ Difficulty  │  └──────────────┘                          │
│  └─────────────┘                                            │
│                                                             │
└─────────────────────────────────────────────────────────────┘
                    │                           │
                    ▼                           ▼
          ┌──────────────────┐       ┌──────────────────┐
          │   LM Studio      │       │    RAG API       │
          │   (로컬 LLM)      │       │   (FastAPI)      │
          │    :1234         │       │    :8000         │
          └──────────────────┘       └──────────────────┘
```

### 주요 설계 결정

#### 1. HTTP/1.1 명시 (HttpRequestSender)
**문제:** LM Studio가 HTTP/2를 완벽 지원하지 않아 연결 실패  
**해결:** `.version(HttpClient.Version.HTTP_1_1)` 명시

#### 2. RAG 검색 결과를 프롬프트에 포함
**고민:** LLM만으로도 되지 않나?  
**결과:** RAG 없이는 우테코 스타일 재현이 어려웠음 → RAG 필수

#### 3. Record 타입 적극 활용
**이유:** 불변 객체로 DTO 관리 + getter/setter 없이 구현

---

## ⭐ 핵심 기능: 자동 테스트 코드 생성

---

### 1. TestGenerator - ApplicationTest.java 자동 생성

TeDDie의 **가장 핵심적인 기능**은 LLM이 생성한 테스트 케이스를 **실제로 실행 가능한 JUnit 테스트 코드로 자동 변환**하는 것입니다.

#### 동작 흐름

```
1. MissionService가 LLM 응답 파싱
   └─> "## 테스트 케이스" 섹션 추출
       ├─> 기능 테스트: 입력 + 예상 출력
       └─> 예외 테스트: 입력

2. TestCase 객체로 구조화
   └─> record TestCase(name, displayName, input, expectedOutput, expectError)

3. TestGenerator가 JUnit 테스트 코드 생성
   └─> NsTest 기반 우테코 스타일 테스트 자동 생성
       ├─> 기능 테스트: assertSimpleTest + assertThat(output()).contains()
       └─> 예외 테스트: assertThatThrownBy + IllegalArgumentException
```

#### 생성되는 테스트 코드 예시

**LLM 응답 (테스트 케이스 섹션)**:
```markdown
## 테스트 케이스

### 기능 테스트
- 입력: pobi,woni\n1
- 출력: pobi : -

### 예외 테스트
- 입력: pobi,javaji\n1
```

**TestGenerator가 자동 생성하는 코드**:
```java
package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertSimpleTest(() -> {
            run("pobi,woni\n1");
            assertThat(output()).contains("pobi : -");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji\n1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
```

### 2. MissionResponse - 구조화된 응답 처리

기존에는 LLM 응답을 단순 문자열로 처리했지만, 현재는 **미션 본문과 테스트 케이스를 분리**하여 구조적으로 관리합니다.

```java
public record MissionResponse(
    String mission,           // README.md에 들어갈 미션 본문
    List<TestCase> testCases  // ApplicationTest.java로 변환될 테스트 케이스들
)
```

### 3. 테스트 케이스 파싱 엔진

MissionService는 정규식 기반으로 LLM 응답에서 테스트 케이스를 추출합니다.

**주요 기능:**
- `## 테스트 케이스` 섹션 감지 및 분리
- `### 기능 테스트`, `### 예외 테스트` 구분
- 입력/출력 값 정제 (`cleanTestValue()`)
  - 마크다운 코드 블록 제거 (```java, ```)
  - 괄호 제거 (`(`, `)`, `[`, `]`)
  - 설명문 제거 ("당첨 통계" 같은 텍스트)

**정규식 예시:**
```java
Pattern.compile(
    "(###?\\s*기능\\s*테스트|기능 테스트)\\s*-\\s*입력[:\\s]*(.+?)\\s*-\\s*출력[:\\s]*(.+?)(?=(###?|$))",
    Pattern.DOTALL | Pattern.CASE_INSENSITIVE
);
```

### 4. SystemPrompt 고도화

LLM이 올바른 형식으로 테스트 케이스를 생성하도록 프롬프트를 강화했습니다.

**핵심 규칙:**
```
## 테스트 케이스

### 기능 테스트
- 입력: [실제 입력값]
- 출력: [예상 출력값]

### 예외 테스트
- 입력: [예외를 발생시킬 입력값]

절대 규칙:
- 입력/출력에 순수 값만 작성 (코드 블록, 괄호, 설명문 금지)
- 마크다운 블록(```) 사용 금지
```

---

## 🤔 개발하면서 고민했던 점들

---

### 1. 테스트 전략

**문제:**
- LM Studio API 테스트를 어떻게 하지?
- 실제 API 호출하면 테스트가 느리고 불안정함

**시도 1: MockWebServer 사용**
```java
@Test
void 서버가_200_OK_응답시_응답_본문을_그대로_반환() {
    mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody(expectedBody));
    
    String result = sender.post(mockUrl, dummyRequestBody);
    
    assertThat(result).isEqualTo(expectedBody);
}
```

**시도 2: Mockito로 의존성 주입**
```java
@Mock
private HttpRequestSender mockSender;

@Test
void API_응답을_파싱하여_실제_텍스트_반환() {
    when(mockSender.post(anyString(), anyString()))
            .thenReturn(testResponse);
    // ...
}
```

**배운 점:**
- MockWebServer: HTTP 통신 자체를 테스트
- Mockito: 비즈니스 로직에 집중
- 상황에 따라 적절한 도구 선택이 중요

---

### 2. 테스트 케이스 파싱 - LLM 출력의 불확실성

**문제:**
- LLM이 테스트 케이스를 다양한 형식으로 생성함
- 마크다운 코드 블록, 괄호, 설명문이 섞여서 출력됨
- 정규식으로 정확하게 입력/출력 추출이 어려움

**시도 1: 단순 split 방식**
```java
String[] parts = content.split("입력:");
String input = parts[1].split("출력:")[0];
```
**문제점:**
- LLM이 "- 입력:", "입력 :", "### 입력" 등 다양하게 출력
- 실패

**시도 2: 정규식 패턴 매칭**
```java
Pattern pattern = Pattern.compile(
    "(###?\\s*기능\\s*테스트|기능 테스트)\\s*-\\s*입력[:\\s]*(.+?)\\s*-\\s*출력[:\\s]*(.+?)(?=(###?|$))",
    Pattern.DOTALL | Pattern.CASE_INSENSITIVE
);
```
**해결:** 다양한 형식을 모두 포용하도록 정규식 설계

**시도 3: cleanTestValue() 로직 구현**

LLM이 자꾸 이상한 값을 넣어서, 후처리 로직을 추가했습니다:

```java
private String cleanTestValue(String value) {
    value = value.replaceAll("```[a-z]*\\n?", "").replaceAll("```", "");  // 마크다운 블록 제거
    value = value.replaceAll("`", "");                                    // 인라인 코드 제거
    value = value.replaceAll("\\([^)]+\\)", "");                         // 괄호와 설명문 제거
    if (value.contains("- 출력:")) {
        value = value.split("-\\s*출력:", 2)[0];                         // 다음 섹션 제거
    }
    String[] lines = value.split("\n");
    value = lines[0].trim();                                              // 첫 줄만 추출
    return value;
}
```

**배운 점:**
- LLM 출력은 항상 불확실함 → 방어적 파싱 필수
- SystemPrompt로 형식을 강제해도 100% 보장 안 됨
- 정규식 + 후처리 조합이 가장 안정적

---

### 3. 문자열 이스케이프 처리 - TestGenerator의 고민

**문제:**
- 테스트 입력/출력에 `"` (큰따옴표)가 있으면 Java 문자열이 깨짐

**예시:**
```java
// LLM이 제안한 출력: "당첨 결과"를 출력합니다
// 이대로 생성하면:
assertThat(output()).contains(""당첨 결과"를 출력합니다");  // 컴파일 에러!
```

**해결:**
```java
private String escapeString(String str) {
    return str.replace("\"", "\\\"");  // " → \"
}
```

**적용:**
```java
private String generateFunctionalTest(TestCase testCase) {
    return """
            @Test
            void %s() {
                assertSimpleTest(() -> {
                    run("%s");
                    assertThat(output()).contains("%s");
                });
            }
        """.formatted(
        testCase.name(),
        escapeString(testCase.input()),       // ⭐ 이스케이프 적용
        escapeString(testCase.expectedOutput())  // ⭐ 이스케이프 적용
    );
}
```

**배운 점:**
- 동적 코드 생성 시 이스케이프 처리 필수
- `\n`, `\t` 같은 특수문자는 자동으로 처리되지만, `"`는 수동 처리 필요

---

### 4. MissionResponse 구조 설계

**고민:**
- 처음엔 LLM 응답을 단순 String으로 처리
- 근데 README와 TestCode는 다른 파일로 가야 함
- 어떻게 분리할까?

**시도 1: String을 두 번 파싱**
```java
String mission = extractMission(llmResponse);
List<TestCase> testCases = extractTestCases(llmResponse);
```
**문제점:** 중복 파싱, 비효율적

**시도 2: MissionResponse record 도입**
```java
public record MissionResponse(
    String mission,           // README용
    List<TestCase> testCases  // ApplicationTest용
)
```
**장점:**
- 한 번 파싱으로 두 가지 결과 획득
- 불변 객체로 안전성 보장
- Controller에서 용도별로 깔끔하게 분리 사용

**배운 점:**
- 관심사 분리는 데이터 구조 설계부터 시작
- Record 타입은 DTO로 완벽함

---

## ⚠️ 예외 처리

---

| 분류 | 예외 상황 | 예시 입력 | 에러 메시지 | 발생 위치 |
|------|----------|----------|------------|----------|
| **CLI 인자** | 필수 인자 누락 | `--topic collection` (difficulty 없음) | `[ERROR] 필수 옵션을 입력해야 합니다.` | `TeDDieController` |
| | topic 공백 | `--topic "" --difficulty easy` | `[ERROR] 주제는 빈 값일 수 없습니다.` | `Topic` |
| | difficulty 공백 | `--topic collection --difficulty ""` | `[ERROR] 난이도는 빈 문자열일 수 없습니다.` | `Difficulty` |
| | 잘못된 난이도 | `--difficulty invalid` | `[ERROR] 유효하지 않은 난이도입니다.` | `Difficulty` |
| **API 통신** | LLM 서버 미응답 (IO 에러) | (서버 중지 상태) | `HttpRequestException: HTTP 요청 중 IO 오류 발생: {url}` | `HttpRequestSender` |
| | HTTP 요청 중단 | (타임아웃 등) | `HttpRequestException: HTTP 요청이 중단되었습니다: {url}` | `HttpRequestSender` |
| | HTTP 상태 코드 에러 | (500 서버 에러) | `HttpRequestException: HTTP 에러 상태: 500` | `HttpRequestSender` |
| | RAG API 미응답 | (RAG 서버 중지) | `HttpRequestException` (IOException 래핑) | `RagClient` |
| **응답 파싱** | choices 없음 | `{"choices": []}` | `IllegalStateException: API 응답에 choice가 없습니다.` | `ApiResponse` |
| | message null | `{"choices": [{"message": }]}` | `IllegalStateException: API 응답에 message가 없습니다.` | `Choice` |
| **파일 생성** | 테스트 파일 생성 실패 | (디스크 쓰기 권한 없음) | `RuntimeException: 테스트 파일 생성 실패: {message}` | `TestGenerator` |

---

## 테스트 전략

---

### TDD 사이클

```
1. Red   → 실패하는 테스트 작성
2. Green → 최소한으로 구현
3. Refactor → 리팩토링
```

### 테스트 구조

**1. 단위 테스트 (Unit Test)**
- 각 클래스의 메서드별 테스트
- Mock 객체로 의존성 분리
- 예외 상황 테스트 포함

```java
@Test
@DisplayName("서버가 200 OK 응답시 응답 본문을 그대로 반환")
void 서버가_200_OK_응답시_응답_본문을_그대로_반환() {
    // given
    String expectedBody = "{\"response\":\"성공\"}";
    mockWebServer.enqueue(new MockResponse()
            .setResponseCode(200)
            .setBody(expectedBody));
    
    // when
    String result = sender.post(mockUrl, dummyRequestBody);
    
    // then
    assertThat(result).isEqualTo(expectedBody);
}
```

**2. 통합 테스트 (Integration Test)**
- Controller - Service - View 연동
- Mock을 활용한 전체 흐름 검증

```java
@Test
void CLI_인자를_파싱하여_Service와_View를_올바르게_호출() {
    // given
    String[] args = {"--topic", "collection", "--difficulty", "easy"};
    when(mockService.generateMission("collection", "easy"))
            .thenReturn(missionResult);
    
    // when
    controller.run(args);
    
    // then
    verify(mockService).generateMission("collection", "easy");
    verify(mockView).printMission(missionResult);
}
```

**3. MockWebServer 활용**
- 실제 HTTP 통신 시뮬레이션
- 네트워크 레이어 테스트

---

## 코딩 컨벤션 체크리스트

---

- [X] 한 메서드에 오직 한 단계의 들여쓰기만
- [X] else 예약어 사용하지 않기
- [X] 모든 원시값과 문자열 포장
- [X] 콜렉션에 대해 일급 컬렉션 적용
- [X] 3개 이상의 인스턴스 변수를 가진 클래스 없음
- [X] getter/setter 없이 구현
- [X] 메서드의 인자 수를 제한 (3개 이하)
- [X] 코드 한 줄에 점(.) 하나만
- [X] 메서드가 한 가지 일만 담당
- [X] 클래스를 작게 유지

---

## 🚀 빌드 및 실행

---

### 1. 의존성 설치

```bash
./gradlew build
```

### 2. 테스트 실행

```bash
./gradlew test
```

### 3. 애플리케이션 실행

```bash
./gradlew run --args="--topic lotto --difficulty medium"
```

#### 실행 옵션

| 옵션 | 설명 | 필수 | 예시 |
|------|------|------|------|
| `--topic` | 생성할 문제의 주제 | ✅ | `collection`, `string`, `loop` |
| `--difficulty` | 난이도 | ✅ | `easy`, `medium`, `hard` |

---

## 👨‍💻 개발자

---

정용태 ([@jyt6640](https://github.com/jyt6640))
