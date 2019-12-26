package ru.itis.smarteducation.uni_timetable.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import ru.itis.smarteducation.uni_timetable.dto.AuditoryDto;
import ru.itis.smarteducation.uni_timetable.dto.DayOfWeekDto;
import ru.itis.smarteducation.uni_timetable.dto.GroupDto;
import ru.itis.smarteducation.uni_timetable.dto.PairTimeDto;
import ru.itis.smarteducation.uni_timetable.dto.RestrictionTeachersSlotsDto;
import ru.itis.smarteducation.uni_timetable.dto.StudentDto;
import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;
import ru.itis.smarteducation.uni_timetable.dto.TeacherDto;
import ru.itis.smarteducation.uni_timetable.entity.Auditory;
import ru.itis.smarteducation.uni_timetable.entity.DayOfWeek;
import ru.itis.smarteducation.uni_timetable.entity.Group;
import ru.itis.smarteducation.uni_timetable.entity.PairTime;
import ru.itis.smarteducation.uni_timetable.entity.Student;
import ru.itis.smarteducation.uni_timetable.entity.Subject;
import ru.itis.smarteducation.uni_timetable.entity.Teacher;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionTeachersSlots;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonMapper {

    @Named("groupDtoMapper")
    @Mappings({
        @Mapping(target = "studentList", qualifiedByName = "studentDtoMapper"),
        @Mapping(target = "subjectList", qualifiedByName = "subjectDtoMapper")
    })
    GroupDto groupToGroupDto(Group group);

    @Named("subjectDtoMapper")
    @Mapping(target = "teachers", qualifiedByName = "teacherDtoMapper")
    SubjectDto subjectToSubjectDto(Subject subject);

    @Named("auditoryDtoMapper")
    AuditoryDto auditoryToAuditoryDto(Auditory auditory);

    @Named("dayOfWeekDtoMapper")
    DayOfWeekDto dayOfWeekToDayOfWeekDto(DayOfWeek dayOfWeek);

    @Named("teacherDtoMapper")
    @Mapping(target = "slotList", qualifiedByName = "restrictionTeachersSlotsMapper")
    TeacherDto teacherToTeacherDto(Teacher teacher);

    @Named("pairTime")
    PairTimeDto pairTimeToPairTimeDto(PairTime pairTime);

    @Named("studentDtoMapper")
    StudentDto studentToStudentDto(Student student);

    @Named("restrictionTeachersSlotsMapper")
    @Mappings({
        @Mapping(target = "dayOfWeek", qualifiedByName = "dayOfWeekDtoMapper"),
        @Mapping(target = "pairTime", qualifiedByName = "pairTime")
    })
    RestrictionTeachersSlotsDto restrictionTeachersSlotsToDto(RestrictionTeachersSlots restrictionTeachersSlots);

}
