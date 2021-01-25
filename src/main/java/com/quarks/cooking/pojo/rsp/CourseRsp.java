package com.quarks.cooking.pojo.rsp;

import com.quarks.cooking.pojo.common.HttpResponse;

import java.util.List;

/**
 * Curriculum
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-24 14:36
 **/
public class CourseRsp implements HttpResponse {
    private Integer cid;
    private String name;
    private String image;
    private String description;
    private ProfileRsp chefProfile;
    private String type;
    private Long refreshTime;
    private List<CurriculumRsp> curriculumRspList;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProfileRsp getChefProfile() {
        return chefProfile;
    }

    public void setChefProfile(ProfileRsp chefProfile) {
        this.chefProfile = chefProfile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
    }

    public List<CurriculumRsp> getCurriculumRspList() {
        return curriculumRspList;
    }

    public void setCurriculumRspList(List<CurriculumRsp> curriculumRspList) {
        this.curriculumRspList = curriculumRspList;
    }

    public static class CurriculumRsp{
        private Integer id;
        private String name;
        private String url;
        private String description;
        private Integer index;
        private Long refreshTime;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Long getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(Long refreshTime) {
            this.refreshTime = refreshTime;
        }
    }
}
