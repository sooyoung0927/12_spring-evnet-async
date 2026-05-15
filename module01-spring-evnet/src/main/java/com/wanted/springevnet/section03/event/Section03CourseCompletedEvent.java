package com.wanted.springevnet.section03.event;

/*comment
*  record 란?
*  - DTO랑 동일한 역할을 한다
*  - 불변객체이다
*    ()내부에 작성하는 필드에는 자동으로 final 처리가 된다
*    아무것도 하지 않더라도 자동 추가되는 구문 - 생성자 getter equals hashcode toString
*  : 레코드는 dto 와 역할이 동일하다(데이터 운반 전용)
*    다만 dto 와 가장 큰 차이점은 불변 객체라는 것과 읽기 전용 객체라는 것이다.  */

/*comment
*  String도 str1에 "안녕" 이라고 하고 str1+"하세요" 를 하면 안녕과 안녕하세요는 서로 다른 공간을 바라보게 된다
*  이게 레코드에서도 유사함
*  만약 레코드의 값이 바뀌게 된다면 그냥 완전 서로 다른 객체가 되어버림
*  (String에서 문자열 추가하고도 같은 공간을 보게 하려면 스트링빌더를 써야 함) */

/*comment
*  이벤트에서 레코드가 쓰이는 이유는
*  이벤트는 수정될 일이 없기 때문
*  누군가가 함부로 변경할 수 없기 때문
*  한 번 설정해둔 이벤트는 누군가가 가져다가 쓸 때 바뀌면 안 되니까 */
public record Section03CourseCompletedEvent(
        Long enrollmentId,
        String courseTitle
) {

    // 클래스 본문에는 Validation 작성
}
