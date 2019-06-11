# KIOSK

<h4>
<li>개발 기간 : 2019년 05월 20일 ~ 2019년 06월 10일(총 21일간)</li>
<li>개발 인원 : 2명</li>
<li>개발 환경 : STS4, javaSE, OracleXE, mybatis, Spring</li>
<li>개발 목적 : 프렌차이즈 운영이 가능한 무인 주문 시스템인 'kiosk(키오스크)' 프로그램 제작</li>
<hr> 
</h4>
<h3>ㅁ 프로그램 소개</h3>
<li>소켓과 Thread기술을 활용하여 네트워킹</li>
<li>객체 직렬화 기술을 활용하여 서로 다른 프로젝트간에 파일, 이미지, 객체 등을 전달</li>
<li>매출을 구성하는 각 테이블에 존재여부 컬럼을 부여하여 해당 테이블의 update, delete 요청 후에도 이전 매출의 데이터 유지</li>
<li>각 클라이언트측 로그인 정보를 활용하여 결제 시 유니캐스팅과 멀티캐스팅을 각각 상황에 맞게 구현</li> 
<hr>
<h3> DataBase 설계</h3>
<hr>
<h4>
<li>개체 관계도</li>
</h4>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfOTkg/MDAxNTYwMjM5ODQ4Mjk2.43iSLlYii8LQsjabhD2V7TlvKMfKTPuKhiuqsPX_1L8g.LjrH-8dp5nBUbyok8wa-4x0nCVojNqdT0fVmqIjTc_Ig.PNG.dmsl620/%EA%B0%9C%EC%B2%B4%EA%B4%80%EA%B3%84%EB%8F%84.png?type=w580"/>
<hr>
<h3>KIOSKserver 프로젝트</h3>
<hr>
<h4>
<li>DataBase를 직접적으로 접근하여 쿼리 수행</li>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTAy/MDAxNTYwMjQ4NDM3OTkx.PzZ0as72iwezjO6c_kUCV0lG8XcaZJAWzDLKDAzusVYg.N6HxOqj-0r9mwPZ-F3OVBuep_G2zLL9As7ApegELc5gg.PNG.dmsl620/%EC%84%9C%EB%B2%84.png?type=w580" />
<li>서버 소켓을 생성하여 클라이언트(본사, 각 가맹점)측과의 네트워킹</li>
<li>JSONObject를 활용하여 여러 객체를 송.수신</li>
</h4>
<hr>
<h3>KIOSKhead 프로젝트</h3>
<hr>
<h4>
<li>상품 관리</li>
<li>매출 확인</li>
<li>가맹점 및 키오스크 장치 관리</li>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTk5/MDAxNTYwMjQwNDUzMjY5.TXRIrvpC6OEv7e1FJ8WWRPH024N0zvhjXuP8RzuRO6Yg.ytbfdFqBXvuciUraEqdNuZeHEFrGUZoSVW_-DmVNxMog.PNG.dmsl620/%EB%A1%9C%EA%B7%B8%EC%9D%B8_-_root.png?type=w580"/>
<li>본사 등급으로 로그인</li>
</h4>
<h3>▶ 상품 관리 탭</h3>
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTM0/MDAxNTYwMjQwNDUzNzQ1.yooXbpDjSD7S3CdR-lmJllHkDnT4pWX3okxLNYz0Ku8g.2JSaGxu-1e02KShAEgQd3lSFmI1-ikC90mNq3Iqz7Fkg.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC_%EB%AA%A9%EB%A1%9D.png?type=w580"/>
<h4>
<li>카테고리 관련 CRUD 기능</li>
<li>카테고리는 최대 4개까지 등록 가능</li>
<li>JTable 클래스를 사용하여 목록을 표로 구성</li>
<br>
ㅁ 재고 관리 전체 화면
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjgy/MDAxNTYwMjQwNDUzNjA1.axbwLOZlkMGOJtTJ2ETI5D_jARzjckc-xI0D70LlwhAg.yitRUc0zPSpv6xpRcWnE7dB7Nkmn3O2Y58Y2kbxjy7Ug.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_%EC%9D%B4%EB%AF%B8%EC%A7%80_%EB%AF%B8%EB%A6%AC%EB%B3%B4%EA%B8%B0.png?type=w580"/>
<br>
ㅁ 선택한 메뉴 재고 상태 변경
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfNDEg/MDAxNTYwMjQwNDUzNTMx.S4pAWKF39dLsNKOKtYTZibLFnnbnrJSpr-pLxvG3Gawg.C_LEzfpbN-XXjPLS9X38JAPQEx4V7Dbeo-D7pEWqaagg.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_%EB%A9%94%EB%89%B4%EB%93%B1%EB%A1%9D.png?type=w580"/>
<br>
<li>메뉴 관련 CRUD 기능 및 유효성 검사</li>
<li>JFileChooser 클래스를 사용</li>
<li>등록/수정시에 이미지파일을 인코딩하여 서버로 전송</li>
<br>
ㅁ 로그인한 가맹점 매출 실시간 확인
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTQ5/MDAxNTYwMjQwNDUzNTIy.yZoNmum1V52o6lzPNAjOomUmwZyvrIjRIY6MTdIQKc8g.vgpOI-cTN7C2jikq8KNg8wo7GAxwjj6RnC1lmwlEEU4g.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_default.png?type=w580">
<li>JTable클래스를 사용하여 메뉴 목록을 표로 구성</li>
<br>
ㅁ 매출 건 선택시 자세한 매출 내역 확인 가능
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTQ5/MDAxNTYwMjQwNDUzNTIy.yZoNmum1V52o6lzPNAjOomUmwZyvrIjRIY6MTdIQKc8g.vgpOI-cTN7C2jikq8KNg8wo7GAxwjj6RnC1lmwlEEU4g.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_default.png?type=w580">
<li>JTable클래스에 ActionListener를 사용하여 선택시 상세 매출 리스트 출력</li>
</h4>
<br>

