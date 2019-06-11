# KIOSK

<ul>
<h4>
<li>개발 기간 : 2019년 05월 20일 ~ 2019년 06월 10일(총 21일간)</li>
<li>개발 인원 : 2명</li>
<li>개발 환경 : STS4, javaSE, OracleXE, mybatis, Spring</li>
<li>개발 목적 : 프렌차이즈 운영이 가능한 무인 주문 시스템인 'kiosk(키오스크)' 프로그램 제작</li>
</h4>  
</ul>
<hr>
<h3>DataBase 설계</h3>
<h4>개체 관계도</h4>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfOTkg/MDAxNTYwMjM5ODQ4Mjk2.43iSLlYii8LQsjabhD2V7TlvKMfKTPuKhiuqsPX_1L8g.LjrH-8dp5nBUbyok8wa-4x0nCVojNqdT0fVmqIjTc_Ig.PNG.dmsl620/%EA%B0%9C%EC%B2%B4%EA%B4%80%EA%B3%84%EB%8F%84.png?type=w580"/>
<hr>
<h3>KIOSKserver 프로젝트</h3>
<h4>
<li>DataBase를 직접적으로 접근하여 쿼리 수행</li>
</h4>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMSAg/MDAxNTYwMjM5ODQ4Mjc4.8aILM1RbQ6-pjPF40K6Gj9uqg946Gpbdi2Gga7qAyTYg.hEtekAEQI1Nmmff93xN7bFcTI2D0hvJ2efO5KuS3VQ8g.PNG.dmsl620/%EC%84%9C%EB%B2%84.png?type=w580" />
<h4>
<li>서버 소켓을 생성하여 클라이언트(본사, 각 가맹점)측과의 네트워킹</li>
<li>JSONObject를 활용하여 여러 객체를 송.수신</li>
</h4>
<hr>
<h3>KIOSKhead 프로젝트</h3>
<h4>
<li>상품 관리</li>
<li>매출 확인</li>
<li>가맹점 및 키오스크 장치 관리</li>
</h4>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTk5/MDAxNTYwMjQwNDUzMjY5.TXRIrvpC6OEv7e1FJ8WWRPH024N0zvhjXuP8RzuRO6Yg.ytbfdFqBXvuciUraEqdNuZeHEFrGUZoSVW_-DmVNxMog.PNG.dmsl620/%EB%A1%9C%EA%B7%B8%EC%9D%B8_-_root.png?type=w580"/>
<h4>
<li>본사 등급으로 로그인</li>
</h4>
<h4>ㅁ 상품관리 탭</h4>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTM0/MDAxNTYwMjQwNDUzNzQ1.yooXbpDjSD7S3CdR-lmJllHkDnT4pWX3okxLNYz0Ku8g.2JSaGxu-1e02KShAEgQd3lSFmI1-ikC90mNq3Iqz7Fkg.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC_%EB%AA%A9%EB%A1%9D.png?type=w580"/>
<h4>
<li>카테고리 관련 CRUD 기능</li>
<li>카테고리는 최대 4개까지 등록 가능</li>
<li>JTable 클래스를 사용하여 목록을 표로 구성</li>
</h4>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMjgy/MDAxNTYwMjQwNDUzNjA1.axbwLOZlkMGOJtTJ2ETI5D_jARzjckc-xI0D70LlwhAg.yitRUc0zPSpv6xpRcWnE7dB7Nkmn3O2Y58Y2kbxjy7Ug.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_%EC%9D%B4%EB%AF%B8%EC%A7%80_%EB%AF%B8%EB%A6%AC%EB%B3%B4%EA%B8%B0.png?type=w580"/>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfNDEg/MDAxNTYwMjQwNDUzNTMx.S4pAWKF39dLsNKOKtYTZibLFnnbnrJSpr-pLxvG3Gawg.C_LEzfpbN-XXjPLS9X38JAPQEx4V7Dbeo-D7pEWqaagg.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_%EB%A9%94%EB%89%B4%EB%93%B1%EB%A1%9D.png?type=w580"/>
<h4>
<li>메뉴 관련 CRUD 기능 및 유효성 검사</li>
<li>JFileChooser 클래스를 사용</li>
<li>등록/수정시에 이미지파일을 인코딩하여 서버로 전송</li>
</h4>
<img src="https://postfiles.pstatic.net/MjAxOTA2MTFfMTQ5/MDAxNTYwMjQwNDUzNTIy.yZoNmum1V52o6lzPNAjOomUmwZyvrIjRIY6MTdIQKc8g.vgpOI-cTN7C2jikq8KNg8wo7GAxwjj6RnC1lmwlEEU4g.PNG.dmsl620/%EC%83%81%ED%92%88%EA%B4%80%EB%A6%AC_-_default.png?type=w580">
<h4>
<li>JTable클래스를 사용하여 메뉴 목록을 표로 구성</li>
<li>JTable클래스를 ActionListener를 사용하여 클릭 시 해당 건을 메뉴폼에 출력</li>
</h4>
<hr>


<h4>ㅁ 매출관리 탭</h4>











<h3>KIOSKadmin 프로젝트</h3>
<h4>
<li>재고 관리</li>
<li>매출 확인</li>
</h4>
<hr>








<h3>KIOSKuser 프로젝트</h3>
<h4>
<li>각 가맹점에 배치된 키오스크 기계 역할</li>
</h4>
<hr>









<h3>KIOSKkitchen 프로젝트</h3>
<h4>
<li>각 가맹점의 주방에 배치된 주문서 출력 기계 역할</li>
</h4>
