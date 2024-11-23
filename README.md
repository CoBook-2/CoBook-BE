## `24.11.23 진행상황
### 1. 파이어베이스 및 몽고DB와 연동 성공
* 몽고DB는 향후 추가적인 보수 필요
### __2. FirebaseConfig.java 파일의 17 - 18번 라인__
        FileInputStream serviceAccount = 
        new FileInputStream("src/main/resources/[serviceAccountKey].json");

파라미터에는 파이어베이스 프로젝트의 비공캐 키를 받은 후 지정된 디렉토리에 위치 시키고 해당 패스를 입력 

   * 현재 깃에는 비공개 키를 의도적으로 누락시킴 --> 보안 이슈
~~### 3. 파이어베이스의 ezbudget-test의 User 및 Space 컬렉션의 필드를 불러오는 것까지 확인~~
### 3. 특정 스페이스 정보 읽기 및 해당 스페이스의 영수증, 아이템 컬렉션 읽기까지 확인
   * 상기 동작을 위한 쿼리는 'src/main/service/TestFirebaseQuery.java' 파일 40번 라인부터 있음
   * 해당 파일은 가독성을 위한 리팩토링 필요
   * 프론트 페이지와의 연동 시에 다량의 작업이 예상됨