<hr>
<h3>▶ 매출 정보 탭</h3>
<h4>
<br>
ㅁ 가맹점 전체에 대한 매출 정보
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjA1/MDAxNTYwMjQwNDUzMjg2.IC2JDkdIP1-6HKk5tbA10L9osYZkCOHyMLrdH3K35cAg.xoru9Bz2CUsbVLPqznPax_Ug6ruPC2QXGwBbOrqgJj8g.PNG.dmsl620/%EB%A7%A4%EC%B6%9C%EC%A0%95%EB%B3%B4_-_default.png?type=w580">
<li>JTable클래스를 사용하여 매출의 요약 내용 출력</li>
<li>유저가 선택한 날짜로 매출 조회 기능(조회시, 실시간 매출 기능 off)</li>
<li>[실시간 매출 확인]버튼 클릭시 10초간격으로 매출을 실시간으로 출력</li>
<br>
ㅁ 엑셀로 변환
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjcx/MDAxNTYwMjQ3NjQ5OTgy.bkW53cTB-7ovbLC-8nPiKbw4fpMx6o4PiIpanfPIncIg.XPryym-NcK-h_cNuaVht6jqeD7fzdaYCUKp7TOb-8nwg.PNG.dmsl620/%EC%97%91%EC%85%80.png?type=w580">
<li>TableModel클래스를 사용하여 JTable 데이터를 엑셀로 변환</li>
</h4>

<h3>▶ 가맹점 관리 탭</h3>

