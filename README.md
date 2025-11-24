## 🚀 Encore framework
Spring framework의 MVC 구조를 차용한 개인화된 경량 웹 프레임워크

## 구조
<img width="1369" height="554" alt="스크린샷 2025-11-24 오후 7 57 57" src="https://github.com/user-attachments/assets/19fb41fd-877c-4c90-8687-50f08d4720c2" />


## ⚙️ 기능

### ✔️ 사용자의 HTTP 요청 수신, 응답
내장 웹 서버를 통해 TCP 연결을 맺고, 요청의 Query parameter, Path variable, 요청 본문의 변수를 파싱해 컨트롤러에 제공

### ✔️ 다양한 응답 방식
Plain text 맟 Json 응답 기능 제공

### ✔️ Servlet 기반 웹 서버 호환
Dispatcher 로직을 Servlet 컨테이너와 분리해 구현하여 Tomcat, Jetty 등 Servlet 기반 서버와 호환 가능

### ✔️ 다양한 컨트롤러 매핑 방식 지원
HandlerMapping 인터페이스를 기반으로 URL 기반, Path Variable 패턴 기반 등 다양한 매핑 전략 사용 가능


## ❓ 사용 방법
### 1. build.gradle 의존성 추가
```java
dependencies { 
    implementation 'io.github.encorejeong:encore-framework:0.1.1'
}
```
### 2. DispatcherServlet을 상속해 필요한 훅 메서드를 오버라이드
```java
public class LottoDispatcherServlet extends DispatcherServlet {

    /*
    *  요청 매핑 전략 구성
    */
    @Override
    protected List<HandlerMapping> initHandlerMappings() {

        // /users/{userId} or /users/{userId}/orders/{orderId} 등 HTTP Method + Path Variable 기반 패턴 등록 가능
        RestHandlerMapping restHandlerMapping = new RestHandlerMapping();
        restHandlerMapping.register("POST", "/budget", new PurchaseLottosController());

        // /users or /orders 등 HTTP Method + uri 기반 패턴 등록 가능
        UrlWithMethodMapping urlWithMethodMapping = new UrlWithMethodMapping();
        urlWithMethodMapping.register("POST", "/winning-condition", new GetMatchingResultController());

        return List.of(restHandlerMapping, urlWithMethodMapping);
    }

    /*
    *  일반 컨트롤러는 DefaultHandlerAdapter, Json을 반환하는 RestController는 RestHandlerAdapter (필요한 어뎁터만 등록)
    */
    @Override
    protected List<HandlerAdapter> initHandlerAdapters() {
        return List.of(
                new DefaultHandlerAdapter(),
                new RestHandlerAdapter()
        );
    }

    /*
    *  뷰 랜터링 방식 선택 Plain Text -> PlainTextViewResolver, Json 응답 -> JsonViewResolver
    */
    @Override
    protected List<ViewResolver> initViewResolvers() {
        return List.of(
                new JsonViewResolver(),
                new PlainTextViewResolver()
        );
    }
}
```

### 3. 애플리케이션의 시작 지점으로 사용할 내장 웹 서버 설정. 포트 설정 및 DispatcherServlet 구현체를 서블릿으로 등록
```java
public class Application {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext("", docBase);

        Tomcat.addServlet(context, "dispatcher", new LottoDispatcherServlet()).addMapping("/*");

        tomcat.start();
        tomcat.getServer().await();
    }
}
```

### 4. Controller 구현
```java
    public class RestPOSTController implements RestController {

    private static final Logger log = LoggerFactory.getLogger(RestPOSTController.class);

    @Override
    public String handle(RequestParams params, RequestBody body, Map<String, Object> model) throws IOException {
        log.info("[RestPOSTController.handle]");

        model.putAll(params.params());
        model.putAll(body.getBody());
        return "jsonView";
    }
}
```


