package com.yyr.enums;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @Author 杨亚茹
 * @Date 2022/7/12 9:15
 * @PackageName:com.scjydz.enums
 * @ClassName: PermissionIconEnum
 * @Description: TODO
 * @Version 1.0
 */

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum FamPermissionIconEnum {

    ICON_DIANNAO_ALLSTATUS("收入与支出", "icon-diannao"),
    //ICON_RENWUQIANYUE_DAILYDATA("固定资产", "icon-renwuqianyue"),
    ICON_WENJIANJIA_SHAREDDOCUMENTQUERY("收入支出类型", "icon-a-wenjianjia-zhankai"),
    ICON_DIANNAO1("收入详情","icon-diannao"),
    ICON_GONGWENBAO1("支出详情","icon-gongwenbao"),
    ICON_ICONFONTZHIZUOBIAOZHUN0247_SPECIALTOPIC("家庭预算", "icon-iconfontzhizuobiaozhun0247"),

    ICON_BAOBIAO_AZKABAN("借贷", "icon-baobiao"),
    ICON_XITONGGUANLI_METADATA("固定资产", "icon-xitongguanli"),
    ICON_ICONRENWUQIANYUE("日志管理","icon-renwuqianyue"),
    ICON_DIANNAO("登录日志","icon-diannao"),
    ICON_GONGWENBAO("操作日志","icon-gongwenbao"),
    ICON_XITONG("系统管理","icon--xitong"),
    ICON_YONGHUGUANLI("家庭用户管理","icon-yonghuguanli"),
    ICON_JIAOSEGUANLI("家庭角色管理","icon-jiaoseguanli"),
    ICON_SYS_XITONG("理财","icon--xitong"),
    ICON_DIAN_NAO("基金","icon-diannao"),
    ICON_GONG_WENBAO("家庭管理","icon-gongwenbao"),
    ICON_XI_TONG("股票查询","icon--xitong")
    ;

    /**
    * 权限图标编码
    */
    private String code;
    /**
    * 权限图标名称
    */
    private String icon;

    public static String getIconForCode(String code) {
        for (FamPermissionIconEnum permissionIconEnum : FamPermissionIconEnum.values()) {
            if (code.equals(permissionIconEnum.getCode())) {
                return permissionIconEnum.icon;
            }
        }
        return null;
    }

    public static FamPermissionIconEnum getTypeByCode(String code) {
        if (code == null) {
            return null;
        }
        for (FamPermissionIconEnum permissionIconEnum:  FamPermissionIconEnum.values()){
            if(permissionIconEnum.getCode().equals(code)){

                return permissionIconEnum;
            }
        }
        return null;
    }
}