<br>
ㅁ 가맹점 관리 페이지
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfNDMg/MDAxNTYwMjQwNDUzMjc2.eA5TadcU3hKv0aKIY2HP1ykBXNxQiCtoep9noP_5a5sg.XB4uCu3e5DiOx2Mwx5ez37hhRsWn63HCkA9MER9alYcg.PNG.dmsl620/%EA%B0%80%EB%A7%B9%EC%A0%90%EA%B4%80%EB%A6%AC_-_default.png?type=w580">
<h4>
<br>
ㅁ 가맹점 CRUD 폼
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjMx/MDAxNTYwMjQ4OTc0ODY1.Tp0ah2vsopFSuy4EOpHSeRBZK-IorqnnCcn8NHHBXtwg.byXqBnj-h-y5cF29VWUxvbaU4snR0xNkl6SKJiNi-Kgg.PNG.dmsl620/%EA%B0%80%EB%A7%B9%EC%A0%90_%EB%93%B1%EB%A1%9D.png?type=w580">
<li>가맹점 관련 CRUD 서버로 요청 및 유효성 체크</li>
<br>
ㅁ 등록된 가맹점 전체 정보 리스트
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjgw/MDAxNTYwMjQ5MDg4ODQ2.qXX6h_GuB38IGsz1jafCMw7HmKx_8IwAcHrpEiiD1Tgg.CRBL4uP5Zf2X8mqOpGJYM_04Tf5t_xg611xPH9aZ1fcg.PNG.dmsl620/image.png?type=w580">
<li>JTable클래스를 사용하여 가맹점 목록 출력</li>

<br>
ㅁ 디바이스(키오스크 기계) 관련 CRUD 폼
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfNzAg/MDAxNTYwMjQ5MTg0NjMx.AhT5Fxdjs6AET1PKJT0Pje1Wu37TCqGLETE7bfDI0SUg.QtSHYQLYzWfWJLoxD2pFL7rHH59oqlWgpRiL11E0SPYg.PNG.dmsl620/image.png?type=w580">
<li>디바이스(가맹점에 배포된 키오스크) CRUD 서버로 요청</li>
<li>키오스크 반납 및 대여 관리</li>
</h4>

<hr>
<h3>KIOSKadmin 프로젝트</h3>
<hr>
<h4>
<li>재고 관리</li>
<li>매출 확인</li>
<br>
ㅁ 로그인한 가맹점 메뉴 재고 관리
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjE2/MDAxNTYwMjQ5Mzg3NDYy._y0o-RNsflO_bY1V98eSQ8Odg5cqioxQDaAGpwbeBaog.eQC6llDovbrqeCA_HcpJgzD6yB0GEtsbEFFcIG_Yerkg.PNG.dmsl620/%EB%A7%A4%EC%B6%9C_%ED%99%95%EC%9D%B8.png?type=w580">
<br>
<li>로그인한 해당 가맹점의 메뉴 재고 관리</li>
<li>메뉴 상태가 '일시품절'인 경우 키오스크 장치에서 구매 불가</li>
ㅁ 로그인한 가맹점에 관한 매출 리스트
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfNTEg/MDAxNTYwMjQ5Mzg3NDg3.tyjKU50htWiHqg-DFWCWBcXHZ5eMLxVVr0mKgT1Q3C0g.5JbKATIedB5Ms0YyhqLd-TliihxgGMIo6HLnecnAluMg.PNG.dmsl620/%EB%A7%A4%EC%B6%9C_%ED%99%95%EC%9D%B8_-_%EC%83%81%EC%84%B8%EB%B3%B4%EA%B8%B0.png?type=w580">
<br>
<li>로그인한 해당 가맹점의 매출을 15초 단위로 실시간 확인</li>
<li>TableModel클래스를 사용하여 테이블에 있는 데이터를 엑셀로 변환</li>
<br>
ㅁ 로그인한 가맹점에 관한 상세 매출 
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjc2/MDAxNTYwMjQ5Nzk4MzE0.BU7qne7wzl2VXRlG8qpgODXWFHhH-J0lPA2Hy44lDqsg.bH2D81mxhfAcomfVEn5zOJB8cf63RAckKL2QuULRDr0g.PNG.dmsl620/%EC%9E%AC%EA%B3%A0.png?type=w580">
<li>JTable클래스에 ActionListener를 사용하여 선택시 상세 매출 리스트 출력</li>
</h4>

