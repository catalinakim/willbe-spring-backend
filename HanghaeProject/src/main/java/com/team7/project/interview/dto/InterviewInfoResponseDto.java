package com.team7.project.interview.dto;

import com.team7.project.interview.model.Interview;
import com.team7.project.question.dto.QuestionResponseDto;
import com.team7.project.question.model.Question;
import com.team7.project.user.dto.UserInfoResponseDto;
import com.team7.project.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@Builder
public class InterviewInfoResponseDto {
    private InterviewInfoResponseDto.Data interview;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Data{
        private Long id;

        private String video;
        private String thumbnail;

        private QuestionResponseDto.data question;
        private UserInfoResponseDto.UserBody user;

        private String badge;
        private String note;
        private Boolean scrapsMe;
        private Long scrapsCount;
        private Long likesCount;
        private Boolean isPublic;

        private Boolean isMine;

        private String createdAt;
        private String updatedAt;
    }

    public InterviewInfoResponseDto(Interview interview, String videoUrl, String imageUrl, String profileUrl, Boolean isMine, Boolean scrapsMe, Long scrapsCount){
        Question question = interview.getQuestion();
        Long questionId = question.getId();
        String questionCategory = question.getCategory().name();
        String questionContents = question.getContents();
        String questionReference = question.getReference();

        User user = interview.getUser();
        Long userId = user.getId();
        String userNickname = user.getNickname();
        String userGithubLink = user.getGithubLink();
        String userIntroduce = user.getIntroduce();

        UserInfoResponseDto.UserBody userBody = UserInfoResponseDto.UserBody.builder()
                .id(userId)
                .nickname(userNickname)
                .githubLink(userGithubLink)
                .profileImageUrl(profileUrl)
                .introduce(userIntroduce)
                .build();

        this.interview = Data.builder()
                .id(interview.getId())
                .video(videoUrl)
                .thumbnail(imageUrl)
                .question(new QuestionResponseDto.data(questionId, questionCategory, questionContents,questionReference))
                .user(userBody)
                .badge(interview.getBadge())
                .note(interview.getMemo())
                .scrapsMe(scrapsMe)
                .scrapsCount(scrapsCount)
                .likesCount(0L)
                .isPublic(interview.getIsPublic())
                .isMine(isMine)
                .createdAt(interview.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(interview.getModifiedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}