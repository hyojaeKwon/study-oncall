## Oncall

### 구현 기능 목록

### 도메인
1. Person

- 이름 /
- 이름 5자 이하 
2. Cycle
- 이름 중복 X
- Quque 
- 평일인지 주말인지 구분
- 사이클은 불변 알고리즘
- 근무자가 각각 1회씩 편성되어있는가 확인
3. onCallResult
- 달, 월 정보
- 일마다 onCall정보 들고있는다.
- n, n+1 근무자가 일치하는지 확인하고 일치한다면 종류에 맞고, 연속되지 않은 다음 근무자가 나타날 때 까지 순회
4. Day => 공휴일 저장
- 휴일인지 정보
- 무슨 요일 인지 
- 몇일인지
5. Month
- 무슨 달인지 
- 요일 정보

### Service
1. Person 객체 생성
2. Cycle 생성
3. onCallResult 생성
4. 반환

### Controller
1. processController
2. onCallController

### Formatter
1. outputFormatter

### Input
1. InputValidator
2. InputParser
3. InputProvider

### Exception