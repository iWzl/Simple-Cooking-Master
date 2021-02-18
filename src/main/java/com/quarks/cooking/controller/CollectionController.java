package com.quarks.cooking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quarks.cooking.annotation.Security;
import com.quarks.cooking.pojo.bean.Collection;
import com.quarks.cooking.pojo.common.CollectType;
import com.quarks.cooking.pojo.common.DefaultHttpRsp;
import com.quarks.cooking.pojo.common.Msg;
import com.quarks.cooking.pojo.common.RoleEnum;
import com.quarks.cooking.pojo.req.CollectBuildReq;
import com.quarks.cooking.pojo.rsp.ChefFollowNumberRsp;
import com.quarks.cooking.pojo.rsp.CollectBuildRsp;
import com.quarks.cooking.pojo.rsp.SelfCollectNumberRsp;
import com.quarks.cooking.service.CollectionService;
import com.quarks.cooking.utils.HttpUtil;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


/**
 * Collection Controller
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-02-01 20:57
 **/
@RestController
@RequestMapping(
        value = "/api/collect",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CollectionController {
    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Security(RoleEnum.LOGIN)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Msg<DefaultHttpRsp> addCollect(@Validated @RequestBody CollectBuildReq collectBuildReq){
        Collection collection = new Collection();
        collection.setData(collectBuildReq.getData());
        collection.setType(collectBuildReq.getType().trim().toUpperCase());
        collection.setUid(HttpUtil.getUid());
        if(collectionService.save(collection)){
            return Msg.buildSuccessMsg("collection created successfully");
        }
        return Msg.buildFailedMsg("collection created failed");
    }

    @Security(RoleEnum.LOGIN)
    @PostMapping(value = "check",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Msg<CollectBuildRsp> checkHasCollect(@Validated @RequestBody CollectBuildReq collectBuildReq){
        QueryWrapper<Collection> collectionQueryWrapper = new QueryWrapper<>();
        collectionQueryWrapper.eq("type",collectBuildReq.getType().trim().toUpperCase())
                .eq("uid",HttpUtil.getUid())
                .eq("data",collectBuildReq.getData());
        int count = collectionService.count(collectionQueryWrapper);
        CollectBuildRsp collectBuildRsp = new CollectBuildRsp();
        collectBuildRsp.setHasCollect(count >= 1);
        return Msg.buildSuccessMsg(collectBuildRsp);
    }

    @Security(RoleEnum.LOGIN)
    @GetMapping(value = "chef",consumes = MediaType.ALL_VALUE)
    public Msg<ChefFollowNumberRsp> fetchChefFollowNumber(@Validated @Min(1) @RequestParam("chefId") Integer chefId){
        QueryWrapper<Collection> collectionQueryWrapper = new QueryWrapper<>();
        collectionQueryWrapper.eq("type", CollectType.FOLLOW.name())
                .eq("data",chefId);
        int count = collectionService.count(collectionQueryWrapper);
        ChefFollowNumberRsp chefFollowNumberRsp = new ChefFollowNumberRsp();
        chefFollowNumberRsp.setFollowNum(count);
        return Msg.buildSuccessMsg(chefFollowNumberRsp);
    }

    @Security(RoleEnum.LOGIN)
    @GetMapping(value = "count",consumes = MediaType.ALL_VALUE)
    public Msg<SelfCollectNumberRsp> fetchChefFollowNumber(@Validated @NotBlank() @RequestParam("type") String type,
                                                           @RequestParam(value = "reverse",defaultValue = "false") boolean reverse){
        QueryWrapper<Collection> collectionQueryWrapper = new QueryWrapper<>();
        if(reverse){
            collectionQueryWrapper.eq("type", type.trim().toUpperCase())
                    .eq("data",HttpUtil.getUid());
        }else {
            collectionQueryWrapper.eq("type", type.trim().toUpperCase())
                    .eq("uid",HttpUtil.getUid());
        }
        int count = collectionService.count(collectionQueryWrapper);
        SelfCollectNumberRsp selfCollectNumberRsp = new SelfCollectNumberRsp();
        selfCollectNumberRsp.setCollectNum(count);
        return Msg.buildSuccessMsg(selfCollectNumberRsp);
    }
}

