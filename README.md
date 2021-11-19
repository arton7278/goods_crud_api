## 상품 등록/조회 API
#### 기술 스택
- Java8
- SpringBoot
- QueryDsl
- H2Base
#### 패키지 구조
- goods_crud
	- goods_api
		- aspect
		- common
		- config
		- controller
		- domain
		- dot
		- exception
		- repository
			- impl
		- service

#### 공통 기능
- ApiCustomException
	- ValidatoinCheck : 정리한 ValidatoinError 코드로 오류 메시지 보내도록 처리
	- ApiException : 정리한 Error코드로 오류 메시지 보내도록 처리 
- MethodRunTimeCheck : 메소드별 처리 시간 측정하는 AOP
- GoodsChangeLogging : 상품의 변경사항을 로깅 처리하는 AOP (현재는 로그만 찍고 있습니다.)

#### 기능
- 상품 등록
	- 필수입력 사항 체크
	- 중복 등록 체크
- 상품 수정
	- 중복 등록 체크
- 상품 조회
	- 목록
	- 상세

#### 테스트
- 등록
	- 정상 저장
	- 유효성 검사 예외처리
	- 중복상품 예외처리
- 조회
	- 정상 조회
	- 존재 하지 않는 상품 조회

### 기타 설명
- 프로젝트에 임의로 개발 편의성을 위해 InitDb 라는것을 Application 실행 할 때
- 테스트 데이터 넣도록 처리 해두었습니다.
- 사용을 안하시면 @Componet 어노테이션을 주석 처리 해주시면 됩니다.
