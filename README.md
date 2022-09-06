# LG Uplus 인턴 3조
> 기존 Lguplus 홈페이지를 분석하여 기본 기능을 구현하고 개선사항을 적용하여 UI/UX 개선

[김수현](https://github.com/HiBird00) [김형준](https://github.com/hjkim0822) [성아영](https://github.com/Sungayoung) [윤병찬](https://github.com/Chaaany) [이일환](https://github.com/pppp0722)

<a href="https://github.com/uplus-3/backend/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=uplus-3/backend" />
</a>

## 배포 사이트
http://uplus3-dev.s3-website.ap-northeast-2.amazonaws.com


## 개발 환경

- JDK(Java version) : jdk 11
- Spring-Boot : Spring-Boot 2.6.11
- Spring-Boot-Starter : Spring-Boot-Starter 3.0.0
- Annotation auto create : Lombok 1.18.24
- Production DB : MySQL Community Server 8.0.30
- API documentation & management : swagger(2) 3.0.0
- Test Tool : Junit 5.8.2, Mockito 4.0.0
- Test DB : H2 1.4.200

## 담당자
- global : [김수현](https://github.com/HiBird00), [성아영](https://github.com/Sungayoung), [윤병찬](https://github.com/Chaaany), [이일환](https://github.com/pppp0722)
- cart
  - controller : [성아영](https://github.com/Sungayoung)
  - dto : [성아영](https://github.com/Sungayoung)
  - entity : [성아영](https://github.com/Sungayoung)
  - repository : [성아영](https://github.com/Sungayoung)
  - service : [성아영](https://github.com/Sungayoung)
- device
  - controller : [이일환](https://github.com/pppp0722)
  - dto : [이일환](https://github.com/pppp0722)
  - entity : [이일환](https://github.com/pppp0722), [윤병찬](https://github.com/Chaaany)
  - repository : [이일환](https://github.com/pppp0722), [윤병찬](https://github.com/Chaaany)
  - service : [이일환](https://github.com/pppp0722)
- launchingdevice
  - controller : [김수현](https://github.com/HiBird00)
  - dto : [김수현](https://github.com/HiBird00)
  - entity : [김수현](https://github.com/HiBird00)
  - repository : [김수현](https://github.com/HiBird00)
  - service : [김수현](https://github.com/HiBird00)
- order
  - controller : [윤병찬](https://github.com/Chaaany), [김수현](https://github.com/HiBird00)
  - dto : [윤병찬](https://github.com/Chaaany), [김수현](https://github.com/HiBird00)
  - entity : [윤병찬](https://github.com/Chaaany)
  - repository : [윤병찬](https://github.com/Chaaany)
  - service : [윤병찬](https://github.com/Chaaany), [김수현](https://github.com/HiBird00)
- plan
  - controller : [윤병찬](https://github.com/Chaaany)
  - dto : [윤병찬](https://github.com/Chaaany)
  - entity : [윤병찬](https://github.com/Chaaany)
  - repository : [윤병찬](https://github.com/Chaaany)
  - service : [윤병찬](https://github.com/Chaaany)
- search
  - controller : [윤병찬](https://github.com/Chaaany)
  - dto : [윤병찬](https://github.com/Chaaany)
  - service : [윤병찬](https://github.com/Chaaany)
 - test : 각 도메인 담당자별 작성
## ERD 
![image](https://user-images.githubusercontent.com/34885297/188538778-3c07653e-3806-421a-b8d9-71257f773751.png)

## UML(Class Diagram)
![image](https://user-images.githubusercontent.com/34885297/188303218-930d47a1-a9ee-4660-9892-743fbfde5565.png)

## API (Swagger)
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/34885297/188302774-f9b39abe-cae9-437e-9aeb-47b2b1be2031.png">
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/34885297/188302808-3a6cbcd5-4492-4ce8-b7c9-59d15c8146c0.png">
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/34885297/188302823-c94734d0-3f35-49db-b738-1bfccec0a57f.png">
<img width="1000" alt="스크린샷 2022-09-04 오후 4 38 02" src="https://user-images.githubusercontent.com/34885297/188302847-092baa6d-ea9e-4701-b9f8-bd9aa8cf75fe.png">


<!-- Markdown link & img dfn's -->
[npm-image]: https://img.shields.io/npm/v/datadog-metrics.svg?style=flat-square
[npm-url]: https://npmjs.org/package/datadog-metrics
[npm-downloads]: https://img.shields.io/npm/dm/datadog-metrics.svg?style=flat-square
[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
[wiki]: https://github.com/yourname/yourproject/wiki
