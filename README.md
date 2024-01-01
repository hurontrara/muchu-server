# muchu-server
💥Mu-Chu(무추) : 검색 키워드에 맞는 무한도전 짤을 추천해주는 서비스

<br>

## 서비스 설명

검색 기능을 통해 키워드에 맞는 무한도전 짤을 추천해주고, 사용자가 좋아할 만한 짤을 추천해주는 서비스

- 최소 기능 (MVP)

  - 인증 권한 : 로그인, 소셜로그인, 마이페이지, 아이디 찾기, 비밀번호 찾기(메일링)
   
  - 키워드를 통한 검색

<br>

## 공통 컨벤션 (Convention)

### 🌴 Branch 전략 
- Git Flow

|  **항목**  |                                  **설명**                                   |
| ---------- | --------------------------------------------------------------------------- |
| main       | 기준이 되는 브랜치로 제품을 배포하는 브랜치                                  |
| develop    | 개발 브랜치로 개발자들이 이 브랜치를 기준으로 각자 작업한 기능들을 Merge      |
| feature    | 단위 기능을 개발하는 브랜치로 기능 개발이 완료되면 develop 브랜치에 Merge     |
| release    | 배포를 위해 main 브랜치로 보내기 전에 먼저 QA(품질검사)를 하기위한 브랜치     |
| hotfix     | main 브랜치로 배포를 했는데 버그가 생겼을 떄 긴급 수정하는 브랜치           |


- Git Flow 과정
  - master 브랜치에서 develop 브랜치를 분기함
  - 개발자들은 develop 브랜치에 자유롭게 커밋을 함
  - 기능 구현이 있는 경우 develop 브랜치에서 feature/* 브랜치를 분기합니다.
  - 배포를 준비하기 위해 develop 브랜치에서 release/* 브랜치를 분기합니다.
  - 테스트를 진행하면서 발생하는 버그 수정은 release/* 브랜치에 직접 반영합니다.
  - 테스트가 완료되면 release 브랜치를 master와 develop에 merge함

- 보통 `main <- release <- develop(default) <- feature`
  - 모든 기능 구현은 `feature`을 이용해 브랜치를 분기해 개발 후 `devleop`으로 pull-request 하기!
  - 직접 `develop`으로 push ❌❌
- 예시 : `feature/login` 


### 🍕 Commit 전략 

|  **태그**  |             **설명**                |
| ---------- | ---------------------------------- |
| Feat        | 새로운 기능을 추가한 경우                 |
| Fix     | 에러를 수정한 경우        |
| Design     | CSS 등 UI 디자인을 변경한 경우                  |
| BREAKING CHANGE     | 중대한 API를 변경한 경우                  |
| HOTFIX     | 급하게 치명적인 에러를 고친 경우                  |
| Style     | 코드 포맷 변경을 하거나 세미 콜론 누락하여 추가하면서 코드 수정이 없는 경우                  |
| Refactor     | 코드를 리팩토링한 경우                  |
| Comment     | 주석을 추가하거나 변경한 경우                 |
| Docs     | 문서를 수정한 경우                  |
| Test     | 테스트 코드를 추가, 변경, 리팩토링한 경우                 |
| Chore     | 기타 변경사항 (빌드 스크립트 수정, 패키지 매니징 설정 등)                  |
| Rename     | 파일 or 폴더명 수정하거나 옮기는 경우                 |
| Remove    | 	파일을 삭제하는 작업만 수행한 경우                 |

1. 제목
<pre>
  - "[태그] 요약 메시지" 형식으로 작성합니다.
  - 총 글자 수는 50자 이내로 합니다.
  - 문장의 끝에 특수문자는 삽입하지 않습니다. 예) ., !, ?
  - 요약 메시지의 형태는 한글, 영어 모두 허용합니다.
</pre>

2. 본문
<pre>
  - 본문은 한 줄 당 72자 이내로 합니다.
  - 본문 내용은 양에 구애받지 않고 최대한 상세히 작성합니다.
  - 본문 내용은 어떻게 변경했는지 보다 무엇을 변경했는지 또는 왜 변경했는지를 설명합니다.
</pre>

3. 꼬릿말
<pre>
  - 꼬릿말은 아래에 작성: ex) #이슈 번호
  - 꼬리말은 반드시 작성할 필요는 없습니다.
  - << 유형 >>: #<< 이슈 번호 >> 형식으로 작성합니다.
  - 여러 개의 이슈 번호를 적을 때는 쉼표로 구분합니다.
  - 이슈 트래커 유형은 다음 중 하나를 사용합니다.
    - Fixes: 이슈 수정중 (아직 해결되지 않은 경우)
    - Resolves: 이슈를 해결했을 때 사용
    - Ref: 참고할 이슈가 있을 때 사용
    - Related to: 해당 커밋에 관련된 이슈번호 (아직 해결되지 않은 경우)
</pre>
  
- 예시
<pre>
[Feat] 사용자 인증 기능 구현

- OAuth 연동
- 회원가입과 로그인 로직 구현
- 로그인, 회원가입 폼 컴포넌트 구현

Fixes: #13
Resolves: #11, #12
Ref: #10
Related to: # 14
</pre>

- CI/CD 관련 작업 완료 후(27 commits ~) 부터 적용됩니다.


### 🍭 PR 전략
- PR Template에 따름

<br>

## Backend

### ✨ 기술 설명
SpringBoot, Spring Data JPA, JWT 등을 이용해 Back-end 개발
Thymeleaf(SSR) 를 이용해 정적 페이지 개발  

### 🔥 기술 스택

|  **항목**  |  **기술 스택**                          |
| ---------- | --------------------------------------- |
| 사용언어   | JAVA, SpringBoot                         |
| DB         | Amazon RDS(MySQL), Redis                 |
| 보안       | JWT, Spring Security                     |
| CI/CD      | AWS EC2, AWS S3, AWS RDS, Github Actions |
| 템플릿엔진  | Thymeleaf                                | 

### 🔅 코드 컨벤션

|  **항목**  |    **규칙**            |
| ---------- | ---------------------- |
| Package    | camelCase              |
| File       | PascalCase             |
| Constant   | UPPER_SNAKE_CASE       |
| Variable   | camelCase              |
| Function   | camelCase              |


### ✔ 추가 라이브러리 & 버전 정보

|  **항목**  |  **버전 정보**    |
| ---------- | ------------------ |
| JAVA       |  v11               |
| SpringBoot |  v2.7.17           |
| SpringSecurity | v5.7.11        |
| Lombok     |  v1.18.4           |


<br>


---

🔗 [MUCHU(무추)](http://ec2-43-201-106-215.ap-northeast-2.compute.amazonaws.com:8080)
