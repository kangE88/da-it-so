# Da-it-so Project

자바 스윙과 Oracle, mySQL을 사용한 전자상거래 어플리케이션.

Communicatable electronic commerce app made with java swign and oracle, mySQL.

## Dev Environment

- Language : java 8
- O/S : Windows, iOS
- DataBase : MySQL, ORACLE
  - interface 를 통한 Connector 구현으로 두가지 데이터 베이스 모두 사용 가능
  - Both DataBase can runable because db connector structed by interface.
- Framework : java swing, Spring Structure ( 스프링 구조 )
- Design Pattern : Singleton Pattern, MVC Pattern(Model)
- IDE : intelliJ, Eclipse
- Version Control : github


## Data Base Modeling

![database modeling](https://i.imgur.com/1rJTjKH.png)


## Flow Chart

<div style="display:inline;">

<img src="https://i.imgur.com/JDuEej5.png" alt="flow chart" width="350"/>
<img src="https://i.imgur.com/p7dDMCF.png" alt="flow chart2" width="350"/>

</div>


## Story Board

![story board1](https://i.imgur.com/ReGvRcB.png)
![story board2](https://i.imgur.com/hGBCaKs.png)

## Introduce Program

- 프로젝트의 목적
  - 거래 조건정보를 미리 설정해놓고 업로드하는 예전의 방식을 탈피해, 사용자들끼리의 소통이 거래까지 매끄럽게 이어지는 SNS형 전자상거래 시스템을 구축한다.
- 프로젝트 기간
  - 18.01.01 ~ 01.01.12 (약 2주)


## Main Tech

1. 사용자는 기본 정보를 입력하여 회원가입을 할 수 있다.
    - 아이디 중복 확인, 비밀번호 길이, 비밀번호 재확인 등 체크 가능

2. 사용자는 다른 사용자가 등록한 글과 사진의 리스트를 확인하고 원하는 글을 볼 수 있다.

3. 사용자는 다른 사용자가 등록한 글과 사진을 볼 수 있다.

4. 사용자는 글을 작성할 수 있다.
    - 사용자가 등록하는 사진은 이미지 서버에 사용자 이름의 폴더를 생성하고 그 안에 저장된다.
    - **Network Socket** 사용.

5. 채팅 버튼을 누르면 다른 사용자와 메세지를 주고 받을 수 있다.

6. 다른 사용자가 등록한 사진을 포함한 모든 사진은 ***이미지 서버*** 에서 가져온다.
    - 로고와 버튼 이미지도 서버에서 가져온다.

7. 관리자로 로그인 하면 사용자들의 정보를 관리할 수 있다.
    - 관리자에게 보낸 메세지 확인 가능
    - 특정 유저가 올린 모든 글을 확인할 수 있다.
    - 삭제된 글도 모두 조회가 가능하다.
    - 관리자의 권한으로 글을 삭제할 수 있다.



## Todo

- 물건, 인력 뿐 아니라 장소를 제공하는 태그 등 플랫폼을 확장하는 방식의 구현.
