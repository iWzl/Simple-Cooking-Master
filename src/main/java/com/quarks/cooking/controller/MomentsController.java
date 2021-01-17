package com.quarks.cooking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quarks.cooking.annotation.Security;
import com.quarks.cooking.pojo.bean.Comment;
import com.quarks.cooking.pojo.bean.Moment;
import com.quarks.cooking.pojo.common.DefaultHttpRsp;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.PageOfInfoListRsp;
import com.quarks.cooking.pojo.common.RoleEnum;
import com.quarks.cooking.pojo.req.CommentMomentReq;
import com.quarks.cooking.pojo.req.MomentBuildReq;
import com.quarks.cooking.pojo.rsp.MomentsCellRsp;
import com.quarks.cooking.service.CommentsService;
import com.quarks.cooking.service.MomentsService;
import com.quarks.cooking.utils.HttpUtil;
import com.quarks.cooking.utils.JsonConvertUtil;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Moments
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-12-13 13:43
 **/

@RestController
@RequestMapping(
        value = "/api/moments",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MomentsController {
    private final MomentsService momentsService;
    private final CommentsService commentsService;

    public MomentsController(MomentsService momentsService, CommentsService commentsService) {
        this.momentsService = momentsService;
        this.commentsService = commentsService;
    }

    @Security(RoleEnum.LOGIN)
    @PostMapping(value = "/publish",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Msg<DefaultHttpRsp> publishNewMoments(@Validated @RequestBody MomentBuildReq momentBuildReq){
        Moment moment = new Moment();
        moment.setContent(momentBuildReq.getContent());
        moment.setImages(JsonConvertUtil.allToJson(momentBuildReq.getImages()));
        moment.setUid(HttpUtil.getUid());
        moment.setCreateTime(System.currentTimeMillis());
        moment.setUpdateTime(System.currentTimeMillis());
        moment.setStatus(0);
        if(momentsService.save(moment)){
            return Msg.buildSuccessMsg("moments created successfully");
        }
        return Msg.buildFailedMsg("moments created failed");
    }

    @Security(RoleEnum.LOGIN)
    @DeleteMapping
    public Msg<DefaultHttpRsp> deleteMomentsByMomentsId(@Validated @Min(100) @RequestParam Long mid){
        Moment moment = momentsService.getById(mid);
        if(null == moment || !mid.equals(moment.getMid())){
            return Msg.buildFailedMsg("failed to delete moments");
        }
        if(!moment.getUid().equals(HttpUtil.getUid())){
            return Msg.buildFailedMsg("delete moment is not allowed");
        }
        if(momentsService.removeById(mid)){
            return Msg.buildSuccessMsg("moments deleted successfully");
        }
        return Msg.buildFailedMsg("failed to delete moments");
    }

    @Security(RoleEnum.LOGIN)
    @GetMapping("/one")
    public Msg<MomentsCellRsp> fetchMomentDetailByMomentId(@Min(100) @RequestParam Long mid){
        MomentsCellRsp momentsCellRsp = momentsService.fetchMomentDetailByMomentId(mid);
        return Msg.buildSuccessMsg(momentsCellRsp);
    }

    @Security(RoleEnum.LOGIN)
    @GetMapping("/poster")
    public Msg<PageOfInfoListRsp<MomentsCellRsp>> fetchMomentDetailByMomentPosterUid(@Min(1) @RequestParam Integer uid,
                                                                  @Validated @Min(1) @RequestParam Integer pageNo,
                                                                  @Validated @Min(1) @Max(100) @RequestParam Integer pageSize){
        PageOfInfoListRsp<MomentsCellRsp> pageOfInfoListRsp = momentsService
                .fetchMomentDetailListByMomentPosterUid(uid,pageNo,pageSize);
        return Msg.buildSuccessMsg(pageOfInfoListRsp);
    }

    @Security(RoleEnum.LOGIN)
    @GetMapping
    public Msg<PageOfInfoListRsp<MomentsCellRsp>> fetchMomentDetailWithPageInfo(
            @Validated @Min(1) @RequestParam Integer pageNo,@Validated @Min(1) @Max(100) @RequestParam Integer pageSize){
        PageOfInfoListRsp<MomentsCellRsp> pageOfInfoListRsp = momentsService.fetchMomentDetailWithPageInfo(pageNo,pageSize);
        return Msg.buildSuccessMsg(pageOfInfoListRsp);
    }

    @Security(RoleEnum.LOGIN)
    @PostMapping(value = "/comment",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Msg<MomentsCellRsp> commentOrLikeMoments(@Validated @RequestBody CommentMomentReq commentMomentReq){
        Integer uid = HttpUtil.getUid();
        if("Favor".equalsIgnoreCase(commentMomentReq.getType())){
            QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uid",uid);
            queryWrapper.eq("type","Favor");
            queryWrapper.eq("mid",commentMomentReq.getMid());
            Comment comment = commentsService.getOne(queryWrapper);
            if(null != comment){
                commentsService.removeById(comment.getCid());
                return fetchMomentDetailByMomentId(comment.getMid());
            }
        }
        Comment comment = new Comment();
        comment.setContent(commentMomentReq.getContent());
        comment.setMid(commentMomentReq.getMid());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setType(commentMomentReq.getType());
        comment.setImage(commentMomentReq.getImage());
        comment.setUid(uid);
        comment.setStatus(0);
        if(commentsService.save(comment)){
            return fetchMomentDetailByMomentId(comment.getMid());
        }
        return Msg.buildFailedMsg("Comment user Moment failed");
    }
}