<hr>
<h3>KIOSKuser 프로젝트</h3>
<hr>
<h4>
<li>각 가맹점에 배치된 키오스크 기계 역할</li>
<br>
□ 가맹점 및 디바이스 등록
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTQw/MDAxNTYwMjUwOTExODA4.J7Kp1bkui4obI0Xbh8Q7A0GK7fUvVgnH8gNJcPmvi2og.2NHph28qnZNNa9wqUfAFV5V06hpHLTKJuHzu9pEViJIg.PNG.dmsl620/image.png?type=w580">
<li>본사에서 해당 가맹점으로 대여처리한 디바이스만 등록 가능</li>
<br>
□ 로그인 유효성 처리
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTMg/MDAxNTYwMjUyNDEyNTgz.k6V9R28QHCI3RE2QFKbIxas9f7up5qtMA31BQk187Bsg.tlMFU4fpDEuJShvdg9MRwkOZaRxt0DMGCiFbnbr17kwg.PNG.dmsl620/image.png?type=w580">
<li>이미 접속한 디바이스가 있거나, 본사에서 대여처리가 안된 디바이스는 등록 불가능</li>
<br>
□ 카테고리 선택시 해당 카테고리 하위 메뉴 출력
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfNjYg/MDAxNTYwMjUxODkzODY0.jacjmlg5RPzUwFtJhDVvQkbM0tc6adzi6hE04lfX-MMg.nujFZc5fKxEH-y8U78eUr-agozn_Ka6EA3PyVe3MI2Mg.PNG.dmsl620/image.png?type=w580">
<li>JLabel을 활용하여 해당 메뉴의 정보를 카테고리별로 부모 컨테이너에 부착</li>
<br>
□ 메뉴 페이징 처리
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTM2/MDAxNTYwMjUxNzY2NTk1.GtfDckUL77Tam0TnKTTzSdhMAoc5oMpeLhx40rF4grgg.XGk3HOwzwxtfnk9Bv1Gw235EwOZy6zHQlchRFaOhAV0g.PNG.dmsl620/image.png?type=w580">
<li>하단의 버튼 클릭 시 페이지 이동 효과 구현</li>
<br>
□ 장바구니 컨테이너의 버튼 처리
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjE5/MDAxNTYwMjUzMTEwNzAy.ssMofVYaVy1SDa08tBsAr8bFcDmjw0R3WQzEK5F0jJsg.V6u-dWbFLyOJyhkPC7q4svmEoLnuSU75r1poc9wVTqAg.PNG.dmsl620/%EA%B8%88%EC%95%A1_%EC%B2%98%EB%A6%AC.png?type=w580">
<li>선택한 메뉴의 수량 및 가격 조정</li>
<li>'X'버튼 클릭 시 해당 메뉴 제거 및 repaint처리</li>
<li>메뉴의 전체 수량 및 총 금액 동적으로 구현</li>
<li>'전체 취소'버튼 클릭 시 유저가 선택한 모든 메뉴 제거 및 repaint처리</li>
<li>현금 및 카드 버튼 클릭 시 결제 진행</li>
<br>
□ 현금 및 카드 결제
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTg0/MDAxNTYwMjUwOTQ2Mzky.pS5RrwGuueKdpnP5tU2eINqyJTCyURsEpJyExuj8IeUg.cAnDrLHu1r-naMxmGWmjCkKhG0lW6lvX0w3exxGgPyQg.PNG.dmsl620/image.png?type=w580">
<li>결제 의사 최종 확인</li>
<br>
□ 결제 후 출력되는 영수증, 주문확인서
<br>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTg1/MDAxNTYwMjUxODEwNzYy.4WczdaNDv0Uo5cFb_fbYvpWxIEk6GiIaLtmbHs54iZUg.m_eizWkurl6cXsvo2Vlsa_Se1XqiuM35WJBpy67qkLIg.PNG.dmsl620/%EA%B5%AC%EB%A7%A4%ED%9B%84.png?type=w580">
<li>각 가맹점 별로 연동된 키오스크 장치 단위로 매일 주문 번호 갱신</li>
<li>결제 후 키오스크 장치(UserProject)에서 JDialog로 '구매 영수증' 구현</li>
<li>결제 후 주방쪽(KitchenProject)에서 JDialog로 '주문 확인서' 구현</li>
</h4>

<hr>
<h3>KIOSKkitchen 프로젝트</h3>
<hr>
<h4>
<li>각 가맹점의 주방에 배치된 주문서 출력 기계 역할</li>
</h4>
