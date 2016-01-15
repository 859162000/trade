/**
 * @Author lukangle
 * @2015年10月26日@下午4:52:37
 */
package com.hbc.api.trade.bdata.mapper.guide.genx;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;
import com.hbc.api.trade.bdata.mapper.guide.genx.param.QGuideLimitParam;

public interface GxGuideMapper {
	/**
	 * @param carType
	 * @param carClass
	 * @param cityIds
	 * @return
	 */
	@Select({"<script>",
        "select DISTINCT guide.* from guide,guide_car,guide_crop  where guide.guide_id=guide_car.guide_id and  guide.guide_id=guide_crop.guide_id and guide.status='1'  ",
		"<if test='carClass !=null '>",
		" and guide_car.car_class&gt;='${carClass}' ",
		"</if>",
		"<if test='carType !=null '>",
		" and guide_car.car_type&gt;='${carType}' ",
		"</if>",
		"<if test='guideLevel !=null '>",
		" and guide.guide_level&lt;='${guideLevel}' ",
		"</if>",
		"<if test='cityId !=null '>",
		" and guide.city_id = '${cityId}' ",
		"</if>",
		"<if test='cropType !=null '>",
		" and guide_crop.crop_type = '${cropType}' ",
		"</if>",
		"<if test='guideIds !=null '>",
		" and guide.guide_id in ",
		"<foreach item='item' index='index' collection='guideIds' open='(' separator=',' close=')'> ${item} </foreach>",
		"</if>",
		"<if test='signStatus !=null '>",
		" and guide.sign_status in ",
		"<foreach item='item' index='index' collection='signStatus' open='(' separator=',' close=')'> ${item} </foreach>",
		"</if>",
		"<if test='signStatus ==null '>",
		" and guide.sign_status in ('2','3') ",
		"</if>",
		"<if test='guideNo !=null '>",
		" and guide.guide_no like '%${guideNo}%' ",
		"</if>",
		"<if test='guideName !=null '>",
		" and guide.guide_name like '%${guideName}%' ",
		"</if>",
		"<if test='limit !=null '>",
		" limit #{offset}, #{limit} ",
		"</if>",
	"</script>"})
	List<GuideBean> queryByOrderGuideLimit(QGuideLimitParam guideParam);
	
	//TODO lkl 删除
	@Select({"<script>",
        "select guide.* from guide,guide_car,guide_crop  where guide.guide_id=guide_car.guide_id and guide_car.car_class is not null and guide_crop.crop_type is not null"
        + " and guide.guide_level is not null and  guide.guide_id=guide_crop.guide_id and guide.status='1'  ",
		"<if test='limit !=null '>",
		" limit #{offset}, #{limit} ",
		"</if>",
	"</script>"})
	List<GuideBean> queryAllByOrderGuideLimit(QGuideLimitParam guideParam);
	
	
	
	@Select({"<script>",
        "select count(distinct guide.guide_id) from guide,guide_car,guide_crop where guide.guide_id=guide_car.guide_id and  guide.guide_id=guide_crop.guide_id and guide.status='1'  ",
		"<if test='carClass !=null '>",
		" and guide_car.car_class&gt;='${carClass}' ",
		"</if>",
		"<if test='carType !=null '>",
		" and guide_car.car_type&gt;='${carType}' ",
		"</if>",
		"<if test='guideLevel !=null '>",
		" and guide.guide_level&lt;='${guideLevel}' ",
		"</if>",
		"<if test='cityId !=null '>",
		" and guide.city_id = '${cityId}' ",
		"</if>",
		"<if test='cropType !=null '>",
		" and guide_crop.crop_type = '${cropType}' ",
		"</if>",
		"<if test='guideIds !=null '>",
		" and guide.guide_id in ",
		"<foreach item='item' index='index' collection='guideIds' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		"<if test='signStatus !=null '>",
		" and guide.sign_status in ",
		"<foreach item='item' index='index' collection='signStatus' open='(' separator=',' close=')'> #{item} </foreach>",
		"</if>",
		"<if test='signStatus ==null '>",
		" and guide.sign_status='1' ",
		"</if>",
		"<if test='guideNo !=null '>",
		" and guide.guide_no like '%${guideNo}%' ",
		"</if>",
		"<if test='guideName !=null '>",
		" and guide.guide_name like '%${guideName}%' ",
		"</if>",
	"</script>"})
	int countByOrderGuideIds(QGuideLimitParam guideParam);
	

}
