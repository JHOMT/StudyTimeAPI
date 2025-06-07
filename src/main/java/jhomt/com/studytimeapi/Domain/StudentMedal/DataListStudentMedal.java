package jhomt.com.studytimeapi.Domain.StudentMedal;

import jhomt.com.studytimeapi.Domain.Core.Utils.DateUtils;

public record DataListStudentMedal(
        Integer id,
        Integer studentId,
        Integer medalId,
        Integer unitId,
        String earnedDate
) {
    public DataListStudentMedal(StudentMedal studentMedal) {
        this(
                studentMedal.getId(),
                studentMedal.getStudent().getId(),
                studentMedal.getMedal().getId(),
                studentMedal.getUnit().getId(),
                DateUtils.formatDate(studentMedal.getEarnedDate())
        );
    }
}
