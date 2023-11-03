package org.example.gradeCalculator;

import java.util.List;

public class GradeCalculator
{
    private final Courses courses;

//    private final List<Course> courses;

    public GradeCalculator(List<Course> courses)
    {
        this.courses = new Courses(courses);
    }

    /**
     * 아래와 같이 일급 컬렉션으로 바로 받을 수도 있다.
     */
//    public GradeCalculator(Courses courses)
//    {
//        this.courses = courses;
//    }

    /**
     * 요구사항
     * 평균학점 계산 방법 = (학점수 * 교과목 평점) 의 합계 / 수강신청 총 학점 수
     * 일급 컬렉션 사용
     */
    public double calculateGrade()
    {
        // (학점수 * 교과목 평점) 의 합계
//        double multipliedCreditAndCourseGrade = 0;

//        for (Course course : courses)
//        {
            // 값을 가지고 있는 Course 클래스가 아닌 다른 클래스가 getter 로 값을 가져와서
            // 계산하고 있다. --> 동일한 로직을 여러 개의 클래스에서 사용한다면? 수정이 발생할 경우 모든 로직을 수정해야 한다.
            // 따라서 값을 가지고 있는 Course 클래스에서 계산하는 로직이 구현되는 것이 유지보수 측면에서 좋은 코드이다.
            // multipliedCreditAndCourseGrade += course.getCredit() * course.getGradeToNumber();

//            multipliedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();

            // 일급 컬렉션에 작업 위임
            double multipliedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();
//        }

        // 수강신청 총 학점 수
//        int totalCompletedCredit = courses.stream()
//                .mapToInt(Course::getCredit)
//                .sum();

        // 일급 컬렉션에 작업 위임
        int totalCompletedCredit = courses.calculateTotalCompletedCredit();

        return multipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}
