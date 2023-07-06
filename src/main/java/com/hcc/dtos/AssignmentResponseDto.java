package com.hcc.dtos;

public class AssignmentResponseDto {
    private Long id;
    private String status;
    private Integer number;
    private String githubUrl;
    private String branch;
    private String reviewVideoUrl;
    private Long user_id;
    private Long code_reviewer_id;

    private AssignmentResponseDto() {
    }
    private AssignmentResponseDto(Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.number = builder.number;
        this.githubUrl = builder.githubUrl;
        this.branch = builder.branch;
        this.reviewVideoUrl = builder.reviewVideoUrl;
        this.user_id = builder.user_id;
        this.code_reviewer_id = builder.code_reviewer_id;
    }
    public static final Builder builder() {
        return new Builder();
    };
    public static final class Builder {
        private Long id;
        private String status;
        private Integer number;
        private String githubUrl;
        private String branch;
        private String reviewVideoUrl;
        private Long user_id;
        private Long code_reviewer_id;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setNumber(Integer number) {
            this.number = number;
            return this;
        }

        public Builder setGithubUrl(String githubUrl) {
            this.githubUrl = githubUrl;
            return this;
        }

        public Builder setBranch(String branch) {
            this.branch = branch;
            return this;
        }

        public Builder setReviewVideoUrl(String reviewVideoUrl) {
            this.reviewVideoUrl = reviewVideoUrl;
            return this;
        }

        public Builder setUser_id(Long user_id) {
            this.user_id = user_id;
            return this;
        }

        public Builder setCode_reviewer_id(Long code_reviewer_id) {
            this.code_reviewer_id = code_reviewer_id;
            return this;
        }

        public AssignmentResponseDto build() {
            return new AssignmentResponseDto(this);
        }
    }
}
