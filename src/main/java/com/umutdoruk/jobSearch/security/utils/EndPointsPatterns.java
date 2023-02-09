package com.umutdoruk.jobSearch.security.utils;

public class EndPointsPatterns {
    public static final String[] candidatesPatterns = {
            "/api/candidates/update",
            "/api/educations/create",
            "/api/educations/update",
            "/api/educations/**",
            "/api/educations/getAllByResumeId",
            "/api/educations/findAllByOrderByGraduationDateAsc",
            "/api/educations/findAllByOrderByGraduationDateDesc",
            "/api/foreign_languages/create",
            "/api/foreign_languages/update",
            "/api/foreign_languages/**",
            "/api/foreign_languages/getAllByResumeId",
            "/api/employers/getAll",
            "/api/resumes/update",
            "/api/resumes/delete",
            "/api/resumes/getAll",
            "/api/resumes/**",
            "/api/technologies/create",
            "/api/technologies/update",
            "/api/technologies/**",
            "/api/technologies/getAll",
            "/api/work_experiences/create",
            "/api/work_experiences/update",
            "/api/work_experiences/**",
            "/api/work_experiences/getAll",
            "/api/work_experiences/findAllByOrderByEndDateDesc"
    };

    public static final String[] employersPatterns = {
            "/api/employers/update",
            "/api/employment_type/create",
            "/api/employment_type/update",
            "/api/employment_type/**",
            "/api/employment_type/getAll",
            "/api/job_advertisement/create",
            "/api/job_advertisement/update",
            "/api/job_advertisement/delete/**",
            "/api/job_locations/create",
            "/api/job_locations/update",
            "/api/job_locations/**",
            "/api/job_titles/create",
            "/api/job_titles/update",
            "/api/job_titles/**",
            "/api/job_titles/getAll",
            "/api/working_time/create",
            "/api/working_time/update",
            "/api/working_time/**",
            "/api/working_time/id/**",
            "/api/working_time/name/**",
            "/api/working_time/getAll"
    };

    public static final String[] bothRolesPatterns = {
            "/api/candidates/**",
            "/api/candidates/getAll",
            "/api/employers/getAll",
            "/api/employers/**",
            "/api/users/update",
            "/api/users/delete",
            "/api/job_advertisement/**",
            "/api/job_advertisement/getAll",
            "/api/job_advertisement/getAllByActiveTrue",
            "/api/job_advertisement/getAllByActiveTrueAndCreatedDateAsc",
            "/api/job_advertisement/getAllByActiveTrueOrderByCreatedDateDesc",
            "/api/job_advertisement/getAllByActiveTrueAndEmployerId",
            "/api/job_advertisement/getAllByEmployerId"
    };

    public static final String [] noRolesPatterns = {
            "/api/users/signup",
            "/api/users/signIn"
    };

}
