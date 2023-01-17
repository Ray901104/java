package org.example.gradeCalculator;

import java.util.List;

/**
 * 일급 컬렉션 : 컬렉션 형태의 한 개의 인스턴스만 가지고 있는 클래스
 */
public class Courses
{
    private final List<Course> courses;

    public Courses(List<Course> courses)
    {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade()
    {
//        double multipliedCreditAndCourseGrade = 0;
//
//        for (Course course : courses)
//        {
//            multipliedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();
//        }
//
//        return multipliedCreditAndCourseGrade;

        // 위의 코드를 스트림을 이용해 리팩토링 한다.
        return courses.stream()
                .mapToDouble(Course::multiplyCreditAndCourseGrade)
                .sum();
    }

    public int calculateTotalCompletedCredit()
    {
        return courses.stream()
                .mapToInt(Course::getCredit)
                .sum();
    }
}
